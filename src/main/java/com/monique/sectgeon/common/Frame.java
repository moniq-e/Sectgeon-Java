package com.monique.sectgeon.common;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.monique.sectgeon.common.gui.Board;
import com.monique.sectgeon.common.gui.InitialMenu;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.common.listeners.Listener;
import com.monique.sectgeon.level.dungeons.*;

public class Frame extends JFrame {
    public static Board board;
    public static Listener listener = new Listener();
    public Timer timer = new Timer(33, listener);
    public ArrayList<Board> phasis = new ArrayList<Board>();

    public static void main(String[] args) {
        new Frame();
    }

    Frame() {
        super("Sectgeon");
        addKeyListener(listener);
        addMouseListener(listener);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        setLocationRelativeTo(null);

        listener.addListener(Events.Action, null, e -> {
            if (board != null) board.tick(e);
        });

        registerFont();
        timer.start();
        setBoard(new InitialMenu(this));
    }

    public void registerFont() {
        try {
            var ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/Minecraftia-Regular.ttf")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBoard(Board board) {
        if (Frame.board != null) {
            remove(Frame.board);
        }
        Frame.board = board;
        add(Frame.board);
        pack();
    }

    public void finishDungeon(boolean winOrLos) {
        if (winOrLos) {
            Board nextBoard;
            try {
                nextBoard = new Dungeon(this);
                phasis.add(nextBoard);
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

    public static void crash(Exception e) {
        System.err.println(e);
        Frame.board.frame.dispose();
    }
}