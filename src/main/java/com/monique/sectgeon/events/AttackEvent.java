package com.monique.sectgeon.events;

public class AttackEvent<T> extends CustomEvent<T> {
    private T target;
    private float damage;

    public AttackEvent(T source, T target, float damage) {
        super(Triggers.ATTACK);
        this.source = source;
        this.target = target;
        this.damage = damage;
    }

    public T getTarget() {
        return target;
    }

    public float getDamage() {
        return damage;
    }
}