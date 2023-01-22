package com.monique.sectgeon.lair.cards.packs;

public enum Packs {
    Macabre(new Macabre());

    private final Pack PACK;

    Packs(Pack pack) {
        PACK = pack;
    }

    public Pack getPack() {
        return PACK;
    }
}