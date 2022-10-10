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
    public ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    Dungeon(Frame frame) {
        super(frame);
        drawables.add(player);
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

                    if (entity.getLife() <= 0) entity.kill(); break;
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

        if (drawables.size() > 0) {
            for (Drawable drawable : drawables) {
                drawable.draw(g);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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