package com.monique.sectgeon.events;

public class HealEvent<T> extends CustomEvent<T> {
    private int value;

    public HealEvent(T source, int value) {
        super(Triggers.HEAL, source);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}