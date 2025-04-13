package com.game.model.items;


/**
 * Abstract base class for items. Could be gear (equipment) or consumables.
 * JSON configuration defines the properties of each item.
 */
public abstract class Item {
    protected String id;          // unique identifier or name
    protected String name;
    protected String description;
    protected ItemType type;
    protected int value;          // gold value (price or sell cost)

    public Item(String id, String name, String desc, ItemType type, int value) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.type = type;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}

