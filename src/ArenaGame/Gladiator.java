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
    protected int maxHealth;
    protected int attack;
    protected int defense;
    protected boolean isBlocking;

    public Gladiator(String name, int health, int attack, int defense, int maxHealth) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        // Damage reduction factor: smaller defense scales reasonably
        double damageMultiplier = 1 - ((double) defense / (defense + 50));
        int totalDamage = (int) Math.round(damage * damageMultiplier);

        if (isBlocking()) {
            totalDamage = (int) Math.ceil(totalDamage / 2.0);
        }

        totalDamage = Math.max(totalDamage, 1); // always deal at least 1 damage
        health -= totalDamage;

        System.out.println(name + " has taken " + totalDamage + " damage. Remaining Health = " + Math.max(health, 0));
    }

    public void setBlocking(boolean status) {
        isBlocking = status;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public abstract void takeTurn(Gladiator opponent);
}
