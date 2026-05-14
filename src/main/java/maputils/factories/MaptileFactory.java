package maputils.factories;

import maputils.interfaces.MapTile;

/**
 * Abstract factory base class for creating map tiles.
 *
 * This abstract class defines the factory pattern interface for creating different
 * types of map tiles. Concrete factories extend this class to produce specific tile types
 * with proper initialization and randomization. */
public abstract class MaptileFactory {
    /**
     * Creates and returns a new map tile instance.
     * Concrete implementations determine the specific tile type and any randomization.
     * @return a new MapTile instance     */
    public abstract MapTile getNewMapTile();

}
