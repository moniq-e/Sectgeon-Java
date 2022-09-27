package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Point;

import com.monique.txtrpg.*;
import com.monique.txtrpg.items.*;

public class Player extends Entity {
    public boolean canMove;
    public Point lastClick = new Point();
    public ArrayList<Item> inventory = new ArrayList<Item>(9);

    public Player(Board board, String name) {
        super(board, "player", name, 20, 10, 50, 50);
        this.canMove = false;
        
        inventory.add(new Sword());
        pos.move(board.width / 2, board.height - height);
    }

    public void move(KeyEvent e) {
        if (!canMove) return;
        switch (e.getKeyChar()) {
            case 'w':
                pos.move(pos.x, pos.y -= walkDistance);
                break;
            case 's':
                pos.move(pos.x, pos.y += walkDistance);
                break;
            case 'a':
                pos.move(pos.x -= walkDistance, pos.y);
                break;
            case 'd':
                pos.move(pos.x += walkDistance, pos.y);
                break;
            default:
                System.out.println(e.getKeyChar());
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.decode("#2ebee6"));
        g.fillRect(pos.x, pos.y, width, height);
    }
}