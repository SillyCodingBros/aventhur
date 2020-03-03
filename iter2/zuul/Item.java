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

  private String description;
  private String longDescription;
  private String comment;
  private Integer price = -1;
  private Integer weight = -1;

  public Item(){
    this.description = "Wow";
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
   * Set the description of the given item.
   * @param description The description for the item.
   */
  public void setDescription(String description){
    this.description = description;
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

}