package com.monique.sectgeon.gui;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import com.monique.sectgeon.Frame;

/*
    main class, onde o jogo vai rodar
*/
public abstract class Board extends JPanel {
    public Frame frame;

    public Board(Frame frame) {
        this.frame = frame;
        setSize(frame.WIDTH, frame.HEIGHT);
        setLayout(null);
    }

    /**
     * This method is called by the timer every DELAY ms. Use this space to update the state of your game or animation before the graphics are redrawn.
     * @param e
     */
    public abstract void actionPerformed(ActionEvent e);
}