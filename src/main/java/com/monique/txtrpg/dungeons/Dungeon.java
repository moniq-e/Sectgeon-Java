package com.monique.txtrpg.dungeons;

import java.util.ArrayList;
import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Entity;

public class Dungeon {
    public Board board;
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    Dungeon(Board board) {
        this.board = board;
    }

    public void showEnemies() {
        board.entities = entities;
    }

    public void battleMode() {
        boolean playerTurn = true;
        
        while(entities.size() > 0) {
            if (playerTurn) {

            } else {

            }
        }
    }
}