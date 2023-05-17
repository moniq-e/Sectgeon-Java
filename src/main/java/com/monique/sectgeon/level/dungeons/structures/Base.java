package com.monique.sectgeon.level.dungeons.structures;

import java.awt.Rectangle;
import java.awt.Point;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.level.dungeons.Dungeon;

public abstract class Base {
    public Dungeon dungeon;
    private Rectangle rect = new Rectangle();

    public Base(Dungeon dungeon, int x, int y, int width, int height) {
        this.dungeon = dungeon;
        rect.setBounds(x, y, width, height);
    }

    public Base(Dungeon dungeon, int width, int height) {
        this(dungeon, 0, 0, width, height);
    }

    public boolean playerColliding() {
        return Util.collides(dungeon.player.getRect(), getRect());
    }

    public Rectangle getRect() {
        return rect;
    }

    public Point getPos() {
        return rect.getLocation();
    }

    public void setPos(int x, int y) {
        rect.setLocation(x, y);
    }

    public void setPos(Point position) {
        rect.setLocation(position);
    }
}