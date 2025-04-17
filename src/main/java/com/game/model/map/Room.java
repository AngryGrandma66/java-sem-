package com.game.model.map;

import com.game.model.encounters.Encounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room/node in the dungeon map graph.
 * Each room has an Encounter that triggers upon entry and links to subsequent rooms.
 */
public class Room {
    private Encounter encounter;
    private List<Room> nextRooms = new ArrayList<>();

    public Room(Encounter encounter) {
        this.encounter = encounter;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public List<Room> getNextRooms() {
        return nextRooms;
    }

    /**
     * Connect this room to a subsequent room (unidirectional link).
     */
    public void addNextRoom(Room room) {
        nextRooms.add(room);
    }
}
