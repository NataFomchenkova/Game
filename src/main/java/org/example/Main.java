package org.example;

import org.example.creatures.*;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player(100, 0, 56, -4, 16);
        Player player2 = new Player(-3, 1, -516, 120, 16);
        Monster monster1 = new Monster(13, 15, 200, 6, 0);
        Monster monster2 = new Monster(13, 15, 2, 6, 0);

        player1.attack(monster1);
        player2.attack(monster1);
        monster1.attack(player1);
        monster2.attack(player1);
        player1.attack(monster2);
        player2.attack(monster2);
        player1.heal();

    }
}