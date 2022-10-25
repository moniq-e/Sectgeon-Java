package com.monique.txtrpg.listeners;

import java.util.function.Consumer;
import java.util.HashMap;

import com.monique.txtrpg.events.CustomEvent;

public class CustomListener {
    private static HashMap<String, HashMap<String, Consumer<CustomEvent>>> consumers = new HashMap<String, HashMap<String, Consumer<CustomEvent>>>();
    
    public static void addConsumer(String id, Consumer<CustomEvent> consumer) {
        if (consumers.get(consumer.getClass().getGenericSuperclass().getTypeName()) == null) {
            createCustomEventType(consumer.getClass().getTypeName());
        } else {
            consumers.get(consumer.getClass().getTypeName()).put(id, consumer);
        }
    }

    public static void removeConsumer(String type, String id) {
        consumers.get(type).remove(id);
    }

    public static void dispatchEvent(CustomEvent e) {
        for (Consumer<CustomEvent> consumer : consumers.get(e.getClass().getName()).values()) {
            consumer.accept(e);
        }
    }

    public static void createCustomEventType(String name) {
        consumers.put(name, new HashMap<String, Consumer<CustomEvent>>());
    }
}