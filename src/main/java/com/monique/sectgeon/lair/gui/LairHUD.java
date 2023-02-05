package com.monique.sectgeon.lair.gui;

import java.awt.Graphics;
import java.awt.Point;

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
    }

    private void setTablesPos(int wid, int hei) {
        for (int i = 0; i < 3; i++) {
            PlayerTablePos[i] = new Point((int) (wid / 2 - Card.WIDTH * (i - 1)), (int) (hei / 2));
        }

        for (int i = 0; i < 3; i++) {
            EnemyTablePos[i] = new Point((int) (wid / 2 - Card.WIDTH * (i - 1)), (int) (hei / 2 - Card.HEIGHT));
        }
    }
}