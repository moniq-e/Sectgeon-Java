package com.monique.txtrpg;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/*
    main class, onde o jogo vai rodar
*/
public abstract class Board extends JPanel {
    public Frame frame;

    public Board(Frame frame) {
        this.frame = frame;
    }

    /**
     * This method is called by the timer every DELAY ms. Use this space to update the state of your game or animation before the graphics are redrawn.
     * @param e
     */
    public abstract void actionPerformed(ActionEvent e);
}