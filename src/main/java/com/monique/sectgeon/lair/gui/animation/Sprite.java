package com.monique.sectgeon.lair.gui.animation;

import java.awt.image.BufferedImage;

import com.monique.sectgeon.common.Util;

public class Sprite {
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 32;

    public static BufferedImage getSprite(int xGrid, int yGrid, String file) {
        if (spriteSheet == null) {
            spriteSheet = Util.getImage("animations/" + file);
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static BufferedImage getSprite(int xGrid, int yGrid, int tileSize, String file) {
        if (spriteSheet == null) {
            spriteSheet = Util.getImage("animations/" + file);
        }

        return spriteSheet.getSubimage(xGrid * tileSize, yGrid * tileSize, tileSize, tileSize);
    }
}