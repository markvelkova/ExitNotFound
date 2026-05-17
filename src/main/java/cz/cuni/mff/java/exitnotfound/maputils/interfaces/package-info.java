/**
 * Interface definitions for map system components.
 *
 * This package defines all interfaces and abstract base classes used by the map system.
 * These interfaces establish contracts for tile types, top objects, and discovery mechanics,
 * enabling flexible tile implementations and consistent behavior across different tile types.
 *
 * <h2>Core Interfaces:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.MapTile} - Base interface for all map tiles</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.Discoverable} - Tracks discovery state of objects</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.EmptyMapTile} - Interface for passable tiles</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.NonEmptyMapTile} - Interface for impassable tiles</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.TopObject} - Marker for top-level objects</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.FoundableObject} - Objects that can be discovered</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.MovableMapObject} - Objects that can move on the map</li>
 * </ul>
 *
 * <h2>Abstract Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.AbstractDiscoverable} - Common discovery implementation</li>
 * </ul>
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.maputils.interfaces;