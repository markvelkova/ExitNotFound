package maputils.factories;

import maputils.tiles.Good;

import java.io.Serializable;
import java.util.Random;

/**
 * Factory for creating beneficial ("good") map tiles with randomized healing probability.
 * This factory creates Good tile instances with random probability values up to the
 * configured maximum probability. Each tile may have different healing chances. */
public class GoodTileFactory extends MaptileFactory implements Serializable {
    private final int maxProb;
    private final Random rand = new Random();

    /**
     * Constructs a GoodTileFactory with the specified maximum probability.
     * @param maxProb the maximum healing probability for created tiles     */
    public GoodTileFactory(int maxProb) {
        this.maxProb = maxProb;
    }

    /**
     * Creates a new Good tile with a random healing probability.
     * The probability is randomly selected between 0 and maxProb.
     * @return a new Good tile with randomized probability     */
    @Override
    public Good getNewMapTile() {
        return new Good(rand.nextInt(maxProb));
    }
}