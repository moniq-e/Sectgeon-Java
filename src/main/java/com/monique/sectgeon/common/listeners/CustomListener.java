package com.monique.sectgeon.common.listeners;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import com.monique.sectgeon.common.events.*;

public class CustomListener<T> {
    private ConcurrentHashMap<Triggers, ConcurrentHashMap<UUID, Consumer<CustomEvent<T>>>> listeners = new ConcurrentHashMap<Triggers, ConcurrentHashMap<UUID, Consumer<CustomEvent<T>>>>();

    public void addListener(Triggers type, UUID id, Consumer<CustomEvent<T>> consumer) {
        if (listeners.get(type) == null) createCustomEventType(type);
        synchronized(listeners.get(type)) {
            listeners.get(type).put(id, consumer);
        }
    }

    public void removeListener(Triggers type, UUID id) {
        listeners.get(type).remove(id);
    }

    public CustomEvent<T> dispatch(CustomEvent<T> e) {
        if (!listeners.isEmpty()) {
            if (listeners.get(e.TYPE) != null) {
                synchronized(listeners.get(e.TYPE)) {
                    //TODO: sort by speed
                    listeners.get(e.TYPE).forEach((id, listener) -> listener.accept(e.setSkillID(id)));
                }
            }
        }
        return e;
    }

    public void createCustomEventType(Triggers type) {
        listeners.put(type, new ConcurrentHashMap<UUID, Consumer<CustomEvent<T>>>());
    }

    public void clear() {
        listeners.clear();
    }
}