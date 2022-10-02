package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.monique.txtrpg.*;
import com.monique.txtrpg.items.*;

public class Skeleton extends Entity {

    public Skeleton(Board board) {
        super(board, "skeleton", 10, 100, 35, 35);

        inventory.add(new Sword());
        setPos(Util.random(0, board.width - width), Util.random(0, board.height - height));
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