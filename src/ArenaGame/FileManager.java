/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

import java.io.*;
import java.util.*;

/**
 * The FileManager class handles file inputs and outputs Gladiator data, player
 * scores and game logs are handled currently
 */
public class FileManager {

    // asigns file names
    private static final String GLADIATOR_FILE = "gladiators.txt";
    private static final String SCORE_FILE = "scores.txt";
    private static final String BATTLE_LOG = "battles.log";

    // read methods
    /**
     * Pulls gladiators from gladiators.txt Use convention:
     * name,health,attack,defense
     *
     * @return List of Gladiators loaded from file
     */
    public static List<Gladiator> readGladiators() {
        List<Gladiator> gladiators = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(GLADIATOR_FILE))) {
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
            System.out.println("Gladiators.txt unreadable: " + error.getMessage());
        }
        return gladiators;
    }

    /**
     * Reads player scores from scores.txt Format: PlayerName:Score
     *
     * @return Map of player name -> score
     */
    public static Map<String, Integer> readScores() {
        Map<String, Integer> scores = new HashMap<>();
        File file = new File(SCORE_FILE);

        if (!file.exists()) {
            return scores;
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
     *
     * @return Set of player names
     */
    public static Set<String> loadPlayerNames() {
        return readScores().keySet();
    }

    // write methods
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
            System.out.println("Could not write to Gladiators.txt: " + error.getMessage());
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
            System.out.println("Could not write scores: " + error.getMessage());
        }
    }

    /**
     * Appends a battle log entry to battles.log
     */
    public static void writeBattleLog(String logEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BATTLE_LOG))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException error) {
            System.out.println("Could not write to battle log: " + error.getMessage());
        }
    }


    public static List<Gladiator> loadGladiators() {
        List<Gladiator> gladiators = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(GLADIATOR_FILE))) {
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
            System.out.println("Gladiators.txt unreadable: " + error.getMessage());
        }
        return gladiators;
    }

}
