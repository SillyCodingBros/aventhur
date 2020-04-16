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

    /**
     * Create a door to door block rooms
     * @param key The room's description.
     */
    public Door(Item key, String description){
        this.isLocked = true;
        this.unlockItem = key;
        this.description = description;
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
 }