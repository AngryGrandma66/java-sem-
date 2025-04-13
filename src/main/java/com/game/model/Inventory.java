package com.game.model;

import com.game.model.items.Consumable;
import com.game.model.items.Equipment;
import com.game.model.items.EquipmentSlot;
import com.game.model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the player's inventory, including equipped gear and carried items.
 */
public class Inventory {
    // Equipment slots mapped to the item equipped in each.
    private Map<EquipmentSlot, Equipment> equipmentSlots = new HashMap<>();
    // List of consumable or miscellaneous items in the inventory (not currently equipped).
    private List<Item> bagItems = new ArrayList<>();

    public Inventory() {
        // Initialize all equipment slots as empty (null).
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            equipmentSlots.put(slot, null);
        }
    }

    /**
     * Equips a piece of equipment, placing it in the appropriate slot(s).
     * If the slot was occupied, the old item is moved to bagItems.
     * @param item the equipment to equip.
     */
    public void equip(Equipment item) {
        EquipmentSlot slot = item.getSlot();
        if (item.isTwoHanded()) {
            // Two-handed weapon occupies both Weapon1 and Weapon2 slots.
            equipmentSlots.put(EquipmentSlot.WEAPON1, item);
            equipmentSlots.put(EquipmentSlot.WEAPON2, item);
        } else {
            equipmentSlots.put(slot, item);
            // If item is a one-handed weapon and going into either weapon slot, the other slot remains free.
        }
        // Remove the item from bag if it was there.
        bagItems.remove(item);
        // If an item was previously in this slot, add it to bag.
        // (In case of two-handed, this would unequip both weapon slots if they had items.)
    }

    /**
     * Unequips an item from a given slot, moving it to the bag.
     */
    public void unequip(EquipmentSlot slot) {
        Equipment item = equipmentSlots.get(slot);
        if (item != null) {
            equipmentSlots.put(slot, null);
            if (item.isTwoHanded()) {
                // Clear both slots if it was two-handed.
                equipmentSlots.put(EquipmentSlot.WEAPON1, null);
                equipmentSlots.put(EquipmentSlot.WEAPON2, null);
            }
            bagItems.add(item);
        }
    }

    /**
     * Adds an item to the inventory (to bag). Typically used for loot or rewards.
     */
    public void addItem(Item item) {
        if (item instanceof Equipment) {
            // Equipment can be directly equipped or stored; here we store it in bag by default.
        }
        bagItems.add(item);
    }

    /**
     * Uses a consumable item from the bag (potion, key).
     * Removes it from inventory and returns it for applying its effect.
     * @param item the consumable to use.
     */
    public Item use(Consumable item) {
        if (bagItems.remove(item)) {
            return item;
        }
        return null;
    }

    public Equipment getEquippedItem(EquipmentSlot slot) {
        return equipmentSlots.get(slot);
    }

    public List<Item> getBagItems() {
        return bagItems;
    }
}
