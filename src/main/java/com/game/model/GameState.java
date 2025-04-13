package com.game.model;

/**
 * Enum of possible high-level game states.
 */
public enum GameState {
    MAP_EXPLORATION,  // exploring the map (choosing next room)
    ROOM_ENTRY,       // (optional) state when entering a room, immediately triggers encounter
    ENCOUNTER,        // in a non-combat encounter (store, event, etc.)
    COMBAT,           // in a combat encounter (battle turn-cycle)
    PAUSED,           // game paused (menu open)
    GAME_OVER         // game ended (win or loss)
}
