package com.game.model.encounters;

import com.game.model.items.Item;
import com.game.model.entities.Player;

/** An encounter with a locked safe requiring a key to open for reward. */
public class LockedSafeEncounter extends Encounter {
    private Item containedItem;
    private boolean opened = false;

    public LockedSafeEncounter(Item containedItem) {
        super("A locked safe is here... perhaps a key could open it.");
        this.containedItem = containedItem;
    }

    @Override
    public void execute(Player player) {
        //open the safe if the player has a key
    }

    public boolean wasOpened() { return opened; }
}