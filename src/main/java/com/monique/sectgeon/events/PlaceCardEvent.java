package com.monique.sectgeon.events;

import com.monique.sectgeon.lair.cards.Card;

public class PlaceCardEvent extends CustomEvent<Card> {
    private int pos;

    public PlaceCardEvent(Card source) {
        super(Triggers.PlaceCard, source);
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}