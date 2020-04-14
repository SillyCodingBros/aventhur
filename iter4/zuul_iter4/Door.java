/**
 * Door class - a door separating two rooms
 *
 * This class represents doors
 *
 **/

 public class Door {
    private Boolean isLocked;
    private Item unlockItem;
    private Boolean isTrap;
    private Room r1;
    private Room r2;
    private Boolean direction; // in case door is a trap : 0 for r1->r2, 1 for r2->r1
    
    public Door(Boolean lock, Item key, Boolean trap, Room r1, Room r2, Boolean direction){
        this.isLocked = lock;
        this.unlockItem = key;
        this.isTrap = trap;
        this.r1 = r1;
        this.r2 = r1;
        this.direction = direction;
    }

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
 }