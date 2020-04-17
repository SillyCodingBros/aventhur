import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import pkg_game_logic.UserInterface;
import pkg_game_logic.GameEngine;

/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class.
 *
 *  This main class creates the necessary implementation objects and starts the game off.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2.0 (Jan 2003)
 */


public class Game
{
  private UserInterface gui;
  private GameEngine engine;

  /**
   * Create the game and initialise its internal map.
   * @param language The language "en" or "fr".
   */
  public Game(String language)
  {
    engine = new GameEngine(language);
    gui = new UserInterface(engine, language);
    engine.setGUI(gui);
  }

  public static void main(String[] args) {
    System.out.println("Choose a language\n\t(1) English\n\t(2) Français\n");
    Scanner scan = new Scanner(System.in);
    int number;
    while (true){
      System.out.print("Enter the Number: ");
      if (scan.hasNextInt()) {
        number = scan.nextInt() ;
        if (number > 0 && number < 3) {
          break;
        }
        else {
          scan.nextLine();
          System.out.println("Input has to be a valid number!\n");
        }
      }
      else {
        scan.nextLine();
        System.out.println("Input has to be a number!\n");
      }
    }
    if (number == 1) {
      new Game("en");
    }
    else{
      new Game("fr");
    }
  }
}



