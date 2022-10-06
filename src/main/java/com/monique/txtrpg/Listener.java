package com.monique.txtrpg;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Listener implements ActionListener, KeyListener, MouseListener {
    Frame janela;

    Listener(Frame janela) {
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (janela.board != null) janela.board.actionPerformed(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (janela.board != null) janela.board.keyPressed(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (janela.board != null) janela.board.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}