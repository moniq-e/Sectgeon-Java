package com.monique.sectgeon.common.listeners;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import com.monique.sectgeon.common.Frame;

import java.util.HashSet;
import java.util.HashMap;
import java.util.UUID;

public class Listener extends MouseAdapter implements ActionListener, KeyListener {
    private HashMap<Events, HashMap<UUID, Consumer<Object>>> listeners = new HashMap<Events, HashMap<UUID, Consumer<Object>>>();
    private HashSet<Character> pressedKeys = new HashSet<Character>();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listeners.get(Events.Action) != null) {
            for (Consumer<Object> listener : listeners.get(Events.Action).values()) {
                listener.accept(e);
            }
        }

        if (Frame.board != null) {
            Frame.board.tick(e);
        }
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if (listeners.get(Events.Key) != null) {
            if (!pressedKeys.contains(e.getKeyChar())) pressedKeys.add(e.getKeyChar());
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
        if (listeners.get(Events.Mouse) != null) {
            for (Consumer<Object> listener : listeners.get(Events.Mouse).values()) {
                listener.accept(e);
            }
        }
    }

    public void addListener(Events type, UUID id, Consumer<Object> consumer) {
        if (listeners.get(type) == null)
            createCustomEventType(type);
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