package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.monique.txtrpg.dungeons.Dungeon;
import com.monique.txtrpg.items.*;

public class Player extends Entity {
    public boolean canMove = true;
    public ArrayList<Item> inventory = new ArrayList<Item>(9);

    public Player(Dungeon dungeon, String name) {
        super(dungeon, "player", name, 20, 10, 50, 50);
        
        dungeon.frame.listener.addKeyPressedConsumer(ID, this::move);
        inventory.add(new Sword());
        setPos(dungeon.frame.WIDTH / 2, dungeon.frame.HEIGHT - HEIGHT);
    }

    public void move(KeyEvent e) {
        if (!canMove) return;
        switch (e.getKeyChar()) {
            case 'w':
                if (getPos().y <= 0) return;
                setPos(getPos().x, getPos().y -= WALKDISTANCE);
                break;
            case 's':
                if (getPos().y >= dungeon.frame.HEIGHT - HEIGHT) return;
                setPos(getPos().x, getPos().y += WALKDISTANCE);
                break;
            case 'a':
                if (getPos().x <= 0) return;
                setPos(getPos().x -= WALKDISTANCE, getPos().y);
                break;
            case 'd':
                if (getPos().x >= dungeon.frame.WIDTH - WIDTH) return;
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
    }

    @Override
    public void kill() {
        dungeon.drawables.remove(this);
        dungeon.finish(false);
    }
}