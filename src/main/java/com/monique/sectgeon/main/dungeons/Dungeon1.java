package com.monique.sectgeon.main.dungeons;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.main.entities.monsters.Skeleton;
import com.monique.sectgeon.main.entities.npcs.NPCs;

public class Dungeon1 extends Dungeon {

    public Dungeon1(Frame frame) {
        super(frame);
    }

    @Override
    public void start() {
        new Skeleton(this);
        NPCs.instanceateNPC("John", this);
    }
}