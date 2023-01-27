package com.monique.sectgeon.lair.gui;

import java.awt.Graphics;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.lair.Lair;

public class LairHUD implements Drawable {
    public final Lair LAIR;

    public LairHUD(Lair lair) {
        LAIR = lair;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Util.getImage("lair/mesa.png"), 0, 0, LAIR);
    }
}