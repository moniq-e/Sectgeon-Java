package com.monique.sectgeon.common.listeners;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import java.util.HashSet;
import java.util.HashMap;
import java.util.UUID;

public class Listener extends MouseAdapter implements ActionListener, KeyListener {
    private HashMap<Events, HashMap<UUID, Consumer<Object>>> listeners = new HashMap<Events, HashMap<UUID, Consumer<Object>>>();
    private HashSet<Character> pressedKeys = new HashSet<Character>();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listeners.get(Events.Action) != null) {
            //ConcurrentModificationException
            for (int i = 0; i < listeners.get(Events.Action).size(); i++) {
                @SuppressWarnings("unchecked")
                Consumer<Object> l = (Consumer<Object>) listeners.get(Events.Action).values().toArray()[i];
                l.accept(e);
            }
        }
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if (listeners.get(Events.Key) != null) {
            pressedKeys.add(e.getKeyChar());
            for (Consumer<Object> listener : listeners.get(Events.Key).values()) {
                listener.accept(pressedKeys);
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        if (!pressedKeys.isEmpty()) pressedKeys.remove(e.getKeyChar());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (listeners.get(Events.Click) != null) {
            for (Consumer<Object> listener : listeners.get(Events.Click).values()) {
                listener.accept(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (listeners.get(Events.Move) != null) {
            for (Consumer<Object> listener : listeners.get(Events.Move).values()) {
                listener.accept(e);
            }
        }
    }

    public void addListener(Events type, UUID id, Consumer<Object> consumer) {
        if (listeners.get(type) == null) createCustomEventType(type);
        listeners.get(type).put(id, consumer);
    }

    public void removeListener(Events type, UUID id) {
        listeners.get(type).remove(id);
    }

    public void createCustomEventType(Events type) {
        listeners.put(type, new HashMap<UUID, Consumer<Object>>());
    }

    public void clear() {
        listeners.clear();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}