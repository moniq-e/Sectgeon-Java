package com.monique.sectgeon.lair;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.UUID;

import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.events.lair.PlaceCardEvent;
import com.monique.sectgeon.common.gui.*;
import com.monique.sectgeon.common.listeners.CustomListener;
import com.monique.sectgeon.lair.cards.Card;
import com.monique.sectgeon.lair.gui.LairHUD;
import com.monique.sectgeon.main.dungeons.Dungeon;

public class Lair extends Board {
    private Dungeon dungeon;
    private LairHUD hud = new LairHUD(this);
    private int turn = 1;
    public CustomListener<Card> listener = new CustomListener<Card>();
    public LPlayer player = new LPlayer(this, "player", 20);
    public LEnemy enemy = new LEnemy(this, "enemy", 20);
    public HashMap<UUID, Card> tableCards = new HashMap<UUID, Card>();

    public Lair(Dungeon dungeon) {
        super(dungeon.frame);
        this.dungeon = dungeon;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.decode("#aeebe0"));

        hud.draw(g);
        for (Card card : tableCards.values()) {
            card.draw(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void tick(Object e) {
        revalidate();
        repaint();
    }

    public void placeCard(Card card, int pos) {
        PlaceCardEvent e = (PlaceCardEvent) listener.dispatchEvent(new PlaceCardEvent(card, pos));

        if (e.getSource() != null && checkSlot(e.getPos())) {
            if (e.getPos() != -1) {
                e.getSource().setPos(e.getPos());
                tableCards.put(e.getSource().ID, e.getSource());
            }
        }
    }

    public void summon(Card source, Card summoned, int pos) {
        SummonEvent<Card> e = (SummonEvent<Card>) listener.dispatchEvent(new SummonEvent<Card>(source, summoned, pos));

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
        frame.finishLair(winOrLoss, dungeon);
    }
}