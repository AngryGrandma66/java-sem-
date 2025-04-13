package com.game.model.map;

import com.game.model.encounters.*;

import java.util.Random;

/**
 * Utility class for generating procedural dungeon maps (graphs of Room nodes) for each level.
 */
public class MapGenerator {
    private static Random rand = new Random();

    /**
     * Generates a random DungeonMap for the given level.
     * The map is a directed acyclic graph of Room nodes from a start to a boss.
     *
     * @param levelNumber the level index (could affect difficulty of encounters).
     * @return a DungeonMap with connected rooms leading to a boss encounter.
     */
    public static DungeonMap generateMap(int levelNumber) {
        Room start = new Room(generateRandomEncounter(levelNumber, false));
        return new DungeonMap(levelNumber, start);
    }

    /**
     * Helper to generate a random encounter. If isBoss is true, generate a boss battle.
     * Otherwise, randomly pick from enemy battles or events.
     */
    private static Encounter generateRandomEncounter(int levelNumber, boolean isBoss) {
        return null;
    }
}
