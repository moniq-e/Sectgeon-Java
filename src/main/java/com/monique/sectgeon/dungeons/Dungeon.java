package com.monique.sectgeon.dungeons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.monique.sectgeon.*;
import com.monique.sectgeon.entities.*;
import com.monique.sectgeon.gui.*;
import com.monique.sectgeon.listeners.CustomListener;

public abstract class Dungeon extends Board {
    private boolean started = false;
    private boolean playerTurn = true;
    private DungeonHUD hud = new DungeonHUD(this);
    public CustomListener<Entity> listener = new CustomListener<Entity>();
    public Player player = new Player(this, "default");
    public ArrayList<LivingEntity> livingEntities = new ArrayList<LivingEntity>();
    public ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    Dungeon(Frame frame) {
        super(frame);
        drawables.add(player);
        drawables.add(hud);
    }

    private void entitiesAction() {
        if (livingEntities.size() <= 0) finish(true);

        for (LivingEntity livingEntity : livingEntities) {
            livingEntity.ai();
        }

        setPlayerTurn(true);
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
    public void actionPerformed(ActionEvent e) {
        if (!started) {
            started = true;
            start();
        }

        if (!getPlayerTurn()) entitiesAction();

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

    public boolean getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean turn) {
        playerTurn = turn;
        player.setCanMove(turn);
    }

    private void drawHUD(Graphics g) {
        String lifeString = String.valueOf(player.getLife());
        g.drawString(lifeString, getWidth()/2 - lifeString.length(), 30);
    }

    protected abstract void start();
}