/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "eat"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Return all valid commands as a String.
     */
    public String getCommandList()
    {
      /*
      StringBuilder sb = new StringBuilder();
      for (String s : hugeArray) {
          sb.append(s);
      }
      String result = sb.toString();
      */
      StringBuilder sb = new StringBuilder("");
      //String commandList = "";
        for(String command : validCommands) {
          //commandList += (command + " ");
          sb.append(command);
          sb.append(" ");
          //System.out.print(command + " ");
        }
        //return commandList;
        return sb.toString();
        //System.out.println();
    }
}
