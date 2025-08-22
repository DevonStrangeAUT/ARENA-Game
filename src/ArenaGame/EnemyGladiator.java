/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

import java.util.Random;

/**
 *
 * @author hipst
 */
public class EnemyGladiator extends Gladiator {

    private Random randNum;

    public EnemyGladiator(String name, int health, int attack, int defense, Random randNum) {
        super(name, health, attack, defense);
        this.randNum = randNum;
    }

    @Override
    public void takeTurn(Gladiator opponent) {
        // reset guard status at the start of a turn
        setBlocking(false);

        System.out.println("\nOpponent's turn...");
        try {
            Thread.sleep(600);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }

        int choice;
        if (health < getHealth() * 0.3) {
            choice = 1; // guarding 
        } else if (opponent.getHealth() < opponent.getHealth() * 0.3) {
            choice = 0; //attack
        } else {
            int rand = randNum.nextInt(10); // 0-9
            if (rand < 5) {
                choice = 0;         // attack
            } else if (rand < 9) {
                choice = 1;    // guard
            } else {
                choice = 2; // taunt
            }
        }

        switch (choice) {
            case 0 -> {
                System.out.println(name + " attacks.");
                opponent.takeDamage(getAttack());
                ArenaGame.BattleManager.logAction(getName(), "attacks.", getAttack());
            }
            case 1 -> {
                setBlocking(true);
                System.out.println(name + " prepares to guard your attack.");
                ArenaGame.BattleManager.logAction(getName(), "guards.", 0);
            }
            case 2 -> {
                System.out.println(name + " taunts you.");
                ArenaGame.BattleManager.logAction(getName(), "taunts.", 0);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
