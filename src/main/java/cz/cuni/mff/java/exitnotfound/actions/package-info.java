/**
 * Game action system providing executable commands for player interactions.
 *
 * This package contains all action classes that implement specific player commands.
 * Each action represents a distinct player operation such as movement, displaying information,
 * or handling invalid input. Actions are created by the CommandHandler and executed during gameplay.
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.actions.Action} - Abstract base class for all actions</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.actions.Move} - Handles player movement in cardinal directions</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.actions.DisplayMap} - Renders the game map</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.actions.DisplayStats} - Shows player statistics</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.actions.DisplayCommands} - Lists available commands</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.actions.Answer} - Validates yes/no responses</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.actions.InvalidAction} - Handles unrecognized commands</li>
 * </ul>
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.actions;