package com.monique.sectgeon.lair.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class LairHUD implements Drawable {
    public final Lair LAIR;
    public final Point[] PlayerTablePos = new Point[3];
    public final Point[] EnemyTablePos = new Point[3];

    public LairHUD(Lair lair) {
        LAIR = lair;
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
            PlayerTablePos[i] = new Point(wid / 2 - Card.width * (i - 1), hei / 2);
        }

        for (int i = 0; i < 3; i++) {
            EnemyTablePos[i] = new Point(wid / 2 - Card.width * (i - 1), hei / 2 - Card.height);
        }
    }

    private void drawHand(Graphics g, int wid, int hei) {
        ArrayList<Card> hand = LAIR.player.hand;

        int buffer = -3 * hand.size();
        if (hand.size() > 7) buffer = -21;

        int x = (wid / 2 - Card.width / 2) - ((Card.width + buffer) * (hand.size() - 1)) / 2;

        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).draw(g, x, hei / 2 + Card.height);
            x += Card.width + buffer + 2;
        }
    }
}