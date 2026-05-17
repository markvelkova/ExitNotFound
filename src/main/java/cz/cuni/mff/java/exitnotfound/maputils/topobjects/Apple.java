package cz.cuni.mff.java.exitnotfound.maputils.topobjects;

import cz.cuni.mff.java.exitnotfound.maputils.interfaces.FoundableObject;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.TopObject;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;

import java.io.Serializable;

/**
 * Represents a beneficial item (apple) that heals the player when found.
 *
 * Apples are collectible objects that restore a fixed amount of health when
 * the player discovers them. The healing amount is configured during creation.
 */
public class Apple implements FoundableObject, TopObject, Serializable {
    /**
     * The amount of health restoration provided when the apple is consumed.
     */
    private final int bonus;

    /**
     * Constructs an Apple with the specified healing bonus.
     *
     * @param bonus the amount of health to restore
     */
    public Apple(int bonus) {
        this.bonus = bonus;
    }

    /**
     * Heals the player by the apple's bonus amount when discovered.
     *
     * @param p the player who found the apple
     */
    @Override
    public void find(Player p) {
        p.updateHealth(bonus);
    }

    /**
     * Provides the descriptive message when the player finds an apple.
     *
     * @return a message describing finding and eating the apple
     */
    @Override
    public String getEndingOfFindingMessage() {
        return "a little apple. You ate it and felt a wave of energy, as little as was the apple.";
    }
}