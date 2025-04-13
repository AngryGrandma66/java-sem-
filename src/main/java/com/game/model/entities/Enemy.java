package com.game.model.entities;

import com.game.model.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * An enemy entity controlled by the game. May have AI behavior and loot drops.
 */
public class Enemy extends Entity {
    private String enemyType;

    public String getEnemyType() {
        return enemyType;
    }

    public List<Item> getLootTable() {
        return lootTable;
    }

    public int getGoldReward() {
        return goldReward;
    }

    private List<Item> lootTable = new ArrayList<>();  // items this enemy can drop
    private int goldReward;

    public Enemy(String type) {
        this.enemyType = type;
        // Stats could be loaded from JSON config based on type.
    }

    /**
     * AI chooses an action to perform (e.g., attack or special move) on the player.
     * In a simple implementation, this could choose a random available action.
     * @param player the player (target).
     * @return the chosen Action.
     */
    public Action chooseAction(Player player) {

        return null;
    }

}
