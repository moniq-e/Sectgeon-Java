package com.monique.sectgeon.lair.gui;

import java.awt.Rectangle;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.lair.Lair;

public class ReadyButton extends Button {

    public ReadyButton(Lair lair) {
        super(lair, "lair/pronto.png", 5);
    }

    @Override
    public void onClick(Object o) {
        if (Util.collides(getRect(), Util.getMouseRect())) {
            LAIR.ready(LAIR.player);
        }
    }

    @Override
    public Rectangle getRect() {
        rect.setLocation(LAIR.getWidth() - getWidth(), LAIR.getHeight() / 2 - getHeight() / 2);
        rect.setSize(getWidth(), getHeight());
        return rect;
    }
}