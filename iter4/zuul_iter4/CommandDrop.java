
/**
 * Décrivez votre classe CommandDrop ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
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
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
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
