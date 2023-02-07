package com.monique.sectgeon.lair.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.UUID;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class CardPile {
    public final UUID ID = UUID.randomUUID();
    private final Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());
    private final Lair LAIR;

    public CardPile(Lair lair) {
        LAIR = lair;
        setClick();
    }

    private void setClick() {
        LAIR.defaultListener.addListener(Events.Click, ID, note -> {
            MouseEvent e = (MouseEvent) note;
            if (Util.collides(getRect(), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                LAIR.player.hand.add(new Card(LAIR.player.deck.get(Util.random(0, LAIR.player.deck.size() - 1)), LAIR.player));
            }
        });
    }

    public void draw(Graphics g) {
        g.drawImage(Util.getImage("lair/baralho.png"), getRect().x, getRect().y, getWidth(), getHeight(), LAIR);
    }

    public static int getWidth() {
        return Frame.board.getHeight() / 10;
    }

    public static int getHeight() {
        return Frame.board.getHeight() / 6;
    }

    public Rectangle getRect() {
        rect.setLocation((int) (LAIR.getWidth() - getWidth() * 1.1), (int) (LAIR.getHeight() - getHeight() * 1.1));
        rect.setSize(getWidth(), getHeight());
        return rect;
    }
}