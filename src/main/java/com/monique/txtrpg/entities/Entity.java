package com.monique.txtrpg.entities;

import java.util.ArrayList;
import java.util.UUID;
import java.awt.Point;
import java.awt.Graphics;
import com.monique.txtrpg.Board;
import com.monique.txtrpg.Item;
import com.monique.txtrpg.Tool;

public class Entity {
    public Board board;
    public final String id = UUID.randomUUID().toString();
    public final String type;
    public final String name;
    public final float maxLife;
    public float life;
    public float armor;
    public final int walkDistance;
    public Point pos;
    public ArrayList<Item> inventory = new ArrayList<Item>();

    Entity(Board board, String type, String name, float maxLife, int walkDistance) {
        this.board = board;
        this.type = type;
        this.name = name;
        this.maxLife = maxLife;
        this.walkDistance = walkDistance;
        this.life = maxLife;
    }

    public void attack(Entity target, Tool tool) {
        target.takeDamage(tool.damage);
    }

    public void takeDamage(float damage) {
        life -= damage - armor;
    }

    public void draw(Graphics g) {
        
    }
}