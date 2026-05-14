package maputils.tiles;

import maputils.interfaces.AbstractDiscoverable;
import maputils.interfaces.EmptyMapTile;

import java.io.Serializable;

public class Bad extends AbstractDiscoverable implements EmptyMapTile, Serializable {
    private int healthImpact;
    public Bad(int healthImpact) {
        this.healthImpact = healthImpact;
    }
    @Override
    public int getPlayerHealthImpact() {
        //System.out.println(healthImpact);
        return -healthImpact;
    }
}
