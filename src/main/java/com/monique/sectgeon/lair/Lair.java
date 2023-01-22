package com.monique.sectgeon.lair;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.monique.sectgeon.Frame;
import com.monique.sectgeon.entities.*;
import com.monique.sectgeon.gui.*;

public class Lair extends Board {
    private boolean playerTurn = true;
    private LairHUD hud = new LairHUD(this);
    private int turn = 1;
    public LairPlayer player = new LairPlayer(this, "default", 20);
    public ArrayList<LivingEntity> livingEntities = new ArrayList<LivingEntity>();
    public ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    public Lair(Frame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}