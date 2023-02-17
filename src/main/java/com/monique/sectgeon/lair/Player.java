package com.monique.sectgeon.lair;

import java.util.ArrayList;
import java.util.UUID;

import com.monique.sectgeon.common.events.lair.*;
import com.monique.sectgeon.lair.cards.*;
import com.monique.sectgeon.lair.cards.packs.Macabre;

public class Player {
    public final String NAME;
    public final Lair LAIR;
    public boolean ready;
    public ArrayList<Card> cemetery = new ArrayList<Card>();
    public ArrayList<CardRegistry> deck = Macabre.getCards();
    public ArrayList<Card> hand = new ArrayList<Card>();
    private int life;
    private int buyAmount = 0;

    public Player(Lair lair, String name, int maxLife) {
        this.LAIR = lair;
        this.NAME = name;
        this.life = maxLife;
    }

    /**
     * @param source who attacked
     */
    public void takeDamage(Card source, int damage) {
        var e = (LPHurtEvent) LAIR.listener.dispatch(new LPHurtEvent(source, this, damage));

        e.getTarget().life -= e.getDamage();
        if (e.getTarget().life <= 0) e.getTarget().death();
    }

    /**
     * @param source who healed
     */
    public void heal(Card source, int value) {
        if (value > 0) {
            var e = (LPHealEvent) LAIR.listener.dispatch(new LPHealEvent(source, this, value));

            if (e.getTarget() == this) {
                life += e.getValue();
            } else {
                e.getTarget().heal(source, e.getValue());
            }
        }
    }

    public Card getHandCard(UUID id) {
        for (Card card : hand) {
            if (card.ID.equals(id)) return card;
        }
        return null;
    }

    public void death() {
        LAIR.finish(false);
    }

    public int getLife() {
        return life;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(int value) {
        buyAmount = value < 0 ? 0 : value;
    }
}