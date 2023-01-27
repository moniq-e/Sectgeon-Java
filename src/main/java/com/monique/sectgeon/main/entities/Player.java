package com.monique.sectgeon.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.main.dungeons.Dungeon;
import com.monique.sectgeon.main.items.*;

public class Player extends Entity {
    public ArrayList<Item> inventory = new ArrayList<Item>(9);
    private Point initialPos;

    public Player(Dungeon dungeon, String name) {
        super(dungeon, "player", name, 200, 5, 32, 32);

        Frame.listener.addListener(Events.Key, ID, this::move);
        inventory.add(new Sword());
        inventory.add(new Bow());
        setHeldItem(inventory.get(0));
        initialPos = new Point(dungeon.getWidth() / 2, dungeon.getHeight() - HEIGHT);
        setPos(initialPos);
    }

    public void move(Object e) {
        @SuppressWarnings("unchecked")
        HashSet<Character> keys = (HashSet<Character>) e;
        for (char key : keys) {
            switch (key) {
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
                    System.out.println(key);
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(getPos().x, getPos().y - HEIGHT, WIDTH, HEIGHT);
        getHeldItem().display(g, getPos().x, getPos().y);
    }

    @Override
    public void kill() {
        dungeon.drawables.remove(this);
        dungeon.finish(false);
    }
}