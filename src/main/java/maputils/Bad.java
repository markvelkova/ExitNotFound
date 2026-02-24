package maputils;

import maputils.interfaces.AbstractDiscoverable;
import maputils.interfaces.Discoverable;
import maputils.interfaces.EmptyMapTile;

public class Bad extends AbstractDiscoverable implements EmptyMapTile {
    private int healthImpact;
    public Bad(int healthImpact) {
        this.healthImpact = healthImpact;
    }
    @Override
    public int getPlayerHealthImpact() {
        return -healthImpact;
    }
}
