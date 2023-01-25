package com.monique.sectgeon.lair.cards.packs;

import java.util.HashMap;

import com.monique.sectgeon.lair.LPlayer;
import com.monique.sectgeon.lair.cards.Card;
import com.monique.sectgeon.lair.cards.CardRegistry;

public abstract class Pack {
    protected final HashMap<String, CardRegistry> CARDS = new HashMap<String, CardRegistry>();

    public Pack() {
        buildings();
        spells();
        troops();
    }

    protected abstract void buildings();

    protected abstract void spells();

    protected abstract void troops();

    public Card instanceateCard(String cardName, LPlayer player) {
        return new Card(CARDS.get(cardName), player);
    }
}