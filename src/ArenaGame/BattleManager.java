/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

import java.util.*;
/**
 *
 * @author hipst
 */
public class BattleManager {
    
    private PlayerGladiator player;
    private EnemyGladiator enemy;

    public BattleManager(PlayerGladiator player, EnemyGladiator enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void startBattle() {
        while (player.isAlive() && enemy.isAlive()) {
            clearScreen();
            showStats();

            player.takeTurn(enemy);

            pause(800);
            if (!enemy.isAlive()) {
                break;
            }

            enemy.takeTurn(player);

            pause(1000);
        }

        System.out.println(player.isAlive() ? "\nYou WIN the battle!" : "\nYou LOSE the battle.");
        recordResult();
    }

    private void recordResult() {
        String result = player.isAlive()
                ? player.getName() + " won against " + enemy.getName()
                : player.getName() + " lost to " + enemy.getName();

        Map<String, Integer> scores = FileManager.readScores();
        if (player.isAlive()) {
            scores.put(player.getName(), scores.getOrDefault(player.getName(), 0) + 1);
        }
        FileManager.writeScores(scores);
        FileManager.writeBattleLog(result);
    }

    private void showStats() {
        System.out.println("======= ARENA STATUS =======");
        System.out.printf("%-10s HP: %-4d%n", player.getName(), player.getHealth());
        System.out.printf("%-10s HP: %-4d%n", enemy.getName(), enemy.getHealth());
        System.out.println("============================");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
