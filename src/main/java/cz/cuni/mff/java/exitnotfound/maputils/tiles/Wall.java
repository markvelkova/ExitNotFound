package cz.cuni.mff.java.exitnotfound.maputils.tiles;

import cz.cuni.mff.java.exitnotfound.maputils.interfaces.AbstractDiscoverable;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.MapTile;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.NonEmptyMapTile;

import java.io.Serializable;

/**
 * Represents a solid wall or obstacle on the map.
 * Walls are non-passable tiles that block player movement. The player cannot move
 * into or through wall tiles.
 */
public class Wall extends AbstractDiscoverable implements NonEmptyMapTile, MapTile, Serializable {
    /**
     * Initializes a new Wall tile with default settings.
     */
    public Wall() {
        super();
    }
}