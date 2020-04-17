
/**
 * Implementation of the 'quit' user command.
 *
 * @author LEGOUEIX Nicolas and ORNIACKI Thomas
 * @version 2020.04
 */
public class CommandQuit extends Command
{
    /**
     * Constructeur d'objets de classe CommandQuit
     */
    public CommandQuit()
    {
    }

    /**
     * Quits the game.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      if(hasSecondWord()) {
          String quitMessage = "Quit what?";
          gui.println(quitMessage);
          return;
        }
        else {
          engine.endGame();
        }
    }
}
