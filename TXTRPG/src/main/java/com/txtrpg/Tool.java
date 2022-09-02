package com.txtrpg;

public class Tool extends Item {
    public float damage;
    Tool(String name, float damage) {
        super(name);
        this.damage = damage;
    }
}