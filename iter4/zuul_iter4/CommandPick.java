
/**
 * Décrivez votre classe CommandPick ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
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
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
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
