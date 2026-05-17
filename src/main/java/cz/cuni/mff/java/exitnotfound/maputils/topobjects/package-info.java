/**
 * Interactive top-level objects on the game map.
 *
 * This package contains objects that exist on top of map tiles and provide special
 * interactions when the player discovers them. These are collectible items or goal objects.
 *
 * <h2>Top Objects:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.topobjects.Door} - The exit goal; finding it triggers win condition</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.topobjects.Apple} - Collectible item that heals the player</li>
 * </ul>
 *
 * <h2>Interaction System:</h2>
 * All top objects implement {@link cz.cuni.mff.java.exitnotfound.maputils.interfaces.FoundableObject} and define
 * custom behavior when discovered by the player through the find() method.
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.maputils.topobjects;