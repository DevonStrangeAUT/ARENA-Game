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
/**
 * GameMenu handles the menu operations for ARENA It handles game loop, menus
 * and navigation
 */
public class GameMenu {

    private Scanner scanner;
    private Map<String, Integer> scores;
    private List<Gladiator> gladiators;
    private String playerName;
    public static boolean running = true;

    public GameMenu() {
        this.scanner = new Scanner(System.in);
        this.scores = FileManager.readScores();
        this.gladiators = FileManager.readGladiators();
    }

    
    
    /**
     * Game loop, show menu until player exits.
     */
    public void run() {
        playIntro();

        
        
        System.out.print("Who dares enter the ARENA?: ");
        playerName = scanner.nextLine().trim();
        if (!scores.containsKey(playerName)) {
            scores.put(playerName, 0);
        }
        
        while (running) {
            displayMenu();
            int playerChoice = getChoice();

            switch (playerChoice) {
                case 1 ->
                    startBattle();
                case 2 ->
                    viewScores();
                case 3 ->
                    viewBattleLog();
                case 4 -> {
                    FileManager.clearScores();
                    scores.clear();
                    System.out.println("Scores cleared.");
                    if (!scores.containsKey(playerName)) {
                        scores.put(playerName, 0);
                    }
                }
                case 5 -> {
                    FileManager.resetGladiators();
                    gladiators = FileManager.readGladiators();
                }
                case 6 -> {
                    FileManager.clearBattleLog();
                    System.out.println("Battle Log cleared.");
                }
                case 7 ->
                    quit();

                default ->
                    System.out.println("Enter a valid choice.");
            }
        }

        FileManager.writeScores(scores);
        playOutro();
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n===== ARENA MENU =====");
        System.out.println("1. Enter Battle");
        System.out.println("2. View Scores");
        System.out.println("3. View Battle Logs");
        System.out.println("4. Reset Scores");
        System.out.println("5. Reset Gladiators");
        System.out.println("6. Reset Battle Log");
        System.out.println("7. Exit");
        System.out.print("> ");
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException error) {
            return -1;
        }
    }

    private void startBattle() {
        if (gladiators.isEmpty()) {
            System.out.println("No enemies await...");
            return;
        }
        Random rand = new Random();
        Gladiator enemy = gladiators.get(rand.nextInt(gladiators.size()));
        PlayerGladiator player = new PlayerGladiator(playerName, 100, 20, 5, scanner);

        BattleManager battle = new BattleManager(player, enemy);
        boolean playerWon = battle.startBattle();

        if (playerWon) {
            int newScore = scores.getOrDefault(playerName, 0) + 1;
            scores.put(playerName, newScore);
            FileManager.writeScores(scores);
        }

        String result = playerWon ? playerName + " defeated gladiator: " + enemy.getName()
                : playerName + " was defeated by gladiator: " + enemy.getName();
        FileManager.writeBattleLog(result);
    }

    private void viewScores() {
        System.out.println("\n===== Player Scores =====");
        if (scores.isEmpty()) {
            System.out.println("Could not read scores.txt");
        } else {
            scores.forEach((name, score) -> System.out.println(name + ": " + score));
        }
    }

    private void viewBattleLog() {
        System.out.println("\n===== Battle Log =====");
        try (Scanner logReader = new Scanner(new java.io.File("battles.log"))) {
            if (!logReader.hasNextLine()) {
                System.out.println("No available at this time...");
            }
            while (logReader.hasNextLine()) {
                System.out.println(logReader.nextLine());
            }
        } catch (Exception error) {
            System.out.println("Could not read battle.log");
        }
    }

    private void playIntro() {
        String[] title = {
            "    _   ___ ___ _  _   _   ",
            "   /_\\ | _ \\ __| \\| | /_\\  ",
            "  / _ \\|   / _|| .` |/ _ \\ ",
            " /_/ \\_\\_|_\\___|_|\\_\\/_/ \\_\\",
            "                            "
        };

        System.out.println();

        try {
            for (String line : title) {
                System.out.println(line);
                Thread.sleep(250); // pause 150ms between lines
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nWelcome to the ARENA, warrior!");
    }

    private void playOutro() {
        System.out.println("This is a test line :)");
    }

    public static void quit() {
        running = false;
    }
}
