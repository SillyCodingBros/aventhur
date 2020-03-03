/**
 *  This class is part of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.
 *
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that
 *  the parser returns.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */

import java.util.HashMap;
import java.util.Stack;

public class GameEngine
{
    private Parser parser;
    private Room currentRoom;
    public HashMap <String, Room> roomMap;
    public Stack <Command> history;
    private UserInterface gui;

    /**
     * Constructor for objects of class GameEngine
     * Create the game and initialise its internal map.
     */
    public GameEngine()
    {
        createRooms();
        parser = new Parser();
        history = new Stack<Command>();
    }


    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
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
        gui.println(welcomeMessage);
        //System.out.println("");
        //System.out.println("");
        //System.out.println("");
        //System.out.println();
        printLocationInfo();
        gui.showImage(currentRoom.getImageName());
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
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // rooms for village
        Room attic, farm, pigs, pub, storageRoom, fountain, market, forge, home, entrance, abandonnedHouse, basement;

        // create the rooms
        attic = new Room("in the farm's attic. There is \nhay all over the floor and a chicken looks at you as you climb the\nladder.", "pictures/village/attic.jpg");
        farm = new Room("inside the farm. There is a strong smell.", "pictures/village/farm.jpg");
        pigs = new Room("in the pig's enclosure. You spot\nsomething shining on the ground.", "pictures/village/pigs.jpg");
        pub = new Room("in the pub. The barman greets you, but \nnot the drunk customer on the left.", "pictures/village/pub.jpg");
        storageRoom = new Room("in the pub's stroage room.\nYou never loiked small spaces. There is something on the ground.", "pictures/village/storageRoom.jpg");
        fountain = new Room("in the village's square.\nNext to the fountain, the old Elibed is staring at you and a chicken\nis runnin, around.", "pictures/village/fountain.jpg");
        market = new Room("in the village's market. Its a very busy place. \nYou think you hear the clucking of a chicken", "pictures/village/market.jpg");
        forge = new Room("in the village's forge. The black-smith greets you.\nThere is an old rusty sword on the ground.", "pictures/village/forge.png");
        home = new Room("in your parent's house. You mom is here, as allways.\nA tastefull cake is on the table.", "pictures/village/home.jpg");
        entrance = new Room("at the village entrance. The guard calls you out.\nBetter go see what he wants", "pictures/village/entrance.jpeg");
        abandonnedHouse = new Room("inside a rotting house. For some reason,\nyou feel bad. There is a big, dirty, helmet on the ground.", "pictures/village/abandonnedHouse.jpg");
        basement = new Room("in the house basement. It feels like a dim, red\nlight is coming off the walls, and a huge chicken is staring at you.\nYou can't tell if it's eyes are actually glowing red or if its just reflexion.", "pictures/village/basement.jpg");

        // initialise room exits
        // attic exits
        attic.setExit("down", farm);
        //farm exists
        farm.setExit("east", fountain);
        farm.setExit("south", pigs);
        farm.setExit("up", attic);
        // pigs exits
        pigs.setExit("north", farm);
        // pub exits
        pub.setExit("east", storageRoom);
        pub.setExit("south", fountain);
        // storageRoom exists
        storageRoom.setExit("west", pub);
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

        //add item to some room
        Item necklace;
        necklace = new Item();
        necklace.setPrice(50);
        necklace.setDescription("It looks magical!\n");
        necklace.setLongDescription("But I ain't no witcher after all!\n");
        necklace.setCommment("Oh! What's shining over there?\n");
        pigs.addItem(necklace);
        //add them to the roomMap
        /*
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
        */
    }

    /**
     * Print out room's description and exists.
     */
    private void printLocationInfo()
    {
      gui.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public void interpretCommand(String commandLine)
    {
        //boolean wantToQuit = false;
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
          String unknownCommand = "I don't know what you mean...";
          gui.println(unknownCommand);
          return ;//false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
        {
            printHelp();
        }
        else if (commandWord.equals("back"))
        {
            goBack(command);
        }
        else if (commandWord.equals("go"))
        {
            goRoom(command, false);
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
          if(command.hasSecondWord()) {
              String quitMessage = "Quit what?";
              gui.println(quitMessage);
          }
          else {
              endGame();
          }
        }
        //return wantToQuit;
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
      gui.println(helpString);
    }

    /**
     * Try to go back to the previous room
     *
     */
    private void goBack(Command command)
    {
        String errorMessage;
        Command tmp, newCommand;
        if(command.hasSecondWord()){
            // the user gave a second word. Not good.
            errorMessage = "You can go back here, friend ! If you want to go here, dont go back and just go ! Try only \"back\"...";
            gui.println(errorMessage);
            return;
        }
        if(history.size() == 0){
            // no history
            errorMessage = "Can't go back anymore, you are allready where you started !";
            gui.println(errorMessage);
            return;
        }
        tmp = history.get(history.size() - 1);
        history.pop();

        if(tmp.getSecondWord().equals("north"))
            newCommand = new Command("go", "south");
        else if(tmp.getSecondWord().equals("south"))
            newCommand = new Command("go", "north");
        else if(tmp.getSecondWord().equals("east"))
            newCommand = new Command("go", "west");
        else if(tmp.getSecondWord().equals("west"))
            newCommand = new Command("go", "east");
        else if(tmp.getSecondWord().equals("up"))
            newCommand = new Command("go", "down");
        else
            newCommand = new Command("go", "up");
        goRoom(newCommand, true);
    }
    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command, Boolean fromBack)
    {
        String errorMessage;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            errorMessage = "Go where?";
            gui.println(errorMessage);
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
            gui.println(errorMessage);
        }
        else {
            if(!fromBack)
                history.push(command);
            currentRoom = nextRoom;
            printLocationInfo();

            if(currentRoom.getImageName() != null) {
                gui.showImage(currentRoom.getImageName());
            }



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
        gui.println(currentRoom.looking());
    }

    /**
     * "eat" was entered, print dummy info about eating TODO
     */
    private void eat()
    {
        String eatMessage = "You eat part of you provisions and feel full.";
        gui.println(eatMessage);
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

    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

}
