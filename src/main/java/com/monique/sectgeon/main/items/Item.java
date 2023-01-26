package com.monique.sectgeon.main.items;

import java.awt.Graphics;
import java.util.UUID;

public abstract class Item {
    public final UUID ID = UUID.randomUUID();
    protected String name;

    Item(String name) {
        this.name = name;
    }

    public abstract void display(Graphics g, int x, int y);

    public String getName() {
        return name;
    }
}