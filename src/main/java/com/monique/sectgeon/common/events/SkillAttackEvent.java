package com.monique.sectgeon.common.events;

import com.monique.sectgeon.lair.Player;

public class SkillAttackEvent<T> extends CustomEvent<T> {
    private T target;
    private Player player;
    private int damage;

    public SkillAttackEvent(T source, T target, int damage) {
        super(Triggers.SkillAttack, source);
        this.target = target;
        this.damage = damage;
    }

    public SkillAttackEvent(T source, Player player, int damage) {
        super(Triggers.SkillAttack, source);
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