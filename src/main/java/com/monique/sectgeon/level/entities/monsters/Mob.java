package com.monique.sectgeon.level.entities.monsters;

import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.level.dungeons.Dungeon;
import com.monique.sectgeon.level.entities.Entity;
import com.monique.sectgeon.level.entities.LivingEntity;

import java.awt.Rectangle;

public abstract class Mob extends Entity implements LivingEntity {
    protected JPopupMenu popup = new JPopupMenu();

    Mob(Dungeon dungeon, String type, int maxlife, int walkdistance, int width, int height) {
        super(dungeon, type, maxlife, walkdistance, width, height);

        dungeon.livingEntities.add(this);
        dungeon.drawables.add(this);
        popup.setName(type.substring(0, 1).toUpperCase() + type.substring(1));
        setPopupItems();
        setPopupConsumer();
    }

    protected abstract void setPopupItems();

    protected void setPopupConsumer() {
        Frame.listener.addListener(Events.Mouse, ID, note -> {
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
        Frame.listener.removeListener(Events.Mouse, ID);
        dungeon.remove(popup);
    }
}