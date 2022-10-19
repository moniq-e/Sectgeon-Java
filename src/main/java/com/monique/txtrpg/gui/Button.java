package com.monique.txtrpg.gui;

import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.Point;

import com.monique.txtrpg.*;

public class Button extends JButton {
    private Rectangle rect;
    private Board board;

    public Button(Board board, String txt, int x, int y, int width, int height, ActionListener actionListener) {
        super(txt);
        setFocusable(false);
        this.board = board;
        rect = new Rectangle(x, y, width, height);
        setBounds(rect);
        addActionListener(actionListener);
        this.board.add(this);
    }

    public void destroy() {
        board.remove(this);
    }

    //getters
    public Point getPos() {
        return (Point) rect.getLocation().clone();
    }

    public Rectangle getRect() {
        return (Rectangle) rect.clone();
    }
}