package com.game.model.entities;

import com.game.model.Inventory;
import com.game.model.items.Consumable;
import com.game.model.items.Equipment;

/**
 * The Player character, extending Entity with inventory and progression fields.
 */
public class Player extends Entity {
    private Inventory inventory;
    private int gold;   // currency held by the player
    private int keys;   // number of keys for locked safes, etc.

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.gold = 0;
        this.keys = 0;
        // Initialize base stats (could be loaded from config or defaults)
    }

    /**
     * Equips an item from inventory into the appropriate slot, unlocking its action and stat bonuses.
     * @param item the equipment to equip.
     */
    public void equipItem(Equipment item) {
        inventory.equip(item);
        // Update player's stats with item's bonuses (increase defense, strength, ).
        // Also add any actions granted by the item to availableActions.
    }

    /**
     * Uses a consumable item from inventory.
     * @param item the consumable item to use.
     */
    public void useItem(Consumable item) {
        // Remove item from inventory if one-time use.
        inventory.use(item);
        // Apply item effects: heal player, grant buff, or key unlock (handled in encounter).
    }

    // Additional player-specific methods ( gainGold, spendGold...)
}
