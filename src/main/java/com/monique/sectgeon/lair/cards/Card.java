package com.monique.sectgeon.lair.cards;

import java.awt.Graphics;
import java.util.UUID;
import java.util.function.Consumer;

import com.monique.sectgeon.entities.Drawable;
import com.monique.sectgeon.events.*;
import com.monique.sectgeon.events.lairplayer.LPHurtEvent;
import com.monique.sectgeon.lair.*;

public class Card implements Drawable {
    public final UUID ID;
    public final CardTypes TYPE;
    public final String NAME;
    public final Lair LAIR;
    public final LairPlayer PLAYER;
    private int attack;
    private int life;
    private int speed;
    private int sacrifices;
    private int pos;
    private Triggers[] triggers;
    private Consumer<CustomEvent<Card>> skill;

    public Card(String name, CardTypes type, int attk, int life, int speed, int sacr, Consumer<CustomEvent<Card>> skill, Triggers... triggers) {
        ID = null;
        TYPE = type;
        NAME = name;
        LAIR = null;
        PLAYER = null;
        this.attack = attk;
        this.life = life;
        this.speed = speed;
        this.sacrifices = sacr;
        this.triggers = triggers;
        this.skill = skill;
    }

    public Card(Card card, LairPlayer player) {
        ID = UUID.randomUUID();
        TYPE = card.TYPE;
        NAME = new String(card.NAME);
        LAIR = player.lair;
        PLAYER = player;
        this.attack = card.attack;
        this.life = card.life;
        this.speed = card.speed;
        this.sacrifices = card.sacrifices;
        this.triggers = card.triggers;
        this.skill = card.skill;

        if (card.triggers != null && card.skill != null) {
            for (Triggers trigger : triggers) {
                LAIR.listener.addListener(trigger, ID, card.skill);   
            }
        }
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

    public boolean isOnTable() {
        return LAIR.tableCards.get(ID) != null;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSacrifices() {
        return sacrifices;
    }

    public void setSacrifices(int sacrifices) {
        this.sacrifices = sacrifices;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public void draw(Graphics g) {
        //TODO
    }
}