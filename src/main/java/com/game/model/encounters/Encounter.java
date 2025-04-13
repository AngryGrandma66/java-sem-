package com.game.model.encounters;

import com.game.model.entities.Player;

/**
 * Abstract base class for different encounter types.
 * Each encounter triggers some game event when the player enters the room.
 */
public abstract class Encounter {
    protected String description;
    public Encounter(String description) { this.description = description; }

    /**
     * Execute the encounter's main effect. This could start a combat, open a store, etc.
     * @param player The player involved in the encounter.
     */
    public abstract void execute(Player player);
}