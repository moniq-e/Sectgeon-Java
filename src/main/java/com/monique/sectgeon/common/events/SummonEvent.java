package com.monique.sectgeon.common.events;

public class SummonEvent<T> extends CustomEvent<T> {
    private T summoned;
    private int pos;

    public SummonEvent(T source, T summoned, int pos) {
        super(Triggers.Summon, source);
        this.summoned = summoned;
        this.pos = pos;
    }

    public T getSummoned() {
        return summoned;
    }

    public void setSummoned(T summoned) {
        this.summoned = summoned;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}