package com.monique.txtrpg.dungeons;

import java.util.ArrayList;
import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Entity;

public class Dungeon {
    public Board board;

    Dungeon(Board board) {
        this.board = board;
    }

    public void battleMode() {
        boolean playerTurn = true;
        
        while(board.entities.size() > 0) {
            if (playerTurn) {
                board.player.canMove = true;
                for (Entity entity : board.entities) {
                    if (Util.collides(entity, board.player.lastClick)) {
                        board.player.attack(entity, board.player.inventory.get(0));
                    }
                }
            } else {
                board.player.canMove = false;
            }
        }
    }
}