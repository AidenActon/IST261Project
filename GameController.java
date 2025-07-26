
// Author: Hafsah
/*
This is the controller when launching the app.
All functions that will load upon launching is directed from here!
 */
import java.util.ArrayList;

public class GameController {

    private ArrayList<Room> rooms;  //The list of rooms that are available/unlocked upon launch
    private int currentRoomIndex; // The current room Info
    private ArrayList<Item> inventory; // A list of the user's inventory

    // private GameUI view; - OR whatever the name will be for the view

    //--------the rooms and inventory and current index of the room---------

    rooms =new ArrayList<>();
    inventory =new ArrayList<>();
    currentRoomIndex =0;

    createTestData(); // make fake rooms for now.

    // the view will be created and changed from this controller
    view = new

    GameUI(this); //  this will change depending on the view built (Whoever Makes the ui, the name will be called here)

    updateView(); // Show first room


    //----Test data for now!-----
    private void createTestData() {
        rooms.add(new Room("Start Room","The Room you start in", 001));
        rooms.add(new Room("Room 2","The second Room",002));
    }

    //----Update the view for the room the user is in!-------
    public void updateView() {
        Room current = rooms.get(currentRoomIndex);
        view.setRoomData(current); // View will show room name, desc, ID
        view.setInventoryDropdown(inventory); // view will update inv list
    }

    //-----Go to the next room--------
    public void goNextRoom() {
        if (currentRoomIndex < rooms.size() - 1)   {
            currentRoomIndex++;
            updateView();
        } else {
            view.showMessage("You're already in the latest room!");
        }
    }

    //-------Go back to previous rooms--------
    public void goBackRoom() {
        if (currentRoomIndex > 0) {
            currentRoomIndex--;
            updateView();
        } else {
            view.showMessage("You're already in the first room!");
        }
    }

    //----------Pick up items function/AKA add----------
    public void pickupItem(Item item) {
        inventory.add(item);
        view.showMessage(item.getName() + " has been added to your inventory!");
        updateView(); // Refresh inventory dropdown
    }

    //--------Drop items function//AKA delete-----------
    public void dropItem(Item item) {
        if(inventory.contains(item)) {
            inventory.remove(item);
            view.showMessage(item.getName()) + " has been dropped!");
        } else {
            view.showMessage("That item isn't in your inventory.")
        } updateView(); //Refresh inventory dropdown

        //------------Get item descriptions in the droplist--------
        public String getItemDescription(String itemName) {
            for (Item item : inventory) {
                if (item.getName().equals(itemName)) {
                    return item.getName();
                }
            } return "No Description available.";
        }

        //------Search room function---------
        public void searchRoom() {
            Room current = rooms.get(currentRoomIndex);

            //  temp riddle (Later load from puzzlemodel.java which will have info)
        String answer = view.promptUser(
                "Riddle: I have keys but no locks, space but no room. You can enter but not go outside. What am I?");

        if (answer == null) return; // they click cancel

        if (answer.trim().equalsIgnoreCase("Keyboard")) {
            Item reward = new Item("Key", "a strange key", true);
            pickupItem(reward); // adds to inventory and shows message
            } else {
            view.showMessage("Inncorrect. Try again!");
            }
        }

    }
}

