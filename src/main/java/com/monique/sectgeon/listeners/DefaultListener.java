package com.monique.sectgeon.listeners;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import com.monique.sectgeon.Frame;

import java.util.HashMap;


public class DefaultListener implements ActionListener, KeyListener, MouseListener {
    Frame frame;
    HashMap<String, Consumer<MouseEvent>> mouseClickedConsumers = new HashMap<String, Consumer<MouseEvent>>();
    HashMap<String, Consumer<KeyEvent>> keyPressedConsumers = new HashMap<String, Consumer<KeyEvent>>();

    public DefaultListener(Frame frame) {
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
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }
}