package com.monique.txtrpg.items;

public class Item {
    public String name;
    public String type;
    public int dice;
    
    Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    Item(String name, String type, int dice) {
        this.name = name;
        this.type = type;
        this.dice = dice;
    }
}