package com.game.model;

import com.game.model.entities.Enemy;
import com.game.model.items.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for loading game configuration (items, enemies, etc.) and saving/loading game state using JSON (Gson).
 */
public class DataManager {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Loads all item definitions from a JSON file into a list of Item objects.
     * @param filePath JSON file path containing item data.
     */
    public static List<Item> loadItems(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Assuming the JSON is an array of item definitions.
            Item[] itemsArray = gson.fromJson(reader, Item[].class);
            return Arrays.asList(itemsArray);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Loads all enemy definitions from a JSON file.
     */
    public static List<Enemy> loadEnemies(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Enemy[] enemies = gson.fromJson(reader, Enemy[].class);
            return Arrays.asList(enemies);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //TODO Other load methods



    /**
     * Saves the current game session state to a file in JSON format.
     * @param session the game session to save.
     * @param filePath the file to write to.
     */
    public static void saveGame(GameSession session, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(session, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a game session state from a JSON save file.
     * @param filePath the save file to read.
     * @return a GameSession object reconstructed from the file.
     */
    public static GameSession loadGame(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, GameSession.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
