package com.monique.txtrpg.dungeons;

import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Entity;

public class Dungeon {
    public Board board;

    Dungeon(Board board) {
        this.board = board;
    }

    public void battleMode() {
        boolean playerTurn = true;
        if (board.entities.size() <= 0) return;

        if (playerTurn) {
            board.player.canMove = true;

            for (Entity entity : board.entities) {
                if (Util.collides(entity.getRect(), board.player.lastClick)) {
                    board.player.attack(entity, board.player.inventory.get(0));
                    System.out.println(entity.getLife());
                    board.player.lastClick.setLocation(0, 0);

                    if (entity.getLife() <= 0) board.entities.remove(entity); break;
                }
            }
        } else {
            board.player.canMove = false;
        }
    }
}