package com.monique.sectgeon.common.events.lair;

import com.monique.sectgeon.common.events.CustomEvent;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.lair.cards.Card;

public class TurnEnd extends CustomEvent<Card> {
    public TurnEnd() {
        super(Triggers.TurnEnd, null);
    }
}