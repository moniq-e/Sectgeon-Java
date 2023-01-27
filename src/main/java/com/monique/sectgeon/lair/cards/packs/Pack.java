package com.monique.sectgeon.lair.cards.packs;

import java.util.HashMap;

import com.monique.sectgeon.lair.LPlayer;
import com.monique.sectgeon.lair.cards.Card;
import com.monique.sectgeon.lair.cards.CardRegistry;

public abstract class Pack {
    protected static final HashMap<String, CardRegistry> CARDS = new HashMap<String, CardRegistry>();

    public static Card instanceateCard(String cardName, LPlayer player) {
        return new Card(CARDS.get(cardName), player);
    }
}