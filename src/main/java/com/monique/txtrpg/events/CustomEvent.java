package com.monique.txtrpg.events;

import java.util.UUID;
import java.util.HashMap;

import com.monique.txtrpg.entities.Entity;

public abstract class CustomEvent {
    public final String ID = UUID.randomUUID().toString();
    public final String TYPE;
    private HashMap<String, String> stringAttributes = new HashMap<String, String>();
    private HashMap<String, Float> floatAttributes = new HashMap<String, Float>();
    protected Entity sourceEntity;
    protected Entity targetEntity;

    CustomEvent(String type) {
        TYPE = type;
    }

    public String getStringAttribute(String key) {
        return stringAttributes.get(key);
    }

    public void setStringAttribute(String key, String value) {
        stringAttributes.put(key, value);
    }

    public float getFloatAttribute(String key) {
        return floatAttributes.get(key);
    }

    public void setFloatAttribute(String key, float value) {
        floatAttributes.put(key, value);
    }

    public Entity getSourceEntity() {
        return sourceEntity;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }
}