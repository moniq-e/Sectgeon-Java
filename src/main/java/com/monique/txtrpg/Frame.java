package com.monique.txtrpg;

import javax.swing.Timer;
import javax.swing.JFrame;
import java.util.ArrayList;

import com.monique.txtrpg.dungeons.*;
import com.monique.txtrpg.gui.Board;
import com.monique.txtrpg.gui.InitialMenu;
import com.monique.txtrpg.listeners.DefaultListener;

public class Frame extends JFrame {
    public final int WIDTH = 640;
    public final int HEIGHT = 480;
    public Board board;
    public DefaultListener listener = new DefaultListener(this);
    public Timer timer = new Timer(33, listener);
    private ArrayList<Board> cronologia = new ArrayList<Board>();

    public static void main(String[] args) {
        new Frame();
    }

    Frame() {
        super("TXTRPG");
        addKeyListener(listener);
        addMouseListener(listener);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);

        timer.start();
        setCronologia();
        setBoard(new InitialMenu(this));
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