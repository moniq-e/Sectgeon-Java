package com.monique.txtrpg.events;

import com.monique.txtrpg.entities.Entity;

public class SummonEvent extends CustomEvent {

    SummonEvent(Entity sourceEntity) {
        super(EventType.SUMMON);
        this.sourceEntity = sourceEntity;
    }
}