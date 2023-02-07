package com.monique.sectgeon.level.dungeons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.monique.sectgeon.common.Frame;
import com.monique.sectgeon.common.gui.*;
import com.monique.sectgeon.common.listeners.CustomListener;
import com.monique.sectgeon.level.entities.*;
import com.monique.sectgeon.level.gui.DungeonGUI;

public abstract class Dungeon extends Board {
    private boolean started = false;
    private DungeonGUI hud = new DungeonGUI(this);
    public CustomListener<Entity> listener = new CustomListener<Entity>();
    public Player player = new Player(this, "default");
    public ArrayList<LivingEntity> livingEntities = new ArrayList<LivingEntity>();
    public ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    Dungeon(Frame frame) {
        super(frame);
        drawables.add(player);
        drawables.add(hud);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.decode("#aeebe0"));
        drawHUD(g);

        if (drawables.size() > 0) {
            for (Drawable drawable : drawables) {
                drawable.draw(g);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void tick(Object e) {
        if (!started) {
            started = true;
            start();
        }

        for (LivingEntity livingEntity : livingEntities) {
            livingEntity.ai();
        }

        revalidate();
        repaint();
    }

    /**
     * @param winOrLos false == lost, true == win.
     */
    public void finish(boolean winOrLos) {
        frame.finishDungeon(winOrLos);
        System.out.println(winOrLos);
    }

    private void drawHUD(Graphics g) {
        String lifeString = String.valueOf(player.getLife());
        g.drawString(lifeString, getWidth()/2 - lifeString.length(), 30);
    }

    protected abstract void start();
}