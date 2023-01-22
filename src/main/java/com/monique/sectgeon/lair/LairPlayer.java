package com.monique.sectgeon.lair;

import com.monique.sectgeon.events.*;

public class LairPlayer {
    public final String NAME;
    public Lair lair;
    private int life;

    public LairPlayer(Lair lair, String name, int maxLife) {
        this.lair = lair;
        this.NAME = name;
        this.life = maxLife;
    }

    /**
     * @param source who attacked
     */
    public int takeDamage(LairPlayer source, int damage) {
        if (damage > 0) {
            HurtEvent<LairPlayer> e = (HurtEvent<LairPlayer>) lair.playerListener.dispatchEvent(new HurtEvent<LairPlayer>(source, this, damage));

            life -= e.getDamage();
            if (life <= 0) death();
            return Math.abs(life);
        } else return 0;
    }

    public void heal(int value) {
        if (value > 0) {
            HealEvent<LairPlayer> e = (HealEvent<LairPlayer>) lair.playerListener.dispatchEvent(new HealEvent<LairPlayer>(this, value));

            life += e.getValue();
        }
    }

    public void death() {
        //TODO
    }
}