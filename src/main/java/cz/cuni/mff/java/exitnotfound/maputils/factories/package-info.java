/**
 * Factory patterns for tile creation.
 *
 * This package implements the Factory design pattern for creating different types of map tiles.
 * Factories encapsulate tile creation logic including randomization and initialization,
 * allowing consistent and flexible tile generation during map initialization.
 *
 * <h2>Factory Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.factories.MaptileFactory} - Abstract base factory</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.factories.GoodTileFactory} - Creates Good tiles with random healing probability</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.factories.BadTileFactory} - Creates Bad tiles with random damage values</li>
 * </ul>
 *
 * <h2>Factory Pattern:</h2>
 * Each factory implements getNewMapTile() to produce new tile instances with
 * randomized properties defined by configuration parameters.
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.maputils.factories;