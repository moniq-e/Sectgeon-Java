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

                var self = de.getSource().LAIR.getCemeterysCard(de.getSkillID());
                if (self != null) {
                    var filtered = new ArrayList<Card>();
                    filtered.addAll(self.owner.cemetery);
                    filtered.removeIf(Util.onlyOneType(CardTypes.Troop));

                    if (filtered.size() > 0) {
                        Card rn = filtered.get(Util.random(0, filtered.size() - 1));
                        Card newCard = instanceateCard(rn.NAME, self.owner);

                        self.LAIR.summon(self, newCard, self.getPos());
                    }
                }
            }
        }, Triggers.Death);
    }

    private static void spells() {
        CARDS.get("Livro Sagrado").setSkill(e -> {
            var he = (LPHurtEvent) e;
            he.setDamage(0);

            he.getSource().LAIR.listener.removeListener(Triggers.PlayerHurt, he.getSkillID());
        }, Triggers.PlayerHurt);
    }

    private static void troops() {
        CARDS.get("Rainha Zumbi").setSkill(e -> {
            var de = (DeathEvent<Card>) e;

            var self = de.getSource().LAIR.getTableCard(de.getSkillID());
            if (self != null) {
                if (!de.getSkillID().equals(de.getSource().ID)) {
                    self.heal(self, 1);
                }
            }
        }, Triggers.Death);

        CARDS.get("Zumbi").setSkill(e -> {
            var ae = (AttackEvent<Card>) e;

            var tar = ae.getTarget();
            if (ae.getSkillID().equals(ae.getSource().ID)) {
                if (tar.getAttack() > 0) {
                    tar.setAttack(tar.getAttack() - 1);
                }
            }
        }, Triggers.Attack);

        CARDS.get("Ciclope").setSkill(e -> {
            var bs = (BattleStart) e;
            var self = bs.getLair().getTableCard(bs.getSkillID());

            if (self != null) {
                for (Card c : bs.getLair().tableCards)  {
                    if (c.TYPE == CardTypes.Troop && c.owner.equals(self.owner) && !c.ID.equals(self.ID)) return;
                }
                if (!self.infos.has("buff")) {
                    self.setAttack(self.getAttack() + 3);
                    self.setLife(self.getLife() + 3);
                    self.infos.put("buff", true);
                }
            }
        }, Triggers.BattleStart);
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