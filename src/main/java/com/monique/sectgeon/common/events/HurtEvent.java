package com.monique.sectgeon.common.events;

public class HurtEvent<T> extends CustomEvent<T> {
    private T target;
    private int damage;

    /**
     * @param source who attacked
     * @param target who suffered the attack
     */
    public HurtEvent(T source, T target, int damage) {
        super(Triggers.Hurt, source);
        this.target = target;
        this.damage = damage;
    }

    public T getTarget() {
        return target;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}