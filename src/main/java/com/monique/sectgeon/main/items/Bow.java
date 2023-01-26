package com.monique.sectgeon.main.items;

import java.awt.Color;
import java.awt.Graphics;

public class Bow extends Tool {

    public Bow() {
        super("bow", 6, 20);
    }

    @Override
    public void display(Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 20, 20);
    }
}