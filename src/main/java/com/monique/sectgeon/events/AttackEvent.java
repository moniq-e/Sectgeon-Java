package com.monique.sectgeon.events;

public class AttackEvent<T> extends CustomEvent<T> {
    private T target;
    private int damage;

    public AttackEvent(T source, T target, int damage) {
        super(Triggers.Attack, source);
        this.target = target;
        this.damage = damage;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}