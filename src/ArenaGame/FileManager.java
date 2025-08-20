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
