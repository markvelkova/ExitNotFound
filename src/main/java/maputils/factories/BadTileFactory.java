package maputils.factories;

import maputils.Bad;

import java.util.Random;

public class BadTileFactory extends MaptileFactory{
    private int maxHealthImpact;
    private Random rand = new Random();
    public BadTileFactory(int maxHealthImpact) {
        this.maxHealthImpact = maxHealthImpact;
    }
    @Override
    public Bad getNewMapTile() {
        return new Bad(rand.nextInt(maxHealthImpact));
    }
}
