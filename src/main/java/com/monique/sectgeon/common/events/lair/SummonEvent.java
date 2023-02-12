package com.monique.sectgeon.common.events.lair;

import com.monique.sectgeon.common.events.CustomEvent;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.lair.cards.Card;

public class SummonEvent extends CustomEvent<Card> {
    private Card summoned;
    private int pos;

    public SummonEvent(Card source, Card summoned, int pos) {
        super(Triggers.Summon, source);
        this.summoned = summoned;
        this.pos = pos;
    }

    public Card getSummoned() {
        return summoned;
    }

    public void setSummoned(Card summoned) {
        this.summoned = summoned;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}