package com.monique.sectgeon.lair.gui.animation;

import java.awt.image.BufferedImage;

public class Frame {
    private BufferedImage frame;
    private int duration;

    public Frame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    public BufferedImage get() {
        return frame;
    }

    public void set(BufferedImage frame) {
        this.frame = frame;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}