package com.monique.txtrpg.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.monique.txtrpg.*;
import com.monique.txtrpg.dungeons.*;
import com.monique.txtrpg.items.*;

public class Skeleton extends Entity {

    /**
     * Create a new Skeleton and adds it to the dungeon.board.entities
     * @param dungeon
     */
    public Skeleton(Dungeon dungeon) {
        super(dungeon, "skeleton", 10, 100, 35, 35);

        dungeon.entities.add(this);
        inventory.add(new Sword());
        setPos(Util.random(0, dungeon.frame.width - width), Util.random(0, dungeon.frame.height - height));
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