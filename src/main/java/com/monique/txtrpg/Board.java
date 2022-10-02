package com.monique.txtrpg;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.monique.txtrpg.dungeons.*;
import com.monique.txtrpg.entities.*;

/*
    main class, onde o jogo vai rodar
*/
public class Board extends JPanel implements ActionListener, KeyListener, MouseListener {
    public final int width = 640;
    public final int height = 480;
    public final Player player = new Player(this, "default");
    public Dungeon actualDungeon;
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public Timer timer;

    public static void main(String[] args) {
        new Board(new JFrame("TXTRPG"));
    }

    Board(JFrame janela) {
        janela.add(this);
        janela.addKeyListener(this);
        janela.addMouseListener(this);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(width, height);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);

        timer = new Timer(33, this);
        timer.start();

        cronologia();
    }

    public void cronologia() {
        actualDungeon = new Dungeon1(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn.

        if (actualDungeon != null) actualDungeon.battleMode();

        repaint();
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
    public void mouseClicked(MouseEvent e) {
        player.lastClick.setLocation(e.getPoint());
    }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

    // not using yet
    @Override
    public void keyPressed(KeyEvent e) {
        player.move(e);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }
}