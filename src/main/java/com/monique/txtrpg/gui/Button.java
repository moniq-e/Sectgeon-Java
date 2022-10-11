package com.monique.txtrpg.gui;

import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.Point;

import com.monique.txtrpg.dungeons.Dungeon;

public class Button extends JButton {
    private Rectangle rect;
    private Dungeon dungeon;

    public Button(Dungeon dungeon, String txt, int x, int y, int width, int height, ActionListener actionListener) {
        super(txt);
        this.dungeon = dungeon;
        rect = new Rectangle(x, y, width, height);
        setBounds(rect);
        addActionListener(actionListener);
        this.dungeon.add(this);
    }

    public void destroy() {
        dungeon.remove(this);
    }

    //getters
    public Point getPos() {
        return (Point) rect.getLocation().clone();
    }

    public Rectangle getRect() {
        return (Rectangle) rect.clone();
    }
}