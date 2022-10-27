package com.monique.txtrpg.listeners;

import java.util.function.Consumer;
import java.util.HashMap;

import com.monique.txtrpg.events.CustomEvent;

public class CustomListener {
    private static HashMap<String, HashMap<String, Consumer<CustomEvent>>> consumers = new HashMap<String, HashMap<String, Consumer<CustomEvent>>>();

    public static void addConsumer(String type, String id, Consumer<CustomEvent> consumer) {
        if (consumers.get(type) == null) createCustomEventType(type);
        consumers.get(type).put(id, consumer);
    }

    public static void removeConsumer(String type, String id) {
        consumers.get(type).remove(id);
    }

    public static void dispatchEvent(CustomEvent e) {
        if (consumers.isEmpty()) return;
        for (Consumer<CustomEvent> consumer : consumers.get(e.TYPE).values()) {
            consumer.accept(e);
        }
    }

    public static void createCustomEventType(String name) {
        consumers.put(name, new HashMap<String, Consumer<CustomEvent>>());
    }
}