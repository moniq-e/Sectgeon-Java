package com.monique.txtrpg;

import javax.swing.Timer;
import javax.swing.JFrame;

import com.monique.txtrpg.entities.Player;

public class Frame extends JFrame {
    public final int width = 640;
    public final int height = 480;
    public final Player player = new Player(this.board, "default");
    public Board board;
    public Timer timer = new Timer(33, new Listener(this));

    public static void main(String[] args) {
        new Frame();
    }

    Frame() {
        super("TXTRPG");
    }
}