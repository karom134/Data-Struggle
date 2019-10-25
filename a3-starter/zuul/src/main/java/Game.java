import java.util.HashMap;
import java.util.Random;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Room[] room_lst;
    private Room key_room;
    private boolean key=false;
    private boolean Excalibur=false;
    private Room sword_room;
    private int scan_count=10;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office,canteen,com_lab,classroom,oaa,common_room,
                co_working_space, medical_room,locked_room, study_room,library,teleporter,goal;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("something that our university doesn't have");
        pub = new Room("a place that doesn't belong to university");
        lab = new Room("a place where safety=0");
        office = new Room("somewhere you only visit once a year");
        canteen=new Room("where you get food");
        com_lab=new Room("A place where a lot of student suffer");
        classroom= new Room("Good place for sleeping");
        oaa=new Room("place that miraculously doesn't get burn down yet");
        common_room=new Room("room that only some majors can have for some reasons");
        study_room=new Room("a place that you generally doesn't use for study");
        medical_room=new Room("Good option if you plan to skip some class");
        co_working_space=new Room("something that too far for us to use");
        library=new Room("place that you won't visit because of e-book");
        teleporter=new Room("this room is full of magic, might have something happen when you exit the room");
        goal=new Room("there is a door that can be use to escape from this place");
        locked_room=new Room("this room that has no door, so you are dead now");
        // initialise room exits
        /*outside.setExits(theater, null  , lab, pub);
        theater.setExits(classroom, null, null, outside);
        pub.setExits(null, outside, null, canteen);
        lab.setExits(outside, office, theater, null);
        office.setExits(com_lab, null, null, lab);*/
        outside.auto_set_exit(new String[]{"north", "south"}, new Room[]{theater, lab});
        theater.auto_set_exit(new String[]{"north", "south","up"}, new Room[]{classroom, outside,library});
        pub.auto_set_exit(new String[]{"east"},new Room[]{lab});
        lab.auto_set_exit(new String[]{"north","west","east"},new Room[]{outside,pub,canteen});
        canteen.auto_set_exit(new String[]{"west","east"},new Room[]{lab,common_room});
        common_room.auto_set_exit(new String[]{"west","up","down"},new Room[]{canteen,study_room,teleporter});
        com_lab.auto_set_exit(new String[]{"west"},new Room[]{classroom});
        classroom.auto_set_exit(new String[]{"north","west","east","south"},new Room[]{oaa,office,com_lab,theater});
        office.auto_set_exit(new String[]{"east","down"},new Room[]{classroom,co_working_space});
        oaa.auto_set_exit(new String[]{"south","up"},new Room[]{classroom,medical_room});
        study_room.auto_set_exit(new String[]{"down"},new Room[]{common_room});
        library.auto_set_exit(new String[]{"down"},new Room[]{theater});
        medical_room.auto_set_exit(new String[]{"down","up"},new Room[]{oaa,goal});
        co_working_space.auto_set_exit(new String[]{"up"},new Room[]{office});
        goal.auto_set_exit(new String[]{"down"},new Room[]{medical_room});
        teleporter.auto_set_exit(new String[]{"up"},new Room[]{common_room});

        room_lst=new Room[]{outside, theater, pub, lab, office,canteen,com_lab,classroom,oaa,common_room, co_working_space,
                medical_room, locked_room, study_room,library,goal,teleporter};
        currentRoom = outside;  // start game outside
        Random rand=new Random();
        int n = rand.nextInt(room_lst.length-2);
        while (room_lst[n].checker("dead")) {
            n = rand.nextInt(room_lst.length - 2);
        }
        key_room=room_lst[n];
        key_room.key=true;
        int n2=rand.nextInt(room_lst.length-1);
        while(n2==n){
            n2=rand.nextInt(room_lst.length-1);
        }
        sword_room=room_lst[n2];
        sword_room.sword=true;
    }
    /*private void setZombie_room(Room room_name){
        zombie_room.zombie=false;
        zombie_room=room_name;
        zombie_room.zombie=true;
    }
    private void removezombie(){
        for(Room room: room_lst){
            room.zombie=false;
        }
    }*/

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(! endgame()){
                finished=true;
            }
            if(scan_count==0&&key==false){
                System.out.println("You waste all your scan, so you can't find a key anymore");
                finished=true;
            }
            if(currentRoom.checker("dead")){
                finished=true;
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void current_loc_info(Room currentRoom){
        System.out.println(currentRoom.getDescription());
        System.out.print("Exits: ");
        HashMap<String,Room> tmp=currentRoom.get_info();
        for(String key: tmp.keySet()){
            if(tmp.get(key)!=null){
                System.out.print(key+" ");
            }
        }
        /*if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }*/
        System.out.println();
    }





    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        current_loc_info(currentRoom);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            current_loc_info(currentRoom);
        }
        else if (commandWord.equals("back")){
            currentRoom=previousRoom;
            current_loc_info(currentRoom);
        }
        else if (commandWord.equals("scan")) {
            if (key == false && currentRoom.key == true) {
                System.out.println("You found a key");
                current_loc_info(currentRoom);
                key = true;
            } else if (Excalibur == false && currentRoom.sword == true) {
                System.out.println("You found an Excalibur");
                Excalibur = true;
            }
            else{
                System.out.println("You found nothing");
                scan_count--;
            }
            System.out.println("You have "+scan_count+" scans left");
            current_loc_info(currentRoom);

        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help back look scan");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private boolean endgame(){
         if(key==true&&currentRoom.checker("escape")) {
             System.out.println("Victory");
             return false;
        }
         return true;
    }
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Random rand = new Random();
        // Try to leave current room.
        Room nextRoom = null;
        HashMap<String, Room> tmp = currentRoom.get_info();
        if (currentRoom.checker("magic")) {
            System.out.println("You are teleported!!!!!");
            int n = rand.nextInt(room_lst.length - 2);
            nextRoom = room_lst[n];
        } else {
            nextRoom = tmp.get(direction);
        }
        /*if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }*/

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            if (endgame()) {
                current_loc_info(currentRoom);
            }
        }
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
