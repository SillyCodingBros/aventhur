import java.util.Collection;
import java.util.ArrayList;

/**
 * Gestion de liste d'Item
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ItemList
{
    // variables d'instance
    private Collection <Item> items;

    /**
     * Constructeur d'objets de classe ItemList
     */
    public ItemList()
    {
        // initialisation des variables d'instance
        items = new ArrayList<Item>();
    }

    /**
     * Adds an item to the room
     * @param item The item to be added.
     */
    public void addItem(Item item){
     items.add(item);
    }

    /**
     * Removes an item to the room
     * @param item The item to be removed.
     */
    public void removeItem(Item item){
     items.remove(item);
    }

    /**
     * Return your feeling about the collection use by room.
     * @return Unusal things who might notice in a string.
     */
    public String looking()
    {
      if (items.isEmpty()){
        return "Nothing particular in here";
      }
      else {
        String found;
        StringBuilder sb = new StringBuilder("");
        for (Item item : items) {
          sb.append(item.getComment());
          sb.append(item.getLongDescription());
        }
        return sb.toString();
      }
    }

    /**
     * Return a string of the items .
     * @return Name of the items of your inventory in a string.
     */
    public String inventoryToString()
    {
      if (items.isEmpty()){
        return "NullPointerExecption: Nothing in the pockets!";
      }
      else {
        String inventory;
        StringBuilder sb = new StringBuilder("");
        for (Item item : items) {
          sb.append(item.getName());
          sb.append("\n");
        }
        return sb.toString();
      }

    }
}
