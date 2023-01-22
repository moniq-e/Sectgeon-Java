package com.monique.sectgeon.events;

public class DeathEvent<T> extends CustomEvent<T> {

    DeathEvent(T source) {
        super(Triggers.DEATH, source);
    }
}