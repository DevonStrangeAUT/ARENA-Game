package ArenaGame;

import java.util.Scanner;
import java.util.Random;

class Gladiator {
    String name;
    int health;
    int attack;
    int defense;

    public Gladiator(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        int netDamage = damage - defense;
        if (netDamage < 0) netDamage = 0;
        health -= netDamage;
        System.out.println(name + " takes " + netDamage + " damage! Health left: " + Math.max(health, 0));
    }

    public int attack() {
        return attack;
    }
}

public class ARENA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Gladiator player = new Gladiator("Player", 100, 20, 5);
        Gladiator enemy = new Gladiator("Enemy", 100, 15, 3);

        while (player.isAlive() && enemy.isAlive()) {
            clearScreen();
            showStats(player, enemy);

            System.out.println("\nYour turn! Choose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Defend (no effect yet)");

            int choice = getPlayerChoice(scanner);
            if (choice == 1) {
                System.out.println("You attack!");
                enemy.takeDamage(player.attack());
            } else if (choice == 2) {
                System.out.println("You defend! (Defend effect not yet implemented)");
            } else {
                System.out.println("Invalid choice. You lose your turn.");
            }

            pause(800);

            if (!enemy.isAlive()) break;

            System.out.println("\n Enemy's turn...");
            pause(600);
            int enemyChoice = random.nextInt(2); // 0 or 1
            if (enemyChoice == 0) {
                System.out.println("Enemy attacks!");
                player.takeDamage(enemy.attack());
            } else {
                System.out.println("Enemy defends! (No effect yet)");
            }

            pause(1000);
        }

        System.out.println(player.isAlive() ? "\n You WIN the battle!" : "\n You LOSE the battle.");
        scanner.close();
    }

    static int getPlayerChoice(Scanner scanner) {
        System.out.print("> ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
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
        System.out.printf("%-10s HP: %-4d%n", p.name, p.health);
        System.out.printf("%-10s HP: %-4d%n", e.name, e.health);
        System.out.println("============================");
    }

    static void clearScreen() {
        // Simulated console clear (works in most terminals)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
