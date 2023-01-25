package com.monique.sectgeon.lair.cards;

import java.util.function.Consumer;

import com.monique.sectgeon.common.events.*;

public class CardRegistry {
    public final CardTypes TYPE;
    public final String NAME;
    protected Triggers[] triggers;
    protected Consumer<CustomEvent<Card>> skill;
    protected int life;
    protected int attack;
    protected int speed;
    protected int sacrifices;
    protected int pos;

    public CardRegistry(String name, CardTypes type, int attk, int life, int speed, int sacr, Consumer<CustomEvent<Card>> skill, Triggers... triggers) {
        TYPE = type;
        NAME = name;
        this.attack = attk;
        this.life = life;
        this.speed = speed;
        this.sacrifices = sacr;
        this.triggers = triggers;
        this.skill = skill;
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
}