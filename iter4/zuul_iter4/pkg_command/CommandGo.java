package pkg_command;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;
import pkg_game_entities.Room;

/**
 * Implementation of the 'go' user command.
 *
 * @author LEGOUEIX Nicolas and ORNIACKI Thomas
 * @version 2020.04
 */
public class CommandGo extends Command
{
    /**
     * Constructeur d'objets de classe CommandGo
     */
    public CommandGo()
    {
    }

    /**
     * Try to go to one direction.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      String errorMessage;
      if(!hasSecondWord()) {
          // if there is no second word, we don't know where to go...
          errorMessage = "Go where?";
          gui.println(errorMessage);
          return;
      }

      String direction = getSecondWord();

      // Try to leave current room.
      Room nextRoom = player.getCurrentRoom().getExit(direction);

      if (nextRoom == null) {
          errorMessage = "There is no door!";
          gui.println(errorMessage);
          return;
      }
      if (!player.getCurrentRoom().canPass(direction, player.getInventory())) {
        gui.println(player.getCurrentRoom().getDoor(direction).getDescription());
      }
      else {
          if(player.isMaxMovesReached() && player.getWonState() == false){
              errorMessage = "As you walk around, you hear a sudden craking sound. Scared, you look around and see a tide of demonic abominations falling on the village. The sky goes dark and the air fills up in villager's screams. \nThere is blood everywhere. By the time you finally understand what is going on, you feel an extreme pain on your stomach.\n";
              errorMessage += "Instinctively, you place your hand on your stomach, then look at it : it is covered in blood. Your blood. As you look up again, you see a horrible, bearly human face staring at you.\n";
              errorMessage += "After what seemed to be an eternity during which the creature seemed to be ejoying the growing pain in your stomach, it turns around and runs towards a new victim. You see Bork, the weaponsmith ferociously fighting two of the demons. \nYou see a third one sneaking up behind him. You want to scream a warning, but no sound comes out of your mouth. All there is is pain.\n";
              errorMessage += "You fall on your knees, in a growing pound of blood. The screams of the villagers slowly fade away. So does your pain. You feel empty, and tired. Is this even real ? Bork finally losses his fight, taken out by multiple wounds.\nMaybe it is real after all. Maybe the old Elibed's world ending premonitions were correct... Maybe you should have listened.\n\n";
              errorMessage += " - You took too long to complete the quest, and demons are invading the world. Try not to be so slow next time...\n";
              gui.println(errorMessage);
              engine.endGame();
              return;
          }
          if(!player.getFromBack()){
              player.setFromBack(false);
              player.historyAdd(this);
          }

          player.setCurrentRoom(nextRoom);

          gui.println(player.getCurrentRoom().getLongDescription());
          if(player.getCurrentRoom() == engine.roomMap.get("basement")){
            engine.setWarned();
            gui.println("Affraid by the bird, you step back. It walks towadrs you. You feel in danger and that it could attack you anytime. \nWhat should you do ?");
          }
          if(player.getCurrentRoom().getImageName() != null) {
              gui.showImage(player.getCurrentRoom().getImageName());
          }
      }
    }
}
