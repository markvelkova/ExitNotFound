package cz.cuni.mff.java.exitnotfound.maputils.tiles;

import cz.cuni.mff.java.exitnotfound.maputils.interfaces.AbstractDiscoverable;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.EmptyMapTile;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents a beneficial map tile that heals the player.
 * Good tiles have a probability-based effect: with a certain probability they restore
 * 1 health point to the player, otherwise they have no effect. The probability is
 * determined at tile creation.
 */
public class Good extends AbstractDiscoverable implements EmptyMapTile, Serializable {
    /**
     * The healing probability for this tile (0-100).
     * Higher values mean greater chance of healing the player.
     */
    private final int prob;
    /**
     * Random number generator for determining healing success.
     */
    private final Random rand = new Random();

    /**
     * Constructs a Good tile with the specified healing probability.
     *
     * @param prob the probability (0-100) of healing the player
     */
    public Good(int prob) {
        this.prob = prob;
    }

    /**
     * Determines the health effect when the player steps on this tile.
     * Returns 1 health point with probability, or 0 otherwise.
     *
     * @return 1 if the random check succeeds, 0 if it fails
     */
    @Override
    public int getPlayerHealthImpact() {
        if (rand.nextInt(100) > prob)
            return 1;
        else return 0;
    }
}