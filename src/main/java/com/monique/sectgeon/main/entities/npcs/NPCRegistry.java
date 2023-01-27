package com.monique.sectgeon.main.entities.npcs;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPopupMenu;

public class NPCRegistry {
    public final String NAME;
    public final int WIDTH;
    public final int HEIGHT;
    protected final JPopupMenu POP = new JPopupMenu();
    private Point pos = new Point();
    private Rectangle rect;

    public NPCRegistry(String name, int width, int height) {
        NAME = name;
        WIDTH = width;
        HEIGHT = height;
        rect = new Rectangle(0, 0, width, height);

        POP.setName(name.substring(0, 1).toUpperCase() + name.substring(1));
    }

    public Rectangle getRect() {
        return rect;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        pos.move(x, y);
        rect.setLocation(x, y);
    }

    public void setPos(Point position) {
        pos.setLocation(position);
        rect.setLocation(position);
    }
}