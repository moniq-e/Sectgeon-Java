package com.monique.sectgeon.items;

import java.awt.Color;
import java.awt.Graphics;

public class Bow extends Item implements Tool {
    private int dice = 4;

    public Bow() {
        super("bow");
    }

    @Override
    public int getDice() {
        return dice;
    }

    @Override
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 20, 20);
    }
}