package pkg_command;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.util.stream.Stream;
import java.io.IOException;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;
import pkg_game_entities.Player;

/**
 * Implementation of the 'test' user command.
 *
 * @author ORNIACKI Thomas
 * @version 2020.04
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
     * Runs the given script by interpretting it line by line.
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
