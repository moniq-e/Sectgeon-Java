package com.monique.sectgeon.lair;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.UUID;

import com.monique.sectgeon.common.events.lair.*;
import com.monique.sectgeon.common.gui.*;
import com.monique.sectgeon.common.listeners.*;
import com.monique.sectgeon.lair.cards.*;
import com.monique.sectgeon.lair.gui.*;
import com.monique.sectgeon.level.dungeons.Dungeon;

public class Lair extends Board {
    public Listener defaultListener = new Listener();
    public CardPile pile = new CardPile(this);
    public ReadyButton readyButton = new ReadyButton(this);
    public CustomListener<Card> listener = new CustomListener<Card>();
    public Player player = new Player(this, "player", 20);
    public Enemy enemy = new Enemy(this, "enemy", 20);
    public ArrayList<Card> tableCards = new ArrayList<Card>();
    public LairGUI hud = new LairGUI(this);
    private Dungeon dungeon;
    private int turn = 1;

    public Lair(Dungeon dungeon) {
        super(dungeon.frame);
        this.dungeon = dungeon;
        addKeyListener(defaultListener);
        addMouseListener(defaultListener);
        addMouseMotionListener(defaultListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        hud.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void tick(Object e) {
        revalidate();
        repaint();
    }

    public void placeCard(Card card, int pos) {
        var e = (PlaceCardEvent) listener.dispatch(new PlaceCardEvent(card, pos));
        pos = e.getPos();
        int emptySlot = getEmptySlot();

        if (checkSlot(pos)) {
            if (pos != -1) {
                card.PLAYER.hand.remove(card);
                card.setPos(pos);
                tableCards.add(card);
            }
        } else if (emptySlot != -1) {
            getTableCard(pos).setPos(emptySlot);

            card.PLAYER.hand.remove(card);
            card.setPos(pos);
            tableCards.add(card);
        }
    }

    public void summon(Card source, Card summon, int pos) {
        var e = (SummonEvent) listener.dispatch(new SummonEvent(source, summon, pos));
        summon = e.getSummoned();
        pos = e.getPos();

        if (summon != null) {
            if (pos == -1) {
                summon.PLAYER.hand.add(summon);
            } else if (checkSlot(pos)) {
                summon.setPos(pos);
                tableCards.add(summon);
            }
        }
    }

    public boolean checkSlot(int pos) {
        for (Card card : tableCards) {
            if (card.getPos() == pos) return false;
        }
        return true;
    }

    public int getEmptySlot() {
        int[] poses = {0, 1, 2};

        for (Card card : tableCards) {
            if (card.getPos() == poses[card.getPos()]) poses[card.getPos()] = -1;
        }
        for (int i = 0; i < poses.length; i++) {
            if (poses[i] != -1) {
                return i;
            }
        }
        return -1;
    }

    public Card getTableCard(UUID id) {
        for (Card card : tableCards) {
            if (card.ID.equals(id)) return card;
        }
        return null;
    }

    public Card getTableCard(int pos) {
        for (Card card : tableCards) {
            if (card.getPos() == pos) return card;
        }
        return null;
    }

    public void ready(Player player) {
        player.ready = true;
        if (this.player.ready && enemy.ready) {
            battle();
            turn++;
        }
    }

    public void battle() {
        
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