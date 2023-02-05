package com.monique.sectgeon.lair.cards;

import java.awt.Graphics;
import java.util.UUID;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;

public class CardPile {
    public final UUID ID = UUID.randomUUID();
    public int x, y;
    private final Lair LAIR;

    public CardPile(Lair lair) {
        LAIR = lair;
        setClick();
    }

    private void setClick() {
        LAIR.defaultListener.addListener(Events.Mouse, ID, e -> {
            LAIR.player.hand.add(LAIR.player.deck.get(Util.random(0, LAIR.player.deck.size() - 1)));
        });
    }

    public void draw(Graphics g) {
        x = (int) (LAIR.getWidth() - Card.WIDTH * 1.1);
        y = (int) (LAIR.getHeight() - Card.HEIGHT * 1.1);
        g.drawImage(Util.getImage("lair/baralho.png"), x, y, Card.WIDTH, Card.HEIGHT, LAIR);
    }
}