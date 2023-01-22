package com.monique.sectgeon.events;

public class HealEvent<T> extends CustomEvent<T> {
    private T target;
    private int value;

    /**
     * @param source who attacked
     * @param target who suffered the attack
     */
    public HealEvent(T source, T target, int value) {
        super(Triggers.Heal, source);
        this.target = target;
        this.value = value;
    }

    public T getTarget() {
        return target;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}