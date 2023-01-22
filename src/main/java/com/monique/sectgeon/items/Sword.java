package com.monique.sectgeon.items;

import java.awt.Color;
import java.awt.Graphics;

public class Sword extends Item implements Tool {
    private int dice = 8;

    public Sword() {
        super("sword");
    }

    @Override
    public int getDice() {
        return dice;
    }

    @Override
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 20, 20);
    }
}