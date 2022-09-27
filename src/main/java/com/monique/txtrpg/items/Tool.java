package com.monique.txtrpg.items;

import java.lang.reflect.Method;

public class Tool extends Item {
    public Method dice;
    Tool(String name, Method dice) {
        super(name);
        this.dice = dice;
    }
}