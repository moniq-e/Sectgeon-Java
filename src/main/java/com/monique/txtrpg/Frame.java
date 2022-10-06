package com.monique.txtrpg;

import javax.swing.Timer;
import javax.swing.JFrame;

import com.monique.txtrpg.dungeons.*;

public class Frame extends JFrame {
    public final int width = 640;
    public final int height = 480;
    public Board board;
    public Listener listener = new Listener(this);
    public Timer timer = new Timer(33, listener);

    public static void main(String[] args) {
        new Frame();
    }

    Frame() {
        super("TXTRPG");
        this.addKeyListener(listener);
        this.addMouseListener(listener);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        timer.start();
        cronologia();
    }

    public void cronologia() {
        setBoard(new Dungeon1(this));
    }

    public void setBoard(Board board) {
        if (this.board != null) this.remove(this.board);
        this.board = board;
        this.add(this.board);
    }
}