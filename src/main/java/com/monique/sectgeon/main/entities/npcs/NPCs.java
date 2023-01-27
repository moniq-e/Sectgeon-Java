package com.monique.sectgeon.main.entities.npcs;

import java.util.HashMap;

import com.monique.sectgeon.main.dungeons.Dungeon;

public class NPCs {
    private static final HashMap<String, NPCRegistry> NPCS = new HashMap<String, NPCRegistry>();

    static {
        NPCS.put("John", new NPCRegistry("John", 32, 32));
    }

    public static NPC instanceateNPC(String name, Dungeon dungeon) {
        return new NPC(NPCS.get(name), dungeon);
    }
}