import java.util.HashMap;
import java.util.Stack;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.util.stream.Stream;
import java.io.IOException;

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
    //public Stack <Command> history;
    private UserInterface gui;
    private Player player;
    private String language;
    private Boolean warned;
    private Room attic, farm, pigs, pub, storageRoom, fountain, market, forge, home, entrance, abandonnedHouse, basement;
    private TransporterRoom transporter;
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
      //history = new Stack<Command>();
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
        CommandWords help = new CommandWords(language);
        String welcomeMessage = "\n"+
                                "Welcome to Aventhür!\n"+
                                "The Ultimate Adventure Game.\n"+
                                "\n"+
                                "Type '"+ help.commandWordToString(CommandWord.HELP)+"' anytime to see commands.\n";
        gui.println(welcomeMessage);
        printLocationInfo();
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
     * Print out room's description and exists.
     */
    private void printLocationInfo()
    {
      gui.println(player.getCurrentRoom().getLongDescription());
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
            command.execute(player, this, gui);
        }

        //CommandWord commandWord = command.getCommandWord();

        ////if(command.isUnknown() || commandWord == CommandWord.UNKNOWN) {
        //  String unknownCommand = "I don't know what you mean...";
        //  gui.println(unknownCommand);
        //  return;
        //}

        // Special basement handler
        //if(player.getCurrentRoom() == basement && warned){
            // if user was warned
            // next command isnt to use the beamer or player doesnt own it. Beamer is charged
            //if(!(commandWord == CommandWord.USE && command.hasSecondWord() && command.getSecondWord().equals("beamer") && player.getInventory().hasItem(command.getSecondWord()) != null && player.getInventory().hasItem("beamer").getCooldown() == 1)){
            //    chickenDeath();
            //    return;
            //}
        //}
        /*
        switch(commandWord){
            case HELP:
                printHelp(); return;
            case BACK:
                goBack(command); return;
            case GO:
                goRoom(command, false); return;
            case LOOK:
                look(); return;
            case EAT:
                if(command.hasSecondWord()){
                    Item toEat = player.getInventory().hasItem(command.getSecondWord());
                    if(toEat == null){
                        gui.println("Cant eat something you don't have !");
                        return;
                    }
                    gui.println(player.eat(toEat));
                }
                return;
            case TEST:
                test_with_script(command); return;
            case QUIT:
                if(command.hasSecondWord()) {
                    String quitMessage = "Quit what?";
                    gui.println(quitMessage);
                    return;
                  }
                  else {
                    endGame(); return;
                  }
            case PICK:
                if(command.hasSecondWord()){
                    Item toPick = player.getCurrentRoom().getItemList().hasItem(command.getSecondWord());
                    if(toPick == null){
                        String message = "No such item here...";
                        gui.println(message);
                        return;
                    }
                    if(player.getCurrentWeight() + toPick.getWeight() > player.getMaxWeight()){
                        String message = "Can't take it, its too heavy !";
                        gui.println(message);
                        return;
                    }
                    player.getInventory().addItem(toPick);
                    player.setCurrentWeight(player.getCurrentWeight() + toPick.getWeight());
                    player.getCurrentRoom().getItemList().removeItem(toPick);
                    String message = "You pick up the " + command.getSecondWord() + " and put it in your backpack.";
                    gui.println(message);
                    return;
                  }
                  else{
                    String message = "What should I pick up ?";
                    gui.println(message);
                    return;
                  }
            case DROP:
                if(command.hasSecondWord()){
                    Item toDrop = player.getInventory().hasItem(command.getSecondWord());
                    if(toDrop == null){
                        String message = "I dont own such a thing...";
                        gui.println(message);
                        return;
                    }
                    player.getCurrentRoom().getItemList().addItem(toDrop);
                    player.setCurrentWeight(player.getCurrentWeight() - toDrop.getWeight());
                    player.getInventory().removeItem(toDrop);
                    String message = "The " + command.getSecondWord() + " is now on the ground.";
                    gui.println(message);
                    return;
                  }
                  else{
                    String message = "What should I drop ?";
                    gui.println(message);
                    return;
                  }
            case ITEMS:
                  gui.println(player.getInventory().inventoryToString());
                  return;
            case USE :
                  if(command.hasSecondWord()){
                    if(command.getSecondWord().equals("beamer")){
                      history.push(command);
                      gui.println(player.useBeamer());
                      if(player.getCurrentRoom().getImageName() != null) {
                        gui.showImage(player.getCurrentRoom().getImageName());
                      }
                      return;
                    }
                    return;
                  }
                  else{
                    String message = "What should I use ?";
                    gui.println(message);
                    return;
                  }
            default:
                return;
        }
        */
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
     *
    private void goBack(Command command)
    {
        String errorMessage;
        Command previousCmd, newCommand;

        if(command.hasSecondWord()){
            // the user gave a second word. Not good.
            errorMessage = "You can't go back here, friend ! If you want to go here, dont go back and just go ! Try only \"back\"...";
            gui.println(errorMessage);
            return;
        }
        // no history or use beamer, dont do anyhting.
        // beamer limits hisotry because otherwise history contains commands that can't be used in the current room
        if(player.isHistoryEmpty() || history.get(history.size()-1).getSecondWord().equals("beamer")){
            // no history
            errorMessage = "Can't go back anymore, you are allready where you started !";
            gui.println(errorMessage);
            return;
        }
        previousCmd = history.get(history.size() - 1);
        history.pop();

        if(previousCmd.getSecondWord().equals("north"))
            newCommand = new Command(CommandWord.GO, "south");
        else if(previousCmd.getSecondWord().equals("south"))
            newCommand = new Command(CommandWord.GO, "north");
        else if(previousCmd.getSecondWord().equals("east"))
            newCommand = new Command(CommandWord.GO, "west");
        else if(previousCmd.getSecondWord().equals("west"))
            newCommand = new Command(CommandWord.GO, "east");
        else if(previousCmd.getSecondWord().equals("up"))
            newCommand = new Command(CommandWord.GO, "down");
        else
            newCommand = new Command(CommandWord.GO, "up");
        goRoom(newCommand, true);
    }
    */

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
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            errorMessage = "There is no door!";
            gui.println(errorMessage);
            return;
        }
        if (!player.getCurrentRoom().canPass(direction, player.getInventory())) {
          gui.println(player.getCurrentRoom().getDoor(direction).getDescription());
        }
        else {
            if(player.isMaxMovesReached() && player.getWonState() == false){
                errorMessage = "As you walk around, you hear a sudden craking sound. Scared, you look around and see a tide of demonic abominations falling on the village. The sky goes dark and the air fills up in villager's screams. \nThere is blood everywhere. By the time you finally understand what is going on, you feel an extreme pain on your stomach.\n";
                errorMessage += "Instinctively, you place your hand on your stomach, then look at it : it is covered in blood. Your blood. As you look up again, you see a horrible, bearly human face staring at you.\n";
                errorMessage += "After what seemed to be an eternity during which the creature seemed to be ejoying the growing pain in your stomach, it turns around and runs towards a new victim. You see Bork, the weaponsmith ferociously fighting two of the demons. \nYou see a third one sneaking up behind him. You want to scream a warning, but no sound comes out of your mouth. All there is is pain.\n";
                errorMessage += "You fall on your knees, in a growing pound of blood. The screams of the villagers slowly fade away. So does your pain. You feel empty, and tired. Is this even real ? Bork finally losses his fight, taken out by multiple wounds.\nMaybe it is real after all. Maybe the old Elibed's world ending premonitions were correct... Maybe you should have listened.\n\n";
                errorMessage += " - You took too long to complete the quest, and demons are invading the world. Try not to be so slow next time...\n";
                gui.println(errorMessage);
                endGame();
                return;
            }
            if(!fromBack)
                //history.push(command);
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
            if(player.getCurrentRoom() == basement)
                chickenWarn();
            if(player.getCurrentRoom().getImageName() != null) {
                gui.showImage(player.getCurrentRoom().getImageName());
            }
        }
    }

    /**
     * "look" was entered, rewrite to terminal the description of current room
     */
    private void look()
    {
        gui.println(player.getCurrentRoom().getItemList().looking());
    }

    /**
     * Execute command in given script
     */
    private void test_with_script(Command command)
    {
        Path filePath = FileSystems.getDefault().getPath("scripts", command.getSecondWord());
        try (Stream<String> lines = Files.lines( filePath ))
        {
        	lines.forEachOrdered(item->interpretCommand(item));
        }
        catch (IOException e)
        {
        	gui.println(e.toString());
        }
    }

    /**
     * get the history size
     * @return history size
     *
    public Integer getHistoryLenght(){
        return history.size();
    }
    */

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            String quitMessage = "Quit what?";
            gui.println(quitMessage);
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    public void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
        return;
    }

    private void chickenWarn(){
        warned = true;
        gui.println("Affraid by the bird, you step back. It walks towadrs you. You feel in danger and that it could attack you anytime. \nWhat should you do ?");
        return;
    }

    private void chickenDeath(){
        gui.println("The chicken dashes towards you. You panick and try to climb back up the rotten ladder. But as you start climbing, it collapses under your weight. \n Did you really need to eat this much last night ? You regret it now anyway. The chicken clucks in a very scary way. Defenceless, you get poked to death by the giant chaotic chicken. A miserable way to die, you rekon.\n  - You fell into the evil chicken's trap. Be more carefull next time.");
        endGame();
        return;
    }

    public void setWarned(){
      warned = true;
    }

    public String getLanguage(){
      return language;
    }
}
