/**
 * Class Player - a player in the game
 *
 * This class represents the global state of the player
 * This includes location, stats, items...  
 *
 **/

public class Player{
    private Integer maxWeight;
    private Integer currentWeight;
    private Integer hp;
    private Room currentRoom;

    public Player(){

    }

    /**
    * Return the current room.
    * @return The current room.
    */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
    * Set the current room.
    * @param newRoom The new room the player sits in.
    */
    public void setCurrentRoom(Room newRoom){
        this.currentRoom = newRoom;
    }
}