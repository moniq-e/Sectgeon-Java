package com.monique.txtrpg;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import java.util.HashMap;

public class Listener implements ActionListener, KeyListener, MouseListener {
    Frame frame;
    HashMap<String, Consumer<MouseEvent>> mouseClickedConsumers = new HashMap<String, Consumer<MouseEvent>>();
    HashMap<String, Consumer<KeyEvent>> keyPressedConsumers = new HashMap<String, Consumer<KeyEvent>>();

    Listener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (frame.board != null) frame.board.actionPerformed(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Consumer<KeyEvent> consumer : keyPressedConsumers.values()) {
            consumer.accept(e);
        }
    }

    public void addKeyPressedConsumer(String id, Consumer<KeyEvent> consumer) {
        keyPressedConsumers.put(id, consumer);
    }

    public void removeKeyPressedConsumer(String id) {
        keyPressedConsumers.remove(id);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Consumer<MouseEvent> consumer : mouseClickedConsumers.values()) {
            consumer.accept(e);
        }
    }

    public void addMouseClickedConsumer(String id, Consumer<MouseEvent> consumer) {
        mouseClickedConsumers.put(id, consumer);
    }

    public void removeMouseClickedConsumer(String id) {
        mouseClickedConsumers.remove(id);
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