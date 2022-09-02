package com.txtrpg;
import java.util.Scanner;

/*
    main class, onde o jogo vai rodar
*/
public class Board {
    public static Scanner tec = new Scanner(System.in);
    public Player player;
    public Cronologia cron;

    public static void main(String[] args) {
        String name;
        Board board = new Board();

        print("Digite seu nome: ");
        name = tec.nextLine();

        board.player = new Player(name);
        board.cron = new Cronologia();
    }

    public static void print(String txt) {
        System.out.println(txt);
    }

    public static void print(String txt, Object... args) {
        System.out.printf(txt, args);
    }
}