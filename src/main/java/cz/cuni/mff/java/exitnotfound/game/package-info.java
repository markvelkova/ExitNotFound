/**
 * Core game engine and main game loop controller.
 *
 * This package contains the main game logic, game state management, and the central game loop.
 * It orchestrates all game systems including UI, commands, player state, and map management,
 * managing transitions between game phases and coordinating overall gameplay flow.
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.game.GameEngine} - Main entry point and game loop executor</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.game.Game} - Primary game controller managing phase transitions and game state</li>
 * </ul>
 *
 * <h2>Game Phases:</h2>
 * The game progresses through phases defined in {@link cz.cuni.mff.java.exitnotfound.enums.GamePhase}:
 * welcome → loading/nameAsking → tutorial → game → won/lost/exited
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.game;