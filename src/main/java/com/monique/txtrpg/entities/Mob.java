package com.monique.txtrpg.entities;

import javax.swing.JPopupMenu;
import java.awt.Rectangle;

import com.monique.txtrpg.*;
import com.monique.txtrpg.dungeons.Dungeon;

public abstract class Mob extends Entity {
    JPopupMenu popup = new JPopupMenu();

    Mob(Dungeon dungeon, String type, int maxlife, int walkdistance, int width, int height) {
        super(dungeon, type, maxlife, walkdistance, width, height);

        popup.setName(type.substring(0, 1).toUpperCase() + type.substring(1));
        setPopupItems();
        setPopupConsumer();
    }

    protected abstract void setPopupItems();

    private void setPopupConsumer() {
        dungeon.frame.listener.addMouseClickedConsumer(ID, e -> {
            if (Util.collides(this.getRect(), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                popup.show(dungeon, e.getX(), e.getY());
            }
        });
    }

    @Override
    public void kill() {
        dungeon.entities.remove(this);
        dungeon.drawables.remove(this);
        dungeon.frame.listener.removeMouseClickedConsumer(ID);
    }
}