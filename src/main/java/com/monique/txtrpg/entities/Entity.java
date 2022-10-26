package com.monique.txtrpg.entities;

import java.util.ArrayList;
import java.util.UUID;
import java.awt.Point;
import java.awt.Rectangle;

import com.monique.txtrpg.*;
import com.monique.txtrpg.dungeons.Dungeon;
import com.monique.txtrpg.events.AttackEvent;
import com.monique.txtrpg.items.Item;
import com.monique.txtrpg.listeners.CustomListener;

public abstract class Entity implements Drawable {
    public Dungeon dungeon;
    public final String ID = UUID.randomUUID().toString();
    public final String TYPE;
    public final String NAME;
    public final float MAXLIFE;
    public final int WALKDISTANCE;
    public final int WIDTH;
    public final int HEIGHT;
    public ArrayList<Item> inventory = new ArrayList<Item>();

    private float life;
    private float armor;
    private Point pos = new Point();
    private Rectangle rect;

    /**
     * Default entitiy constructor, name is type
     * @param dungeon
     * @param type
     * @param maxLife
     * @param walkDistance
     * @param width
     * @param height
     */
    public Entity(Dungeon dungeon, String type, float maxLife, int walkDistance, int width, int height) {
        this.dungeon = dungeon;
        this.TYPE = type;
        this.NAME = type;
        this.MAXLIFE = maxLife;
        this.WALKDISTANCE = walkDistance;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.rect = new Rectangle(0, 0, width, height);
        this.life = maxLife;
    }

    /**
     * Entity constructor with name
     * @param dungeon
     * @param type
     * @param name
     * @param maxLife
     * @param walkDistance
     * @param width
     * @param height
     */
    public Entity(Dungeon dungeon, String type, String name, float maxLife, int walkDistance, int width, int height) {
        this.dungeon = dungeon;
        this.TYPE = type;
        this.NAME = name;
        this.MAXLIFE = maxLife;
        this.WALKDISTANCE = walkDistance;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.rect = new Rectangle(0, 0, width, height);
        this.life = maxLife;
    }

    public void attack(Entity target, Item item) {
        if (item.type != "tool") return;
        int dmg = Util.d(item.dice);
        target.takeDamage(dmg);
        CustomListener.dispatchEvent("attack", new AttackEvent(this, target, dmg));
    }

    public void takeDamage(float damage) {
        life -= (damage - armor <= 0 ? 0 : damage - armor);
        if (life <= 0) kill();
    }

    public void followPlayer() {
        if (!Util.collides(dungeon.player.getRect(), getRect())) {
            double disObj = Util.distance(getPos(), dungeon.player.getPos());

            double pos = WALKDISTANCE / disObj;
            int x = (int) (getPos().x - pos * (getPos().x - dungeon.player.getPos().x));
            int y = (int) (getPos().y - pos * (getPos().y - dungeon.player.getPos().y));

            setPos(x, y); 
        }
    }

    public boolean playerColliding() {
        return Util.collides(dungeon.player.getRect(), getRect());
    }

    //abstracts
    public abstract void kill();
    
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
    /**
     * Sets the entity pos
     */
    public void setPos(int x, int y) {
        pos.move(x, y);
        rect.setLocation(x, y);
    }
    /**
     * Makes the entity move adding pos
     */
    public void move(int x, int y) {
        pos.move(pos.x + x, pos.y + y);
        rect.setLocation(pos.x + x, pos.y + y);
    }
}