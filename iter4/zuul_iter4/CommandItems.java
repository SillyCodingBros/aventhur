
/**
 * Décrivez votre classe CommandItems ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
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
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      gui.println(player.getInventory().inventoryToString());
      return;
    }
}
