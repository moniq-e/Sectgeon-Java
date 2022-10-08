package com.monique.txtrpg.dungeons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.monique.txtrpg.*;
import com.monique.txtrpg.entities.*;

public abstract class Dungeon extends Board {
    private boolean started = false;
    private boolean playerTurn = true;
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    Dungeon(Frame frame) {
        super(frame);
    }

    public void battleMode() {
        if (this.entities.size() <= 0) return;
        if (playerTurn) {
            this.player.canMove = true;
            for (Entity entity : this.entities) {
                if (Util.collides(entity.getRect(), this.player.lastClick)) {
                    this.player.attack(entity, this.player.inventory.get(0));
                    this.player.lastClick.setLocation(0, 0);

                    playerTurn = false;

                    if (entity.getLife() <= 0) this.entities.remove(entity); break;
                }
            }
        } else {
            this.player.canMove = false;

            for (Entity entity : this.entities) {
                entity.ai();
            }

            playerTurn = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.decode("#aeebe0"));

        if (entities.size() > 0) {
            for (Entity entity : entities) {
                entity.draw(g);
            }
        }
        player.draw(g);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn.
        if (!started) {
            started = true;
            this.start();
        }
        
        if (player.getLife() <= 0) {
            frame.dispose();
            frame.timer.stop();
        }

        battleMode();

        revalidate();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.move(e);
    }

    public abstract void start();
}