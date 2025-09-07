package ArenaGame;

import java.util.*;

/**
 * BattleManager handles the combat between two gladiators.
 * 
 * Responsibilities:
 * - Execute turn-based combat
 * - Determine battle outcome
 * - Log actions to battle log
 */
public class BattleManager {

    private PlayerGladiator player;
    private Gladiator enemy;

    public BattleManager(PlayerGladiator player, Gladiator enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public boolean startBattle() {
        while (player.isAlive() && enemy.isAlive() && GameMenu.running) {
            clearScreen();
            showStats();

            player.takeTurn(enemy);
            if (!GameMenu.running) {
                return false; 
            }

            pause(800);
            if (!enemy.isAlive()) {
                break;
            }

            enemy.takeTurn(player);
            pause(300);
        }

        if (!GameMenu.running) {
            return false; 
        }

        boolean playerWon = player.isAlive();
        System.out.println(playerWon ? "\nYou stand victorious." : "\nYou lose the battle");
        recordResult(playerWon);
        return playerWon;
    }

    private void recordResult(boolean playerWon) {
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
        System.out.printf("%-10s HP: %-4d/%-4d%n", player.getName(), player.getHealth(), player.getMaxHealth());
        System.out.printf("%-10s HP: %-4d %s%n", enemy.getName(), enemy.getHealth(),
                enemy.isBlocking() ? "(Guarding)" : "");
        System.out.println("============================");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void logAction(String actor, String action, int damage) {
        String entry = actor + " " + action;
        if (damage > 0) {
            entry += " for " + damage + " damage.";
        }
        FileManager.writeBattleLog(entry);
    }

    private void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
