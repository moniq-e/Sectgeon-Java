package com.monique.sectgeon.common.listeners;

import java.util.UUID;
import java.util.HashMap;
import java.util.function.Consumer;

import com.monique.sectgeon.common.events.*;

public class CustomListener<T> {
    private HashMap<Triggers, HashMap<UUID, Consumer<CustomEvent<T>>>> listeners = new HashMap<Triggers, HashMap<UUID, Consumer<CustomEvent<T>>>>();

    public void addListener(Triggers type, UUID id, Consumer<CustomEvent<T>> consumer) {
        if (listeners.get(type) == null) createCustomEventType(type);
        listeners.get(type).put(id, consumer);
    }

    public void removeListener(Triggers type, UUID id) {
        listeners.get(type).remove(id);
    }

    public CustomEvent<T> dispatch(CustomEvent<T> e) {
        if (!listeners.isEmpty()) {
            if (listeners.get(e.TYPE) != null) {
                if (!listeners.get(e.TYPE).isEmpty()) {
                    listeners.get(e.TYPE).forEach((id, listener) -> {
                        listener.accept(e.setSkillID(id));
                    });
                }
            }
        }
        return e;
    }

    public void createCustomEventType(Triggers type) {
        listeners.put(type, new HashMap<UUID, Consumer<CustomEvent<T>>>());
    }

    public void clear() {
        listeners.clear();
    }
}