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
        System.out.println("\nOpponent's turn...");
        try {
            Thread.sleep(600);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
        }

        int choice = randNum.nextInt(3); // 0 = attack, 1 = defend, 2 = taunt

        if (choice == 0) {
            System.out.println(name + " attacks.");
            opponent.takeDamage(getAttack());
        } else if (choice == 1) {
            System.out.println(name + " guards.");
            // needs implementation
        } else {
            System.out.println(name + " taunts"); 
        }

    }
}
