package com.monique.txtrpg.dungeons;

import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Entity;

public class Dungeon {
    public Board board;
    private boolean playerTurn = true;

    Dungeon(Board board) {
        this.board = board;
    }

    public void battleMode() {
        if (board.entities.size() <= 0) return;

        if (playerTurn) {
            board.player.canMove = true;

            for (Entity entity : board.entities) {
                if (Util.collides(entity.getRect(), board.player.lastClick)) {
                    board.player.attack(entity, board.player.inventory.get(0));
                    board.player.lastClick.setLocation(0, 0);

                    playerTurn = false;

                    if (entity.getLife() <= 0) board.entities.remove(entity); break;
                }
            }
        } else {
            board.player.canMove = false;

            for (Entity entity : board.entities) {
                entity.ai();
            }

            playerTurn = true;
        }
    }
}