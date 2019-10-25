import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;
    public boolean key=false;
    public boolean sword=false;
    public boolean zombie=false;
    /*public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;*/
    private HashMap<String,Room> room_info = new HashMap<String,Room>();
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */


    public Room(String description)
    {
        this.description = description;
        room_info.put("north",null);
        room_info.put("east",null);
        room_info.put("south",null);
        room_info.put("west",null);
        room_info.put("up",null);
        room_info.put("down",null);
    }

    /*
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void  setExit(String direction, Room neighbor){/*setExits(Room north, Room east, Room south, Room west) */
        room_info.put(direction,neighbor);
    }
    public void auto_set_exit(String[] directions, Room[] neighbors){
        for(int idx=0;idx<directions.length;idx++){
            setExit(directions[idx],neighbors[idx]);
        }
    }
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    public HashMap get_info(){
        return room_info;
    }
    public boolean checker(String word){
        if(description.contains(word)){
            return true;
        }
        return false;
    }
}
