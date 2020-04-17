package pkg_game_entities;

import java.util.HashMap;
import java.lang.reflect.Array;

/**
 * A transporter room in an adventure game
 *
 * @author ORNIACKI Thomas
 * @version 2020.04
 */
public class TransporterRoom extends Room
{
    private HashMap <String, Room> roomMap;

    /**
     * Create a Transporter room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * @param image the path to the room image
     */
    public TransporterRoom(String description, String image)
    {
        super(description, image);
    }

    /**
     * Set the roomMap to wich you can transport to.
     * @param roomMap The direction for new the exit.
     */
    public void setRoomMap(HashMap <String, Room> roomMap)
    {
        this.roomMap = roomMap;
    }

    /**
     * Return a random room, independent of the direction
     * paremeter
     *
     * @param direction Ignored
     * @return A random room
     */
     public Room getExit(String direction)
     {
       int index = RoomRandomizer.getRoom(roomMap.size());
       return roomMap.get(roomMap.keySet().toArray()[index]);
     }
}
