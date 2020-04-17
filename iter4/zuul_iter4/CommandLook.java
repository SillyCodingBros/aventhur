
/**
 * Implementation of the 'look' user command.
 *
 * @author LEGOUEIX Nicolas and ORNIACKI Thomas
 * @version 2020.04
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
     * Gives a description of the current room and allows to see
     * hidden items.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      gui.println(player.getCurrentRoom().getItemList().looking());
    }
}
