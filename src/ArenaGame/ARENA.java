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

        PlayerGladiator player = new PlayerGladiator("Player", 100, 20, 5, scanner);
        EnemyGladiator enemy = new EnemyGladiator("Enemy", 100, 15, 3, random);

        // Run the battle via BattleManager
        BattleManager battle = new BattleManager(player, enemy);
        battle.startBattle();

        scanner.close();
    }
    
}
