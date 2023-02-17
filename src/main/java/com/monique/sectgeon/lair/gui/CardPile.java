package com.monique.sectgeon.lair.gui;

import java.awt.Rectangle;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.Player;
import com.monique.sectgeon.lair.cards.Card;

public class CardPile extends Button {

    public CardPile(Lair lair) {
        super(lair, "lair/baralho.png", 8);

        setListener(note -> {
            if (Util.collides(getRect(), Util.getMouseRect())) {
                buyCard(LAIR.player);
            }
        });
    }

    public void buyCard(Player player) {
        if (player.getBuyAmount() >= 1) {
            player.hand.add(new Card(player.deck.get(Util.random(0, player.deck.size() - 1)), player));
            player.setBuyAmount(player.getBuyAmount() - 1);
        }
    }

    @Override
    public Rectangle getRect() {
        var buffer = getHeight() / 10;
        rect.setLocation(LAIR.getWidth() - getWidth() - buffer, LAIR.getHeight() - getHeight() - buffer);
        rect.setSize(getWidth(), getHeight());
        return rect;
    }
}