package com.monique.sectgeon.common;

import javax.swing.Timer;

import com.monique.sectgeon.common.gui.Board;
import com.monique.sectgeon.common.gui.InitialMenu;
import com.monique.sectgeon.common.listeners.Listener;
import com.monique.sectgeon.level.dungeons.*;

import javax.swing.JFrame;
import java.util.ArrayList;

public class Frame extends JFrame {
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;
    public static Board board;
    public static Listener listener = new Listener();
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
        setSize(Frame.WIDTH, Frame.HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);

        timer.start();
        setCronologia();
        setBoard(new InitialMenu(this));
    }

    public void setCronologia() {
        phasis.add(new Dungeon1(this));
    }

    public void setBoard(Board board) {
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

    public void finishLair(boolean winOrLos, Board board) {
        if (winOrLos) {
            setBoard(board);
        } else {
            finish();
        }
    }

    private void finish() {
        timer.stop();
        dispose();
    }
}