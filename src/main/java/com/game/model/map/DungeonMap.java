package com.game.model.map;

/**
 * Represents the dungeon map (graph of Rooms) for a given level.
 * The map starts at an entrance room and leads through encounters to a boss.
 */
public class DungeonMap {
    private Room startRoom;
    private int levelNumber;

    public DungeonMap(int levelNumber, Room startRoom) {
        this.levelNumber = levelNumber;
        this.startRoom = startRoom;
    }

    public Room getStartRoom() { return startRoom; }
    public int getLevelNumber() { return levelNumber; }
}
