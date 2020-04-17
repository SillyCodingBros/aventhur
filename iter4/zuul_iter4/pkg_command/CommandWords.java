package pkg_command;

import java.util.HashMap;

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
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, Command> validCommands;
    private HashMap<CommandWord, String> translations;

    /**
     * Constructor - initialise the command words.
     * @param language The language "en" or "fr".
     */
    public CommandWords(String language)
    {
      validCommands = new HashMap<String, Command>();
      translations = new HashMap<CommandWord, String>();
      if (language.equals("en")){
        //English
        translations.put(CommandWord.GO, "go");
        translations.put(CommandWord.QUIT, "quit");
        translations.put(CommandWord.HELP, "help");
        translations.put(CommandWord.LOOK, "look");
        translations.put(CommandWord.EAT, "eat");
        translations.put(CommandWord.BACK, "back");
        translations.put(CommandWord.TEST, "test");
        translations.put(CommandWord.PICK, "pick");
        translations.put(CommandWord.DROP, "drop");
        translations.put(CommandWord.ITEMS, "items");
        translations.put(CommandWord.USE, "use");

        validCommands.put("go", new CommandGo());
        validCommands.put("quit", new CommandQuit());
        validCommands.put("help", new CommandHelp(this));
        validCommands.put("look", new CommandLook());
        validCommands.put("eat", new CommandEat());
        validCommands.put("back", new CommandBack());
        validCommands.put("test", new CommandTest());
        validCommands.put("pick", new CommandPick());
        validCommands.put("drop", new CommandDrop());
        validCommands.put("items", new CommandItems());
        validCommands.put("use", new CommandUse());
      }
      else if (language.equals("fr")) {
        //French
        translations.put(CommandWord.GO, "aller");
        translations.put(CommandWord.QUIT, "quitter");
        translations.put(CommandWord.HELP, "aide");
        translations.put(CommandWord.LOOK, "observer");
        translations.put(CommandWord.EAT, "manger");
        translations.put(CommandWord.BACK, "retour");
        translations.put(CommandWord.TEST, "tester");
        translations.put(CommandWord.PICK, "prendre");
        translations.put(CommandWord.DROP, "lacher");
        translations.put(CommandWord.ITEMS, "objets");
        translations.put(CommandWord.USE, "utiliser");

        validCommands.put("aller", new CommandGo());
        validCommands.put("quitter", new CommandQuit());
        validCommands.put("aide", new CommandHelp(this));
        validCommands.put("observer", new CommandLook());
        validCommands.put("manger", new CommandEat());
        validCommands.put("retour", new CommandBack());
        validCommands.put("tester", new CommandTest());
        validCommands.put("prendre", new CommandPick());
        validCommands.put("lacher", new CommandDrop());
        validCommands.put("objets", new CommandItems());
        validCommands.put("utiliser", new CommandUse());

      }
    }

    /**
     * Find the Command associated with a command word.
     * @param commandStr The word to look up.
     * @return The corresponding Command or null.
     */
    public Command getCommandWord(String commandStr)
    {
        Command command = validCommands.get(commandStr);
        if(command != null) {
            return command;
        }
        else {
            return null;
        }
    }

    /**
     * Check whether a given String is a valid command word.
     * @param aString The string to check for.
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Return all valid commands as a String.
     * @return all valid commands as a String.
     */
    public String getCommandList()
    {
        StringBuilder sb = new StringBuilder("");
        for(String command : validCommands.keySet()) {
          sb.append(command);
          sb.append(" ");
        }
        return sb.toString();
    }

    /**
    * Get String for a CommandWord.
    * @param cmd The CommandWord.
    * @return String of the given CommandWord.
    */
    public String commandWordToString(CommandWord cmd)
    {
        return translations.get(cmd);
    }

    /*
     * Return all valid commands as a String.
     */
    public String getAllCommands()
    {
      StringBuilder result = new StringBuilder("");
      validCommands.forEach((k,v)->{
        result.append(k);
        result.append(" ");
      });
      return result.toString();
    }
}
