package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.monique.txtrpg.*;
import com.monique.txtrpg.items.*;

public class Skeleton extends Entity {

    /**
     * Create a new Skeleton and adds it to the dungeon.board.entities
     * @param board
     */
    public Skeleton(Board board) {
        super(board, "skeleton", 10, 100, 35, 35);

        board.entities.add(this);
        inventory.add(new Sword());
        setPos(Util.random(0, board.janela.width - width), Util.random(0, board.janela.height - height));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(getPos().x, getPos().y - height, width, height);
    }

    @Override
    public void ai() {
        followPlayer();
        if (playerColliding()) {
            attack(board.player, inventory.get(0));
        }
    }
}