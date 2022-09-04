package com.monique.txtrpg.dungeons;

import java.awt.Point;
import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Skeleton;

public class Dungeon1 extends Dungeon {

    public Dungeon1(Cronologia cron) {
        super(cron);
        start();
    }

    public void start() {
        cron.board.player.pos = new Point(width / 2, 0);

        for (int i = 1; i <= 3; i++) {
            Skeleton mob = new Skeleton(this.cron.board, "Esqueleto" + i);
            mob.pos = new Point(Util.random(0, width), Util.random(0, height));
            entities.add(mob);
        }

        showEnemies();
        
        battleMode();
    }
}