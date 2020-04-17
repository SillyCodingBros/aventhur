
/**
 * Implementation of the 'eat' user command.
 *
 * @author LEGOUEIX Nicolas
 * @version 2020.04
 */
public class CommandEat extends Command
{
    /**
     * Constructeur d'objets de classe CommandEat
     */
    public CommandEat()
    {
    }

    /**
     * Try to eat a consumable from player's inventory.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      if(hasSecondWord()){
          Item toEat = player.getInventory().hasItem(getSecondWord());
          if(toEat == null){
              gui.println("Cant eat something you don't have !");
              return;
          }
          gui.println(player.eat(toEat));
      }
      else {
        String message = "What should I eat ?";
        gui.println(message);
      }
    }
}
