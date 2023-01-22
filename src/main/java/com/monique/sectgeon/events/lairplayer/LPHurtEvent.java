package com.monique.sectgeon.events.lairplayer;

import com.monique.sectgeon.events.*;
import com.monique.sectgeon.lair.LairPlayer;
import com.monique.sectgeon.lair.cards.Card;

public class LPHurtEvent extends CustomEvent<Card> {
    private LairPlayer target;
    private int damage;

    public LPHurtEvent(Card source, LairPlayer target, int damage) {
        super(Triggers.PlayerHurt, source);
        this.target = target;
        this.damage = damage;
    }

    public LairPlayer getTarget() {
        return target;
    }

    public void setTarget(LairPlayer target) {
        this.target = target;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}