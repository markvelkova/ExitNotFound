package maputils.tiles;

import maputils.interfaces.AbstractDiscoverable;
import maputils.interfaces.EmptyMapTile;

import java.io.Serializable;


/**
 * Represents a harmful map tile that damages the player.
 * Bad tiles always apply a negative health effect to the player. The damage amount
 * is determined at tile creation and remains consistent when encountered. */
public class Bad extends AbstractDiscoverable implements EmptyMapTile, Serializable {
    private int healthImpact;

    /**
     * Constructs a Bad tile with the specified damage amount.
     * @param healthImpact the amount of damage (positive value, will be negated)     */
    public Bad(int healthImpact) {
        this.healthImpact = healthImpact;
    }

    /**
     *  Returns the damage effect of this tile as a negative value.
     *  @return a negative integer representing the health damage     */
    @Override
    public int getPlayerHealthImpact() {
        //System.out.println(healthImpact);
        return -healthImpact;
    }
}
