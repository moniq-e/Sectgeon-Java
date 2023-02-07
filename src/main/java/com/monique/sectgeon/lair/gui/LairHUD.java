package com.monique.sectgeon.lair.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.UUID;
import java.awt.event.MouseEvent;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class LairHUD implements Drawable {
    public static UUID cardHovered;
    public static UUID cardDragged;
    public final Lair LAIR;
    public final Point[] PlayerTablePos = new Point[3];
    public final Point[] EnemyTablePos = new Point[3];

    public LairHUD(Lair lair) {
        LAIR = lair;

        lair.defaultListener.addListener(Events.Move, null, note -> {
            MouseEvent e = (MouseEvent) note;
            int not = 0;
            for (Card card : LAIR.player.hand) {
                if (Util.collides(new Rectangle(card.x, card.y, Card.getWidth(), Card.getHeight()), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                    cardHovered = card.ID;
                } else not++;
            }
            if (not == LAIR.player.hand.size()) cardHovered = null;
        });

        lair.defaultListener.addListener(Events.Dragged, null, note -> {
            MouseEvent e = (MouseEvent) note;
            if (cardDragged == null) {
                for (Card card : LAIR.player.hand) {
                    if (Util.collides(new Rectangle(card.x, card.y, Card.getWidth(), Card.getHeight()), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                        cardDragged = card.ID;
                        break;
                    }
                }
            }
        });
        lair.defaultListener.addListener(Events.Released, null, note -> {
            MouseEvent e = (MouseEvent) note;
            if (cardDragged != null) {
                Card card = lair.player.getHandCard(cardDragged);
                if (card != null) {
                    cardDragged = null;
                    cardHovered = null;
                    for (int i = 0; i < PlayerTablePos.length; i++) {
                        Point p = PlayerTablePos[i];
                        if (Util.collides(new Rectangle(p.x, p.y, Card.getWidth(), Card.getHeight()), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                            lair.placeCard(card, i);
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void draw(Graphics g) {
        int wid = LAIR.getWidth();
        int hei = LAIR.getHeight();

        g.setColor(Color.yellow);
        g.setFont(new Font("Monospaced", Font.PLAIN, Card.getHeight() * 15 / 100));

        setTableCardsPos(wid, hei);
        g.drawImage(Util.getImage("lair/mesa.png"), 0, 0, wid, hei, LAIR);
        drawTableCardsPos(g);
        LAIR.pile.draw(g);
        LAIR.tableCards.values().forEach(c -> c.draw(g));
        drawHand(g, wid, hei);
    }

    private void setTableCardsPos(int wid, int hei) {
        for (int i = 0; i < 3; i++) {
            PlayerTablePos[i] = new Point(wid / 2 - Card.getWidth() * (i - 1) - Card.getWidth() / 2, hei / 2);
        }
        for (int i = 0; i < 3; i++) {
            EnemyTablePos[i] = new Point(wid / 2 - Card.getWidth() * (i - 1) - Card.getWidth() / 2, hei / 2 - Card.getHeight());
        }
    }

    private void drawTableCardsPos(Graphics g) {
        for (int i = 0; i < 3; i++) {
            Point pp = PlayerTablePos[i];
            Point ep = EnemyTablePos[i];
            String is = String.valueOf(i + 1);

            final int xBuffer = Card.getWidth() / 2 - g.getFontMetrics().stringWidth(is) / 2;
            final int yBuffer = Card.getHeight() / 2;

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

        int buffer = -3 * hand.size();
        if (hand.size() > 9) buffer = -27;

        int x = (wid / 2 - Card.getWidth() / 2) - ((Card.getWidth() + buffer) * (hand.size() - 1)) / 2;

        for (int i = 0; i < hand.size(); i++) {
            hand.get(i).drawInHand(g, x, hei / 2 + Card.getHeight());
            x += Card.getWidth() + buffer + 2;
        }
    }
}