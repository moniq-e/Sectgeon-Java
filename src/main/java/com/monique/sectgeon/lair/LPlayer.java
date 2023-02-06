package com.monique.sectgeon.lair;

import java.util.ArrayList;

import com.monique.sectgeon.common.events.lair.*;
import com.monique.sectgeon.lair.cards.*;
import com.monique.sectgeon.lair.cards.packs.Macabre;

public class LPlayer {
    public final String NAME;
    public Lair lair;
    public boolean ready;
    public ArrayList<Card> cemetery = new ArrayList<Card>();
    public ArrayList<CardRegistry> deck = Macabre.getCards();
    public ArrayList<Card> hand = new ArrayList<Card>();
    private int life;

    public LPlayer(Lair lair, String name, int maxLife) {
        this.lair = lair;
        this.NAME = name;
        this.life = maxLife;
    }

    /**
     * @param source who attacked
     */
    public int takeDamage(Card source, int damage) {
        if (damage > 0) {
            LPHurtEvent e = (LPHurtEvent) lair.listener.dispatch(new LPHurtEvent(source, this, damage));

            e.getTarget().life -= e.getDamage();
            if (e.getTarget().life <= 0) e.getTarget().death();
            return Math.abs(life);
        } else return 0;
    }

    /**
     * @param source who healed
     */
    public void heal(Card source, int value) {
        if (value > 0) {
            LPHealEvent e = (LPHealEvent) lair.listener.dispatch(new LPHealEvent(source, this, value));

            life += e.getValue();
        }
    }

    public void death() {
        lair.finish(false);
    }

    public int getLife() {
        return life;
    }
}