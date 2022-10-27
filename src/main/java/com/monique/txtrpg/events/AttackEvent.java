package com.monique.txtrpg.events;

import com.monique.txtrpg.entities.Entity;

public class AttackEvent extends CustomEvent {

    /**
     * @fields float damage;
     */
    public AttackEvent(Entity sourceEntity, Entity targetEntity, float damage) {
        super("attack");
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
        setFloatAttribute("damage", damage);
    }
}