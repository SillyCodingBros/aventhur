import java.util.Random;

/**
 * Generate a random int for a given bounce
 *
 * @author ORNIACKI Thomas
 * @version 2020.04
 */
public class RoomRandomizer
{
    /**
     * Return a random int betwen 0 included and
     * the given argument
     * @param  nbRoom number of rooms
     * @return a radom index for a room
     */
    public static int getRoom(int nbRoom)
    {
      Random rand = new Random(System.currentTimeMillis());
      return rand.nextInt(nbRoom);
    }
}
