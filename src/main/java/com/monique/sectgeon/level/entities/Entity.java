package com.monique.sectgeon.level.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.UUID;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.events.AttackEvent;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.level.dungeons.Dungeon;
import com.monique.sectgeon.level.items.Item;
import com.monique.sectgeon.level.items.Tool;

public abstract class Entity implements Drawable {
    public Dungeon dungeon;
    public final UUID ID = UUID.randomUUID();
    public final String TYPE;
    public final String NAME;
    public final float MAXLIFE;
    public final int WALKDISTANCE;
    public final int WIDTH;
    public final int HEIGHT;
    public ArrayList<Item> inventory = new ArrayList<Item>(9);

    private float life;
    private float armor;
    private Point pos = new Point();
    private Rectangle rect;
    private Item heldItem;

    /**
     * Entity constructor with name
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

    /**
     * Default entitiy constructor, name is type
     */
    public Entity(Dungeon dungeon, String type, float maxLife, int walkDistance, int width, int height) {
        this(dungeon, type, type, maxLife, walkDistance, width, height);
    }

    public void attack(Entity target, Tool item) {
        int dmg = item.rollDice();
        target.takeDamage(dmg);
        dungeon.listener.dispatch(new AttackEvent<Entity>(this, target, dmg));
    }

    public void tryAttack(Entity target) {
        if (getHeldItem() instanceof Tool) {
            Tool tool = (Tool) getHeldItem();
            if (tool.getTicks() >= tool.getSpeed()) {
                tool.resetTicks();
                attack(target, tool);
            }
        }
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

    // abstracts
    public abstract void kill();

    // getters
    public float getLife() {
        return life;
    }

    public float getArmor() {
        return armor;
    }

    public Point getPos() {
        return pos;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Item getHeldItem() {
        return heldItem;
    }

    // setters
    public void setPos(int x, int y) {
        pos.move(x, y);
        rect.setLocation(x, y);
    }

    public void setPos(Point position) {
        pos.setLocation(position);
        rect.setLocation(position);
    }

    /**
     * Makes the entity move adding pos
     */
    public void move(int x, int y) {
        pos.move(pos.x + x, pos.y + y);
        rect.setLocation(pos.x + x, pos.y + y);
    }

    public void setHeldItem(Item item) {
        this.heldItem = item;
    }
}