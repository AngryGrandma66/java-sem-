package com.game.model.entities;

/**
 * Represents a temporary status effect (buff/debuff or damage over time) on an entity.
 * Buffs/debuffs modify stats, while DOT (damage-over-time) or HOT (healing-over-time) effects modify health each turn.
 */
public class StatusEffect {
    public enum EffectType { BUFF, DEBUFF, DOT, HOT }

    private String name;
    private EffectType type;
    private int duration;      // lasts for this many turns or rounds
    private int turnsRemaining;

    public int getHealthChangePerTurn() {
        return healthChangePerTurn;
    }

    public int getStatModifier() {
        return statModifier;
    }

    public String getStatAffected() {
        return statAffected;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public int getDuration() {
        return duration;
    }

    public EffectType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private String statAffected;   // name of stat affected (for buff/debuff)
    private int statModifier;      // amount to add/subtract from stat (buffs positive, debuffs negative)
    private int healthChangePerTurn; // damage or healing each turn (for DOT/HOT)

    public StatusEffect(String name, EffectType type, int duration) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.turnsRemaining = duration;
    }

    /**
     * Called when the effect is applied to an entity.
     * For buffs/debuffs, apply stat changes. For DOT/HOT, might apply initial tick if needed.
     */
    public void onApply(Entity target) {
        if (type == EffectType.BUFF || type == EffectType.DEBUFF) {
        }
    }

    /**
     * Called at the start of each round to decrement duration (if duration is measured in rounds).
     * If duration is measured per turn of the entity, this could be called at the end of that entity's turn.
     */
    public void decrementDuration() {
        turnsRemaining--;
    }

    /**
     * Determines if the effect duration has expired.
     */
    public boolean isExpired() {
        return turnsRemaining <= 0;
    }

    /**
     * Called when the effect expires or is removed, to revert stat changes.
     */
    public void onExpire(Entity target) {
        if (type == EffectType.BUFF || type == EffectType.DEBUFF) {
        }
    }

    /**
     * Called at the end of the affected entity's turn to apply ongoing effects (e.g., DOT/HOT).
     */
    public void applyTurnEffect(Entity target) {
        if (healthChangePerTurn != 0) {
            if (healthChangePerTurn < 0) {
                target.takeDamage(-healthChangePerTurn); // negative value means damage
            } else {
                target.heal(healthChangePerTurn);
            }
        }
    }

}
