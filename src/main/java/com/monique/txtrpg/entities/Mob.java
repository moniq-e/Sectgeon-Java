package com.monique.txtrpg.entities;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.awt.Rectangle;

import com.monique.txtrpg.*;
import com.monique.txtrpg.dungeons.Dungeon;

public abstract class Mob extends Entity {
    JPopupMenu popup = new JPopupMenu();
    ArrayList<JMenuItem> items = new ArrayList<JMenuItem>();

    Mob(Dungeon dungeon, String type, int maxlife, int walkdistance, int width, int height) {
        super(dungeon, type, maxlife, walkdistance, width, height);

        popup.setName(type.substring(0, 1).toUpperCase() + type.substring(1));
        setPopupItems();
        setItemsInPopup();
        setPopupConsumer();
    }

    protected abstract void setPopupItems();

    private void setItemsInPopup() {
        for (JMenuItem item : items) {
            popup.add(item);
        }
    }

    private void setPopupConsumer() {
        dungeon.frame.listener.addMouseClickedConsumer(e -> {
            if (Util.collides(this.getRect(), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                popup.show(dungeon, e.getX(), e.getY());
            }
        });
    }
}