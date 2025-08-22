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
    private Inventory inventory;

    public PlayerGladiator(String name, int health, int attack, int defense, Scanner scanner) {
        super(name, health, attack, defense);
        this.scanner = scanner;
        this.inventory = new Inventory();
        inventory.addItem(new Item("Health Potion", "Restores 20 HP", "heal", 20));
        inventory.addItem(new Item("Attack Boost", "Increases attack by 5", "buff", 5));
    }

    public Inventory getInventory() {
        return inventory;
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
        // reset guard status at the start of a turn
        setBlocking(false);

        System.out.println("\nYour turn. Select an action:");
        System.out.println("1. Attack.");
        System.out.println("2. Guard.");
        System.out.println("3. Use item.");
        System.out.println("4. Taunt.");
        System.out.println("5. Exit");

        int combatChoice = getChoice();

        switch (combatChoice) {
            case 1 -> {
                System.out.println("You attack.");
                opponent.takeDamage(getAttack());
                ArenaGame.BattleManager.logAction(getName(), "attacks.", getAttack());
            }
            case 2 -> {
                System.out.println("You guard.");
                setBlocking(true);
                ArenaGame.BattleManager.logAction(getName(), "guards.", 0);
            }
            case 3 -> {
                if (inventory.isEmpty()) {
                    System.out.println("You have no items to use!");
                } else {
                    inventory.display();
                    System.out.print("Select an item to use: ");
                    int itemChoice = getChoice() - 1;
                    Item selectedItem = inventory.useItem(itemChoice);
                    if (selectedItem != null) {
                        useItem(selectedItem);
                        ArenaGame.BattleManager.logAction(getName(), "uses an item.", 0);
                    }
                }
            }
            case 4 -> {
                System.out.println("You taunt.");
                ArenaGame.BattleManager.logAction(getName(), "taunts.", 0);
            }
            case 5 ->
                GameMenu.quit();
            default ->
                System.out.println("Invalid choice. You lose your turn.");

        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void useItem(Item item) {
        switch (item.getName()) {
            case "Health Potion" -> {
                setHealth(getHealth() + item.getValue());
                System.out.println(getName() + " healed for " + item.getValue() + " HP!");
            }
            case "Attack Boost" -> {
                setAttack(getAttack() + item.getValue());
                System.out.println(getName() + "'s attack increased by " + item.getValue() + "!");
            }
            default ->
                System.out.println("Nothing happens...");
        }
    }

}
