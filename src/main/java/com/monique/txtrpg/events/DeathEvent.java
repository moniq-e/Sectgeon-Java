package com.monique.txtrpg.events;

import com.monique.txtrpg.entities.Entity;

public class DeathEvent extends CustomEvent {

    DeathEvent(Entity sourceEntity) {
        super(EventType.DEATH);
        this.sourceEntity = sourceEntity;
    }
}