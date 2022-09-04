package com.monique.txtrpg;

import com.monique.txtrpg.dungeons.*;

public class Cronologia {
    public Board board;
    Cronologia(Board board) {
        this.board = board;
    }

    public void start() {
        new Dungeon1(this);
    }
}