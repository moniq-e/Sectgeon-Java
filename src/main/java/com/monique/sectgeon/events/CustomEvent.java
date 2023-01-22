package com.monique.sectgeon.events;

import java.util.UUID;

public abstract class CustomEvent<T> {
    protected final T source;
    public final String ID = UUID.randomUUID().toString();
    public final Triggers TYPE;

    CustomEvent(Triggers type, T source) {
        this.source = source;
        TYPE = type;
    }

    public T getSource() {
        return source;
    }
}