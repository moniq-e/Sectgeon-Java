package com.monique.txtrpg;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Listener implements ActionListener, KeyListener, MouseListener {
    Frame frame;
    ArrayList<Consumer<MouseEvent>> mouseClickedConsumers = new ArrayList<Consumer<MouseEvent>>();
    ArrayList<Consumer<KeyEvent>> keyPressedConsumers = new ArrayList<Consumer<KeyEvent>>();

    Listener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (frame.board != null) frame.board.actionPerformed(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (frame.board != null) frame.board.keyPressed(e);
        for (Consumer<KeyEvent> consumer : keyPressedConsumers) {
            consumer.accept(e);
        }
    }

    public void addKeyPressedConsumer(Consumer<KeyEvent> consumer) {
        keyPressedConsumers.add(consumer);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (frame.board != null) frame.board.mouseClicked(e);
        for (Consumer<MouseEvent> consumer : mouseClickedConsumers) {
            consumer.accept(e);
        }
    }

    public void addMouseClickedConsumer(Consumer<MouseEvent> consumer) {
        mouseClickedConsumers.add(consumer);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}