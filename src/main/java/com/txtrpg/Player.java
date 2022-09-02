package com.txtrpg;

public class Player extends Entity {
    public String name;

    Player(String name) {
        super("player");
        this.name = name;
    }
}