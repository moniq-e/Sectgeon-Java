package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.monique.txtrpg.*;

public class Skeleton extends Entity {

    public Skeleton(Board board, String name) {
        super(board, "skeleton", name, 10, 10, 35, 35);

        setPos(Util.random(0, board.width - width), Util.random(0, board.height - height));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(getPos().x, getPos().y, width, height);
    }
}