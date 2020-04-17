package pkg_command;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;

/**
 * Implementation of the 'help' user command.
 *
 * @author ORNIACKI Thomas
 * @version 2020.04
 */
public class CommandHelp extends Command
{
    private CommandWords words;

    /**
     * Constructeur d'objets de classe CommandHelp
     */
    public CommandHelp(CommandWords words)
    {
      this.words = words;
    }

    /**
     * Displays all game commands in previously choosen language.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      String helpString = "Your commands are : \n" + words.getAllCommands();
      gui.println(helpString);
    }
}
