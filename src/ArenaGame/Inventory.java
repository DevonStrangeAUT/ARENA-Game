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
public class Inventory {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("===== Inventory =====");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getDescription());
        }
    }

    public Item useItem(int index) {
        if (index < 0 || index >= items.size()) {
            System.out.println("Invalid item, please choose again.");
            return null;
        }
        return items.remove(index);
    }
}
