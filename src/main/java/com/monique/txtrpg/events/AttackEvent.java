package com.monique.txtrpg.events;

import com.monique.txtrpg.entities.Entity;

public class AttackEvent extends CustomEvent {
    protected Entity targetEntity;

    public AttackEvent(Entity sourceEntity, Entity targetEntity, float damage) {
        super(EventType.ATTACK);
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }
}