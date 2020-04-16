
/**
 * Décrivez votre classe CommandEat ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
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
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
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
