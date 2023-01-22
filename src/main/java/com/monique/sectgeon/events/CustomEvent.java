package com.monique.sectgeon.events;

import java.util.UUID;

public abstract class CustomEvent<T> {
    public final String ID = UUID.randomUUID().toString();
    public final Triggers TYPE;
    protected T source;

    CustomEvent(Triggers type) {
        TYPE = type;
    }

    public T getSource() {
        return source;
    }
}