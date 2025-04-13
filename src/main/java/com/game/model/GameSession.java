package com.game.model;

import com.game.model.entities.Player;
import com.game.model.map.DungeonMap;
import com.game.model.map.Room;

import java.io.Serializable;

/**
 * Holds the current game session state for saving/loading.
 * This can be serialized to JSON via Gson to save progress.
 */
public class GameSession implements Serializable {
    private Player player;
    private DungeonMap currentMap;
    private Room currentRoom;
    private int currentLevel;
    private GameState gameState;

    public GameSession(Player player) {
        this.player = player;
        this.currentLevel = 1;
        this.gameState = GameState.MAP_EXPLORATION;
    }

    public Player getPlayer() { return player; }
    public DungeonMap getCurrentMap() { return currentMap; }
    public Room getCurrentRoom() { return currentRoom; }
    public int getCurrentLevel() { return currentLevel; }
    public GameState getGameState() { return gameState; }

    public void setCurrentMap(DungeonMap map) { this.currentMap = map; }
    public void setCurrentRoom(Room room) { this.currentRoom = room; }
    public void setGameState(GameState state) { this.gameState = state; }
    public void setCurrentLevel(int level) { this.currentLevel = level; }
}
