package com.monique.sectgeon.lair;

import java.util.UUID;

import com.monique.sectgeon.common.Util;
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
        LAIR.pile.buyCards(this);
        placeCards();
        LAIR.ready(this);
    }

    private void placeCards() {
        int[] empty = LAIR.getEmptySlots(this);
        Util.shuffleArray(empty);
        for (int i = 0; i < Math.min(empty.length, hand.size()); i++) {
            if (empty[i] != -1) {
                var toPlaceCard = hand.get(Util.random(0, hand.size() - 1));
                if (toPlaceCard.getSacrifices() > 0) {
                    //inimigo sacrificar da mesa (se n tiver na mesa, jogar uma da mão pra sacrificar)
                }
                LAIR.placeCard(toPlaceCard, empty[i]);
            }
        }
    }
}