package com.game.model.map;

import java.util.*;

/**
 * A helper class to print a DungeonMap as an ASCII graph.
 * Uses '*' for rooms and draws connections with '/', '\' and '|'.
 */
public class GraphPrinter {

    /**
     * Prints the given DungeonMap to the console as an ASCII diagram.
     *
     * @param dungeonMap the map to print.
     */
    public static void printDungeonMap(DungeonMap dungeonMap) {
        // Compute the layers via breadth-first search.
        List<List<Room>> layers = new ArrayList<>();
        Set<Room> visited = new HashSet<>();
        List<Room> currentLayer = new ArrayList<>();
        currentLayer.add(dungeonMap.getStartRoom());
        layers.add(currentLayer);
        visited.add(dungeonMap.getStartRoom());

        while (true) {
            List<Room> nextLayer = new ArrayList<>();
            for (Room room : currentLayer) {
                // Assuming each room's children are in the next layer.
                for (Room child : room.getNextRooms()) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nextLayer.add(child);
                    }
                }
            }
            if (nextLayer.isEmpty()) {
                break;
            }
            layers.add(nextLayer);
            currentLayer = nextLayer;
        }

        // Determine the global width for printing.
        int maxNodes = 0;
        for (List<Room> layer : layers) {
            maxNodes = Math.max(maxNodes, layer.size());
        }
        int globalWidth = maxNodes * 2 - 1;
        int numPrintRows = 2 * layers.size() - 1;

        // Create a grid (2D char array) for the ASCII drawing.
        char[][] grid = new char[numPrintRows][globalWidth];
        for (int i = 0; i < numPrintRows; i++) {
            Arrays.fill(grid[i], ' ');
        }

        // For each layer, compute horizontal positions for each node and draw the node.
        // Also store each layer's positions for drawing connections later.
        List<int[]> layerPositions = new ArrayList<>();
        for (int i = 0; i < layers.size(); i++) {
            List<Room> layer = layers.get(i);
            int count = layer.size();
            int levelWidth = 2 * (count - 1) + 1; // nodes spaced 2 columns apart.
            int leftMargin = (globalWidth - levelWidth) / 2;
            int[] positions = new int[count];
            for (int j = 0; j < count; j++) {
                positions[j] = leftMargin + 2 * j;
                grid[2 * i][positions[j]] = '*';
            }
            layerPositions.add(positions);
        }

        // Draw connections between adjacent layers.
        for (int i = 0; i < layers.size() - 1; i++) {
            List<Room> current = layers.get(i);
            List<Room> next = layers.get(i + 1);
            int[] currentPos = layerPositions.get(i);
            int[] nextPos = layerPositions.get(i + 1);
            int connectionRow = 2 * i + 1;
            // For each room in the current layer, draw lines to its children.
            for (int j = 0; j < current.size(); j++) {
                Room parent = current.get(j);
                int parentX = currentPos[j];
                // For each child of the parent, find its index in the next layer.
                for (Room child : parent.getNextRooms()) {
                    int childIndex = next.indexOf(child);
                    if (childIndex == -1) continue; // Should not happen.
                    int childX = nextPos[childIndex];
                    int mid = (parentX + childX) / 2;
                    char symbol;
                    if (parentX == childX) {
                        symbol = '|';
                    } else if (childX > parentX) {
                        symbol = '\\';  // Down-right connection.
                    } else {
                        symbol = '/';   // Down-left connection.
                    }
                    // Only place a symbol if the cell is still empty.
                    if (grid[connectionRow][mid] == ' ') {
                        grid[connectionRow][mid] = symbol;
                    }
                }
            }
        }

        // Print the grid row by row.
        for (int i = 0; i < numPrintRows; i++) {
            System.out.println(new String(grid[i]));
        }
    }
}
