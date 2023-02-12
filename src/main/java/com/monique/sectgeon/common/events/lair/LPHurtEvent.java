package com.monique.sectgeon.common.events.lair;

import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.lair.Player;
import com.monique.sectgeon.lair.cards.Card;

public class LPHurtEvent extends CustomEvent<Card> {
    private Player target;
    private int damage;

    public LPHurtEvent(Card source, Player target, int damage) {
        super(Triggers.PlayerHurt, source);
        this.target = target;
        this.damage = damage;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}