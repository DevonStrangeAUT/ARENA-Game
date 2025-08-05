/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author hipst
 */
public class ARENA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Create player and enemy using the new classes
        PlayerGladiator player = new PlayerGladiator("Player", 100, 20, 5, scanner);
        EnemyGladiator enemy = new EnemyGladiator("Enemy", 100, 15, 3, random);

        while (player.isAlive() && enemy.isAlive()) {
            clearScreen();
            showStats(player, enemy);

            // Player's turn
            player.takeTurn(enemy);

            pause(800);
            if (!enemy.isAlive()) {
                break;
            }

            // Enemy's turn
            enemy.takeTurn(player);

            pause(1000);
        }

        System.out.println(player.isAlive() ? "\nYou WIN the battle!" : "\nYou LOSE the battle.");
        scanner.close();
    }

    static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void showStats(Gladiator p, Gladiator e) {
        System.out.println("======= ARENA STATUS =======");
        System.out.printf("%-10s HP: %-4d%n", p.getName(), p.getHealth());
        System.out.printf("%-10s HP: %-4d%n", e.getName(), e.getHealth());
        System.out.println("============================");
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
