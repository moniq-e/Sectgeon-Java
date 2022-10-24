package com.monique.txtrpg;

import javax.swing.Timer;
import javax.swing.JFrame;
import java.util.ArrayList;

import com.monique.txtrpg.dungeons.*;

public class Frame extends JFrame {
    public final int WIDTH = 640;
    public final int HEIGHT = 480;
    public Board board;
    public Listener listener = new Listener(this);
    public Timer timer = new Timer(33, listener);
    private ArrayList<Dungeon> cronologia = new ArrayList<Dungeon>();

    public static void main(String[] args) {
        new Frame();
    }

    Frame() {
        super("TXTRPG");
        this.addKeyListener(listener);
        this.addMouseListener(listener);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        timer.start();
        setCronologia();
        finishDungeon(true);
    }

    public void setCronologia() {
        cronologia.add(new Dungeon1(this));
    }

    private void setBoard(Board board) {
        if (this.board != null) remove(this.board);
        this.board = board;
        add(this.board);
    }

    public void finishDungeon(boolean winOrLos) {
        if (winOrLos) {
            Board nextBoard;
            try {
                nextBoard = cronologia.get(cronologia.indexOf(board) + 1);
                setBoard(nextBoard);
            } catch (IndexOutOfBoundsException e) {
                finish();
            }
        } else {
            finish();
        }
    }

    private void finish() {
        timer.stop();
        dispose();
    }
}