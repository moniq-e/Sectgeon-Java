package com.monique.sectgeon.items;

import com.monique.sectgeon.Util;

public interface Tool {
    public abstract int getDice();
    public default int rollDice() {
        return Util.d(getDice());
    };
}