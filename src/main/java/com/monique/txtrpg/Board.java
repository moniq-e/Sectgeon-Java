package com.monique.txtrpg;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;

import com.monique.txtrpg.entities.*;

/*
    main class, onde o jogo vai rodar
*/
public abstract class Board extends JPanel {
    public Frame janela;
    public final Player player;
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public Board(Frame janela) {
        this.janela = janela;
        this.player = new Player(this, "default");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    public abstract void paint(Graphics g);

    //called by janela.listener events:
    public void mouseClicked(MouseEvent e) {
        player.lastClick.setLocation(e.getPoint());
    }

    public abstract void actionPerformed(ActionEvent e);
    
    public abstract void keyPressed(KeyEvent e);
}