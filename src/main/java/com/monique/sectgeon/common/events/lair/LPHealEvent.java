package com.monique.sectgeon.common.events.lair;

import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.lair.Player;
import com.monique.sectgeon.lair.cards.Card;

public class LPHealEvent extends CustomEvent<Card> {
    private Player target;
    private int value;

    public LPHealEvent(Card source, Player target, int value) {
        super(Triggers.PlayerHeal, source);
        this.target = target;
        this.value = value;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player player) {
        this.target = player;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}