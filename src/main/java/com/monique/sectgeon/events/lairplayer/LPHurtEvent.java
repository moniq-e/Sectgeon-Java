package com.monique.sectgeon.events.lairplayer;

import com.monique.sectgeon.events.*;
import com.monique.sectgeon.lair.LPlayer;
import com.monique.sectgeon.lair.cards.Card;

public class LPHurtEvent extends CustomEvent<Card> {
    private LPlayer target;
    private int damage;

    public LPHurtEvent(Card source, LPlayer target, int damage) {
        super(Triggers.PlayerHurt, source);
        this.target = target;
        this.damage = damage;
    }

    public LPlayer getTarget() {
        return target;
    }

    public void setTarget(LPlayer target) {
        this.target = target;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}