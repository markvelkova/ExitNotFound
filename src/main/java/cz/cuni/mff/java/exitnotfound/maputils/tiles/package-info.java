/**
 * Map tile implementations.
 *
 * This package contains concrete implementations of different tile types that make up
 * the game world. Each tile type has distinct properties affecting player health and
 * movement capabilities.
 *
 * <h2>Tile Types:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.tiles.Good} - Beneficial tiles with probability-based healing</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.tiles.Bad} - Harmful tiles that damage the player</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.tiles.Wall} - Impassable obstacles blocking movement</li>
 * </ul>
 *
 * <h2>Tile Properties:</h2>
 * All tiles implement {@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.MapTile} and are discoverable.
 * Empty tiles (Good, Bad) implement {@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.EmptyMapTile} and affect player health.
 * Walls implement {@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.NonEmptyMapTile} and are impassable.
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.maputils.tiles;