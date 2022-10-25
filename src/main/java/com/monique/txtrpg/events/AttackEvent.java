package com.monique.txtrpg.events;

import com.monique.txtrpg.entities.Entity;

public class AttackEvent extends CustomEvent {
    private Entity sourceEntity;
    private Entity targetEntity;
    private int damage;

    public AttackEvent(Entity sourceEntity, Entity targetEntity, int damage) {
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
        this.damage = damage;
    }

    public Entity getSourceEntity() {
        return sourceEntity;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public int getDamage() {
        return damage;
    }
}