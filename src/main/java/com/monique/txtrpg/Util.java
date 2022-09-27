package com.monique.txtrpg;

import java.awt.Point;

import com.monique.txtrpg.entities.Entity;

public class Util {
    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    public static boolean collides(Entity ent1, Point ent2) {
        boolean colliding = false;
        if (distance(ent1.pos, ent2) < ent1.width) colliding = true;
        return colliding;
    }

    public static double random(float min, float max) {
        return (Math.random() * max) + min;
    }

    public static int random(int min, int max) {
        return (int) (Math.random() * max) + min;
    }

    public static int d(int dice) {
        return random(1, dice);
    }
}