package com.monique.sectgeon.common.events;

public class DeathEvent<T> extends CustomEvent<T> {

    public DeathEvent(T source) {
        super(Triggers.Death, source);
    }
}