package com.monique.sectgeon.lair.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.UUID;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;

public abstract class Button {
    public final UUID ID = UUID.randomUUID();
    protected final Rectangle rect = new Rectangle();
    protected final Lair LAIR;
    protected BufferedImage image;
    protected int divider;

    public Button(Lair lair, String location, int heightDivider) {
        LAIR = lair;
        image = Util.getImage(location);
        divider = heightDivider;

        LAIR.defaultListener.addListener(Events.Click, ID, this::onClick);
    }

    public abstract void onClick(Object o);

    public void draw(Graphics g) {
        var rect = getRect();
        g.drawImage(image, rect.x, rect.y, rect.width, rect.height, LAIR);
    }

    private int parsedWidth() {
        return Frame.board.getHeight() / divider;
    }

    public int getWidth() {
        return image.getWidth() * (getHeight() / image.getHeight());
    }

    public int getHeight() {
        return image.getHeight() * (parsedWidth() / image.getWidth());
    }

    public abstract Rectangle getRect();
}