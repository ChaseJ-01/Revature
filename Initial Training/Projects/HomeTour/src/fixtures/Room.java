package fixtures;

import java.util.*;

public class Room extends Fixture {
	private Map<String, Room> exits;
	public boolean locked = false;
	private List<Item> inventory;
	
	//Constructor.
	public Room(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
		exits = new HashMap<String, Room>();
		inventory = new ArrayList<Item>();
	}
	
	//Returns all exits of this room.
	public Set<Map.Entry<String, Room>> getExits() {
		
		return exits.entrySet();
	}
	
	//Returns a certain exit of this room.
	public Room getExit(String string) {
		return this.exits.get(string);
	}
	
	//Adds a room 
	public void addRoom(String string, Room room) {
		exits.put(string, room);
		return;
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
	
	@Override
	public void unlock() {
		locked = false;
		System.out.println(activationDescription);
	}
}
