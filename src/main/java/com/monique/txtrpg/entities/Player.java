package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Rectangle;

import com.monique.txtrpg.*;
import com.monique.txtrpg.items.*;

public class Player extends Entity {
    public boolean canMove;
    public Rectangle lastClick = new Rectangle(0, 0, 1, 1);
    public ArrayList<Item> inventory = new ArrayList<Item>(9);

    public Player(Board board, String name) {
        super(board, "player", name, 20, 10, 50, 50);
        this.canMove = false;
        
        inventory.add(new Sword());
        setPos(board.width / 2, board.height - height);
    }

    public void move(KeyEvent e) {
        if (!canMove) return;
        switch (e.getKeyChar()) {
            case 'w':
                if (getPos().y <= 0) return;
                setPos(getPos().x, getPos().y -= walkDistance);
                break;
            case 's':
                if (getPos().y >= board.height - height) return;
                setPos(getPos().x, getPos().y += walkDistance);
                break;
            case 'a':
                if (getPos().x <= 0) return;
                setPos(getPos().x -= walkDistance, getPos().y);
                break;
            case 'd':
                if (getPos().x >= board.width - width) return;
                setPos(getPos().x += walkDistance, getPos().y);
                break;
            default:
                System.out.println(e.getKeyChar());
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.decode("#2ebee6"));
        g.fillRect(getPos().x, getPos().y, width, height);
    }

    @Override
    public void ai() { }
}