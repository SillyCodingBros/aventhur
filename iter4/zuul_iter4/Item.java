/**
 * Class Item - an item in the game
 *
 * An "Item" represents one object of the game,
 * it could have a description, a long description,
 * a weight, a price. Value set -1 if not used.
 *
 **/

public class Item
{

  private String name;
  private String description;
  private String longDescription;
  private String comment;
  private Integer price = -1;
  private Integer weight = -1;
  private Boolean canPick = false;
  private Boolean eatable = false;
  private Integer buffWeight = 0;
  private Integer buffHp = 0;
  private Integer buffArmor = 0;
  private Integer buffAttack = 0;

  public Item(){
  }

  public Item(String name){
    this.name = name;
  }

  /**
  * Return the name of the item.
  * @return The name of the item.
  */
  public String getName(){
    return name;
  }

  /**
  * Return the description of the item.
  * @return The description of the item.
  */
  public String getDescription(){
    return description;
  }

  /**
  * Return the long description of the item.
  * @return The long description of the item.
  */
  public String getLongDescription(){
    return description + longDescription;
  }

  /**
  * Return the finding comment of the item.
  * @return The comment of the item.
  */
  public String getComment(){
    return comment;
  }

  /**
  * Return the price of the item.
  * @return The price of the item.
  */
  public Integer getPrice(){
    return price;
  }

  /**
  * Return the weight of the item.
  * @return The weight of the item.
  */
  public Integer getWeight(){
    return weight;
  }
  /**
   * Return if the item can be picked up or not
   * @return A boolean representing the state of the item
   */
  public Boolean getPickable(){
    return canPick;
  }

  /**
     * return the weight carry buff of the item
     * @return the weight buff
     */
    public Integer getBuffWeight(){
      return buffWeight;
  }

  /**
   *  return the health bonus of an item
   * @return the health buff
   */
  public Integer getBuffHp(){
      return buffHp;
  }

  /**
   * return the amror bonus of an item
   * @return the armor buff
   */
  public Integer getBuffArmor(){
      return buffArmor;
  }

  /**
   * return the attack bonus of the item
   * @return the attack buff
   */
  public Integer getBuffAttack(){
      return buffAttack;
  }

  /**
   * return if you can eat the item
   * @return boolean
   */
  public Boolean getEatable(){
    return eatable;
  }

  /**
   * Set the description of the given item.
   * @param description The description for the item.
   */
  public void setDescription(String description){
    this.description = description;
  }

  /**
   * Set the name of the given item.
   * @param name The name for the item.
   */
  public void setName(String name){
    this.name = name;
  }

  /**
   * Set the long description of the given item.
   * @param description The long description for the item.
   */
  public void setLongDescription(String description){
    this.longDescription = description;
  }

  /**
   * Set the finding comment of the given item.
   * @param comment The comment for the item.
   */
  public void setCommment(String comment){
    this.comment = comment;
  }

  /**
   * Set the price description of the given item.
   * @param price The price for the item.
   */
  public void setPrice(Integer price){
    this.price = price;
  }

  /**
   * Set the weight of the given item.
   * @param weight The weight for the item.
   */
  public void setWeight(Integer weight){
    this.weight = weight;
  }

  /**
   * Set if an item can be picked up.
   * @param canPick boolean representing the new state of the item.
   */
  public void setPickable(Boolean canPick){
    this.canPick = canPick;
  }

  /**
     * Set the weight buff of the item
     * @param buff the value of the buff
     */
    public void setBuffWeight(Integer buff){
      buffWeight = buff;
  }

  /**
   * Set the health buff of the item
   * @param buff the value of the buff
   */
  public void setBuffHp(Integer buff){
      buffHp = buff;
  }

  /**
   * Set the armor buff of the item
   * @param buff the value of the buff
   */
  public void setBuffArmor(Integer buff){
      buffArmor = buff;
  }

  /**
   * Set the attack buff of the item
   * @param buff the value of the buff
   */
  public void setBuffAttack(Integer buff){
      buffAttack = buff;
  }

  public void setEatable(){
    eatable = true;
  }


}