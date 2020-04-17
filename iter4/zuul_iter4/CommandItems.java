
/**
 * Implementation of the 'items' user command.
 *
 * @author LEGOUEIX Nicolas and ORNIACKI Thomas
 * @version 2020.04
 */
public class CommandItems extends Command
{
    /**
     * Constructeur d'objets de classe CommandItems
     */
    public CommandItems()
    {
    }

    /**
     * Displays every items of player's inventory.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      gui.println(player.getInventory().inventoryToString());
      return;
    }
}
