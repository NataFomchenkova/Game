package org.example;

import org.example.creatures.*;

public class Main {

    public static void main(String[] args) {

/*        Scanner scanner = new Scanner(System.in);
        //String title = scanner.nextLine();
        int pages = scanner.nextInt();*/

        Creature creature1 = new Player( 100, 0, 56, -4, 16);
        Player creature2 = new Player(-3, 1, -516, 120, 16);
        Creature creature3 = new Monster(13, 31, 0, 6, 0);
        System.out.println(creature2);
        System.out.println(creature3);

    }
}