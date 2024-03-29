package com.monique.sectgeon.lair.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.events.CustomEvent;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.common.events.lair.PlaceCardEvent;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class LairGUI implements Drawable {
    public static UUID cardHovered;
    public static UUID cardDragged;
    public static ArrayList<UUID> evidence = new ArrayList<UUID>();
    public final UUID ID = UUID.randomUUID();
    public final Lair LAIR;
    public final Point[] PlayerTablePos = new Point[3];
    public final Point[] EnemyTablePos = new Point[3];
    public final Font DefaultFont = new Font("Minecraftia", Font.PLAIN, Card.getHeight() * 15 / 100);

    public LairGUI(Lair lair) {
        LAIR = lair;

        lair.defaultListener.addListener(Events.Move, ID, note -> {
            int not = 0;
            for (Card card : LAIR.player.hand) {
                if (card.collidesMouse()) {
                    cardHovered = card.ID;
                } else not++;
            }
            for (Card card : LAIR.tableCards) {
                if (card.collidesMouse()) {
                    cardHovered = card.ID;
                } else not++;
            }
            if (not == LAIR.player.hand.size() + LAIR.tableCards.size()) cardHovered = null;
        });

        lair.defaultListener.addListener(Events.Dragged, ID, note -> {
            if (cardDragged == null) {
                for (Card card : LAIR.player.hand) {
                    if (card.collidesMouse()) {
                        cardDragged = card.ID;
                        break;
                    }
                }
            }
        });
        lair.defaultListener.addListener(Events.Released, ID, note -> {
            if (cardDragged != null) {
                var card = lair.player.getHandCard(cardDragged);
                if (card != null) {
                    if (cardDragged != cardHovered) cardHovered = null;
                    cardDragged = null;

                    for (int i = 0; i < PlayerTablePos.length; i++) {
                        Point p = PlayerTablePos[i];
                        if (Util.collides(new Rectangle(p.x, p.y, Card.getWidth(), Card.getHeight()), Util.getMouseRect())) {
                            lair.placeCard(card, i);
                            break;
                        }
                    }
                }
            } else {
                for (var card : LAIR.getTableCards(lair.player)) {
                    if (card.collidesMouse()) {
                        if (!card.owner.toSacrifice.contains(card.ID)) card.owner.toSacrifice.add(card.ID);
                        else card.owner.toSacrifice.remove(card.ID);
                    }
                }
                for (var card : LAIR.player.hand) {
                    if (card.collidesMouse()) {
                        if (!card.owner.handSacrifice.contains(card.ID)) card.owner.handSacrifice.add(card.ID);
                        else card.owner.handSacrifice.remove(card.ID);
                    }
                }
            }
        });

        lair.listener.addListener(Triggers.PlaceCard, ID, e -> {
            var pe = (PlaceCardEvent) e;
            var owner = pe.getSource().owner;
            if (pe.getSource().getSacrifices() <= owner.toSacrifice.size()) {
                for (int i = 0; i < pe.getSource().getSacrifices(); i++) {
                    LAIR.getTableCard(owner.toSacrifice.remove(0)).death(null);
                }
            } else {
                pe.setPos(-1);
            }
        });
        Consumer<CustomEvent<Card>> clearSacrifices = e -> {
            lair.player.toSacrifice.clear();
            lair.enemy.toSacrifice.clear();
        };
        lair.listener.addListener(Triggers.BattleStart, ID, clearSacrifices);
        lair.listener.addListener(Triggers.TurnStart, ID, clearSacrifices);
    }

    @Override
    public void draw(Graphics g) {
        int wid = LAIR.getWidth();
        int hei = LAIR.getHeight();

        g.setColor(Color.yellow);
        g.setFont(DefaultFont);

        setTableCardsPos(wid, hei);

        var mesa = Util.getImage("lair/mesa.png").getScaledInstance(wid / 10, hei / 10, BufferedImage.SCALE_FAST);
        for (int i = 0; i <= wid; i += mesa.getWidth(null)) {
            for (int j = 0; j <= hei; j += mesa.getHeight(null)) {
                g.drawImage(mesa, i, j, LAIR);
            }
        }

        drawTableCardsPos(g);
        drawLifes(g);
        LAIR.readyButton.draw(g);
        LAIR.pile.draw(g);
        LAIR.cemetery.draw(g);
        LAIR.tableCards.forEach(c -> c.draw(g));
        drawHand(g, wid, hei);
    }

    private void drawLifes(Graphics g) {
        var enemyInfo = LAIR.enemy.NAME.concat(" " + String.valueOf(LAIR.enemy.getLife()));
        var y = g.getFontMetrics().getHeight() * 2;

        g.drawString(String.valueOf(LAIR.player.getLife()).concat(" " + LAIR.player.NAME), y, y);
        g.drawString(enemyInfo, LAIR.getWidth() - g.getFontMetrics().stringWidth(enemyInfo) - y, y);
    }

    private void setTableCardsPos(int wid, int hei) {
        int buffer = Card.getWidth() * 15 / 10;
        int x = wid / 2 - Card.getWidth() / 2 - buffer;
        int y = hei / 2 + Card.getHeight() * 25 / 100;

        for (int i = 0; i < 3; i++) {
            PlayerTablePos[i] = new Point(x, y);
            EnemyTablePos[i] = new Point(x, y - Card.getHeight() * 15 / 10);
            x += buffer;
        }
    }

    private void drawTableCardsPos(Graphics g) {
        for (int i = 0; i < 3; i++) {
            Point pp = PlayerTablePos[i];
            Point ep = EnemyTablePos[i];
            String is = String.valueOf(i + 1);

            final int xBuffer = Card.getWidth() / 2 - g.getFontMetrics().stringWidth(is) / 2;
            final int yBuffer = Card.getHeight() / 2 + g.getFont().getSize();

            g.setColor(Color.CYAN);
            g.drawRect(pp.x, pp.y, Card.getWidth(), Card.getHeight());
            g.drawRect(ep.x, ep.y, Card.getWidth(), Card.getHeight());

            g.setColor(Color.YELLOW);
            g.drawString(is, pp.x + xBuffer, pp.y + yBuffer);
            g.drawString(is, ep.x + xBuffer, ep.y + yBuffer);
        }
    }

    private void drawHand(Graphics g, int wid, int hei) {
        ArrayList<Card> hand = LAIR.player.hand;
        int buffer;

        if (hand.size() > 10) buffer = -30;
        else buffer = -3 * hand.size();

        int x = (wid / 2 - Card.getWidth() / 2) - ((Card.getWidth() + buffer) * (hand.size() - 1)) / 2;

        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).drawInHand(g, x, hei / 2 + Card.getHeight() * 15 / 10);
            x += Card.getWidth() + buffer + 2;
        }
    }
}