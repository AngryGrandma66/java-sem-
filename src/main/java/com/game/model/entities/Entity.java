package com.game.model.entities;

import java.util.List;

/**
 * Base class for all entities in the game (player or enemy).
 * Holds core stats and active status effects, and provides methods for combat actions.
 */
public abstract class Entity {
    // Core stats determining combat performance and turn order
    protected String name;

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getManaPerTurn() {
        return manaPerTurn;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public List<Action> getAvailableActions() {
        return availableActions;
    }

    public List<StatusEffect> getStatusEffects() {
        return statusEffects;
    }

    protected int strength;
    protected int defense;
    protected int speed;
    protected int dexterity;
    protected int intelligence;
    protected int health;
    protected int mana;
    protected int manaPerTurn;
    protected int critChance;  // percentage (0-100)

    protected int maxHealth;
    protected int maxMana;

    protected List<Action> availableActions = new ArrayList<>();  // actions (skills/spells) this entity can use
    protected List<StatusEffect> statusEffects = new ArrayList<>();  // active buffs/debuffs on this entity

    /**
     * Applies damage to this entity, reducing health (after defense or other mitigation).
     * @param amount Raw damage amount.
     */
    public void takeDamage(int amount) {
        // Placeholder: reduce health by (amount - defense) minimum 0.
        // e.g., health -= Math.max(0, amount - defense);
        // Ensure health doesn't drop below 0.
    }

    /**
     * Heals this entity by the given amount, up to maxHealth.
     */
    public void heal(int amount) {
        // health = Math.min(maxHealth, health + amount);
    }

    /**
     * Performs an action on a target.
     * If this entity is the player, the action may be chosen by the user via the controller.
     * @param action The Action to perform (must be one of availableActions).
     * @param target The target entity (can be an enemy, player, or self depending on action).
     */
    public void performAction(Action action, Entity target) {
        // In a real implementation, this would execute the action's effect on the target.
        // e.g., action.execute(this, target);
    }

    /**
     * Adds a status effect to this entity (buff, debuff, DOT, etc).
     * Immediately applies its benefits/penalties, and it will expire after its duration.
     * @param effect The status effect to add.
     */
    public void addStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
        effect.onApply(this);
    }

    /**
     * Removes a status effect from this entity, reverting any stat changes.
     * Typically called when effect duration expires or is dispelled.
     */
    public void removeStatusEffect(StatusEffect effect) {
        statusEffects.remove(effect);
        effect.onExpire(this);
    }

}
