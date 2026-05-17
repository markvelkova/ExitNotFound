/**
 * Game world map system and tile management.
 *
 * This package manages the game world map structure, tiles, and interactive objects.
 * It provides map initialization, tile effects, discovery tracking, and map rendering
 * for display to the player. The map is the foundation of spatial gameplay.
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.Map} - Main map container and spatial queries</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.Map.Coord} - Coordinate representation on the map</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.MapConfig} - Configuration for map generation and difficulty</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.GameSymbols} - Symbol constants for map parsing and display</li>
 * </ul>
 *
 * <h2>Subpackages:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.tiles} - Tile implementations (Good, Bad, Wall)</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.topobjects} - Interactive objects (Door, Apple)</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.factories} - Tile creation factories</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.maputils.interfaces} - Interfaces for map components</li>
 * </ul>
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.maputils;