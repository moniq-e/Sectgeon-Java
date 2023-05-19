package com.monique.sectgeon.lair.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.Player;
import com.monique.sectgeon.lair.cards.Card;

public class CardPile extends Button {

    public CardPile(Lair lair) {
        super(lair, "lair/baralho.png", 8);
    }

    @Override
    public void onClick(Object o) {
        if (Util.collides(getRect(), Util.getMouseRect())) {
            buyCard(LAIR.player);
        }
    }

    public void buyCard(Player player) {
        if (player.getBuyAmount() > 0) {
            player.hand.add(new Card(player.deck.get(Util.random(0, player.deck.size() - 1)), player));
            player.setBuyAmount(player.getBuyAmount() - 1);
        }
    }

    public void buyCards(Player player) {
        while (player.getBuyAmount() >= 1) {
            player.hand.add(new Card(player.deck.get(Util.random(0, player.deck.size() - 1)), player));
            player.setBuyAmount(player.getBuyAmount() - 1);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (LAIR.player.getBuyAmount() > 0) {
            var old = g.getColor();
            g.setColor(Color.CYAN);
            g.fillRoundRect(getRect().x - 2, getRect().y - 2, getWidth() + 4, getHeight() + 4, 10, 10);
            g.setColor(old);
        }
        super.draw(g);
    }

    @Override
    public int getHeight() {
        if (LAIR.player.getBuyAmount() > 0) {
            return (int) (super.getHeight() * 1.3);
        }
        return super.getHeight();
    }

    @Override
    public Rectangle getRect() {
        var buffer = getHeight() / 10;
        rect.setLocation(LAIR.getWidth() - getWidth() - buffer, LAIR.getHeight() - getHeight() - buffer);
        rect.setSize(getWidth(), getHeight());
        return rect;
    }
}