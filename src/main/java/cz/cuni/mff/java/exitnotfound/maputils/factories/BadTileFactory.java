package cz.cuni.mff.java.exitnotfound.maputils.factories;

import cz.cuni.mff.java.exitnotfound.maputils.tiles.Bad;

import java.io.Serializable;
import java.util.Random;

/**
 * Factory for creating harmful ("bad") map tiles with randomized damage values.
 *
 * This factory creates Bad tile instances with random damage amounts up to the
 * configured maximum health impact. Each tile may deal different amounts of damage.
 */
public class BadTileFactory extends MaptileFactory implements Serializable {
    /**
     * The maximum health damage that bad tiles created by this factory can inflict.
     */
    private final int maxHealthImpact;
    /**
     * Random number generator for creating varied damage values.
     */
    private final Random rand = new Random();

    /**
     * Constructs a BadTileFactory with the specified maximum damage.
     *
     * @param maxHealthImpact the maximum damage amount for created tiles
     */
    public BadTileFactory(int maxHealthImpact) {
        this.maxHealthImpact = maxHealthImpact;
    }

    /**
     * Creates a new Bad tile with a random damage value.
     *
     * The damage amount is randomly selected between 0 and maxHealthImpact.
     *
     * @return a new Bad tile with randomized damage
     */
    @Override
    public Bad getNewMapTile() {
        return new Bad(rand.nextInt(maxHealthImpact));
    }
}