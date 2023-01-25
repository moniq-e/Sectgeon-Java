package com.monique.sectgeon.lair.cards.packs;

import java.util.ArrayList;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.events.lair.*;
import com.monique.sectgeon.lair.cards.*;

public class Macabre extends Pack {

    @Override
    protected void buildings() {
        CARDS.put("Lápide", new Card("Lápide", CardTypes.Building, 0, 3, 1, 0, e -> {
            DeathEvent<Card> de = (DeathEvent<Card>) e;

            if (de.getSkillID().equals(de.getSource().ID)) {

                Card self = de.getSource().LAIR.tableCards.get(de.getSkillID());
                ArrayList<Card> cm = de.getSource().PLAYER.cemetery;
    
                Card rn = cm.get(Util.random(0, cm.size() - 1));
                Card newCard = instanceateCard(rn.NAME, self.PLAYER);
    
                self.LAIR.summon(self, newCard, self.getPos());
            }
        }, Triggers.Death));
    }

    @Override
    protected void spells() {
        CARDS.put("Livro Sagrado", new Card("Livro Sagrado", CardTypes.Spell, 0, 3, 1, 0, e -> {
            LPHurtEvent he = (LPHurtEvent) e;

            he.setDamage(0);
        }, Triggers.PlayerHurt));
    }

    @Override
    protected void troops() {
        CARDS.put("Rainha Zumbi", new Card("Rainha Zumbi", CardTypes.Troop, 3, 2, 3, 2, e -> {
            DeathEvent<Card> de = (DeathEvent<Card>) e;

            if (!de.getSkillID().equals(de.getSource().ID) && de.getSource().isOnTable()) {
                Card self = de.getSource().LAIR.tableCards.get(de.getSkillID());
                self.heal(self, 1);
            }
        }, Triggers.PlayerHurt));
    }
}