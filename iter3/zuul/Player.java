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
    private ItemList inventory;

    public Player(){
        inventory = new ItemList();
    }
    /**
    * Return the current room.
    * @return The current room.
    */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     * return the player's inventory
     * @return the inventory
     */
    public ItemList getInventory(){
        return this.inventory;
    }


    /**
    * Set the current room.
    * @param newRoom The new room the player sits in.
    */
    public void setCurrentRoom(Room newRoom){
        this.currentRoom = newRoom;
    }

    /**
    * Return the remaining HP.
    * @return The HP of the player.
    */
    public Integer getHealthPoints(){
        return hp;
    }

    /**
    * Set the HP of the player.
    * @param newHP The new HP value of the player.
    */
    public void setHealthPoints(Integer newHP){
        this.hp = newHP;
    }

    /**
    * Return the current weight carried by player.
    * @return The total weight of inventory.
    */
    public Integer getCurrentWeight(){
        return currentWeight;
    }

    /**
    * Set the current weight carried by player.
    * @param newWeight The new weight of the player.
    */
    public void setCurrentWeight(Integer newWeight){
        this.currentWeight = newWeight;
    }

    /**
    * Return the total weight player can carry.
    * @return The maximum weight.
    */
    public Integer getMaxWeight(){
        return maxWeight;
    }

    /**
    * Set the maximum weight a plyer can carry.
    * @param newMaxWeight The new maximum weight the player can carry.
    */
    public void setMaxWeight(Integer newMaxWeight){
        this.maxWeight = newMaxWeight;
    }
}