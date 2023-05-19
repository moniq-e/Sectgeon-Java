package com.monique.sectgeon.lair.cards.packs;

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.events.lair.*;
import com.monique.sectgeon.lair.Player;
import com.monique.sectgeon.lair.cards.*;
import com.monique.sectgeon.lair.gui.LairGUI;

public class Macabre {
    protected static final HashMap<String, CardRegistry> CARDS = new HashMap<String, CardRegistry>();

    static {
        try {
            var file = new Scanner(Macabre.class.getResourceAsStream("/data/packs/Macabre.json"), "UTF-8");
            var txt = "";

            while (file.hasNextLine()) {
                txt += file.nextLine();
            }
            setUpCardInfos(new JSONObject(txt));

            buildings();
            spells();
            troops();
        } catch (Exception e) {
            Frame.crash(e);
        }
    }

    private static void buildings() {
        CARDS.get("Lápide").setSkill(e -> {
            var de = (DeathEvent<Card>) e;

            if (de.getSkillID().equals(de.getSource().ID)) {
                var self = de.getSource();

                var filtered = new ArrayList<Card>();
                filtered.addAll(self.owner.cemetery);
                filtered.removeIf(Util.onlyOneType(CardTypes.Troop));

                if (filtered.size() > 0) {
                    Card rn = filtered.get(Util.random(0, filtered.size() - 1));
                    Card newCard = instanceateCard(rn.NAME, self.owner);

                    self.LAIR.summon(self, newCard, self.getPos());
                }
            }
        }, Triggers.Death);

        CARDS.get("Cerca Viva").setSkill(e -> {
            var he = (HurtEvent<Card>) e;
            var self = he.getTarget();
            var tar = he.getSource();

            if (he.getSkillID().equals(self.ID) && tar.LAIR.getTableCard(self.ID) != null) {
                int damage = (he.getDamage() / 2) < 1 ? 1 : he.getDamage() / 2;
                var sae = (SkillAttackEvent<Card>) self.LAIR.listener
                        .dispatch(new SkillAttackEvent<Card>(self, tar, damage));
                tar.takeDamage(self, sae.getDamage());
            }
        }, Triggers.Hurt);

        CARDS.get("Coração da Floresta").setSkill(e -> {
            var be = (BattleEnd) e;
            var self = be.getLair().getTableCard(be.getSkillID());

            if (self != null) {
                // já manda evento no heal
                self.owner.heal(self, 3);
            }
        }, Triggers.BattleEnd);
    }

    private static void spells() {
        CARDS.get("Livro Sagrado").setSkill(e -> {
            var he = (LPHurtEvent) e;
            var self = he.getSource().LAIR.getCemeterysCard(he.getSkillID());

            if (self != null) {
                if (self.owner == he.getTarget()) {
                    he.setDamage(0);
                    self.LAIR.listener.removeListener(Triggers.PlayerHurt, he.getSkillID());
                }
            }
        }, Triggers.PlayerHurt);

        CARDS.get("Barganha Justa").setSkill(e -> {
            var pe = (PlaceCardEvent) e;
            var self = pe.getSource();

            if (pe.getSkillID().equals(self.ID)) {
                if (!LairGUI.handSacrifice.contains(self.ID) && LairGUI.handSacrifice.size() > 0) {
                    self.owner.getHandCard(LairGUI.handSacrifice.remove(0)).death(null);
                    self.owner.setBuyAmount(self.owner.getBuyAmount() + 2);
                } else {
                    pe.setPos(-1);
                }
            }
        }, Triggers.PlaceCard);

        CARDS.get("Injeção Letal").setSkill(e -> {
            var pe = (PlaceCardEvent) e;
            var self = pe.getSource();

            if (pe.getSkillID().equals(self.ID)) {
                var playerCards = self.LAIR.getTableCards(self.owner);
                if (playerCards.size() > 0) {
                    playerCards.sort((a, b) -> {
                        var life = b.getLife() - a.getLife();
                        return life == 0 ? a.getAttack() - b.getAttack() : life;
                    });

                    self.owner.heal(self, playerCards.get(0).getLife());
                    playerCards.get(0).death(null);
                } else {
                    pe.setPos(-1);
                }
            }
        }, Triggers.PlaceCard);
    }

