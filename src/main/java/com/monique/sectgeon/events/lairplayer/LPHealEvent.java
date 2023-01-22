package com.monique.sectgeon.events.lairplayer;

import com.monique.sectgeon.events.*;
import com.monique.sectgeon.lair.LairPlayer;
import com.monique.sectgeon.lair.cards.Card;

public class LPHealEvent extends CustomEvent<Card> {
    private LairPlayer target;
    private int value;

    public LPHealEvent(Card source, LairPlayer target, int value) {
        super(Triggers.PlayerHeal, source);
        this.target = target;
        this.value = value;
    }

    public LairPlayer getTarget() {
        return target;
    }

    public void setTarget(LairPlayer player) {
        this.target = player;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}