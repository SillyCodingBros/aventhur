import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Room
{
    private String description;
    private HashMap <String, Room> exits;
    private HashMap <String, Door> doors;
    private String imageName;
    private ItemList items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String image)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        doors = new HashMap<String, Door>();
        items = new ItemList();
        imageName = image;
    }

    /**
     * return the room's itemlist
     * @return the itemlist
     */
    public ItemList getItemList(){
      return this.items;
    }

    /**
    * Return a description of the room's exits,
    * for example, "Exits: north west".
    * @return A description of the available exits.
    */
    public String getExitString()
    {
        StringBuilder sb = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for(String exit : keys ) {
            sb.append(" ");
            sb.append(exit);
        }
        return sb.toString();
    }

    /**
     * Return the room associated to the given direction.
     * @param direction The direction you wish to go to.
     * @return the room associated to the given direction.
     */
    public Room getExit(String direction)
    {
      return exits.get(direction);
    }

    /**
     * Add an exit for the given direction to a given room for this room.
     * @param direction The direction for new the exit.
     * @param neighbor The room for the new exit.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Tells you whether you can access the direction.
     * @param direction The direction for new the exit.
     * @param inventory The player's inventory.
     * @return A boolean according to your ability to pass.
     */
    public boolean canPass(String direction, ItemList inventory)
    {
      Door door = doors.get(direction);
      if (door == null) return true;
      if (door.canPass(inventory)) return true;
      return false;
    }

    /**
     * Return the door associated to the given direction.
     * @param direction The direction you wish to go to.
     * @return the door associated to the given direction.
     */
    public Door getDoor(String direction)
    {
      return doors.get(direction);
    }

    /**
     * Add a door for the given direction for this room.
     * @param direction The direction for new the exit.
     * @param door The door to build.
     */
    public void setDoor(String direction, Door door)
    {
        doors.put(direction, door);
    }

    /**
     * Return the room's description, of the form:
     * You are in the kitchen.
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, of the form:
     * You are in the kitchen.
     * Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
      return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's image name
     * @return the name of room's image.
     */
    public String getImageName()
    {
     return imageName;
    }
}
