package com.game.model.items;

import com.game.model.entities.Entity;
import com.game.model.entities.Player;

/**
 * A consumable item, used either in battle or in exploration (like potions, keys, coins).
 */
public class Consumable extends Item {
    private boolean usableInBattle;
    private boolean usableOutsideBattle;

    public Consumable(String id, String name, String desc, boolean inBattle, boolean outsideBattle, int value) {
        super(id, name, desc, ItemType.CONSUMABLE_BATTLE, value);
        this.usableInBattle = inBattle;
        this.usableOutsideBattle = outsideBattle;
        if (!inBattle && outsideBattle) {
            this.type = ItemType.CONSUMABLE_MISC;
        } else if (inBattle && !outsideBattle) {
            this.type = ItemType.CONSUMABLE_BATTLE;
        } else {
            // Could be usable in both contexts.
        }
    }

    /**
     * Applies the effect of this consumable to the target. Could heal, buff, etc.
     * After use, the item would be removed from inventory.
     * @param user the player using the item.
     * @param target the target (could be user themselves or an enemy if offensive).
     */
    public void applyEffect(Player user, Entity target) {
        // For example, a healing potion: if target is player, target.heal(x).
        // A key might unlock something in an encounter (logic handled in encounter).
    }

    public boolean isUsableInBattle() { return usableInBattle; }
    public boolean isUsableOutsideBattle() { return usableOutsideBattle; }
}
