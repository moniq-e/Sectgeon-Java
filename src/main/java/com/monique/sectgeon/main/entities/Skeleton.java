package com.monique.sectgeon.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JMenuItem;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.main.items.*;
import com.monique.sectgeon.main.dungeons.*;

public class Skeleton extends Mob {

    /**
     * Create a new Skeleton and adds it to the dungeon.board.entities
     * @param dungeon
     */
    public Skeleton(Dungeon dungeon) {
        super(dungeon, "skeleton", 10, 3, 35, 35);

        inventory.add(new Sword());
        setHeldItem(inventory.get(0));
        setPos(Util.random(0, dungeon.getWidth() - WIDTH), Util.random(0, dungeon.getHeight() - HEIGHT));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(getPos().x, getPos().y - HEIGHT, WIDTH, HEIGHT);
    }

    @Override
    public void ai() {
        followPlayer();
        if (playerColliding()) {
            tryAttack(dungeon.player);
        }
    }

    @Override
    protected void setPopupItems() {
        JMenuItem attack = new JMenuItem("Attack");
        attack.addActionListener(e -> {
            dungeon.player.tryAttack(this);
        });
        popup.add(attack);
    }
}