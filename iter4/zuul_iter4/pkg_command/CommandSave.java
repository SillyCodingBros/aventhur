package pkg_command;

//import java.io.DataOutputStream;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;

/**
 * Implementation of the 'save' user command.
 *
 * @author ORNIACKI Thomas
 * @version 2020.04
 */
public class CommandSave extends Command
{
    private CommandWords words;

    /**
     * Constructeur d'objets de classe CommandSave
     */
    public CommandSave(CommandWords words)
    {
      this.words = words;
    }

    /**
     * Saves current game state in the file.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      if (hasSecondWord()){
        try {
            FileWriter fw = new FileWriter("saves/" + getSecondWord(), true);
            PrintWriter out = new PrintWriter(fw);
            for (String cmd : engine.getHistory()) {
              out.println(cmd);
            }
            out.close();
            gui.println("Party Saved!\nLoad it with '"+ words.commandWordToString(CommandWord.LOAD) + " "+ getSecondWord() +"' after any startup");
        }
        catch (IOException e){
          gui.println("Error!");
        }
      }
      else {
        gui.println("Add name for your save");
      }
    }
}