    private static void troops() {
        CARDS.get("Rainha Zumbi").setSkill(e -> {
            var de = (DeathEvent<Card>) e;

            var self = de.getSource().LAIR.getTableCard(de.getSkillID());
            if (self != null) {
                self.heal(self, 1);
            }
        }, Triggers.Death);

        CARDS.get("Zumbi").setSkill(e -> {
            var ae = (AttackEvent<Card>) e;

            var tar = ae.getTarget();
            if (ae.getSkillID().equals(ae.getSource().ID) && tar != null) {
                if (tar.getAttack() > 0) {
                    tar.setAttack(tar.getAttack() - 1);
                }
            }
        }, Triggers.Attack);

        CARDS.get("Ciclope").setSkill(e -> {
            var bs = (BattleStart) e;
            var self = bs.getLair().getTableCard(bs.getSkillID());

            if (self != null) {
                for (Card c : bs.getLair().getTableCards(self.owner)) {
                    if (c.TYPE == CardTypes.Troop && !c.ID.equals(self.ID))
                        return;
                }
                if (!self.infos.has("buff")) {
                    self.setAttack(self.getAttack() + 3);
                    self.setLife(self.getLife() + 3);
                    self.infos.put("buff", true);
                }
            }
        }, Triggers.BattleStart);

        CARDS.get("Camaleão").setSkill(e -> {
            var de = (DeathEvent<Card>) e;

            if (de.getSkillID().equals(de.getKiller().ID)) {
                var self = de.getKiller();

                if (self.isOnTable()) {
                    Card newCard = instanceateCard(de.getSource().NAME, self.owner);
                    self.death(self);
                    self.LAIR.summon(self, newCard, self.getPos());
                }
            }
        }, Triggers.Death);

        CARDS.get("Sereia").setSkill(e -> {
            var ae = (AttackEvent<Card>) e;

            if (ae.getSkillID().equals(ae.getSource().ID)) {
                var friends = new ArrayList<Card>();
                var lair = ae.getSource().LAIR;

                for (int i = 0; i < 3; i++) {
                    var card = lair.getTableCard(ae.getSource().owner, i);
                    if (card != ae.getSource() && card != null) {
                        friends.add(card);
                    }
                }

                if (friends.size() > 0) {
                    var tar = Util.randomElement(friends);
                    tar.heal(ae.getSource(), 1);
                }
            }
        }, Triggers.Attack);

        CARDS.get("Sapo Cururu").setSkill(e -> {
            var se = (SummonEvent) e;
            var self = se.getSource().LAIR.player.getHandCard(se.getSkillID());
            if (self == null) self = se.getSource().LAIR.enemy.getHandCard(se.getSkillID());

            if (self != null) {
                if (Util.random(0, 1) == 0) {
                    self.heal(self, 1);
                } else {
                    self.setAttack(self.getAttack() + 1);
                }
            }
        }, Triggers.Summon);
    }

    public static ArrayList<CardRegistry> getCards() {
        var array = new ArrayList<CardRegistry>();
        array.addAll(CARDS.values());
        return array;
    }

    public static Card instanceateCard(String cardName, Player player) {
        return new Card(CARDS.get(cardName), player);
    }

    private static void setUpCardInfos(JSONObject json) {
        var array = new JSONArray();
        var buildings = json.getJSONArray("buildings");
        var spells = json.getJSONArray("spells");
        var troops = json.getJSONArray("troops");

        for (int i = 0; i < buildings.length(); i++) {
            buildings.getJSONObject(i).put("type", "Building");
        }
        for (int i = 0; i < spells.length(); i++) {
            spells.getJSONObject(i).put("type", "Spell");
        }
        for (int i = 0; i < troops.length(); i++) {
            troops.getJSONObject(i).put("type", "Troop");
        }

        array.putAll(buildings);
        array.putAll(spells);
        array.putAll(troops);

        for (int i = 0; i < array.length(); i++) {
            var obj = array.getJSONObject(i);
            CARDS.put(obj.getString("name"), new CardRegistry(obj));
        }
    }
}