package pkg_command;

/**
 * Representations for all the valid command words for the game.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord
{
    // A value for each command word, plus one for unrecognised
    // commands.
    GO, QUIT, HELP, LOOK, EAT, BACK, TEST, PICK, DROP, ITEMS, USE, SAVE, LOAD, UNKNOWN;
}