package com.monique.sectgeon.common;

import javax.swing.Timer;

import com.monique.sectgeon.common.gui.Board;
import com.monique.sectgeon.common.gui.InitialMenu;
import com.monique.sectgeon.common.listeners.DefaultListener;
import com.monique.sectgeon.main.dungeons.*;

import javax.swing.JFrame;
import java.util.ArrayList;

public class Frame extends JFrame {
    public final int WIDTH = 720;
    public final int HEIGHT = 480;
    public static Board board;
    public static DefaultListener listener = new DefaultListener();
    public Timer timer = new Timer(33, listener);
    public ArrayList<Board> phasis = new ArrayList<Board>();

    public static void main(String[] args) {
        new Frame();
    }

    Frame() {
        super("TXTRPG");
        addKeyListener(listener);
        addMouseListener(listener);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(this.WIDTH, this.HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);

        timer.start();
        setCronologia();
        setBoard(new InitialMenu(this));
    }

    public void setCronologia() {
        phasis.add(new Dungeon1(this));
    }

    private void setBoard(Board board) {
        if (Frame.board != null) {
            remove(Frame.board);
        }
        Frame.board = board;
        add(Frame.board);
    }

    public void finishDungeon(boolean winOrLos) {
        if (winOrLos) {
            Board nextBoard;
            try {
                nextBoard = phasis.get(phasis.indexOf(Frame.board) + 1);
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