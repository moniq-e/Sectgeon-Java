package com.monique.sectgeon.common;

import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Util {
    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    public static boolean collides(Rectangle ent1, Rectangle ent2) {
        return ent1.intersects(ent2);
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

    public static BufferedImage getImage(String location) {
        try {
            return ImageIO.read(new File("./src/main/resources/" + location));
        } catch (IOException e) {
            return null;
        }
    }
}