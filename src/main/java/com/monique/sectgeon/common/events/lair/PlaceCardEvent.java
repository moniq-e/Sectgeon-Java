package com.monique.sectgeon.common.events.lair;

import com.monique.sectgeon.common.events.CustomEvent;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.lair.cards.Card;

public class PlaceCardEvent extends CustomEvent<Card> {
    private int pos;

    public PlaceCardEvent(Card source, int pos) {
        super(Triggers.PlaceCard, source);
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}