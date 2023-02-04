package com.monique.sectgeon.level.items;

import java.awt.Color;
import java.awt.Graphics;

public class Sword extends Tool {

    public Sword() {
        super("sword", 8, 15);
    }

    @Override
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 20, 20);
    }
}