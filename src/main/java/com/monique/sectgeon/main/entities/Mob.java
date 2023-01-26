package com.monique.sectgeon.main.entities;

import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.listeners.DefaultEvents;
import com.monique.sectgeon.main.dungeons.Dungeon;

import java.awt.Rectangle;

public abstract class Mob extends Entity implements LivingEntity {
    JPopupMenu popup = new JPopupMenu();

    Mob(Dungeon dungeon, String type, int maxlife, int walkdistance, int width, int height) {
        super(dungeon, type, maxlife, walkdistance, width, height);

        dungeon.livingEntities.add(this);
        dungeon.drawables.add(this);
        popup.setName(type.substring(0, 1).toUpperCase() + type.substring(1));
        setPopupItems();
        setPopupConsumer();
    }

    protected abstract void setPopupItems();

    private void setPopupConsumer() {
        Frame.listener.addListener(DefaultEvents.Mouse, ID, note -> {
            MouseEvent e = (MouseEvent) note;
            if (Util.collides(this.getRect(), new Rectangle(e.getX(), e.getY(), 1, 1))) {
                popup.show(dungeon, e.getX(), e.getY());
            }
        });
    }

    @Override
    public void kill() {
        dungeon.livingEntities.remove(this);
        dungeon.drawables.remove(this);
        Frame.listener.removeListener(DefaultEvents.Mouse, ID);
        dungeon.remove(popup);
    }
}