package com.txtrpg;

public class Entity {
    public final String type;
    public float life;
    public float armor;

    Entity(String type) {
        this.type = type;
    }

    public void attack(Entity target, Tool tool) {
        target.takeDamage(tool.damage);
    }

    public void takeDamage(float damage) {
        life -= damage - armor;
    }
}