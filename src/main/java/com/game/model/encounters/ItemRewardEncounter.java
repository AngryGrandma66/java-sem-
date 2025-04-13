package com.game.model.encounters;

import com.game.model.items.Item;
import com.game.model.entities.Player;

/** An encounter that grants the player an item (loot) without combat. */
public class ItemRewardEncounter extends Encounter {
    private Item reward;

    public ItemRewardEncounter(Item reward) {
        super("You found a treasure!");
        this.reward = reward;
    }

    @Override
    public void execute(Player player) {
        // Give the item to the player.
        // Maybe display a message about the found item.
    }
}