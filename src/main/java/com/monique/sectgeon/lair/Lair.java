package com.monique.sectgeon.lair;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.UUID;

import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.events.lair.PlaceCardEvent;
import com.monique.sectgeon.common.gui.*;
import com.monique.sectgeon.common.listeners.*;
import com.monique.sectgeon.lair.cards.*;
import com.monique.sectgeon.lair.gui.LairHUD;
import com.monique.sectgeon.level.dungeons.Dungeon;

public class Lair extends Board {
    public Listener defaultListener = new Listener();
    public CardPile pile = new CardPile(this);
    public CustomListener<Card> listener = new CustomListener<Card>();
    public LPlayer player = new LPlayer(this, "player", 20);
    public LEnemy enemy = new LEnemy(this, "enemy", 20);
    public HashMap<UUID, Card> tableCards = new HashMap<UUID, Card>();
    public LairHUD hud = new LairHUD(this);
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
        SummonEvent<Card> e = (SummonEvent<Card>) listener.dispatch(new SummonEvent<Card>(source, summoned, pos));

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

    public void ready(LPlayer player) {
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