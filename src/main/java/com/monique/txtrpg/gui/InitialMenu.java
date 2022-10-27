package com.monique.txtrpg.gui;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import com.monique.txtrpg.Frame;

public class InitialMenu extends Board {

    public InitialMenu(Frame frame) {
        super(frame);

        new Button(this, "Começar", frame.WIDTH/2 - 50, frame.HEIGHT/2 - 10, 100, 15, e -> {
            frame.finishDungeon(true);
        });
        new Button(this, "Sair", frame.WIDTH/2 - 50, frame.HEIGHT/2 + 15, 100, 15, e -> {
            frame.finishDungeon(false);
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
    }
}