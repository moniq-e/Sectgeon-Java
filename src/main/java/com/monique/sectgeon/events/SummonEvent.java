package com.monique.sectgeon.events;

public class SummonEvent<T> extends CustomEvent<T> {

    SummonEvent(T source) {
        super(Triggers.SUMMON);
        this.source = source;
    }
}