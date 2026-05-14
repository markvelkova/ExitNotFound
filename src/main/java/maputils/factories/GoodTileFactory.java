package maputils.factories;

import maputils.tiles.Good;

import java.io.Serializable;
import java.util.Random;

public class GoodTileFactory extends MaptileFactory implements Serializable {
    private final int maxProb;
    private final Random rand = new Random();
    public GoodTileFactory(int maxProb) {
        this.maxProb = maxProb;
    }
    @Override
    public Good getNewMapTile() {
        return new Good(rand.nextInt(maxProb));
    }
}
