package com.monique.sectgeon.level.gui;

import java.awt.Graphics;

import com.monique.sectgeon.common.gui.Button;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.level.dungeons.Dungeon;
import com.monique.sectgeon.level.items.Item;

import java.awt.Color;

public class DungeonGUI implements Drawable {
    private Dungeon dungeon;

    public DungeonGUI(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public void draw(Graphics g) {
        displayInventory(g);
    }

    private void displayInventory(Graphics g) {
        int x = 1;
        int y = dungeon.getHeight() - 23;
        for (Item item : dungeon.player.inventory) {

            Button<Dungeon> b = new Button<Dungeon>(dungeon, x, y, 22, 22, e -> {
                dungeon.player.setHeldItem(item);
            });

            b.setBackground(null);
            b.setBorder(null);
            b.setOpaque(false);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, 21, 21);

            item.display(g, x + 1, y + 1);
            x += 21;
        }
    }
}