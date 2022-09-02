package com.monique.txtrpg.entities;

import com.monique.txtrpg.Board;
import com.monique.txtrpg.Tool;

public class Entity {
    public Board board;
    public final String type;
    public final float maxLife;
    public float life;
    public float armor;

    Entity(Board board, String type, float maxLife) {
        this.board = board;
        this.type = type;
        this.maxLife = maxLife;
        this.life = maxLife;
    }

    public void attack(Entity target, Tool tool) {
        target.takeDamage(tool.damage);
    }

    public void takeDamage(float damage) {
        life -= damage - armor;
    }
}