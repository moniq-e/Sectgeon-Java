package com.monique.sectgeon.lair.cards;

import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.Utilities;

import com.monique.sectgeon.common.*;
import com.monique.sectgeon.common.events.*;
import com.monique.sectgeon.common.gui.Drawable;
import com.monique.sectgeon.lair.*;
import com.monique.sectgeon.lair.gui.LairGUI;

import org.json.JSONObject;

public class Card extends CardRegistry implements Drawable {
    private static BufferedImage image = Util.getImage("cards/carta_vazia.png");
    public final UUID ID = UUID.randomUUID();
    public final Lair LAIR;
    public Player owner;
    public int x, y;
    public JSONObject infos = new JSONObject();
    private boolean attacked = false;

    public Card(CardRegistry card, Player player) {
        super(new String(card.NAME), new String(card.DESC), card.TYPE, card.attack, card.life, card.speed, card.sacrifices, card.skill, card.triggers);
        pos = -1;

        LAIR = player.LAIR;
        owner = player;

        if (card.triggers != null && card.skill != null) {
            for (Triggers trigger : triggers) {
                LAIR.listener.addListener(trigger, ID, card.skill);
            }
        }
    }

    public void attack(Card card) {
        var e = (AttackEvent<Card>) LAIR.listener.dispatch(new AttackEvent<Card>(this, card, attack));

        e.getTarget().takeDamage(this, e.getDamage());
        setAttacked(true);
    }

    public void attack(Player player) {
        var e = (AttackEvent<Card>) LAIR.listener.dispatch(new AttackEvent<Card>(this, player, attack));

        e.getPlayer().takeDamage(this, e.getDamage());
    }

    /**
     * @param source who attacked
     */
    public void takeDamage(Card source, int damage) {
        if (damage > 0) {
            var e = (HurtEvent<Card>) LAIR.listener.dispatch(new HurtEvent<Card>(source, this, damage));

            if (e.getTarget() != this) {
                e.getTarget().takeDamage(source, e.getDamage());   
            } else {
                life -= e.getDamage();
                if (life <= 0) death(source);
            }
        }
    }

    /**
     * @param source who healed
     */
    public void heal(Card source, int value) {
        if (value > 0) {
            var e = (HealEvent<Card>) LAIR.listener.dispatch(new HealEvent<Card>(source, this, value));

            if (e.getTarget() != this) {
                e.getTarget().heal(source, e.getValue());
            } else {
                life += e.getValue();
            }
        }
    }

    public void death(Card killer) {
        if (TYPE != CardTypes.Spell) {
            if (life < 0) owner.takeDamage(this, Math.abs(life));

            LAIR.tableCards.remove(this);
            owner.cemetery.add(this);
            LAIR.listener.dispatch(new DeathEvent<Card>(this, killer));
        } else {
            owner.cemetery.add(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        Point pos;
        if (owner instanceof Enemy) {
            pos = LAIR.hud.EnemyTablePos[this.pos];
        } else {
            pos = LAIR.hud.PlayerTablePos[this.pos];
        }
        drawInTable(g, pos.x, pos.y);
    }

    public void drawInHand(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;

        if (LairGUI.cardDragged == ID) {
            var mouse = Util.getMouseRect();
            x = (int) (mouse.x) - getWidth() / 2;
            y = (int) (mouse.y) - getHeight() / 2;
        }
        if (LairGUI.cardHovered == ID) {
            drawCard(g, x, y, 0.5f);
            x = getWidth();
            y = LAIR.getHeight() / 2 - getHeight() / 2;
            g.drawString(NAME, x + getWidth() / 2 - g.getFontMetrics().stringWidth(NAME) / 2, y);
            drawDesc(g, x, y);
        }

        drawCard(g, x, y);
    }

    public void drawInTable(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;

        if (LairGUI.cardHovered == ID) {
            drawCard(g, x, y);
            x = getWidth();
            y = LAIR.getHeight() / 2 - getHeight() / 2;
            g.drawString(NAME, x + getWidth() / 2 - g.getFontMetrics().stringWidth(NAME) / 2, y);
            drawDesc(g, x, y);
        }

        drawCard(g, x, y);
    }

    private void drawCard(Graphics g, int x, int y) {
        int fontSize = g.getFont().getSize() / 5;
        var metrics = g.getFontMetrics();

        g.drawImage(image, x, y, getWidth(), getHeight(), LAIR);

        String attkString = String.valueOf(attack);
        g.drawString(attkString, x + getWidth() * 22 / 100 - metrics.stringWidth(attkString) / 2, y + getHeight() - fontSize);

        String lifeString = String.valueOf(life);
        g.drawString(lifeString, x + getWidth() * 82 / 100 - metrics.stringWidth(lifeString) / 2, y + getHeight() - fontSize);
    }

    private void drawCard(Graphics g, int x, int y, float alpha) {
        var g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
        drawCard(g, x, y);
        g2d.setComposite(AlphaComposite.SrcOver);
    }

    private void drawDesc(Graphics g, int x, int y) {
        var pane = new JTextPane();
        pane.setText(DESC);
        pane.setFont(g.getFont().deriveFont(Card.getHeight() * 0.12f));
        pane.setForeground(g.getColor());
        pane.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
        pane.setBackground(Color.black);
        pane.setBorder(BorderFactory.createLineBorder(g.getColor(), 1, true));
        pane.setBounds(0, 0, getWidth() * 2, LAIR.getHeight());

        //align vertical
        var doc = pane.getStyledDocument();
        var center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        int lineCount = (DESC.length() == 0) ? 1 : 0;
        try {
            int offset = DESC.length(); 
            while (offset > 0) {
                offset = Utilities.getRowStart(pane, offset) - 1;
                lineCount++;
            }
        } catch (Exception e) { e.printStackTrace(); }
        pane.setBounds(0, 0, pane.getWidth(), lineCount * g.getFontMetrics().getHeight());

        pane.paint(g.create(x - getWidth() / 2, y + getHeight() + g.getFont().getSize() * 2, pane.getWidth(), pane.getHeight()));
    }

    public boolean collidesMouse() {
        return Util.collides(new Rectangle(this.x, this.y, getWidth(), getHeight()), Util.getMouseRect());
    }

    public boolean isOnTable() {
        return LAIR.getTableCard(ID) != null;
    }

    private static int parsedWidth() {
        return Frame.board.getHeight() * 2 / 10;
    }

    public static int getWidth() {
        return image.getWidth() * (getHeight() / image.getHeight());
    }

    public static int getHeight() {
        return image.getHeight() * (parsedWidth() / image.getWidth());
    }

    public boolean getAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }
}