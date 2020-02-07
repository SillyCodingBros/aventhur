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

public class Game
{
    private Parser parser;
    private Room currentRoom;

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

        // initialise room exits
        attic.setExits(null, null, null, null, null, farm);
        farm.setExits(null, fountain, pigs, null, attic, null);
        pigs.setExits(farm, null, null, null, null, null);
        pub.setExits(null, storageRoom, fountain, null, null, null);
        fountain.setExits(pub, entrance, market, farm, null, null);
        market.setExits(fountain, abandonnedHouse, home, forge, null, null);
        forge.setExits(null, market, null, null, null, null);
        home.setExits(market, null, null, null, null, null);
        entrance.setExits(null, null, null, fountain, null, null);
        abandonnedHouse.setExits(null, null, null, market, null, basement);
        basement.setExits(null, null, null, null, abandonnedHouse, null);

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
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printLocationInfo()
    {
      System.out.println("You are " + currentRoom.getDescription());
      System.out.print("Exits: ");
      if(currentRoom.northExit != null) {
        System.out.print("north ");
      }
      if(currentRoom.eastExit != null) {
        System.out.print("east ");
      }
      if(currentRoom.southExit != null) {
        System.out.print("south ");
      }
      if(currentRoom.westExit != null) {
        System.out.print("west ");
      }
      if(currentRoom.upExit != null) {
        System.out.print("up ");
      }
      if(currentRoom.downExit != null) {
        System.out.print("down ");
      }
      System.out.println();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
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
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
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
            System.out.println("There is no door!");
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
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
