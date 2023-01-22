package com.monique.sectgeon.lair;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.UUID;

import com.monique.sectgeon.Frame;
import com.monique.sectgeon.events.SummonEvent;
import com.monique.sectgeon.gui.*;
import com.monique.sectgeon.lair.cards.Card;
import com.monique.sectgeon.listeners.CustomListener;

public class Lair extends Board {
    private LairHUD hud = new LairHUD(this);
    private int turn = 1;
    public CustomListener<Card> listener = new CustomListener<Card>();
    public LairPlayer player = new LairPlayer(this, "default", 20);
    public HashMap<UUID, Card> tableCards = new HashMap<UUID, Card>();

    public Lair(Frame frame) {
        super(frame);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.decode("#aeebe0"));

        hud.draw(g);
        for (Card card : tableCards.values()) {
            card.draw(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
    }

    public void placeCard(Card card) {
        //TODO
    }

    public void summon(Card source, Card summoned, int pos) {
        SummonEvent<Card> e = (SummonEvent<Card>) listener.dispatchEvent(new SummonEvent<Card>(source, summoned, pos));

        if (e.getSummoned() != null) {
            tableCards.put(e.getSummoned().ID, e.getSummoned());
        }
    }
}