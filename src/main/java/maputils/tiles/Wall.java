package maputils.tiles;

import maputils.interfaces.AbstractDiscoverable;
import maputils.interfaces.MapTile;
import maputils.interfaces.NonEmptyMapTile;

import java.io.Serializable;

/**
 * Represents a solid wall or obstacle on the map.
 * Walls are non-passable tiles that block player movement. The player cannot move
 * into or through wall tiles. */
public class Wall extends AbstractDiscoverable implements NonEmptyMapTile, MapTile, Serializable
{
}
