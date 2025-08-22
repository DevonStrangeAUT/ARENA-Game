package ArenaGame;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * FileManager handles all file I/O for Gladiators, Scores, and Battle Logs.
 * Reads, writes, clears, and resets files safely.
 */
public class FileManager {

    private static final String GLADIATOR_FILE = "gladiators.txt";
    private static final String SCORE_FILE = "scores.txt";
    private static final String BATTLE_LOG = "battles.log";

    // ================= READ METHODS =================
    public static List<Gladiator> readGladiators() {
        List<Gladiator> gladiators = new ArrayList<>();
        Path path = Paths.get(GLADIATOR_FILE);

        if (!Files.exists(path)) {
            System.out.println("Gladiator file missing, creating defaults.");
            return resetGladiators();
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
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
        } catch (IOException e) {
            System.out.println("Error reading gladiators: " + e.getMessage());
        }
        return gladiators;
    }

    public static Map<String, Integer> readScores() {
        Map<String, Integer> scores = new HashMap<>();
        Path path = Paths.get(SCORE_FILE);

        if (!Files.exists(path)) {
            try { Files.createFile(path); } catch (IOException e) { System.out.println("Cannot create scores.txt"); }
            return scores;
        }

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 2) {
                    scores.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading scores.txt: " + e.getMessage());
        }
        return scores;
    }

    public static Set<String> loadPlayerNames() {
        return readScores().keySet();
    }

    // ================= WRITE METHODS =================
    public static void writeGladiators(List<Gladiator> gladiators) {
        Path path = Paths.get(GLADIATOR_FILE);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Gladiator g : gladiators) {
                writer.write(g.getName() + "," + g.getHealth() + "," + g.getAttack() + "," + g.getDefense());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing gladiators.txt: " + e.getMessage());
        }
    }

    public static void writeScores(Map<String, Integer> scores) {
        Path path = Paths.get(SCORE_FILE);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing scores.txt: " + e.getMessage());
        }
    }

    public static void writeBattleLog(String logEntry) {
        Path path = Paths.get(BATTLE_LOG);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write("[" + new Date() + "] " + logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing battles.log: " + e.getMessage());
        }
    }

    // ================= CLEAR METHODS =================
    public static void clearScores() {
        Path path = Paths.get(SCORE_FILE);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // file truncated
        } catch (IOException e) {
            System.out.println("Could not clear scores.txt: " + e.getMessage());
        }
    }

    public static void clearBattleLog() {
        Path path = Paths.get(BATTLE_LOG);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // file truncated
        } catch (IOException e) {
            System.out.println("Could not clear battles.log: " + e.getMessage());
        }
    }

    // ================= RESET METHODS =================
    public static List<Gladiator> resetGladiators() {
        List<Gladiator> defaults = new ArrayList<>();
        defaults.add(new EnemyGladiator("Spartacus", 100, 20, 5, new Random()));
        defaults.add(new EnemyGladiator("Maximus", 120, 25, 10, new Random()));
        defaults.add(new EnemyGladiator("Commodus", 90, 15, 3, new Random()));
        writeGladiators(defaults);
        System.out.println("Gladiators reset to defaults.");
        return defaults;
    }
}
