package com.monique.sectgeon.listeners;

import java.util.HashMap;
import java.util.function.Consumer;

import com.monique.sectgeon.events.*;

public class CustomListener<T> {
    private HashMap<Triggers, HashMap<String, Consumer<CustomEvent<T>>>> listeners = new HashMap<Triggers, HashMap<String, Consumer<CustomEvent<T>>>>();

    public void addListener(Triggers type, String id, Consumer<CustomEvent<T>> consumer) {
        if (listeners.get(type) == null) createCustomEventType(type);
        listeners.get(type).put(id, consumer);
    }

    public void removeListener(Triggers type, String id) {
        listeners.get(type).remove(id);
    }

    public CustomEvent<T> dispatchEvent(CustomEvent<T> e) {
        if (!listeners.isEmpty()) {
            for (Consumer<CustomEvent<T>> listener : listeners.get(e.TYPE).values()) {
                listener.accept(e);
            }
        }
        return e;
    }

    public void createCustomEventType(Triggers type) {
        listeners.put(type, new HashMap<String, Consumer<CustomEvent<T>>>());
    }
}