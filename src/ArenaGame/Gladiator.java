/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

/**
 *
 * @author hipst
 */
// Abstract base gladiator class - contains stat info, alive status, damage taking function and stat getters.
public abstract class Gladiator {

    protected String name;
    protected int health;
    protected int attack;
    protected int defense;

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
        int totalDamage = damage - defense;
        if (totalDamage < 0) {
            totalDamage = 0;
        }
        health -= totalDamage;
        System.out.println(name + " has taken " + totalDamage + " damage. Remaining Health: " + Math.max(health, 0));
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public abstract void takeTurn(Gladiator opponent);
}
