package com.monique.sectgeon.common.events.lair;

import com.monique.sectgeon.common.events.CustomEvent;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.lair.cards.Card;

public class TurnStart extends CustomEvent<Card> {
    private int turn;

    public TurnStart(int turn) {
        super(Triggers.TurnStart, null);
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }
}