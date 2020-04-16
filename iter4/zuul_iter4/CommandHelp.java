
/**
 * Décrivez votre classe CommandHelp ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CommandHelp extends Command
{
    private CommandWords words;

    /**
     * Constructeur d'objets de classe CommandHelp
     */
    public CommandHelp()
    {
    }

    /**
     * Constructeur d'objets de classe CommandHelp
     */
    public CommandHelp(CommandWords words)
    {
      this.words = words;
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      String helpString = "Your commands are : \n" + words.getAllCommands();
      gui.println(helpString);
    }
}
