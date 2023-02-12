package com.monique.sectgeon.lair.cards;

import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.UUID;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.common.events.lair.LPHurtEvent;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.lair.*;
import com.monique.sectgeon.lair.gui.LairGUI;

public class Card extends CardRegistry implements Drawable {
    private static BufferedImage image = Util.getImage("cards/carta_vazia.png");
    public final UUID ID = UUID.randomUUID();
    public final Lair LAIR;
    public final Player PLAYER;
    public int x, y;

    public Card(CardRegistry card, Player player) {
        super(new String(card.NAME), card.TYPE, card.attack, card.life, card.speed, card.sacrifices, card.skill, card.triggers);
        pos = -1;

        LAIR = player.lair;
        PLAYER = player;

        if (card.triggers != null && card.skill != null) {
            for (Triggers trigger : triggers) {
                LAIR.listener.addListener(trigger, ID, card.skill);
            }
        }
    }

    public void attack(Card card) {
        AttackEvent<Card> e = (AttackEvent<Card>) LAIR.listener.dispatch(new AttackEvent<Card>(this, card, attack));

        e.getTarget().takeDamage(this, e.getDamage());
    }

    /**
     * @param source who attacked
     */
    public void takeDamage(Card source, int damage) {
        if (damage > 0) {
            HurtEvent<Card> e = (HurtEvent<Card>) LAIR.listener.dispatch(new HurtEvent<Card>(source, this, damage));

            if (e.getTarget() != this) {
                e.getTarget().takeDamage(source, e.getDamage());   
            } else {
                life -= e.getDamage();
                if (life <= 0) death();
            }
        }
    }

    /**
     * @param source who healed
     */
    public void heal(Card source, int value) {
        if (value > 0) {
            HealEvent<Card> e = (HealEvent<Card>) LAIR.listener.dispatch(new HealEvent<Card>(source, this, value));

            if (e.getTarget() != this) {
                e.getTarget().heal(source, e.getValue());
            } else {
                life += e.getValue();
            }
        }
    }

    public void death() {
        if (life < 0) {
            LPHurtEvent e = (LPHurtEvent) LAIR.listener.dispatch(new LPHurtEvent(this, PLAYER, Math.abs(life)));

            if (e.getTarget() != PLAYER) {
                e.getTarget().takeDamage(this, e.getDamage());
            } else {
                PLAYER.takeDamage(this, e.getDamage());
            }
        }

        PLAYER.cemetery.add(LAIR.tableCards.remove(ID));
        LAIR.listener.dispatch(new DeathEvent<Card>(this));
    }

    @Override
    public void draw(Graphics g) {
        Point pos;
        if (PLAYER instanceof Enemy) {
            pos = LAIR.hud.EnemyTablePos[this.pos];
        } else {
            pos = LAIR.hud.PlayerTablePos[this.pos];
        }
        drawCard(g, pos.x, pos.y);
    }

    public void drawInHand(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;

        Point mouse = LAIR.getMousePosition();

        if (mouse != null) {
            if (LairGUI.cardDragged == ID) {
                x = (int) (mouse.getX()) - getWidth() / 2;
                y = (int) (mouse.getY()) - getHeight() / 2;
            } else if (LairGUI.cardHovered == ID && LairGUI.cardDragged == null) {
                y -= getHeight();
                g.drawString(NAME, x + getWidth() / 2 - g.getFontMetrics().stringWidth(NAME) / 2, y - g.getFont().getSize());
            }
        }

        drawCard(g, x, y);
    }

    public void drawCard(Graphics g, int x, int y) {
        int fontSize = g.getFont().getSize();
        FontMetrics metrics = g.getFontMetrics();

        g.drawImage(image, x, y, getWidth(), getHeight(), LAIR);

        String attkString = String.valueOf(attack);
        g.drawString(attkString, x + getWidth() * 20 / 100 - metrics.stringWidth(attkString) / 2, y + getHeight() - fontSize);

        String lifeString = String.valueOf(life);
        g.drawString(lifeString, x + getWidth() * 80 / 100 - metrics.stringWidth(lifeString) / 2, y + getHeight() - fontSize);
    }

    public boolean isOnTable() {
        return LAIR.tableCards.get(ID) != null;
    }

    private static int parsedWidth() {
        return Frame.board.getHeight() * 2 / 10;
    }

    public static int getWidth() {
        return image.getWidth() * (getHeight() / image.getHeight());
    }

    public static int getHeight() {
        return image.getHeight() * (parsedWidth() / image.getWidth());
    }
}