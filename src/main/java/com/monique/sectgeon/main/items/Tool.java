package com.monique.sectgeon.main.items;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.listeners.Events;

public abstract class Tool extends Item {
    private int dice;
    private int speed;
    private int ticks;

    Tool(String name, int dice, int speed) {
        super(name);
        this.dice = dice;
        this.speed = speed;
        Frame.listener.addListener(Events.Action, ID, e -> {
            ticks++;
        });
    }

    public int rollDice() {
        return Util.d(getDice());
    }

    public int getDice() {
        return dice;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTicks() {
        return ticks;
    }

    public void resetTicks() {
        ticks = 0;
    }
}