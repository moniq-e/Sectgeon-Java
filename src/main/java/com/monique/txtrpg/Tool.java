package com.monique.txtrpg;

public class Tool extends Item {
    public float damage;
    Tool(String name, float damage) {
        super(name);
        this.damage = damage;
    }
}