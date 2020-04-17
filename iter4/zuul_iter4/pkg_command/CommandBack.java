package pkg_command;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;

/**
 * Implementation of the 'back' user command.
 *
 * @author LEGOUEIX Nicolas
 * @version 2020.04
 */
public class CommandBack extends Command
{
    /**
     * Constructeur d'objets de classe CommandBack
     */
    public CommandBack()
    {
    }

    /**
     * Try to go to back to the previous room.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      String errorMessage, newCommand;
      Command previousCmd;

      if(hasSecondWord()){
          // the user gave a second word. Not good.
          errorMessage = "You can't go back here, friend ! If you want to go here, dont go back and just go ! Try only \"back\"...";
          gui.println(errorMessage);
          return;
      }

      previousCmd = player.getLastMove();

      // no history or use beamer, dont do anyhting.
      // beamer limits history because otherwise history contains commands that can't be used in the current room
      if(player.isHistoryEmpty() || previousCmd.getSecondWord().equals("beamer")){
          // no history
          errorMessage = "Can't go back anymore, you are allready where you started !";
          gui.println(errorMessage);
          return;
      }

      CommandWords cmdWord = new CommandWords(engine.getLanguage());

      player.historyPop();

      newCommand = cmdWord.commandWordToString(CommandWord.GO);

      if(previousCmd.getSecondWord().equals("north")){
          newCommand += " south";
      }
      else if(previousCmd.getSecondWord().equals("south")){
          newCommand += " north";
      }
      else if(previousCmd.getSecondWord().equals("east")){
          newCommand += " west";
      }
      else if(previousCmd.getSecondWord().equals("west")){
          newCommand += " east";
      }
      else if(previousCmd.getSecondWord().equals("up")){
          newCommand += " down";
      }
      else{
        newCommand += " up";
      }
      player.setFromBack(true);
      engine.interpretCommand(newCommand);
    }
}
