package com.monique.sectgeon.lair.gui;

import java.awt.Rectangle;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class CardPile extends Button {

    public CardPile(Lair lair) {
        super(lair, "lair/baralho.png", 8);

        setListener(note -> {
            if (Util.collides(getRect(), Util.getMouseRect())) {
                LAIR.player.hand.add(new Card(LAIR.player.deck.get(Util.random(0, LAIR.player.deck.size() - 1)), LAIR.player));
            }
        });
    }

    @Override
    public Rectangle getRect() {
        var buffer = getHeight() / 10;
        rect.setLocation(LAIR.getWidth() - getWidth() - buffer, LAIR.getHeight() - getHeight() - buffer);
        rect.setSize(getWidth(), getHeight());
        return rect;
    }
}