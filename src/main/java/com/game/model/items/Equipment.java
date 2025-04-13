package com.game.model.items;

import com.game.model.entities.Action;

import java.util.List;

/**
 * An equippable item (gear) that can be placed in an Inventory slot and may grant stat bonuses or actions.
 */
public class Equipment extends Item {
    private EquipmentSlot slot;    // which slot this item fits into
    private boolean twoHanded;
    // Stat bonuses provided by this gear
    private int bonusStrength;
    private int bonusDefense;
    private int bonusSpeed;
    private int bonusDexterity;
    private int bonusIntelligence;
    private int bonusMaxHealth;
    private int bonusMaxMana;
    private int bonusManaPerTurn;
    private int bonusCritChance;

    // Action granted by this equipment (if any)
    private List<Action> grantedActions;

    public Equipment(String id, String name, String desc, EquipmentSlot slot, boolean twoHanded, int value) {
        super(id, name, desc, ItemType.GEAR, value);
        this.slot = slot;
        this.twoHanded = twoHanded;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public int getBonusStrength() {
        return bonusStrength;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public int getBonusSpeed() {
        return bonusSpeed;
    }

    public int getBonusDexterity() {
        return bonusDexterity;
    }

    public int getBonusIntelligence() {
        return bonusIntelligence;
    }

    public int getBonusMaxHealth() {
        return bonusMaxHealth;
    }

    public int getBonusMaxMana() {
        return bonusMaxMana;
    }

    public int getBonusManaPerTurn() {
        return bonusManaPerTurn;
    }

    public int getBonusCritChance() {
        return bonusCritChance;
    }

    public List<Action> getGrantedActions() {
        return grantedActions;
    }
}
