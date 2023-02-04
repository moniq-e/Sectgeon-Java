package com.monique.sectgeon.level.dungeons;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.level.entities.monsters.Skeleton;
import com.monique.sectgeon.level.entities.npcs.NPCs;

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