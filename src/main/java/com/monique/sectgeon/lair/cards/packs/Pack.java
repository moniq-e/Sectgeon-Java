package com.monique.sectgeon.lair.cards.packs;

import java.util.HashMap;

import com.monique.sectgeon.lair.LairPlayer;
import com.monique.sectgeon.lair.cards.Card;

public abstract class Pack {
    protected final HashMap<String, Card> CARDS = new HashMap<String, Card>();

    public Pack() {
        buildings();
        spells();
        troops();
    }

    protected abstract void buildings();

    protected abstract void spells();

    protected abstract void troops();

    public Card instanceateCard(String cardName, LairPlayer player) {
        return new Card(CARDS.get(cardName), player);
    }
}