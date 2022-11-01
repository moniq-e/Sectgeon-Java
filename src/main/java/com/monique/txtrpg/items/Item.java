package com.monique.txtrpg.items;

import java.awt.Graphics;

public abstract class Item {
    protected String name;

    Item(String name) {
        this.name = name;
    }

    public abstract void display(Graphics g, int x, int y);

    public String getName() {
        return name;
    }
}