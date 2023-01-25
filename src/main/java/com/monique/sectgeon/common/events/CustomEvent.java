package com.monique.sectgeon.common.events;

import java.util.UUID;

public abstract class CustomEvent<T> {
    protected final T source;
    private UUID skillID;
    public final Triggers TYPE;

    public CustomEvent(Triggers type, T source) {
        this.source = source;
        TYPE = type;
    }

    public T getSource() {
        return source;
    }

    public UUID getSkillID() {
        return skillID;
    }

    public CustomEvent<T> setSkillID(UUID skillID) {
        this.skillID = skillID;
        return this;
    }
}