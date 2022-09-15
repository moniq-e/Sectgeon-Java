package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.monique.txtrpg.Board;

public class Player extends Entity {
    public String name;

    public Player(Board board, String name) {
        super(board, "player", name, 20, 10, 50, 50);
        pos.move(board.width / 2, board.height - height);
    }

    public void move(KeyEvent e) {
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