package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Point;

import com.monique.txtrpg.dungeons.Dungeon;
import com.monique.txtrpg.items.*;

public class Player extends Entity {
    private boolean canMove = true;
    public ArrayList<Item> inventory = new ArrayList<Item>(9);
    private Point initialPos;

    public Player(Dungeon dungeon, String name) {
        super(dungeon, "player", name, 20, 10, 50, 50);
        
        dungeon.frame.listener.addKeyPressedConsumer(ID, this::move);
        inventory.add(new Sword());
        setHeldItem(inventory.get(0));
        initialPos = new Point(dungeon.getWidth() / 2, dungeon.getHeight() - HEIGHT);
        setPos(initialPos);
    }

    public void move(KeyEvent e) {
        if (!canMove) return;
        Point newPos;
        switch (e.getKeyChar()) {
            case 'w':
                if (getPos().y <= 0) return;
                newPos = new Point(getPos().x, getPos().y -= WALKDISTANCE);
                if (canWalk(initialPos.y, newPos.y)) setPos(newPos);
                break;
            case 's':
                if (getPos().y >= dungeon.getHeight() - HEIGHT) return;
                newPos = new Point(getPos().x, getPos().y += WALKDISTANCE);
                if (canWalk(initialPos.y, newPos.y)) setPos(newPos);
                break;
            case 'a':
                if (getPos().x <= 0) return;
                newPos = new Point(getPos().x -= WALKDISTANCE, getPos().y);
                if (canWalk(initialPos.x, newPos.x)) setPos(newPos);
                break;
            case 'd':
                if (getPos().x >= dungeon.getWidth() - WIDTH) return;
                newPos = new Point(getPos().x += WALKDISTANCE, getPos().y);
                if (canWalk(initialPos.x, newPos.x)) setPos(newPos);
                break;
            default:
                System.out.println(e.getKeyChar());
                break;
        }
    }

    private boolean canWalk(int initialPos, int newPos) {
        return (Math.abs(initialPos - newPos) <= WALKDISTANCE * 15) ? true : false;
    }

    public boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
        initialPos = getPos();
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