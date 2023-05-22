package com.monique.sectgeon.lair;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TickedFor<T> {
    private final int TICKS;
    private final ArrayList<T> ORIGINAL_ARRAY = new ArrayList<T>();
    private final ArrayList<T> ARRAY = new ArrayList<T>();
    private final Consumer<T> CONSUMER;
    private final Consumer<ArrayList<T>> POST;
    private final Consumer<T> POST_EACH;
    private int ticks = 0;

    public TickedFor(int ticks, ArrayList<T> array, Consumer<T> consumer, Consumer<T> postEach, Consumer<ArrayList<T>> post) {
        TICKS = ticks;
        ORIGINAL_ARRAY.addAll(array);
        ARRAY.addAll(array);
        CONSUMER = consumer;
        POST_EACH = postEach;
        POST = post;
    }

    public boolean tick() {
        if (ARRAY.size() > 0) {
            if (ticks == 0) {
                CONSUMER.accept(ARRAY.get(0));
            }
            ticks++;
            if (ticks == TICKS) {
                if (POST_EACH != null) POST_EACH.accept(ARRAY.remove(0));
                ticks = 0;
            }
            return false;
        } else {
            if (POST != null) POST.accept(ORIGINAL_ARRAY);
            return true;
        }
    }
}