package com.monique.sectgeon.common.events;

public class DeathEvent<T> extends CustomEvent<T> {
    private T killer;

    /**
     * @param source who died
     * @param killer who killed
     */
    public DeathEvent(T source, T killer) {
        super(Triggers.Death, source);
        this.killer = killer;
    }

    public T getKiller() {
        return killer;
    }

    public void setKiller(T k) {
        killer = k;
    }
}