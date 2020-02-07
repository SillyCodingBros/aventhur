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
    public String description;
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public Room upExit;
    public Room downExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
    }

    public Room getExit(String direction)
    {
      if(direction.equals("north")) {
        return northExit;
      }
      if(direction.equals("east")) {
        return eastExit;
      }
      if(direction.equals("south")) {
        return southExit;
      }
      if(direction.equals("west")) {
        return westExit;
      }
      if(direction.equals("up")) {
        return upExit;
      }
      if(direction.equals("down")) {
        return downExit;
      }
      return null;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param up The up exit.
     * @param down The down Exit;
     */
    public void setExits(Room north, Room east, Room south, Room west, Room up, Room down)
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(up != null)
            upExit = up;
        if(down != null)
            downExit = down;
    }

    /**
    * Return a description of the room's exits,
    * for example, "Exits: north west".
    * @return A description of the available exits.
    */
    public String getExitString()
    {
      System.out.print("Exits: ");

      if(northExit != null) {
        System.out.print("north ");
      }
      if(eastExit != null) {
        System.out.print("east ");
      }
      if(southExit != null) {
        System.out.print("south ");
      }
      if(westExit != null) {
        System.out.print("west ");
      }
      if(upExit != null) {
        System.out.print("up ");
      }
      if(downExit != null) {
        System.out.print("down ");
      }
      return null;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
