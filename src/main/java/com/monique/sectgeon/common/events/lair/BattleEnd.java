package com.monique.sectgeon.common.events.lair;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.common.events.CustomEvent;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.lair.cards.Card;

public class BattleEnd extends CustomEvent<Card> {
    public BattleEnd() {
        super(Triggers.BattleEnd, null);
    }

    public Lair getLair() {
        return (Lair) Frame.board;
    }
}