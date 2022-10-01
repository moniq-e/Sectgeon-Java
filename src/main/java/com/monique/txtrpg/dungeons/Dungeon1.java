package com.monique.txtrpg.dungeons;

import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Skeleton;

public class Dungeon1 extends Dungeon {

    public Dungeon1(Board board) {
        super(board);
        start();
    }

    public void start() {
        for (int i = 1; i <= 3; i++) {
            Skeleton mob = new Skeleton(this.board, "Esqueleto" + i);
            board.entities.add(mob);
        }
    }
}