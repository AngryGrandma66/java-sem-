package com.game.model.encounters;

import com.game.model.entities.Enemy;
import com.game.model.entities.Player;

import java.util.List;

/** An encounter where the player fights one or more enemies. */
public class BattleEncounter extends Encounter {
    private List<Enemy> enemies;

    public BattleEncounter(List<Enemy> enemies) {
        super("A battle begins!");
        this.enemies = enemies;
    }

    @Override
    public void execute(Player player) {
        // This would initiate turn-based combat against the enemies.
        // The actual combat loop is handled by the Controller (CombatController).
    }

    public List<Enemy> getEnemies() { return enemies; }
}
