package com.monique.txtrpg.entities;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.util.function.Consumer;

import com.monique.txtrpg.*;
import com.monique.txtrpg.dungeons.Dungeon;

public abstract class Mob extends Entity {
    JPopupMenu popup = new JPopupMenu();
    ArrayList<JMenuItem> items = new ArrayList<JMenuItem>();

    Mob(Dungeon dungeon, String type, int maxlife, int walkdistance, int width, int height) {
        super(dungeon, type, maxlife, walkdistance, width, height);

        popup.setName(type.substring(0, 1).toUpperCase() + type.substring(1));
        setPopup(this);
        setPopupItems();
        setPopupConsumer(this);
    }

    protected abstract void setPopup(Mob mob);

    private void setPopupItems() {
        for (JMenuItem item : items) {
            popup.add(item);
        }
    }

    private void setPopupConsumer(Mob mob) {
        dungeon.frame.listener.addMouseClickedConsumer(new Consumer<MouseEvent>() {
            @Override
            public void accept(MouseEvent e) {
                if (Util.collides(mob.getRect(), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                    popup.show(dungeon, e.getX(), e.getY());
                } else {
                    popup.setVisible(false);
                }
            }
        });
    }
}