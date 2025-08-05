/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

import java.util.Scanner;

/**
 *
 * @author hipst
 */
public class PlayerGladiator extends Gladiator {

    private Scanner scanner;

    public PlayerGladiator(String name, int health, int attack, int defense, Scanner scanner) {
        super(name, health, attack, defense);
        this.scanner = scanner;
    }

    private int getChoice() {
        System.out.print("> ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    @Override
    public void takeTurn(Gladiator opponent) {
        System.out.println("\nYour turn. Select an action:");
        System.out.println("1. Attack.");
        System.out.println("2. Guard.");
        System.out.println("3. Use item.");
        System.out.println("4. Taunt.");

        int combatChoice = getChoice();
        if (combatChoice == 1) {
            System.out.println("You attack.");
            opponent.takeDamage(getAttack());
        } else if (combatChoice == 2) {
            System.out.println("You guard.");
            // requires implementation
        } else if (combatChoice == 3) {
            System.out.println("You use an item.");
            // requires implementation
        } else if (combatChoice == 4) {
            System.out.println("You taunt.");
        } else {
            System.out.println("Invalid choice. You lose your turn.");
        }

    }

}
