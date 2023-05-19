package com.monique.sectgeon.lair;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Consumer;

import com.monique.sectgeon.common.events.lair.*;
import com.monique.sectgeon.common.gui.*;
import com.monique.sectgeon.common.listeners.*;
import com.monique.sectgeon.lair.cards.*;
import com.monique.sectgeon.lair.gui.*;
import com.monique.sectgeon.level.dungeons.Dungeon;

public class Lair extends Board {
    public Listener defaultListener = new Listener();
    public CardPile pile = new CardPile(this);
    public Cemetery cemetery = new Cemetery(this);
    public ReadyButton readyButton = new ReadyButton(this);
    public CustomListener<Card> listener = new CustomListener<Card>();
    public Player player = new Player(this, "player", 20);
    public Enemy enemy = new Enemy(this, "enemy", 20);
    public ArrayList<Card> tableCards = new ArrayList<Card>();
    public LairGUI hud = new LairGUI(this);
    private final ArrayList<TickedFor<?>> TICKED_FORS = new ArrayList<TickedFor<?>>();
    private Dungeon dungeon;
    private int turn = 0;

    public Lair(Dungeon dungeon) {
        super(dungeon.frame);
        this.dungeon = dungeon;
        addKeyListener(defaultListener);
        addMouseListener(defaultListener);
        addMouseMotionListener(defaultListener);
        passTurn();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        hud.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void tick(Object e) {
        var delete = new ArrayList<TickedFor<?>>();
        for (int i = 0; i < TICKED_FORS.size(); i++) {
            if (TICKED_FORS.get(i).tick()) delete.add(TICKED_FORS.get(i));
        }
        TICKED_FORS.removeAll(delete);

        revalidate();
        repaint();
    }

    public void placeCard(Card card, int pos) {
        var e = (PlaceCardEvent) listener.dispatch(new PlaceCardEvent(card, pos));
        pos = e.getPos();

        if (pos != -1) {
            if (card.TYPE != CardTypes.Spell) {
                int emptySlot = getEmptySlot(card.owner);
        
                if (checkSlot(card.owner, pos)) {
                    card.owner.hand.remove(card);
                    card.setPos(pos);
                    tableCards.add(card);
                } else if (emptySlot != -1) {
                    var friend = getTableCard(card.owner, pos);
                    if (friend != null) {
                        friend.setPos(emptySlot);
                        card.owner.hand.remove(card);
                        card.setPos(pos);
                        tableCards.add(card);
                    }
                }
            } else {
                card.death(card);
            }
        }
    }

    public void summon(Card source, Card summon, int pos) {
        var e = (SummonEvent) listener.dispatch(new SummonEvent(source, summon, pos));
        summon = e.getSummoned();
        pos = e.getPos();

        if (summon != null) {
            if (pos == -1) {
                summon.owner.hand.add(summon);
            } else if (checkSlot(summon.owner, pos)) {
                summon.setPos(pos);
                tableCards.add(summon);
            }
            summon.evidenceate();
        }
    }

    public boolean checkSlot(Player player, int pos) {
        for (Card card : tableCards) {
            if (card.getPos() == pos && card.owner == player) return false;
        }
        return true;
    }

    public int getEmptySlot(Player player) {
        int[] poses = {0, 1, 2};

        for (Card card : tableCards) {
            if (card.getPos() == poses[card.getPos()] && card.owner == player) poses[card.getPos()] = -1;
        }
        for (int i = 0; i < poses.length; i++) {
            if (poses[i] != -1) {
                return i;
            }
        }
        return -1;
    }

    public int[] getEmptySlots(Player player) {
        int[] poses = {0, 1, 2};

        for (Card card : tableCards) {
            if (card.getPos() == poses[card.getPos()] && card.owner == player) poses[card.getPos()] = -1;
        }
        return poses;
    }

    public Card getTableCard(UUID id) {
        for (Card card : tableCards) {
            if (card.ID.equals(id)) return card;
        }
        return null;
    }

    public Card getTableCard(Player player, int pos) {
        for (Card card : tableCards) {
            if (card.getPos() == pos && card.owner == player) return card;
        }
        return null;
    }

    public ArrayList<Card> getTableCards(Player player) {
        var array = new ArrayList<Card>();
        for (Card card : tableCards) {
            if (card.owner == player) array.add(card);
        }
        return array;
    }

    public Card getCemeterysCard(UUID id) {
        for (Card card : player.cemetery) {
            if (card.ID.equals(id)) return card;
        }
        for (Card card : enemy.cemetery) {
            if (card.ID.equals(id)) return card;
        }
        return null;
    }

    public void ready(Player p) {
        p.ready = true;
        if (player.ready && enemy.ready) {
            listener.dispatch(new TurnEnd(turn));
            player.ready = false;
            enemy.ready = false;

            for (int i = 0; i < tableCards.size(); i++) {
                tableCards.get(i).setAttacked(false);
            }

            battle();
        }
    }

    public void battle() {
        listener.dispatch(new BattleStart());
        Collections.sort(tableCards);

        tickedFor(tableCards, 30, card -> {
            if (!card.getAttacked()) {
                LairGUI.evidence.add(card.ID);
                Player relativeEnemy = card.owner == player ? enemy : player;
                var opponent = getTableCard(relativeEnemy, card.getPos());

                if (opponent != null) {
                    card.attack(opponent);
                } else {
                    card.attack(relativeEnemy);
                }
            }
        }, card -> {
            LairGUI.evidence.remove(card.ID);
        }, cards -> {
            LairGUI.evidence.clear();
            listener.dispatch(new BattleEnd());
            passTurn();
        });
    }

    public void passTurn() {
        player.setBuyAmount(player.getBuyAmount() + 1);
        enemy.setBuyAmount(enemy.getBuyAmount() + 1);
        turn++;
        listener.dispatch(new TurnStart(turn));
    }

    public <T> void tickedFor(ArrayList<T> array, int delay, Consumer<T> consumer, Consumer<T> postEach, Consumer<ArrayList<T>> post) {
        TICKED_FORS.add(new TickedFor<T>(delay, array, consumer, postEach, post));
    }

    public <T> void tickedElement(T element, int delay, Consumer<T> consumer, Consumer<T> postEach, Consumer<ArrayList<T>> post) {
        var array = new ArrayList<T>();
        array.add(element);
        TICKED_FORS.add(new TickedFor<T>(delay, array, consumer, postEach, post));
    }

    public void finish(boolean winOrLoss) {
        defaultListener.clear();
        listener.clear();
        frame.finishLair(winOrLoss, dungeon);
    }

    public int getTurn() {
        return turn;
    }
}