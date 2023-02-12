package com.monique.sectgeon.lair.gui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.UUID;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;

public class ReadyButton {
    private static BufferedImage image = Util.getImage("lair/pronto.png");
    public final UUID ID = UUID.randomUUID();
    private final Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());
    private final Lair LAIR;

    public ReadyButton(Lair lair) {
        LAIR = lair;

        LAIR.defaultListener.addListener(Events.Click, ID, note -> {
            if (Util.collides(getRect(), Util.getMouseRect())) {
                LAIR.ready(LAIR.player);
                System.out.println('s');
            }
        });
    }

    public void draw(Graphics g) {
        var rect = getRect();
        g.drawImage(image, rect.x, rect.y, rect.width, rect.height, LAIR);
    }

    private static int parsedWidth() {
        return Frame.board.getHeight() / 5;
    }

    public static int getWidth() {
        return image.getWidth() * (getHeight() / image.getHeight());
    }

    public static int getHeight() {
        return image.getHeight() * (parsedWidth() / image.getWidth());
    }

    public Rectangle getRect() {
        rect.setLocation(LAIR.getWidth() - getWidth(), LAIR.getHeight() / 2 - getHeight() / 2);
        rect.setSize(getWidth(), getHeight());
        return rect;
    }
}