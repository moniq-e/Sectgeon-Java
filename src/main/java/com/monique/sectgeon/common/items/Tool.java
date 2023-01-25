package com.monique.sectgeon.common.items;

import com.monique.sectgeon.common.Util;

public interface Tool {
    public abstract int getDice();
    public default int rollDice() {
        return Util.d(getDice());
    };
}