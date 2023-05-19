package com.monique.sectgeon.lair.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.monique.sectgeon.lair.Lair;

public class Cemetery extends Button {

    public Cemetery(Lair lair) {
        super(lair, "lair/cemiterio.png", 8);
    }

    @Override
    public void onClick(Object o) {
        //TODO: mostrar cartas
    }

    @Override
    public void draw(Graphics g) {
        if (LairGUI.evidence.contains(ID)) {
            var old = g.getColor();
            g.setColor(Color.CYAN);
            g.fillRoundRect(getRect().x - 1, getRect().y - 1, getWidth() + 2, getHeight() + 2, 10, 10);
            g.setColor(old);
        }
        super.draw(g);
    }

    @Override
    public Rectangle getRect() {
        var buffer = getHeight() / 10;
        rect.setLocation(buffer, LAIR.getHeight() - getHeight() - buffer);
        rect.setSize(getWidth(), getHeight());
        return rect;
    }

    public void evidenceate() {
        if (!LairGUI.evidence.contains(ID)) LAIR.tickedElement(ID, 30, ID -> LairGUI.evidence.add(ID), ID -> LairGUI.evidence.remove(ID), null);
    }
}