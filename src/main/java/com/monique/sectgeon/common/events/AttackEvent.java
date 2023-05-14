package com.monique.sectgeon.common.events;

import com.monique.sectgeon.lair.Player;

public class AttackEvent<T> extends CustomEvent<T> {
    private T target;
    private Player player;
    private int damage;

    public AttackEvent(T source, T target, int damage) {
        super(Triggers.Attack, source);
        this.target = target;
        this.damage = damage;
    }

    public AttackEvent(T source, Player player, int damage) {
        super(Triggers.Attack, source);
        this.player = player;
        this.damage = damage;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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