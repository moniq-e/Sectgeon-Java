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
import com.monique.txtrpg.entities.Player;

/*
    main class, onde o jogo vai rodar
*/
public class Board extends JPanel implements ActionListener, KeyListener, MouseListener {
    public final Player player;
    public final Cronologia cron;
    public Timer timer;

    public static void main(String[] args) {
        JFrame janela = new JFrame("TXTRPG");
        Board board = new Board();

        janela.add(board);
        janela.addKeyListener(board);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1280, 720);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
    }

    Board() {
        player = new Player(this, "default");
        cron = new Cronologia(this);
        timer = new Timer(1, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn.

        // player.tick();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // muda o background
        setBackground(Color.decode("#aeebe0"));
        // desenha a janela
        g.setColor(Color.decode("#2ebee6"));
        g.fillRect(112, 112, 150, 150);

        // desenha o tampo da mesa
        g.setColor(Color.blue);
        int[] xtampo = { 240, 620, 713, 150 };
        int[] ytampo = { 315, 315, 398, 398 };
        g.fillPolygon(xtampo, ytampo, 4);

        // player.draw(g);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

    // not using yet
    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        switch (e.getKeyChar()) {
            default:
                System.out.println(e.getKeyChar());
                break;
        }
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