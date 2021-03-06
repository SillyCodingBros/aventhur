package pkg_game_entities;

import java.util.Stack;

import pkg_command.Command;

/**
 * Class Player - a player in the game
 *
 * This class represents the global state of the player
 * This includes location, stats, items...
 *
 * @author LEGOUEIX Nicolas and ORNIACKI Thomas
 * @version 2020.04
 **/

public class Player{
    private Integer maxWeight = 25;
    private Integer currentWeight = 0;
    private Integer hp = 100;
    private Integer armor = 0;
    private Integer attack = 10;
    private Room currentRoom;
    private ItemList inventory;
    private Boolean won = false;
    private Room saveRoom = null;
    private Stack <Command> history;
    private boolean fromBack;

    public Player(){
        inventory = new ItemList();
        history = new Stack<Command>();
        fromBack = false;
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
     * return if the player won
     * @return the winning state
     */
    public Boolean getWonState(){
        return this.won;
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

    /**
     * Eats the item if it can be. Eating adds stats to the player based
     * on item attributes.
     * @param toEat the item to eat
     * @return String informing the player about applied changes
     */
    public String eat(Item toEat){
        if(toEat.getEatable() == false){
          return "That wouldn't be so tastefull...";
        }
        StringBuilder str =  new StringBuilder("");
        if(toEat.getBuffWeight() > 0){
            maxWeight += toEat.getBuffWeight();
            str.append("Weight limit is now ");
            str.append(maxWeight);
            str.append("Kg !\n");
        }
        if(toEat.getBuffHp() > 0){
            hp += toEat.getBuffHp();
            str.append("Max health is now ");
            str.append(hp);
            str.append("hp !\n");
        }
        if(toEat.getBuffArmor() > 0){
            armor += toEat.getBuffArmor();
            str.append("Armor will now protect you from ");
            str.append(armor);
            str.append("hit points !\n");
        }
        if(toEat.getBuffAttack() > 0){
            attack += toEat.getBuffAttack();
            str.append("Attack is now ");
            str.append(attack);
            str.append("hit points !\n");
        }

        inventory.removeItem(toEat);

        return str.toString();
      }

    /**
     * Either charges or fires the beamer based on its cooldown value. Upon charge, the current room is saved
     * Upon fire, the current room is set the the saved virable and the cooldown is reset for a new charge.
     * @return Utility String to be displayed by caller
     */
    public String useBeamer(){
        Item beamer = inventory.hasItem("beamer");
        String retStr = new String("");

        if(beamer != null){
            // case beamer isnt charged
            if(beamer.getCooldown() == 0){
                beamer.setCooldown(1);
                saveRoom = currentRoom;
                retStr = "The beamer start emiting a dim blue light. It is now linked to this room.\n";
                return retStr;
            }
            // case beamer is charged
            else {
                beamer.setCooldown(0);
                currentRoom = saveRoom;
                retStr = "The beamer emits a big flash of light and you feel a sudden change in your surroundings\n";
                retStr += currentRoom.getLongDescription();
                return retStr;
            }
        }else{
            retStr = "Can't use the beamer if I dont have it !\n";
            return retStr;
        }
    }

    /**
     * checks if the command history is empty
     * @return boolean result
     */
    public boolean isHistoryEmpty(){
      return history.size() == 0;
    }

    /**
     * Gets the latest command in history
     * @return the last command entered
     */
    public Command getLastMove(){
      return history.get(history.size() - 1);
    }

    /**
     * Adds a command to the history
     * @param command the command to be added
     */
    public void historyAdd(Command command){
      history.add(command);
    }

    /**
     * removes the last command from history
     */
    public void historyPop(){
      history.pop();
    }

    /**
     * checks if the amount of commands entered is game ending
     * @return result boolean
     */
    public boolean isMaxMovesReached(){
      return history.size() == 666;
    }

    /**
     * Sets the fromback variable (for when movement was done by back command
     * and not by the user)
     * @param bool new boolean value for fromback
     */
    public void setFromBack(Boolean bool){
      fromBack = bool;
    }

    /**
     * gets the fromback value
     * @return fromback
     */
    public boolean getFromBack(){
      return fromBack;
    }
}