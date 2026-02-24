package maputils;

import maputils.interfaces.EmptyMapTile;

import java.util.Random;

public class Good implements EmptyMapTile {
    private final int prob;
    private final Random rand = new Random();
    public Good(int prob) {
        this.prob = prob;
    }
    @Override
    public int getPlayerHealthImpact() {
        if (rand.nextInt(100) > prob)
            return 1;
        else return 0;
    }
}
