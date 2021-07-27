package game;

import java.util.*;
import java.util.Scanner;

import fixtures.Item;
import fixtures.Room;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Player player = new Player();
		RoomManager manager = new RoomManager();
		
		manager.init();
		player.setRoom(manager.startingRoom);
		player.addItem(manager.startingItem);
		
		System.out.println("You are awoken from suspended animation and greeted with an alarm.\n'WARNING! MAJOR SYSTEMS MALFUNCTION! AWAKENING ENGINEER'\n");
		
		printRoom(player);
		
		//Intro
		//Instructions
		//Help Command
		//unknown command
		
		while(true) {
			//Check to see if player has beaten the game
			if(manager.checkVictory()) {
				System.out.println("You have successfully repaired the reactor core, reset the ship's AI, and input new navigation coordinates.\nCongratulations! You have completed the game!");
				return;
			}
			
			System.out.println("----------");
			System.out.println("Please enter a command, or type 'help' for help.");
			String[] input = collectInput(scanner);
			
			//This fixes the double main method bug, sorta. It stops the keypad from hitting the default on the switch case.
			//It doesn't, however, stop the player from emptying the command prompt by holding enter.
			while(input[0] == "") {
				input = collectInput(scanner);
			}
			
			String[] command = parse(input, player);
			System.out.println(""); //Make the output look nicer
			
			switch (command[0]) {
				//Default case
				default: {
					System.out.println("Command not recognized.");
					break;
				}
			
				//Quit the game
				case "quit": {
					System.out.println("Are you sure you want to quit? (Y/N)");
					
					while(true) {
						input = collectInput(scanner);
						command = parse(input, player);
						if(command[0].compareTo("yes") == 0) {
							System.out.println("Thank you for playing.");
							scanner.close();
							return;
						}
						else {
							break;
						}
					}
					continue;
				}
				
				//Move the player (or don't, if the player can't move that way)
				case "move": {
					if(player.move(command[1])) {
						System.out.println("You move into " + player.getRoom().shortDescription +  "\n");
						printRoom(player);
						break;
					} else if(player.getRoom().getExit(command[1]) == null) {
						System.out.println("You cannot go that way.");
						break;
					} else {
						break;
					}
				}
				
				//Interact with items and fixtures.
				case "use": {
					
					//Command 1 returns null if both item inputs are invalid
					if(command[1] == null) {
						System.out.println("There are no objects like that in here.");
						break;
					}
					
					//Search for object references
					Item item1 = findItem(command[1], player);
					Item item2 = findItem(command[2], player);
					
					//Use items
					if(item1 != null && item2 != null) {
						item1.activate(item2, player);
					} else try {
						item1.activate(player, scanner);
						break;
					} catch(NullPointerException e){
						e.printStackTrace();
					}
					
					break;
				}
				
				//Look at things
				case "look": {
					//Command 1 returns null if both item inputs are invalid
					if(command[1] == null) {
						printRoom(player);
						break;
					}
					
					//Search for object references
					Item item1 = findItem(command[1], player);
					
					System.out.println(item1.longDescription);
					break;
				}
				
				//Add items to inventory
				case "take":{
					//Command 1 returns null if both item inputs are invalid
					if(command[1] == null) {
						printRoom(player);
						break;
					}
					
					//Search for object references
					Item item1 = findItem(command[1], player);
					
					player.pickUp(item1);
					break;
				}
				
				//Displays commands
				case "help":{
					System.out.println("Available commands:");
					System.out.println("move [direction] - Moves your character to an adjacent room");
					System.out.println("use [object] - Attempts to use an object");
					System.out.println("use [object] on [object] - Attempts to use two objects together, such as using a key on a lock");
					System.out.println("look at [object] - Gives a short description of the given object");
					System.out.println("take [object] - Picks up an object and puts it into your inventory");
					System.out.println("quit - Exits the game");
					break;
				}
			}
		}
	}
	
	//Displays the room descriptions.
	public static void printRoom(Player player) {
		System.out.println(player.getRoom().name);
		System.out.println("\n" + player.getRoom().longDescription);
		for(int i=0; i < player.getRoom().getInventory().size(); i++) {
			if(player.getRoom().getItem(i).holdable == true) {
				System.out.println(player.getRoom().getItem(i).shortDescription);
			}
		}
		printExits(player);
	}
	
	public static void printExits(Player player) {
		System.out.println("\n" + "Exits:");
		for (Map.Entry<String,Room> mapElement : player.getRoom().getExits()) {
			String key = (String)mapElement.getKey();
			Room room = (Room)mapElement.getValue();
			System.out.println(key + " - " + room.name);
		}
		System.out.println("\nYou are carrying:");
		for(int i=0; i < player.getInventory().size(); i++) {
			System.out.println(player.getItem(i).name);
		}
	}
	
	//Gathers player inputs.
	private static String[] collectInput(Scanner scanner) {
		String input = scanner.nextLine();
		return input.split(" ");
	}
	
	//Interpret output of collectInput method.
	private static String[] parse(String[] input, Player player) {
		String action = input[0].toLowerCase();
		String[] arr = {null, null, null};
		
		switch (action) {
			//This case should look for players trying to move
			default : arr[0] = "Error"; return arr;
			//Quit
			case "quit":
			case "exit":
			case "stop":
			case "end":
			case "leave":
			case "q": arr[0] = "quit"; return arr;
			//Yes
			case "yes":
			case "y": arr[0] = "yes"; return arr;
			//No
			case "no":
			case "n": arr[0] = "no"; return arr;
			//Move
			case "move":
			case "go":
			case "enter": {
				arr[0] = "move";
				arr[1] = moveParse(input, player);
				return arr;
			}
			//Use
			case "use":
			case "activate":
			case "open":
			case "unlock":{
				arr = useParse(input, player); //This needs to happen first, else arr[0] will be overwritten
				arr[0] = "use";
				return arr;
			}
			//Look
			case "look":
			case "check":
			case "see":
			case "examine": 
			case "investigate": {
				arr = useParse(input, player);
				arr[0] = "look";
				return arr;
			}
			//Pick Up
			case "pick":
			case "get":
			case "grab":
			case "take": {
				arr = useParse(input, player);
				arr[0] = "take";
				return arr;
			}
			case "help": {
				arr[0] = "help";
				return arr;
			}
		}
	}
	
	//Determines which room the player wants to move to.
	private static String moveParse(String[] input, Player player) {
		for(int i=1; i<input.length; i++) {
			for (Map.Entry<String,Room> e : player.getRoom().getExits()) {
				if(input[i].equalsIgnoreCase(e.getKey()) || input[i].equalsIgnoreCase(e.getValue().name)) {
					return e.getKey();
				}
			}
		}
		
		return "Error";
	}
	
	//Determines which object the player wants to interact with.
	private static String[] useParse(String[] input, Player player) {
		Room room = player.getRoom();
		String[] arr = {null, null, null};
		arr[0] = input[0];
		
		//Check all inputs
		for(int i=1; i < input.length; i++) {
			
			//Check room's inventory
			for(int j=0; j < room.getInventory().size(); j++) {
				if(room.getItem(j).nameCompare(input[i])) {
					if(arr[1] == null) {
						arr[1] = room.getItem(j).name;
					} else if(arr[2] == null) {
						arr[2] = room.getItem(j).name;
					} else return arr;
				}
			}
			
			//Check player's inventory
			for(int j=0; j < player.getInventory().size(); j++) {
				if(player.getItem(j).nameCompare(input[i])) {
					if(arr[1] == null) {
						arr[1] = player.getItem(j).name;
					} else if(arr[2] == null) {
						arr[2] = player.getItem(j).name;
					} else return arr;
				}
			}
		}
			
		return arr;
	}
	
	//Finds item with acceptable name equal to passed string.
	private static Item findItem(String string, Player player) {
		
		//Search in room
		for(int i=0; i < player.getRoom().getInventory().size(); i++) {
			if(player.getRoom().getItem(i).name.equalsIgnoreCase(string)) {
				return player.getRoom().getItem(i);
			}
		}
		//In inventory
		for(int i=0; i < player.getInventory().size(); i++) {
			if(player.getItem(i).name.equalsIgnoreCase(string)) {
				return player.getItem(i);
			}
		}
		
		return null;
	}
}
