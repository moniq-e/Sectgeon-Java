package com.monique.sectgeon.lair;

import java.util.UUID;

import com.monique.sectgeon.common.events.CustomEvent;
import com.monique.sectgeon.common.events.Triggers;
import com.monique.sectgeon.lair.cards.Card;

public class Enemy extends Player {
    public final UUID ID = UUID.randomUUID();

    public Enemy(Lair lair, String name, int maxLife) {
        super(lair, name, maxLife);

        lair.listener.addListener(Triggers.TurnStart, ID, this::startOfTurn);
    }

    private void startOfTurn(CustomEvent<Card> e) {
        lair.pile.buyCard(this);
    }
}