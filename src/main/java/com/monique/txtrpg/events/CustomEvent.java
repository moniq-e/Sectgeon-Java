package com.monique.txtrpg.events;

import java.util.UUID;

import com.monique.txtrpg.entities.Entity;

public abstract class CustomEvent {
    public final String ID = UUID.randomUUID().toString();
    public final EventType TYPE;
    protected Entity sourceEntity;

    CustomEvent(EventType type) {
        TYPE = type;
    }

    public Entity getSourceEntity() {
        return sourceEntity;
    }
}