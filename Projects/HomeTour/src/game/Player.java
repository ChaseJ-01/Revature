package game;

import java.util.*;
import fixtures.Item;
import fixtures.Room;

public class Player {
	private Room currentRoom = null;
	List<Item> inventory = new ArrayList<Item>();
	
	boolean move(String direction) {
		if(currentRoom.getExit(direction) != null) {
			if(currentRoom.getExit(direction).locked == true) {
				System.out.println(currentRoom.getExit(direction).lockedDescription);
				return false;
			}
			this.currentRoom = currentRoom.getExit(direction);
			return true;
		}
		return false;
	}
	
	public Room getRoom() {
		return currentRoom;
	}
	
	public void setRoom(Room room) {
		currentRoom = room;
	}
	
	public List<Item> getInventory(){
		return inventory;
	}
	
	public Item getItem(int index) {
		return inventory.get(index);
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public void pickUp(Item item) {
		for(int i=0; i < inventory.size(); i++) {
			if(inventory.get(i) == item) {
				System.out.println("You're already holding the " + item.name + "!");
				return;
			}
		}
		if(item.holdable == false) {
			System.out.println("You can't pick that up!");
			return;
		}
		this.addItem(item);
		currentRoom.getInventory().remove(item);
		System.out.println("You pick up the " + item.name + ".");
	}
}
