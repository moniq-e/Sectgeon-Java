package com.monique.sectgeon.lair.cards;

import java.util.UUID;
import java.util.function.Consumer;

import com.monique.sectgeon.entities.Drawable;
import com.monique.sectgeon.events.*;
import com.monique.sectgeon.lair.*;

public abstract class Card implements Drawable {
    public final String ID = UUID.randomUUID().toString();
    public Lair lair;
    public LairPlayer player;
    private int attack;
    private int life;
    private int speed;
    private int sacrifices;

    public Card(LairPlayer player, int attack, int life, int speed, int sacrifices, Triggers trigger, Consumer<CustomEvent<Card>> skill) {
        this.lair = player.lair;
        this.player = player;
        this.attack = attack;
        this.life = life;
        this.speed = speed;
        this.sacrifices = sacrifices;
        
        this.lair.listener.addListener(trigger, ID, skill);
    }

    public void attack(Card card) {
        AttackEvent<Card> e = (AttackEvent<Card>) lair.listener.dispatchEvent(new AttackEvent<Card>(this, card, attack));

        e.getTarget().takeDamage(this, e.getDamage());
    }

    /**
     * @param source who attacked
     */
    public int takeDamage(Card source, int damage) {
        if (damage > 0) {
            HurtEvent<Card> e = (HurtEvent<Card>) lair.listener.dispatchEvent(new HurtEvent<Card>(source, this, damage));

            life -= e.getDamage();
            if (life <= 0) death();
            return Math.abs(life);
        } else return 0;
    }

    public void heal(int value) {
        if (value > 0) {
            HealEvent<Card> e = (HealEvent<Card>) lair.listener.dispatchEvent(new HealEvent<Card>(this, value));

            life += e.getValue();
        }
    }

    public void death() {
        //TODO
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
}