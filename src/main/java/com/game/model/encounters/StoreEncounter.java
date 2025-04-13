package com.game.model.encounters;

import com.game.model.items.Item;
import com.game.model.entities.Player;

import java.util.List;

/** An encounter where the player can buy items using gold (a shop). */
public class StoreEncounter extends Encounter {
    private List<Item> stock;  // items available for purchase
    // Prices could be derived from Item.value or adjusted here.

    public StoreEncounter(List<Item> stock) {
        super("You meet a merchant. You can buy items here.");
        this.stock = stock;
    }

    @Override
    public void execute(Player player) {
        // Trigger store interface (handled by view/controller).
        // Player can purchase items from stock using gold.
    }

    public List<Item> getStock() { return stock; }
}