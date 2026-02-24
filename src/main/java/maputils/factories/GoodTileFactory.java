package maputils.factories;

import maputils.Good;

import java.util.Random;

public class GoodTileFactory extends MaptileFactory {
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
