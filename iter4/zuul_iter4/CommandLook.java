
/**
 * Décrivez votre classe CommandLook ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CommandLook extends Command
{
    /**
     * Constructeur d'objets de classe CommandLook
     */
    public CommandLook()
    {
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      gui.println(player.getCurrentRoom().getItemList().looking());
    }
}
