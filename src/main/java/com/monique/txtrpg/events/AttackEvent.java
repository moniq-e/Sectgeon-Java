package com.monique.txtrpg.events;

import com.monique.txtrpg.entities.Entity;

public class AttackEvent extends CustomEvent {
    private Entity targetEntity;
    private float damage;

    public AttackEvent(Entity sourceEntity, Entity targetEntity, float damage) {
        super(EventType.ATTACK);
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
        this.damage = damage;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public float getDamage() {
        return damage;
    }
}