package com.monique.sectgeon.common.gui;

import java.util.UUID;
import java.awt.Dimension;
import javax.swing.JPanel;

import com.monique.sectgeon.common.Frame;

/*
    main class, onde o jogo vai rodar
*/
public abstract class Board extends JPanel {
    public final UUID ID = UUID.randomUUID();
    public Frame frame;

    public Board(Frame frame) {
        this.frame = frame;
        setSize(frame.getSize());
        setLayout(null);
    }

    @Override
    public Dimension getPreferredSize() {
        return getSize();
    }

    /**
     * This method is called by the timer every DELAY ms. Use this space to update the state of your game or animation before the graphics are redrawn.
     * @param e
     */
    public abstract void tick(Object e);
}