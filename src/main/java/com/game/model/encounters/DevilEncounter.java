package com.game.model.encounters;


import com.game.model.items.Item;
import com.game.model.entities.Player;

/** A devil encounter offering a trade-off deal to the player. */
public class DevilEncounter extends Encounter {
    private String offerDescription;
    private Item rewardItem;
    private int healthCost;  // how much health (or max health) the player must sacrifice for the reward

    public DevilEncounter(String offerDescription, Item rewardItem, int healthCost) {
        super("A devil offers you a trade.");
        this.offerDescription = offerDescription;
        this.rewardItem = rewardItem;
        this.healthCost = healthCost;
    }

    @Override
    public void execute(Player player) {
        // Present the offer to the player (e.g., via dialog): sacrifice health for reward.
        // The outcome (accept or decline) will be handled via controller based on player choice.
    }

    // Getters for offer details...
}