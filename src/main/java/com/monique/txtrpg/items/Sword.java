package com.monique.txtrpg.items;

import com.monique.txtrpg.*;

public class Sword extends Tool {
    public Sword() throws NoSuchMethodException {
        super("sword", Util.class.getDeclaredMethod("d6"));
    }
}