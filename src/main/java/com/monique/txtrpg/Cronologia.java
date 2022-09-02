package com.monique.txtrpg;

import com.monique.txtrpg.dungeons.*;

public class Cronologia {
    public Board board;
    Cronologia(Board board) {
        this.board = board;
        new Dungeon1(this);
    }
}