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
    public Board board;
    public static DefaultListener listener = new DefaultListener();
    public Timer timer = new Timer(33, listener);
    public ArrayList<Board> cronologia = new ArrayList<Board>();

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
        cronologia.add(new Dungeon1(this));
    }

    private void setBoard(Board board) {
        if (this.board != null) {
            remove(this.board);
            listener.removeBoardListener();
        }
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