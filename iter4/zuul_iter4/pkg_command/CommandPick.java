package pkg_command;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;
import pkg_game_entities.Item;


/**
 * Implementation of the 'pick' user command.
 *
 * @author LEGOUEIX Nicolas and ORNIACKI Thomas
 * @version 2020.04
 */
public class CommandPick extends Command
{
    /**
     * Constructeur d'objets de classe CommandPick
     */
    public CommandPick()
    {
    }

    /**
     * Try to go to pick the given item from the current room.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      if(hasSecondWord()){
          Item toPick = player.getCurrentRoom().getItemList().hasItem(getSecondWord());
          if(toPick == null){
              String message = "No such item here...";
              gui.println(message);
              return;
          }
          if(player.getCurrentWeight() + toPick.getWeight() > player.getMaxWeight()){
              String message = "Can't take it, its too heavy !";
              gui.println(message);
              return;
          }
          player.getInventory().addItem(toPick);
          player.setCurrentWeight(player.getCurrentWeight() + toPick.getWeight());
          player.getCurrentRoom().getItemList().removeItem(toPick);
          String message = "You pick up the " + getSecondWord() + " and put it in your backpack.";
          gui.println(message);
          return;
        }
        else{
          String message = "What should I pick up ?";
          gui.println(message);
          return;
        }
    }
}
