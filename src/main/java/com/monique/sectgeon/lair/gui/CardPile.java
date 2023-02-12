package com.monique.sectgeon.lair.gui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.UUID;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class CardPile {
    private static BufferedImage image = Util.getImage("lair/baralho.png");
    public final UUID ID = UUID.randomUUID();
    private final Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());
    private final Lair LAIR;

    public CardPile(Lair lair) {
        LAIR = lair;
        
        LAIR.defaultListener.addListener(Events.Click, ID, note -> {
            if (Util.collides(getRect(), Util.getMouseRect())) {
                LAIR.player.hand.add(new Card(LAIR.player.deck.get(Util.random(0, LAIR.player.deck.size() - 1)), LAIR.player));
            }
        });
    }

    public void draw(Graphics g) {
        var rect = getRect();
        g.drawImage(image, rect.x, rect.y, rect.width, rect.height, LAIR);
    }

    private static int parsedWidth() {
        return Frame.board.getHeight() / 8;
    }

    public static int getWidth() {
        return image.getWidth() * (getHeight() / image.getHeight());
    }

    public static int getHeight() {
        return image.getHeight() * (parsedWidth() / image.getWidth());
    }

    public Rectangle getRect() {
        var buffer = getHeight() / 10;
        rect.setLocation(LAIR.getWidth() - getWidth() - buffer, LAIR.getHeight() - getHeight() - buffer);
        rect.setSize(getWidth(), getHeight());
        return rect;
    }
}