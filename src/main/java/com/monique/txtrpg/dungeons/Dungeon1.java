package com.monique.txtrpg.dungeons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.Skeleton;
import com.monique.txtrpg.gui.Button;

public class Dungeon1 extends Dungeon {

    public Dungeon1(Frame frame) {
        super(frame);
    }

    @Override
    public void start() {
        for (int i = 1; i <= 3; i++) {
            new Skeleton(this);
        }

        //teste
        new Button(this, "oi", 100, 100, 100, 100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("oi");
            }
        });
    }
}