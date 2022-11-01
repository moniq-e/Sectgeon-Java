package com.monique.txtrpg.gui;

import java.awt.Graphics;

import com.monique.txtrpg.dungeons.Dungeon;
import com.monique.txtrpg.entities.Drawable;
import com.monique.txtrpg.items.Item;

public class DungeonHUD implements Drawable{
    private Dungeon dungeon;

    public DungeonHUD(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public void draw(Graphics g) {
        displayInventory(g);
    }

    private void displayInventory(Graphics g) {
        int x = 0;
        int y = dungeon.getHeight() - 20;
        for (Item item : dungeon.player.inventory) {
            item.display(g, x, y);
            x += 20;
        }
    }
}