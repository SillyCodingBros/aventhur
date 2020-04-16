
/**
 * Décrivez votre classe CommandQuit ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
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
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
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
