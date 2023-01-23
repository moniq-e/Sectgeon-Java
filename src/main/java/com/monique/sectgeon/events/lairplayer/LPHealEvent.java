package com.monique.sectgeon.events.lairplayer;

import com.monique.sectgeon.events.*;
import com.monique.sectgeon.lair.LPlayer;
import com.monique.sectgeon.lair.cards.Card;

public class LPHealEvent extends CustomEvent<Card> {
    private LPlayer target;
    private int value;

    public LPHealEvent(Card source, LPlayer target, int value) {
        super(Triggers.PlayerHeal, source);
        this.target = target;
        this.value = value;
    }

    public LPlayer getTarget() {
        return target;
    }

    public void setTarget(LPlayer player) {
        this.target = player;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}