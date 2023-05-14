package com.monique.sectgeon.lair.gui.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private int frameCount;                 // Counts ticks for change
    private int frameDelay;                 // frame delay 1-12 (You will have to play around with this)
    private int currentFrame;               // animations current frame
    private int animationDirection;         // animation direction (i.e counting forward or backward)
    private int totalFrames;                // total amount of frames for your animation
    private boolean stopped;                // has animations stopped
    private ArrayList<Frame> frames = new ArrayList<Frame>();

    public Animation(BufferedImage[] frames, int frameDelay) {
        this.frameDelay = frameDelay;
        stopped = true;

        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }

        frameCount = 0;
        currentFrame = 0;
        animationDirection = 1;
        totalFrames = this.frames.size();
    }

    public void start() {
        if (stopped && frames.size() > 0) {
            stopped = false;
        }
    }

    public void stop() {
        if (frames.size() > 0) {
            stopped = true;
        }
    }

    public void restart() {
        if (frames.size() > 0) {
            stopped = false;
            currentFrame = 0;
        }
    }

    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    private void addFrame(BufferedImage frame, int duration) {
        frames.add(new Frame(frame, duration));
    }

    public BufferedImage getFrame() {
        return frames.get(currentFrame).get();
    }

    public void update() {
        if (!stopped) {
            frameCount++;

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;

                if (currentFrame == totalFrames) {
                    currentFrame = 0;
                } else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }
    }
}