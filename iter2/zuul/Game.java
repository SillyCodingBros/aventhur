/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

//import java.util.*;
import java.util.HashMap;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private HashMap <String, Room> roomMap;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // rooms for village
        Room attic, farm, pigs, pub, storageRoom, fountain, market, forge, home, entrance, abandonnedHouse, basement;

        // create the rooms
        attic = new Room("in the farm's attic. There is \nhay all over the floor and a chicken looks at you as you climb the\nladder.");
        farm = new Room("inside the farm. There is a strong smell.");
        pigs = new Room("in the pig's enclosure. You spot\nsomething shining on the ground.");
        pub = new Room("in the pub. The barman greets you, but \nnot the drunk customer on the left.");
        storageRoom = new Room("in the pub's stroage room.\nYou never loiked small spaces. There is something on the ground.");
        fountain = new Room("in the village's square.\nNext to the fountain, the old Elibed is staring at you and a chicken\nis running around.");
        market = new Room("in the village's market. Its a very busy place. \nYou think you hear the clucking of a chicken");
        forge = new Room("in the village's forge. The black-smith greets you.\nThere is an old rusty sword on the ground.");
        home = new Room("in your parent's house. You mom is here, as allways.\nA tastefull cake is on the table.");
        entrance = new Room("at the village entrance. The guard calls you out.\nBetter go see what he wants");
        abandonnedHouse = new Room("inside a rotting house. For some reason,\nyou feel bad. There is a big, dirty, helmet on the ground.");
        basement = new Room("in the house basement. It feels like a dim, red\nlight is coming off the walls, and a huge chicken is staring at you.\nYou can't tell if it's eyes are actually glowing red or if its just reflexion.");

        //add them to the roomMap
        roomMap.put("attic", attic);
        roomMap.put("farm", farm);
        roomMap.put("pigs", pigs);
        roomMap.put("pub", pub);
        roomMap.put("storageRoom", storageRoom);
        roomMap.put("fountain", fountain);
        roomMap.put("market", market);
        roomMap.put("forge", forge);
        roomMap.put("home", home);
        roomMap.put("entrance", entrance);
        roomMap.put("abandonnedHouse", abandonnedHouse);
        roomMap.put("basement", basement);

        // initialise room exits
        // attic exits
        attic.setExit("down", farm);
        //farm exists
        farm.setExit("east", fountain);
        farm.setExit("west", pigs);
        farm.setExit("up", attic);
        // pigs exits
        pigs.setExit("north", farm);
        // pub exits
        pub.setExit("east", storageRoom);
        pub.setExit("south", fountain);
        // fountain exits
        fountain.setExit("north", pub);
        fountain.setExit("east", entrance);
        fountain.setExit("south", market);
        fountain.setExit("west", farm);
        // market exits
        market.setExit("north", fountain);
        market.setExit("east", abandonnedHouse);
        market.setExit("south", home);
        market.setExit("west", forge);
        // forge exits
        forge.setExit("east", market);
        // home exits
        home.setExit("north", market);
        // entrance exits
        entrance.setExit("west", fountain);
        // entrance.setExit("east", greatRoad);
        // abandonned house exists
        abandonnedHouse.setExit("west", market);
        abandonnedHouse.setExit("down", basement);
        // basement exits
        basement.setExit("up", abandonnedHouse);

        currentRoom = home;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        String byeMessage = "Thank you for playing.\nGood bye.";
        System.out.println(byeMessage);
    }

    /**
     * Print out room's description and exists.
     */
    private void printLocationInfo()
    {
      System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        String welcomeMessage = "\n"+
                                "Welcome to Aventhür!\n"+
                                "The Ultimate Adventure Game.\n"+
                                "\n"+
                                "Type 'help' if you need help.\n"+
                                "\n";
        System.out.println(welcomeMessage);
        //System.out.println("");
        //System.out.println("");
        //System.out.println("");
        //System.out.println();
        printLocationInfo();
        /*
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null)
            System.out.print("north ");
        if(currentRoom.eastExit != null)
            System.out.print("east ");
        if(currentRoom.southExit != null)
            System.out.print("south ");
        if(currentRoom.westExit != null)
            System.out.print("west ");
        System.out.println();
        */
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
          String unknownCommand = "I don't know what you mean...";
          System.out.println(unknownCommand);
          return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        }
        else if (commandWord.equals("go"))
        {
            goRoom(command);
        }
        else if (commandWord.equals("look"))
        {
            look();
        }
        else if (commandWord.equals("eat"))
        {
            eat();
        }
        else if (commandWord.equals("quit"))
        {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
      String helpString = "Your commands are : \n" + parser.showCommands();
      System.out.println(helpString);
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        String errorMessage;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            errorMessage = "Go where?";
            System.out.println(errorMessage);
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        //Room nextRoom = null;
        Room nextRoom = currentRoom.getExit(direction);
        /*
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }
        if(direction.equals("up")) {
            nextRoom = currentRoom.upExit;
        }
        if(direction.equals("down")) {
            nextRoom = currentRoom.downExit;
        }
        */


        if (nextRoom == null) {
            errorMessage = "There is no door!";
            System.out.println(errorMessage);
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
            /*
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");
            System.out.println();
            */
        }
    }

    /**
     * "Look" was entered, rewrite to terminal the description of current room
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * "eat" was entered, print dummy info about eating TODO
     */
    private void eat()
    {
        String eatMessage = "You eat part of you provisions and feel full.";
        System.out.println(eatMessage);
    }
    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            String quitMessage = "Quit what?";
            System.out.println(quitMessage);
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
