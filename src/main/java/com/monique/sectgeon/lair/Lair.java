package com.monique.sectgeon.lair;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.HashMap;
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
    public HashMap<UUID, Card> tableCards = new HashMap<UUID, Card>();
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
        PlaceCardEvent e = (PlaceCardEvent) listener.dispatch(new PlaceCardEvent(card, pos));

        if (checkSlot(e.getPos())) {
            if (e.getPos() != -1) {
                card.PLAYER.hand.remove(card);
                card.setPos(e.getPos());
                tableCards.put(card.ID, card);
            }
        }
    }

    public void summon(Card source, Card summoned, int pos) {
        SummonEvent e = (SummonEvent) listener.dispatch(new SummonEvent(source, summoned, pos));

        if (e.getSummoned() != null) {
            tableCards.put(e.getSummoned().ID, e.getSummoned());
        }
    }

    public boolean checkSlot(int pos) {
        for (Card card : tableCards.values()) {
            if (card.getPos() == pos) return false;
        }
        return true;
    }

    public int getTurn() {
        return turn;
    }

    public void ready(Player player) {
        player.ready = true;
        if (this.player.ready && enemy.ready) {
            turn++;
        }
    }

    public void finish(boolean winOrLoss) {
        defaultListener.clear();
        listener.clear();
        frame.finishLair(winOrLoss, dungeon);
    }
}