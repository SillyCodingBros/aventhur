import java.util.StringTokenizer;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Parser
{
    private CommandWords commands;  // holds all valid command words

    /**
     * Create a parser to read from the terminal window.
     * @param language The language "en" or "fr".
     */
    public Parser(String language)
    {
        commands = new CommandWords(language);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand(String inputLine)
    {
        String word1;
        String word2;

        // Find up to two words on the line.
        StringTokenizer tokenizer = new StringTokenizer(inputLine);
        if(tokenizer.hasMoreTokens())
        word1 = tokenizer.nextToken();

        // get first word
        else
        word1 = null;
        if(tokenizer.hasMoreTokens())
        word2 = tokenizer.nextToken();

        // get second word
        else
        word2 = null;

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(commands.getCommandWord(word1), word2);
        }
        else {
            return new Command(null, word2);
        }
    }

    /**
     * Returns a list of valid command words as String.
     */
    public String showCommands()
    {
    return commands.getCommandList();
    }
}
