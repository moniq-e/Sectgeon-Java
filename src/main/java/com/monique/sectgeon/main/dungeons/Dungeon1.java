package com.monique.sectgeon.main.dungeons;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.main.entities.Skeleton;

public class Dungeon1 extends Dungeon {

    public Dungeon1(Frame frame) {
        super(frame);
    }

    @Override
    public void start() {
        for (int i = 1; i <= 3; i++) {
            new Skeleton(this);
        }
    }
}