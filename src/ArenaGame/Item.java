/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArenaGame;

/**
 *
 * @author hipst
 */
public class Item {

    private String itemName;
    private String itemDescription;
    private String itemType;
    private int value;

    public Item(String itemName, String itemDescription, String itemType, int value) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
        this.value = value;
    }

    public String getName() {
        return itemName;
    }

    public String getDescription() {
        return itemDescription;
    }

    public String getType() {
        return itemType;
    }

    public int getValue() {
        return value;
    }

}
