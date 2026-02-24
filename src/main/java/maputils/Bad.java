package maputils;

import maputils.interfaces.EmptyMapTile;

public class Bad implements EmptyMapTile {
    private int healthImpact;
    public Bad(int healthImpact) {
        this.healthImpact = healthImpact;
    }
    @Override
    public int getPlayerHealthImpact() {
        return -healthImpact;
    }
}
