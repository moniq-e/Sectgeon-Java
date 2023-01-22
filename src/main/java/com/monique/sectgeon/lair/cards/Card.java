package com.monique.sectgeon.lair.cards;

import java.util.function.Consumer;

import com.monique.sectgeon.entities.Drawable;
import com.monique.sectgeon.events.*;
import com.monique.sectgeon.lair.Lair;

public abstract class Card implements Drawable {
    private Lair lair;
    private int attack;
    private int life;
    private int speed;
    private int sacrifices;
    private Triggers trigger;
    private Consumer<CustomEvent<Card>> skill;

    public Card(Lair lair, int attack, int life, int speed, int sacrifices, Triggers trigger, Consumer<CustomEvent<Card>> skill) {
        this.lair = lair;
        this.attack = attack;
        this.life = life;
        this.speed = speed;
        this.sacrifices = sacrifices;
        this.trigger = trigger;
        this.skill = skill;
    }

    public void attack(Card card) {
        card.takeDamage(attack);
    }

    public int takeDamage(int attack) {
        life -= attack;
        return Math.abs(life);
    }
}