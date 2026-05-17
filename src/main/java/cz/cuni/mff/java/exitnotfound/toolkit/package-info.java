/**
 * Core game utilities and data management.
 *
 * This package provides essential toolkit functionality including player management,
 * game state serialization, and save/load operations. It handles persistence of game progress
 * and maintains player character state throughout gameplay.
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.toolkit.Player} - Player character with state and position management</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.toolkit.Player.FacingDirection} - Direction the player is facing</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.toolkit.GameState} - Serializable game state snapshot</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.toolkit.GameLoaderAndSaver} - Save and load game progress</li>
 * </ul>
 *
 * <h2>Features:</h2>
 * Player state management including health, position, discoveries, and game status.
 * Complete game serialization for save/load functionality using Java ObjectStreams.
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.toolkit;