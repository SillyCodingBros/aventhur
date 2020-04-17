package pkg_command;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;


/**
 * Implementation of the 'use' user command.
 *
 * @author LEGOUEIX Nicolas and ORNIACKI Thomas
 * @version 2020.04
 */
public class CommandUse extends Command
{
    /**
     * Constructeur d'objets de classe CommandUse
     */
    public CommandUse()
    {
    }

    /**
     * Try to use a given item of player's inventory.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      if(hasSecondWord()){
        if(player.getInventory().hasItem(getSecondWord()) != null) {
          if(getSecondWord().equals("beamer")){
            if(player.getInventory().hasItem("beamer").getCooldown() == 1){
              player.setFromBack(false);
              player.historyAdd(this);
            }
            gui.println(player.useBeamer());
            if(player.getCurrentRoom().getImageName() != null) {
              gui.showImage(player.getCurrentRoom().getImageName());
            }
          }
          else{
              gui.println("I can't use it!");
          }
        }
        else{
          gui.println("I don't have that!");
        }
      }
      else{
        gui.println("What should I use ?");
      }
    }
}
