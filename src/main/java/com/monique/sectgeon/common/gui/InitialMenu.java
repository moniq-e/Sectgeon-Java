package com.monique.sectgeon.common.gui;

import com.monique.sectgeon.common.Frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public class InitialMenu extends Board {

    public InitialMenu(Frame frame) {
        super(frame);

        new Button<InitialMenu>(this, "Começar", getWidth()/2 - 50, getHeight()/2 - 10, 100, 15, e -> {
            frame.finishDungeon(true);
        });
        new Button<InitialMenu>(this, "Sair", getWidth()/2 - 50, getHeight()/2 + 15, 100, 15, e -> {
            frame.finishDungeon(false);
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void tick(Object e) {
        revalidate();
        repaint();
    }
}