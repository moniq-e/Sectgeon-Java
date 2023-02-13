package com.monique.sectgeon.lair.cards;

import java.util.function.Consumer;

import org.json.JSONObject;

import com.monique.sectgeon.common.events.*;

public class CardRegistry {
    public final CardTypes TYPE;
    public final String NAME;
    public final String DESC;
    protected JSONObject json;
    protected Triggers[] triggers;
    protected Consumer<CustomEvent<Card>> skill;
    protected int life;
    protected int attack;
    protected int speed;
    protected int sacrifices;
    protected int pos;

    public CardRegistry(JSONObject json) {
        TYPE = CardTypes.valueOf(json.getString("type"));
        NAME = json.getString("name");
        DESC = json.getString("desc");
        this.attack = json.getInt("attack");
        this.life = json.getInt("life");
        this.speed = json.getInt("speed");
        this.sacrifices = json.getInt("sacrifices");
    }

    public CardRegistry(String name, String desc, CardTypes type, int attk, int life, int speed, int sacr, Consumer<CustomEvent<Card>> skill, Triggers... triggers) {
        TYPE = type;
        NAME = name;
        DESC = desc;
        this.attack = attk;
        this.life = life;
        this.speed = speed;
        this.sacrifices = sacr;
        this.triggers = triggers;
        this.skill = skill;
    }

    public void setSkill(Consumer<CustomEvent<Card>> skill, Triggers... triggers) {
        this.triggers = triggers;
        this.skill = skill;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
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