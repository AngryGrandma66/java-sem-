package com.game.model.items;

/**
 * Enumeration of all equipment slots for gear that the player can equip.
 * Two-handed weapons occupy both WEAPON1 and WEAPON2 slots.
 */
public enum EquipmentSlot {
    HEAD,    // helmet
    NECK,    // necklace
    RING,    // ring
    CHEST,   // chestplate
    LEGS,    // leggings
    BOOTS,
    GAUNTLETS,
    WEAPON1, // main-hand or single two-handed weapon
    WEAPON2  // off-hand or second weapon slot (or used by two-handed weapon)
}
