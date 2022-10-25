package com.monique.txtrpg.events;

import java.util.UUID;

public abstract class CustomEvent {
    protected final String ID = UUID.randomUUID().toString();
}