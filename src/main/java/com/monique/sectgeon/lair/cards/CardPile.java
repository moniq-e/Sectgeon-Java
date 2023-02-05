package com.monique.sectgeon.lair.cards;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.UUID;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;

public class CardPile {
    public static int width;
    public static int height;
    public final UUID ID = UUID.randomUUID();
    public int x, y;
    private final Lair LAIR;

    public CardPile(Lair lair) {
        LAIR = lair;
        setClick();
    }

    private void setClick() {
        LAIR.defaultListener.addListener(Events.Mouse, ID, note -> {
            MouseEvent e = (MouseEvent) note;
            if (Util.collides(new Rectangle(x, y, width, height), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                LAIR.player.hand.add(new Card(LAIR.player.deck.get(Util.random(0, LAIR.player.deck.size() - 1)), LAIR.player));
            }
        });
    }

    public void draw(Graphics g) {
        x = (int) (LAIR.getWidth() - width * 1.1);
        y = (int) (LAIR.getHeight() - height * 1.1);
        g.drawImage(Util.getImage("lair/baralho.png"), x, y, width, height, LAIR);
    }
}