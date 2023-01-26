package com.monique.sectgeon.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.common.listeners.DefaultEvents;
import com.monique.sectgeon.main.dungeons.Dungeon;
import com.monique.sectgeon.main.items.*;

import java.awt.Point;

public class Player extends Entity {
    public ArrayList<Item> inventory = new ArrayList<Item>(9);
    private Point initialPos;

    public Player(Dungeon dungeon, String name) {
        super(dungeon, "player", name, 20, 5, 50, 50);

        Frame.listener.addListener(DefaultEvents.Key, ID, this::move);
        inventory.add(new Sword());
        inventory.add(new Bow());
        setHeldItem(inventory.get(0));
        initialPos = new Point(dungeon.getWidth() / 2, dungeon.getHeight() - HEIGHT);
        setPos(initialPos);
    }

    public void move(Object note) {
        KeyEvent e = (KeyEvent) note;
        switch (e.getKeyChar()) {
            case 'w':
                if (getPos().y <= 0) return;
                setPos(getPos().x, getPos().y -= WALKDISTANCE);
                break;
            case 's':
                if (getPos().y >= dungeon.getHeight() - HEIGHT) return;
                setPos(getPos().x, getPos().y += WALKDISTANCE);
                break;
            case 'a':
                if (getPos().x <= 0) return;
                setPos(getPos().x -= WALKDISTANCE, getPos().y);
                break;
            case 'd':
                if (getPos().x >= dungeon.getWidth() - WIDTH) return;
                setPos(getPos().x += WALKDISTANCE, getPos().y);
                break;
            default:
                System.out.println(e.getKeyChar());
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getPos().x, getPos().y, WIDTH, HEIGHT);
        getHeldItem().display(g, getPos().x, getPos().y);
    }

    @Override
    public void kill() {
        dungeon.drawables.remove(this);
        dungeon.finish(false);
    }
}