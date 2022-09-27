package com.monique.txtrpg;

import java.awt.Point;

import com.monique.txtrpg.entities.Entity;

public class Util {
    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static boolean collides(Entity ent1, Point ent2) {
        boolean colliding = false;
        if (distance(ent1.pos, ent2) < ent1.width / 2) colliding = true;
        return colliding;
    }

    public static double random(float min, float max) {
        return (Math.random() * max) + min;
    }

    public static int random(int min, int max) {
        return (int) (Math.random() * max) + min;
    }

    public static int d4() {
        return random(0, 4);
    }

    public static int d6() {
        return random(0, 6);
    }

    public static int d8() {
        return random(0, 8);
    }

    public static int d10() {
        return random(0, 10);
    }

    public static int d12() {
        return random(0, 12);
    }

    public static int d20() {
        return random(0, 20);
    }

    public static int d100() {
        return random(0, 100);
    }
}