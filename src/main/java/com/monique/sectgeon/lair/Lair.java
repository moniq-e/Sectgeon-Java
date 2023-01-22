package com.monique.sectgeon.lair;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.monique.sectgeon.Frame;
import com.monique.sectgeon.entities.*;
import com.monique.sectgeon.gui.*;
import com.monique.sectgeon.lair.cards.Card;
import com.monique.sectgeon.listeners.CustomListener;

public class Lair extends Board {
    private boolean playerTurn = true;
    private LairHUD hud = new LairHUD(this);
    private int turn = 1;
    public CustomListener<Card> listener = new CustomListener<Card>();
    public CustomListener<LairPlayer> playerListener = new CustomListener<LairPlayer>();
    public LairPlayer player = new LairPlayer(this, "default", 20);
    public ArrayList<LivingEntity> livingEntities = new ArrayList<LivingEntity>();
    public ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    public Lair(Frame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }
}