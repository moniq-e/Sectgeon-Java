package com.monique.sectgeon.lair.cards;

import java.awt.Graphics;
import java.util.UUID;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.events.lair.LPHurtEvent;
import com.monique.sectgeon.common.gui.Drawable;
//import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.*;

public class Card extends CardRegistry implements Drawable {
    public static final int WIDTH = Frame.board.getHeight() / 10;
    public static final int HEIGHT = Frame.board.getHeight() / 6;
    public final UUID ID = UUID.randomUUID();
    public final Lair LAIR;
    public final LPlayer PLAYER;

    public Card(CardRegistry card, LPlayer player) {
        super(new String(card.NAME), card.TYPE, card.attack, card.life, card.speed, card.sacrifices, card.skill, card.triggers);

        LAIR = player.lair;
        PLAYER = player;

        if (card.triggers != null && card.skill != null) {
            for (Triggers trigger : triggers) {
                LAIR.listener.addListener(trigger, ID, card.skill);   
            }
        }

        //Frame.listener.addListener(Events.Mouse, ID, null);
    }

    public void attack(Card card) {
        AttackEvent<Card> e = (AttackEvent<Card>) LAIR.listener.dispatchEvent(new AttackEvent<Card>(this, card, attack));

        e.getTarget().takeDamage(this, e.getDamage());
    }

    /**
     * @param source who attacked
     */
    public void takeDamage(Card source, int damage) {
        if (damage > 0) {
            HurtEvent<Card> e = (HurtEvent<Card>) LAIR.listener.dispatchEvent(new HurtEvent<Card>(source, this, damage));

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
            HealEvent<Card> e = (HealEvent<Card>) LAIR.listener.dispatchEvent(new HealEvent<Card>(source, this, value));

            if (e.getTarget() != this) {
                e.getTarget().heal(source, e.getValue());
            } else {
                life += e.getValue();
            }
        }
    }

    public void death() {
        if (life < 0) {
            LPHurtEvent e = (LPHurtEvent) LAIR.listener.dispatchEvent(new LPHurtEvent(this, PLAYER, Math.abs(life)));

            if (e.getTarget() != PLAYER) {
                e.getTarget().takeDamage(this, e.getDamage());
            } else {
                PLAYER.takeDamage(this, e.getDamage());
            }
        }

        PLAYER.cemetery.add(LAIR.tableCards.remove(ID));
        LAIR.listener.dispatchEvent(new DeathEvent<Card>(this));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Util.getImage("cards:carta_vazia.png"), 0, 0, Card.WIDTH, Card.HEIGHT, LAIR);
    }

    public boolean isOnTable() {
        return LAIR.tableCards.get(ID) != null;
    }
}