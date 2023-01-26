package com.monique.sectgeon.common.listeners;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DefaultListener implements ActionListener, KeyListener, MouseListener {
    private HashMap<DefaultEvents, HashMap<UUID, Consumer<Object>>> listeners = new HashMap<DefaultEvents, HashMap<UUID, Consumer<Object>>>();
    private ArrayList<Consumer<Object>> boards = new ArrayList<Consumer<Object>>();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listeners.get(DefaultEvents.Action) != null) {
            for (Consumer<Object> listener : listeners.get(DefaultEvents.Action).values()) {
                listener.accept(e);
            }
        }

        if (!boards.isEmpty()) boards.get(0).accept(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (listeners.get(DefaultEvents.Key) != null) {
            for (Consumer<Object> listener : listeners.get(DefaultEvents.Key).values()) {
                listener.accept(e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (listeners.get(DefaultEvents.Mouse) != null) {
            for (Consumer<Object> listener : listeners.get(DefaultEvents.Mouse).values()) {
                listener.accept(e);
            }
        }
    }

    public void addListener(DefaultEvents type, UUID id, Consumer<Object> consumer) {
        if (listeners.get(type) == null) createCustomEventType(type);
        listeners.get(type).put(id, consumer);
    }

    public void removeListener(DefaultEvents type, UUID id) {
        listeners.get(type).remove(id);
    }

    public void createCustomEventType(DefaultEvents type) {
        listeners.put(type, new HashMap<UUID, Consumer<Object>>());
    }

    public void clear() {
        listeners.clear();
    }

    public void addBoardListener(Consumer<Object> consumer) {
        boards.add(consumer);
    }

    public void removeBoardListener() {
        boards.remove(0);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}