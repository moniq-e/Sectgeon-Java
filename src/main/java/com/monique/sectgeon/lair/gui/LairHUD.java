package com.monique.sectgeon.lair.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.UUID;
import java.awt.event.MouseEvent;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class LairHUD implements Drawable {
    public static UUID up;
    public final Lair LAIR;
    public final Point[] PlayerTablePos = new Point[3];
    public final Point[] EnemyTablePos = new Point[3];

    public LairHUD(Lair lair) {
        LAIR = lair;

        lair.defaultListener.addListener(Events.Move, null, note -> {
            MouseEvent e = (MouseEvent) note;
            int not = 0;
            for (Card card : LAIR.player.hand) {
                if (Util.collides(new Rectangle(card.x, card.y, Card.getWidth(), Card.getHeight()), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                    up = card.ID;
                } else not++;
            }
            if (not == LAIR.player.hand.size()) up = null;
        });
    }

    @Override
    public void draw(Graphics g) {
        int wid = LAIR.getWidth();
        int hei = LAIR.getHeight();

        setTablesPos(wid, hei);
        g.drawImage(Util.getImage("lair/mesa.png"), 0, 0, wid, hei, LAIR);
        LAIR.pile.draw(g);
        LAIR.tableCards.values().forEach(c -> c.draw(g));
        drawHand(g, wid, hei);
    }

    private void setTablesPos(int wid, int hei) {
        for (int i = 0; i < 3; i++) {
            PlayerTablePos[i] = new Point(wid / 2 - Card.getWidth() * (i - 1), hei / 2);
        }

        for (int i = 0; i < 3; i++) {
            EnemyTablePos[i] = new Point(wid / 2 - Card.getWidth() * (i - 1), hei / 2 - Card.getHeight());
        }
    }

    private void drawHand(Graphics g, int wid, int hei) {
        ArrayList<Card> hand = LAIR.player.hand;

        int buffer = -3 * hand.size();
        if (hand.size() > 9) buffer = -27;

        int x = (wid / 2 - Card.getWidth() / 2) - ((Card.getWidth() + buffer) * (hand.size() - 1)) / 2;

        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).draw(g, x, hei / 2 + Card.getHeight());
            x += Card.getWidth() + buffer + 2;
        }
    }
}