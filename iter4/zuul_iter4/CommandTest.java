import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.util.stream.Stream;
import java.io.IOException;

/**
 * Décrivez votre classe CommandTest ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CommandTest extends Command
{
    /**
     * Constructeur d'objets de classe CommandTest
     */
    public CommandTest()
    {
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public void execute(Player player, GameEngine engine, UserInterface gui)
    {
      Path filePath = FileSystems.getDefault().getPath("scripts", getSecondWord());
      try (Stream<String> lines = Files.lines(filePath))
      {
        lines.forEachOrdered(item->engine.interpretCommand(item));
      }
      catch (IOException e)
      {
        gui.println(e.toString());
      }
    }
}
