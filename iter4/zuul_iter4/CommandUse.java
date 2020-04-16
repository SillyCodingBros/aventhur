
/**
 * Décrivez votre classe CommandUse ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CommandUse extends Command
{
    /**
     * Constructeur d'objets de classe CommandUse
     */
    public CommandUse()
    {
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      if(hasSecondWord()){
        if(getSecondWord().equals("beamer")){
          player.setFromBack(false);
          player.historyAdd(this);
          gui.println(player.useBeamer());
          if(player.getCurrentRoom().getImageName() != null) {
            gui.showImage(player.getCurrentRoom().getImageName());
          }
        }
      }
      else{
        gui.println("What should I use ?");
      }
    }
}
