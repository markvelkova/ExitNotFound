/**
 * Command processing and parsing system for player input.
 *
 * This package handles the conversion of raw player text input into structured Command objects,
 * validation of command syntax, and creation of appropriate Action instances. It serves as the
 * bridge between user input and game logic execution.
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.commands.Command} - Represents a single parsed command with type and parameters</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.commands.CommandHandler} - Factory and executor for actions based on commands</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.commands.CommandScholar} - Provides command documentation and validation rules</li>
 * </ul>
 *
 * <h2>Command Types:</h2>
 * Commands are defined in {@link cz.cuni.mff.java.exitnotfound.enums.CommandType} and include:
 * ok, stats, commands, go, save, exit, map, and error types.
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.commands;