package com.monique.txtrpg.entities;

import java.util.ArrayList;
import java.util.UUID;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.monique.txtrpg.*;
import com.monique.txtrpg.items.Item;

public class Entity {
    public Board board;
    public final String id = UUID.randomUUID().toString();
    public final String type;
    public final String name;
    public final float maxLife;
    public final int walkDistance;
    public final int width;
    public final int height;
    public ArrayList<Item> inventory = new ArrayList<Item>();

    private float life;
    private float armor;
    private Point pos = new Point();
    private Rectangle rect;

    Entity(Board board, String type, String name, float maxLife, int walkDistance, int width, int height) {
        this.board = board;
        this.type = type;
        this.name = name;
        this.maxLife = maxLife;
        this.walkDistance = walkDistance;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(0, 0, width, height);
        this.life = maxLife;
    }

    public void attack(Entity target, Item item) {
        if (item.type != "tool") return;
        target.takeDamage(Util.d(item.dice));
    }

    public void takeDamage(float damage) {
        life -= damage - armor;
    }

    public void draw(Graphics g) { }
    
    //getters
    public float getLife() {
        return life;
    }
    public float getArmor() {
        return armor;
    }
    public Point getPos() {
        return (Point) pos.clone();
    }
    public Rectangle getRect() {
        return (Rectangle) rect.clone();
    }

    //setters
    public void setPos(int x, int y) {
        this.pos.move(x, y);
        this.rect.setLocation(x, y);
    }
}