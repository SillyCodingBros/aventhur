package pkg_game_logic;

import java.util.HashMap;

import pkg_game_logic.Parser;
import pkg_command.Command;
import pkg_command.CommandWord;
import pkg_command.CommandWords;
import pkg_game_entities.Player;
import pkg_game_entities.Door;
import pkg_game_entities.Item;
import pkg_game_entities.Room;
import pkg_game_entities.TransporterRoom;

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

public class GameEngine
{
    private Parser parser;
    public HashMap <String, Room> roomMap;
    private UserInterface gui;
    private Player player;
    private String language;
    private Boolean warned;
    private Room attic, farm, pigs, pub, storageRoom, fountain, market, forge, home, entrance, abandonnedHouse, basement;
    private TransporterRoom transporter;
    private CommandWords cmdWords;

    /**
     * Constructor for objects of class GameEngine
     * Create the game and initialise its internal map.
     * @param language The language "en" or "fr".
     */
    public GameEngine(String language)
    {
      this.language = language;
      warned = false;
      player = new Player();
      createRooms();
      parser = new Parser(language);
      cmdWords = new CommandWords(language);
    }

    /**
     * Sets the gui and prints le wellcome message
     * @param userInterface ui object
     */
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
                                "Type '"+ cmdWords.commandWordToString(CommandWord.HELP)+"' anytime to see commands.\n";
        gui.println(welcomeMessage);
        gui.println(player.getCurrentRoom().getLongDescription());
        gui.showImage(player.getCurrentRoom().getImageName());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {

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

        //add them to the roomMap
        roomMap = new HashMap<String, Room>();
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

        // Transporter Room
        transporter = new TransporterRoom("in a very strange room.\nWhat that big blue things\nYou can't resist and need to jump in it", "pictures/village/transporterRoom.jpg");
        transporter.setRoomMap(roomMap);

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
        fountain.setDoor("north", new Door(new Item("Majority"), "Bouncer: You're not looking 18 Kiddo!\nI don't even like booze why would I ever wanna go there anyway!"));
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
        forge.setExit("west", transporter);
        // transporter "exists"
        transporter.setExit("north", transporter);
        // home exits
        home.setExit("north", market);
        // entrance exits
        entrance.setExit("west", fountain);
        // entrance.setExit("east", greatRoad);
        // abandonned house exists
        abandonnedHouse.setExit("west", market);
        abandonnedHouse.setExit("down", basement);

        player.setCurrentRoom(home);  // start game outside

        //add item to some room
        Item necklace;
        necklace = new Item();
        necklace.setWeight(1);
        necklace.setPrice(50);
        necklace.setCommment("Oh! What's shining over there?\n");
        necklace.setName("Necklace");
        necklace.setDescription("It looks magical!\n");
        necklace.setLongDescription("But I ain't no witcher after all!\n");
        pigs.getItemList().addItem(necklace);

        Item cookie = new Item();
        cookie.setEatable();
        cookie.setWeight(1);
        cookie.setPrice(0);
        cookie.setBuffWeight(10);
        cookie.setCommment("Its a ");
        cookie.setName("Cookie");
        cookie.setDescription("Mom is the best at making cookies.\n");
        cookie.setLongDescription("Maybe I'll get stronger if I eat it ?\n");
        home.getItemList().addItem(cookie);

        Item beamer = new Item();
        beamer.setWeight(1);
        beamer.setPrice(0);
        beamer.setCommment("There is a weird futuristic looking item on the ground.\n");
        beamer.setName("Beamer");
        beamer.setDescription("You think you know what this is, it lets you teleport !\n");
        beamer.setLongDescription("Charge it somewhere, and fire it to go back to this place !\n");
        market.getItemList().addItem(beamer);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public void interpretCommand(String commandLine)
    {
        gui.println(commandLine);

        Command command = parser.getCommand(commandLine);

        if(command == null) {
            gui.println("I don't know what you mean...");
        } else {
            if(warned && player.getCurrentRoom() == basement){
                if(!commandLine.equalsIgnoreCase(cmdWords.commandWordToString(CommandWord.USE)+ " beamer") ||
                    player.getInventory().hasItem("beamer") == null ||
                    player.getInventory().hasItem("beamer").getCooldown() != 1){

                  chickenDeath();
                  return;
                }
            }
            command.execute(player, this, gui);
        }
    }

    /**
     * Displays goodbye message
     */
    public void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
        return;
    }

    /**
     * Killed by chicken, displays death message and ends the game
     */
    private void chickenDeath(){
        gui.println("The chicken dashes towards you. You panick and try to climb back up the rotten ladder. But as you start climbing, it collapses under your weight. \n Did you really need to eat this much last night ? You regret it now anyway. The chicken clucks in a very scary way. Defenceless, you get poked to death by the giant chaotic chicken. A miserable way to die, you rekon.\n  - You fell into the evil chicken's trap. Be more carefull next time.");
        endGame();
    }

    /**
     * Sets warning variable to true
     */
    public void setWarned(){
      warned = true;
    }

    /**
     * returns the used language
     */
    public String getLanguage(){
      return language;
    }
}
