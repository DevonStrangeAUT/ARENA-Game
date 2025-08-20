/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

import java.io.*;
import java.util.*;

/**
 * The FileManager class handles file inputs and outputs: Gladiator data, player
 * scores, and game logs.
 */
public class FileManager {

    // assigns file names
    private static final String GLADIATOR_FILE = "gladiators.txt";
    private static final String SCORE_FILE = "scores.txt";
    private static final String BATTLE_LOG = "battles.log";

    // READ METHODS
    /**
     * Pulls gladiators from gladiators.txt Use format:
     * name,health,attack,defense
     */
    public static List<EnemyGladiator> readGladiators() {
        List<EnemyGladiator> gladiators = new ArrayList<>();
        File file = new File(GLADIATOR_FILE);

        if (!file.exists()) {
            System.out.println("gladiators.txt not found! Using default gladiators.");
            gladiators.add(new EnemyGladiator("Spartacus", 100, 20, 5, new Random()));
            gladiators.add(new EnemyGladiator("Maximus", 120, 25, 10, new Random()));
            gladiators.add(new EnemyGladiator("Commodus", 90, 15, 3, new Random()));
            return gladiators;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0].trim();
                    int health = Integer.parseInt(data[1].trim());
                    int attack = Integer.parseInt(data[2].trim());
                    int defense = Integer.parseInt(data[3].trim());

                    gladiators.add(new EnemyGladiator(name, health, attack, defense, new Random()));
                }
            }
        } catch (IOException error) {
            System.out.println("Could not read gladiators.txt: " + error.getMessage());
        }
        return gladiators;
    }

    /**
     * Reads player scores from scores.txt Use format: PlayerName:Score
     */
    public static Map<String, Integer> readScores() {
        Map<String, Integer> scores = new HashMap<>();
        File file = new File(SCORE_FILE);

        if (!file.exists()) {
            return scores; // return empty if file missing
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 2) {
                    scores.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                }
            }
        } catch (IOException error) {
            System.out.println("Could not read scores.txt: " + error.getMessage());
        }
        return scores;
    }

    /**
     * Reads unique player names from scores.txt
     */
    public static Set<String> loadPlayerNames() {
        return readScores().keySet();
    }

    // WRITE METHODS
    /**
     * Saves gladiator data to gladiators.txt (overwrite file)
     */
    public static void writeGladiators(List<Gladiator> gladiators) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GLADIATOR_FILE))) {
            for (Gladiator g : gladiators) {
                writer.write(g.getName() + "," + g.getHealth() + "," + g.getAttack() + "," + g.getDefense());
                writer.newLine();
            }
        } catch (IOException error) {
            System.out.println("Could not write to gladiators.txt: " + error.getMessage());
        }
    }

    /**
     * Updates player scores in scores.txt (overwrite file)
     */
    public static void writeScores(Map<String, Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException error) {
            System.out.println("Could not write to scores.txt: " + error.getMessage());
        }
    }

    /**
     * Appends a battle log entry to battles.log
     */
    public static void writeBattleLog(String logEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BATTLE_LOG, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException error) {
            System.out.println("Could not write to battles.log: " + error.getMessage());
        }
    }
}
