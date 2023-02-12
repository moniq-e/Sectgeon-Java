package com.monique.sectgeon.level.entities.npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.UUID;
import javax.swing.JMenuItem;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.common.listeners.Events;
import com.monique.sectgeon.lair.Lair;
import com.monique.sectgeon.level.dungeons.Dungeon;

public class NPC extends NPCRegistry implements Drawable {
    public final UUID ID = UUID.randomUUID();
    public Dungeon dungeon;
    
    public NPC(NPCRegistry npc, Dungeon dungeon) {
        super(new String(npc.NAME), npc.WIDTH, npc.HEIGHT);

        setPos(Util.random(0, dungeon.getWidth() - WIDTH), Util.random(0, dungeon.getHeight() - HEIGHT));

        this.dungeon = dungeon;
        dungeon.drawables.add(this);
        setPopupItems();
        setPopupConsumer();
    }

    private void setPopupItems() {
        JMenuItem challenge = new JMenuItem("Desafiar");
        challenge.addActionListener(e -> {
            dungeon.frame.setBoard(new Lair(dungeon));
        });
        POP.add(challenge);
    }

    private void setPopupConsumer() {
        Frame.listener.addListener(Events.Click, ID, note -> {
            MouseEvent e = (MouseEvent) note;
            if (Util.collides(getRect(), Util.getMouseRect())) {
                POP.show(dungeon, e.getX(), e.getY());
            }
        });
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(getPos().x, getPos().y, WIDTH, HEIGHT);
    }
}