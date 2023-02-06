package com.monique.sectgeon.lair.cards;

import java.awt.Color;
import java.awt.Font;
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
//import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.*;
import com.monique.sectgeon.lair.gui.LairHUD;

public class Card extends CardRegistry implements Drawable {
    public final UUID ID = UUID.randomUUID();
    public final Lair LAIR;
    public final LPlayer PLAYER;
    public int x, y;

    public Card(CardRegistry card, LPlayer player) {
        super(new String(card.NAME), card.TYPE, card.attack, card.life, card.speed, card.sacrifices, card.skill, card.triggers);
        pos = -1;

        LAIR = player.lair;
        PLAYER = player;

        if (card.triggers != null && card.skill != null) {
            for (Triggers trigger : triggers) {
                LAIR.listener.addListener(trigger, ID, card.skill);
            }
        }

        // Frame.listener.addListener(Events.Mouse, ID, null);
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
        if (PLAYER instanceof LEnemy) {
            pos = LAIR.hud.EnemyTablePos[this.pos];
        } else {
            pos = LAIR.hud.PlayerTablePos[this.pos];
        }
        g.drawImage(Util.getImage("cards/carta_vazia.png"), pos.x, pos.y, getWidth(), getHeight(), LAIR);
    }

    public void draw(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;
        g.setColor(Color.yellow);
        g.setFont(new Font("Monospaced", Font.PLAIN, getHeight() * 15 / 100));

        Point mouse = LAIR.getMousePosition();
        int fontSize = g.getFont().getSize();
        FontMetrics metrics = g.getFontMetrics();

        if (mouse != null && LairHUD.up == ID) {
            y -= getHeight();
            g.drawString(NAME, x + Card.getWidth() / 2 - metrics.stringWidth(NAME) / 2, y - fontSize);
        }

        g.drawImage(Util.getImage("cards/carta_vazia.png"), x, y, getWidth(), getHeight(), LAIR);

        String attkString = String.valueOf(attack);
        g.drawString(String.valueOf(attkString), x + getWidth() * 22 / 100 - metrics.stringWidth(attkString) / 2, y + getHeight() - fontSize);

        String lifeString = String.valueOf(life);
        g.drawString(String.valueOf(lifeString), x + getWidth() * 81 / 100 - metrics.stringWidth(lifeString) / 2, y + getHeight() - fontSize);
    }

    public boolean isOnTable() {
        return LAIR.tableCards.get(ID) != null;
    }

    public static int getWidth() {
        return Frame.board.getHeight() * 15 / 100;
    }

    public static int getHeight() {
        return Frame.board.getHeight() * 20 / 100;
    }
}