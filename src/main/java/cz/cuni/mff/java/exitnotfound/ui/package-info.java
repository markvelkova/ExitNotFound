/**
 * User interface and player communication system.
 *
 * This package handles all input/output operations between the game and player.
 * It manages message display, command input parsing, and atmospheric messaging for immersion.
 * The UI layer abstracts console I/O operations and centralizes message management.
 *
 * <h2>Key Classes:</h2>
 * <ul>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth} - Output system for displaying messages to player</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.ui.NarratorsEar} - Input system for reading player commands</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.ui.MessageRepository} - Central message storage with localization support</li>
 *   <li>{@link cz.cuni.mff.java.exitnotfound.ui.UnsettlingMessenger} - Atmospheric message generator</li>
 * </ul>
 *
 * <h2>Message System:</h2>
 * All player-facing text is retrieved from {@link cz.cuni.mff.java.exitnotfound.ui.MessageRepository} using
 * {@link cz.cuni.mff.java.exitnotfound.enums.MessageKey} for consistent messaging and easy maintenance.
 *
 * <h2>Input/Output:</h2>
 * Static {@link java.io.PrintWriter} and {@link java.io.BufferedReader} instances
 * in NarratorsMouth and NarratorsEar enable flexible I/O redirection for testing
 * and different output targets.
 *
 * @author Markéta Velková
 * @version 1.0
 */
package cz.cuni.mff.java.exitnotfound.ui;