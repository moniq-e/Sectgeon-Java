package com.monique.sectgeon.lair;

public class LairPlayer {
    private Lair lair;
    private final String NAME;
    private final int MAXLIFE;
    private int life;

    public LairPlayer(Lair lair, String name, int maxLife) {
        this.lair = lair;
        this.NAME = name;
        this.MAXLIFE = maxLife;
        this.life = maxLife;
    }

    public int takeDamage(int attack) {
        life -= attack;
        return Math.abs(life);
    }
}