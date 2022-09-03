package com.monique.txtrpg.entities;

import com.monique.txtrpg.Board;

public class Player extends Entity {
    public String name;

    public Player(Board board, String name) {
        super(board, "player", name, 20, 9);
    }
}