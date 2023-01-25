package com.monique.sectgeon.common;

import java.awt.Point;
import java.awt.Rectangle;

public class Util {
    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    public static boolean collides(Rectangle ent1, Rectangle ent2) {
        boolean colliding = false;
        if (ent1.intersects(ent2)) colliding = true;
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