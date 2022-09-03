package com.monique.txtrpg.dungeons;

import java.util.ArrayList;
import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Entity;

public class Dungeon {
    public Cronologia cron;
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public final int width = 30;
    public final int height = 30;

    Dungeon(Cronologia cron) {
        this.cron = cron;
    }

    public void showEnemies() {
        for (Entity entity : entities) {
            Board.print("%s:\n  distância: %.1fm", entity.name, Util.distance(cron.board.player.pos, entity.pos));
        }
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