/**
 * Door class - a door separating two rooms
 *
 * This class represents doors
 *
 **/

 public class Door {
    private Boolean isLocked;
    private Item unlockItem;
    private String description;
    //private Boolean isTrap;
    //private Room r1;
    //private Room r2;
    //private Boolean direction; // in case door is a trap : 0 for r1->r2, 1 for r2->r1

    /**
     * Create a door to door block rooms
     * @param key The room's description.
     */
    public Door(Item key, String description /*, Boolean trap, Room r1, Room r2, Boolean direction*/){
        //this.isLocked = isLocked;
        this.isLocked = true;
        this.unlockItem = key;
        this.description = description;
        //this.isTrap = trap;
        //this.r1 = r1;
        //this.r2 = r1;
        //this.direction = direction;
    }

    /**
     * Tells you whether you can access that door block room.
     * @param inventory The player's inventory.
     * @return A boolean according to your ability to pass.
     */
    public boolean canPass(ItemList inventory){
      if (!isLocked) return true;
      if (inventory.hasItem(unlockItem.getName()) == null) return false;
      else isLocked = false;
      return true;
    }

    /**
     * Return the door's description
     * @return The description of the closed door.
     */
    public String getDescription()
    {
        return description;
    }




    /*
    public Boolean getLocked(){
        return this.isLocked;
    }

    public Item getKey(){
        return this.unlockItem;
    }


    public Boolean isTrap(){
        return this.isTrap;
    }

    public Boolean getDoorDirection(){
        return this.direction;
    }

    public void setLoked(Boolean newState){
        this.isLocked = newState;
    }

    public void setTrap(Boolean newState){
        this.isTrap = newState;
    }
    */
 }