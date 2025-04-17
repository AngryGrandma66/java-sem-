package com.game.model.map;

import com.game.model.encounters.*;
import com.game.model.items.Consumable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for generating procedural dungeon maps (graphs of Room nodes) for each level.
 */
public class MapGenerator {
    private static final Random rand = new Random();

    /**
     * Generates a random DungeonMap for the given level.
     * The map is a directed acyclic graph of Room nodes from a start to a boss.
     *
     * @param levelNumber the level index (could affect difficulty of encounters).
     * @return a DungeonMap with connected rooms leading to a boss encounter.
     */
    public static DungeonMap generateMap(int levelNumber, int totalLayers) {
        if (totalLayers < 3 || totalLayers % 2 == 0) {
            throw new IllegalArgumentException("Total layers must be an odd number >= 3");
        }
        return generateMap(levelNumber, totalLayers, null);
    }

    /**
     * Generates a random DungeonMap using the specified total number of layers and optional seed.
     *
     * @param levelNumber the level index.
     * @param totalLayers total layers (must be an odd number >= 3).
     * @param seed        optional seed for deterministic generation (null if not used).
     * @return a DungeonMap with connected rooms.
     */
    public static DungeonMap generateMap(int levelNumber, int totalLayers, Long seed) {
        if (totalLayers < 3 || totalLayers % 2 == 0) {
            throw new IllegalArgumentException("Total layers must be an odd number >= 3");
        }
        if (seed != null) {
            rand.setSeed(seed);
        }

        // Generate a list of integers representing the number of nodes at each layer.
        List<Integer> layerSizes = generateLayers(totalLayers);

        // Create a list of room layers (each layer is a list of Room objects)
        List<List<Room>> roomLayers = new ArrayList<>();
        for (int i = 0; i < layerSizes.size() - 1; i++) {
            List<Room> layer = new ArrayList<>();
            for (int j = 0; j < layerSizes.get(i); j++) {
                //Test room
                layer.add(new Room(generateRandomEncounter(1, false)));
            }
            roomLayers.add(layer);
        }
        roomLayers.add(List.of(new Room(generateRandomEncounter(1, true))));

        // Generate connections between layers.
        // For each pair of adjacent layers:
        // - If next layer has one more node: each room at index j in current layer connects to child at indices j and j+1.
        // - If next layer has one fewer node: for each child index j, connect parent's node j and j+1.
        for (int i = 0; i < roomLayers.size() - 1; i++) {
            List<Room> currentLayer = roomLayers.get(i);
            List<Room> nextLayer = roomLayers.get(i + 1);
            int m = currentLayer.size();
            int n = nextLayer.size();
            if (n == m + 1) { // Increasing layer
                for (int j = 0; j < m; j++) {
                    currentLayer.get(j).addNextRoom(nextLayer.get(j));
                    currentLayer.get(j).addNextRoom(nextLayer.get(j + 1));
                }
            } else if (n == m - 1) { // Decreasing layer
                for (int j = 0; j < n; j++) {
                    currentLayer.get(j).addNextRoom(nextLayer.get(j));
                    currentLayer.get(j + 1).addNextRoom(nextLayer.get(j));
                }
            } else {
                throw new RuntimeException("Adjacent layer sizes differ by more than 1!");
            }
        }

        // The start room is the only room in the first layer.
        Room startRoom = roomLayers.getFirst().getFirst();
        return new DungeonMap(levelNumber, startRoom);
    }

    /**
     * Generates a list of layer sizes.
     * The start layer is 1, the internal layers are generated via a constrained random walk,
     * and the final layer is 1.
     *
     * @param totalLayers total number of layers (must be odd and >= 3).
     * @return List of integers representing the node count per layer.
     */
    private static List<Integer> generateLayers(int totalLayers) {
        List<Integer> layers = new ArrayList<>();
        layers.add(1); // Start layer

        if (totalLayers == 3) {
            layers.add(2);
        } else {
            // Internal layers: length = totalLayers - 2. They must start and end with 2.
            List<Integer> internal = generateInternalSequence(totalLayers - 2);
            layers.addAll(internal);
        }

        layers.add(1); // Final (boss/end) layer
        return layers;
    }

    /**
     * Generates an internal sequence of node counts (of given length) that starts and ends with 2.
     * The difference between consecutive numbers is ±1 and the numbers never drop below 2.
     *
     * @param length the length of the internal sequence.
     * @return a List of Integer representing the internal sequence.
     */
    private static List<Integer> generateInternalSequence(int length) {
        List<Integer> sequence = new ArrayList<>();
        sequence.add(2);
        if (length == 1) {
            return sequence;
        }
        if (!backtrack(sequence, 0, length)) {
            throw new RuntimeException("Couldn't generate a valid internal sequence");
        }
        return sequence;
    }

    /**
     * Recursive helper method to build the internal sequence.
     *
     * @param sequence the current sequence built so far.
     * @param i        current index in the sequence.
     * @param length   total desired length of the sequence.
     * @return true if a valid sequence was generated, false otherwise.
     */
    private static boolean backtrack(List<Integer> sequence, int i, int length) {
        if (i == length - 1) {
            return true;
        }
        int prev = sequence.get(i);
        List<Integer> candidates = new ArrayList<>();
        candidates.add(prev + 1);
        if (prev > 2) {
            candidates.add(prev - 1);
        }
        // Shuffle candidates to add randomness.
        for (int j = candidates.size() - 1; j > 0; j--) {
            int k = rand.nextInt(j + 1);
            int temp = candidates.get(j);
            candidates.set(j, candidates.get(k));
            candidates.set(k, temp);
        }
        for (Integer cand : candidates) {
            int remainingSteps = length - 1 - i;
            // Feasibility check: even if we decrease by 1 at every step, cand should not be too high.
            if (cand > 2 + remainingSteps) {
                continue;
            }
            sequence.add(cand);
            if (backtrack(sequence, i + 1, length)) {
                return true;
            }
            sequence.removeLast();  // backtrack
        }
        return false;
    }

    /**
     * Helper to generate a random encounter. If isBoss is true, generate a boss battle.
     * Otherwise, randomly pick from enemy battles or events.
     */
    private static Encounter generateRandomEncounter(int levelNumber, boolean isBoss) {
        return new ItemRewardEncounter(new Consumable("2", "adwa", "aa", true, true, 2));
    }
}
