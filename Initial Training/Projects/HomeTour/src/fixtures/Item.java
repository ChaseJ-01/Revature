package fixtures;

import java.util.Scanner;

import game.Player;

public class Item extends Fixture{
	//longDescription is look description
	//shortDescription is room description
	public boolean holdable = false;
	private Item keyItem = null;
	private Room unlocks = null;
	public Item contains = null;
	private boolean active = true;
	
	public void setKeyItem(Item item) {
		keyItem = item;
	}
	
	//Checks if two items can be used together
	public void activate(Item item, Player player) {
		if(!this.active) {
			System.out.println("You can't do anything with this.");
			return;
		}
		
		//Check if items are usable together. If so, unlocks any connected rooms and adds any required items.
		if(item == this.keyItem || this == item.keyItem) {
			this.unlock();
			item.unlock();
			if(this.contains != null) {
				player.addItem(this.contains); //I don't use the pickup method here because this item does not exist in currentRoom.
			}
			if(item.contains != null) {
				player.addItem(item.contains);
			}
			if(this.activationDescription != null) {
				System.out.println(this.activationDescription);
			}
			if(item.activationDescription != null) {
				System.out.println(item.activationDescription);
			}
			this.active = false;
			item.active = false;
			
			//Remove used items from player inventory
			for(int i=0; i < player.getInventory().size(); i++) {
				if(player.getItem(i) == this) {
					player.getInventory().remove(i);
					continue;
				}
				if(player.getItem(i) == item) {
					player.getInventory().remove(i);
					continue;
				}
			}
			
			return;
		}
		
		System.out.println("You don't think these items can be used together.");
	}
	
	//This one takes a scanner because the Keypad needs to override this method. It's clunky, but there aren't many special items so it works.
	public void activate(Player player, Scanner scanner) {
		if(!this.active) {
			System.out.println("You can't do anything with this.");
			return;
		}
		
		if(unlocks == null && contains == null) {
			if(this.lockedDescription != null) {
				System.out.println(this.lockedDescription);
			} else {
				System.out.println("You don't know what to do with " + this.name + ".");
			}
			return;
		}
		
		if(keyItem != null) {
			System.out.println(lockedDescription);
			return;
		} else unlock();
		
		if(this.contains != null) {
			player.addItem(this.contains);
		}
		
		if(this.activationDescription != null) {
			System.out.println(activationDescription);
		}
		this.active = false;
	}
	
	public void unlock() {
		if(this.unlocks != null) {
			unlocks.unlock();
		}
	}
	
	public void setUnlocks(Room room) {
		unlocks = room;
	}
	
	public Room getUnlocks() {
		return unlocks;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean b) {
		active = b;
	}
	
}
