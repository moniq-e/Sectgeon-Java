package com.monique.txtrpg.items;

import com.monique.txtrpg.Util;

public interface Tool {
    public abstract int getDice();
    public default int rollDice() {
        return Util.d(getDice());
    };
}