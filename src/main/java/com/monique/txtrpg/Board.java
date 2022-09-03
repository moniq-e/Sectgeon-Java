package com.monique.txtrpg;

import java.util.Scanner;
import com.monique.txtrpg.entities.Player;

/*
    main class, onde o jogo vai rodar
*/
public class Board {
    public static Scanner tec = new Scanner(System.in);
    public final Player player;
    public final Cronologia cron;

    public static void main(String[] args) {
        print("Digite seu nome: ");
        new Board(tec.nextLine());
    }

    Board(String name) {
        player = new Player(this, name);
        cron = new Cronologia(this);
    }

    public static void print(String txt) {
        System.out.println(txt);
    }

    public static void print(String txt, Object... args) {
        System.out.printf(txt + "\n", args);
    }

    public static void diff() {
        System.out.println("\n" + "-".repeat(30) + "\n");
    }
}