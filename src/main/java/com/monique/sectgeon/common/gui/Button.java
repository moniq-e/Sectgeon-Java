package com.monique.sectgeon.common.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Point;

public class Button<T extends Board> extends JButton {
    private Rectangle rect;
    private T board;

    public Button(T board, int x, int y, int width, int height, ActionListener actionListener) {
        super();
        this.board = board;
        rect = new Rectangle(x, y, width, height);

        setFocusable(false);
        setFocusPainted(false);
        setBounds(rect);
        addActionListener(actionListener);
        board.add(this);
    }

    public Button(T board, String txt, int x, int y, int width, int height, ActionListener actionListener) {
        super(txt);
        this.board = board;
        rect = new Rectangle(x, y, width, height);

        setFocusable(false);
        setFocusPainted(false);
        setBounds(rect);
        addActionListener(actionListener);
        board.add(this);
    }

    public Button(T board, BufferedImage image, int x, int y, int width, int height, ActionListener actionListener) {
        super(new ImageIcon(image));
        this.board = board;
        rect = new Rectangle(x, y, width, height);

        setFocusable(false);
        setFocusPainted(false);
        setBounds(rect);
        addActionListener(actionListener);
        board.add(this);
    }

    public void destroy() {
        board.remove(this);
    }

    public Point getPos() {
        return rect.getLocation();
    }

    public void setPos(int x, int y) {
        rect.setLocation(x, y);
    }

    public void setPos(Point pos) {
        rect.setLocation(pos);
    }

    public Rectangle getRect() {
        return rect;
    }
}