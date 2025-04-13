package com.game.model.entities;

/**
 * Represents a combat action/skill that an Entity can perform (e.g., "Slash", "Fireball").
 */
public abstract class Action {
    protected String name;
    protected String description;
    protected int manaCost;
    // Other properties: damage amount, whether it targets an enemy or self.

    public Action(String name, String description, int manaCost) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
    }

    /**
     * Executes the action's effect from the source entity on the target entity.
     * This might deal damage, apply a status effect, heal, etc.
     * @param source the entity performing the action.
     * @param target the target entity of the action.
     */
    public abstract void execute(Entity source, Entity target);

    //TODO add methods for executing actions like calculateDamage

}
