package com.monique.sectgeon.level.entities.monsters;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JMenuItem;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.level.dungeons.*;
import com.monique.sectgeon.level.items.*;

public class Skeleton extends Mob {

    /**
     * Create a new Skeleton and adds it to the dungeon.board.entities
     * @param dungeon
     */
    public Skeleton(Dungeon dungeon) {
        super(dungeon, "skeleton", 10, 3, 32, 32);

        inventory.add(new Bow());
        setHeldItem(inventory.get(0));
        setPos(Util.random(0, dungeon.getWidth() - WIDTH), Util.random(0, dungeon.getHeight() - HEIGHT));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(getPos().x, getPos().y, WIDTH, HEIGHT);
        getHeldItem().display(g, getPos().x, getPos().y);
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