package com.monique.sectgeon.lair.cards.packs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.monique.sectgeon.common.Util;
import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.events.lair.*;
import com.monique.sectgeon.lair.LPlayer;
import com.monique.sectgeon.lair.cards.*;

public class Macabre {
    protected static final HashMap<String, CardRegistry> CARDS = new HashMap<String, CardRegistry>();
    private static JSONObject json;

    static {
        try (Scanner file = new Scanner(new File("./src/main/resources/cards/packs/Macabre.json"))) {
            String txt = "";
            while (file.hasNextLine()) {
                txt += file.nextLine();
            }
            json = new JSONObject(txt);
        } catch (FileNotFoundException | JSONException e) {}

        JSONArray array = new JSONArray();
        array.putAll(json.getJSONArray("buildings"));
        array.putAll(json.getJSONArray("spells"));
        array.putAll(json.getJSONArray("troops"));

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            CARDS.put(obj.getString("name"), new CardRegistry(obj));
        }

        buildings();
        spells();
        troops();
    }

    private static void buildings() {
        CARDS.get("Lápide").setSkill(e -> {
            DeathEvent<Card> de = (DeathEvent<Card>) e;

            if (de.getSkillID().equals(de.getSource().ID)) {

                Card self = de.getSource().LAIR.tableCards.get(de.getSkillID());
                ArrayList<Card> cm = de.getSource().PLAYER.cemetery;

                Card rn = cm.get(Util.random(0, cm.size() - 1));
                Card newCard = instanceateCard(rn.NAME, self.PLAYER);

                self.LAIR.summon(self, newCard, self.getPos());
            }
        }, Triggers.Death);
    }

    private static void spells() {
        CARDS.get("Livro Sagrado").setSkill(e -> {
            LPHurtEvent he = (LPHurtEvent) e;

            he.setDamage(0);
        }, Triggers.PlayerHurt);
    }

    private static void troops() {
        CARDS.get("Rainha Zumbi").setSkill(e -> {
            DeathEvent<Card> de = (DeathEvent<Card>) e;

            if (!de.getSkillID().equals(de.getSource().ID) && de.getSource().isOnTable()) {
                Card self = de.getSource().LAIR.tableCards.get(de.getSkillID());
                self.heal(self, 1);
            }
        }, Triggers.PlayerHurt);
    }

    public static ArrayList<CardRegistry> getCards() {
        ArrayList<CardRegistry> array = new ArrayList<CardRegistry>();
        array.addAll(CARDS.values());
        return array;
    }

    public static Card instanceateCard(String cardName, LPlayer player) {
        return new Card(CARDS.get(cardName), player);
    }
}