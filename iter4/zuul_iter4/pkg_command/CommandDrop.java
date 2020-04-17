package pkg_command;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;
import pkg_game_entities.Item;

/**
 * Implementation of the 'drop' user command.
 *
 * @author LEGOUEIX Nicolas
 * @version 2020.04
 */
public class CommandDrop extends Command
{
    /**
     * Constructeur d'objets de classe CommandDrop
     */
    public CommandDrop()
    {
    }

    /**
     * Try to drop an object of player's inventory.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      if(hasSecondWord()){
          Item toDrop = player.getInventory().hasItem(getSecondWord());
          if(toDrop == null){
              gui.println("I dont own such a thing...");
          }
          player.getCurrentRoom().getItemList().addItem(toDrop);
          player.setCurrentWeight(player.getCurrentWeight() - toDrop.getWeight());
          player.getInventory().removeItem(toDrop);
          gui.println("The " + getSecondWord() + " is now on the ground.");
        }
        else{
          gui.println("What should I drop ?");
        }
    }
}
