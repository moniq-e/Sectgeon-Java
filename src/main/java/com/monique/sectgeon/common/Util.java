package com.monique.sectgeon.common;

import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;
import javax.imageio.ImageIO;

import com.monique.sectgeon.lair.cards.*;

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
        return (int) (Math.random() * (max + 1)) + min;
    }

    public static <T> Object randomElement(ArrayList<T> array) {
        return array.get(random(0, array.size() - 1));
    }

    public static void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            var r = random(0, array.length - 1);
            var temp = array[r];
            array[r] = array[i];
            array[i] = temp;
        }
    }

    public static int d(int dice) {
        return random(1, dice);
    }

    public static Predicate<Card> onlyOneType(CardTypes type) {
        return c -> c.TYPE != type;
    }

    public static BufferedImage getImage(String location) {
        try {
            return ImageIO.read(Util.class.getResourceAsStream("/assets/" + location));
        } catch (IOException e) {
            return null;
        }
    }

    public static Rectangle getMouseRect() {
        Point pos = Frame.board.getMousePosition();
        if (pos != null) return new Rectangle(pos.x, pos.y, 1, 1);
        else return new Rectangle();
    }
}