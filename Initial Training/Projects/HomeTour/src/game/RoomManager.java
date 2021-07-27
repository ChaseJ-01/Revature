package game;

import fixtures.Item;
import fixtures.Keypad;
import fixtures.Room;

public class RoomManager {
	Room startingRoom;
	Room bridge;
	Room cryoBay;
	Room passageway;
	Room cargo;
	Room captainsQuarters;
	Room crewQuarters;
	Room airlock;
	Room engineering;
	Room sterilizer;
	Room aiCore;
	Room space;
	Room aiObjective; //This room doesn't exist - it tracks the AI core objective
	Item startingItem;
	Item prybar;
	Item eKeycard;
	Item ePanel;
	Item container;
	Item canister;
	Item reactor;
	Item footlocker;
	Item sConsole;
	Item coreItem;
	Item note;
	Item processor;
	Item aiTerminal;
	Item electrical;
	Item bunks;
	Item head;
	Item navkey;
	Item locker;
	Item pods;
	Item status;
	Item viewport;
	Item console;
	Keypad cKeypad;
	Keypad aiKeypad;
	
	public void init() {
		
		//This room cannot be accessed. It exists solely because items need to either unlock something or give something to the player.
		aiObjective = new Room(
					"Objective",
					"Debug",
					"Debug"
				);
		aiObjective.locked = true;
		aiObjective.activationDescription = "You press a few buttons on the terminal and reset the AI system. The AI should hold up just long enough to get help.";
		
		
		//viewport
		//console
		bridge = new Room(
					"Bridge",
					"the bridge",
					"This compartment is the command center of the entire ship. You can see the dark void of space through the viewport." + "\n" + 
					"Toward the aft of the ship is the suspended animation bay. Near the viewport is a console with a blinking light."
				);
		bridge.lockedDescription = "The door to the bridge is locked. You need to swipe an access keycard to enter.";
		bridge.activationDescription = "You swipe the captain's ID card in the slot and the door slides open with a clean hiss, revealing the bridge.";
		
		//cryo pod
		//panel
		cryoBay = new Room(
					"Suspended Animation Bay",
					"the suspended animation bay",
					"This compartment contains a number of sleeper pods for suspended animation. All pods except one are closed." + "\n" +
					"Toward the fore of the ship is the door to the bridge." + "\n" +
					"Toward the aft of the ship is a door to the main passageway. There is also a hatch in the portside bulkhead labeled 'Airlock.'" + "\n" +
					"There is a small panel with a blinking light next to the open cryo pod."
				);
		
		//keypad
		passageway = new Room(
					"Main Passageway",
					"the short passageway",
					"A short passageway extends from port to starboard, with a door on each end. The port side door is labeled 'Crew Quarters' and the starboard side door is labeled 'Cargo'" + "\n" + 
					"There is also a forward facing door with the label 'Suspended Animation' in the center of the passageway and an aft facing door with the label 'Captain's Quarters.'" + "\n" +
					"There is a numbered keypad next to the door labeled 'Captain`s Quarters.'"
				);
	
		
		//container
		cargo = new Room(
					"Cargo Bay",
					"the cargo bay",
					"This large compartment contains many nondescript containers full of various bits of supplies and equipment." + "\n" +
					"One cylindrical container is labeled with a volatile substance hazard symbol." + "\n" +
					"Toward the port side of the room is a door to the main passageway."
				);
		
		//locker
		//bed
		//navkey
		captainsQuarters = new Room(
					"Captain's Quarters",
					"the captain's quarters",
					"This compartment is far more comfortable than other compartments on the ship." + "\n" +
					"There is a bed in the center of the compartment and a standing locker next to the door." + "\n" +
					"There is a forward facing door to the main passageway."
				);
		captainsQuarters.locked = true;
		captainsQuarters.lockedDescription = "The door to the Captain's Quarters is locked and can only be accessed with the Captain's 4-digit code.";
		captainsQuarters.activationDescription = "You're lucky the captain was lazy enough to make his PIN the same as his crew ID. The door slides open to reveal the compartment.";
		
		//bunk
		//head
		//footlocker
		//panel
		crewQuarters = new Room(
					"Crew Quarters",
					"the crew's quarters",
					"This Spartan compartment is filled with bunks and footlockers with a small divider in the corner for the head." + "\n" +
					"There is a starboard facing door to the main passageway and an aft facing door labeled 'Engineering.'" + "\n" +
					"Next to the door labeled 'Engineering' is a keycard panel."
				);
		
		//prybar
		airlock = new Room(
					"Airlock",
					"the tiny airlock compartment",
					"This tiny compartment is used to transition between the inside and outside of the spacecraft." + "\n" +
					"There is a port facing hatch leading to outer space and a starboard facing hatch leading back to the suspended animation bay."
				);
		
		//This room exists to show a description when you try to exit the airlock.
		space = new Room(
					"Outer Space",
					"outer space",
					"You shouldn't be reading this text."
				);
		space.locked = true;
		space.lockedDescription = "You definitely don't want to go out there.";
		space.activationDescription = "Even with a spacesuit, there's nothing for you to do outside.";
		
		//electrical
		//reactor
		engineering = new Room(
					"Engineering",
					"the engineering compartment",
					"This compartment is tightly packed with machinery and electrical equipment that is essential to the ship's operation." + "\n" +
					"On the far side of the compartment is a power core with an empty fuel canister." + "\n" +
					"There is an aft facing door with a keypad labeled 'AI Core' and a forward facing door leading to the crew quarters."
				);
		engineering.locked = true;
		engineering.lockedDescription = "The engine compartment is restricted to engineers. Luckily, you're the ship's engineer! Swipe your keycard to enter.";
		engineering.activationDescription = "Just as you've done dozens of times before, you swipe your keycard and watch the door slide open to reveal the engine compartment.";
		
		//terminal
		sterilizer = new Room(
					"AI Core Sterilization Chamber",
					"the sterilization chamber",
					"This compartment is immaculately clean. It is used to sterilize anyone attempting to access the AI core to avoid contamination." + "\n" +
					"There is a forward facing door leading to the AI core and an aft facing door leading to the engineering compartment." + "\n" +
					"There is a terminal on the starboard side bulkhead."
				);
		sterilizer.locked = true;
		sterilizer.lockedDescription = "This door is locked and requires a code to access. You recall that you wrote the code on a note in your footlocker.";
		sterilizer.activationDescription = "The keypad beeps and the door slides open to reveal the AI core sterilization chamber.";
		
		//core
		//processor
		//terminal
		aiCore = new Room(
					"AI Core",
					"the AI core",
					"This compartment is covered in wiring and lights all leading to the central computer mainframe housing the ship's AI." + "\n" +
					"There is a destroyed processor along the edge of the compartment. There is a terminal connected to the central mainframe." + "\n" +
					"There is an aft facing door leading to the AI Core Sterilization Chamber."
				);
		aiCore.locked = true;
		aiCore.lockedDescription = "You must run the sterilization protocol before you can access the AI core.";
		aiCore.activationDescription = "You run the sterilization protocol and the door to the AI Core slides open.";
	
		//Connect rooms
		bridge.addRoom("Aft", cryoBay);
		cryoBay.addRoom("Fore", bridge);
		cryoBay.addRoom("Aft", passageway);
		cryoBay.addRoom("Port", airlock);
		passageway.addRoom("Fore", cryoBay);
		passageway.addRoom("Aft", captainsQuarters);
		passageway.addRoom("Port", crewQuarters);
		passageway.addRoom("Starboard", cargo);
		captainsQuarters.addRoom("Fore", passageway);
		crewQuarters.addRoom("Starboard", passageway);
		crewQuarters.addRoom("Aft", engineering);
		airlock.addRoom("Starboard", cryoBay);
		airlock.addRoom("Port", space);
		engineering.addRoom("Aft", sterilizer);
		engineering.addRoom("Fore", crewQuarters);
		sterilizer.addRoom("Aft", aiCore);
		sterilizer.addRoom("Fore", engineering);
		aiCore.addRoom("Fore", sterilizer);
		cargo.addRoom("Port", passageway);
		
		prybar = new Item();
		prybar.name = "Prybar";
		prybar.shortDescription = "A prying tool leans against the bulkhead next to the hatch.";
		prybar.longDescription = "A sturdy prying tool used to open cargo containers.";
		prybar.holdable = true;
		prybar.addCMDName("prybar");
		prybar.addCMDName("crowbar");
		prybar.addCMDName("tool");
		airlock.addItem(prybar);
		
		eKeycard = new Item();
		eKeycard.name = "Engineering Keycard";
		eKeycard.longDescription = "Your engineering keycard. It lets you access the engine compartment.";
		eKeycard.holdable = true;
		eKeycard.addCMDName("keycard");
		eKeycard.addCMDName("card");
		
		ePanel = new Item();
		ePanel.name = "Engineering Access Keycard Panel";
		ePanel.longDescription = "A small panel labeled 'Engineering Access.' It has a slot for inserting a keycard.";
		ePanel.lockedDescription = "You need a keycard to access the engine compartment.";
		ePanel.setKeyItem(eKeycard);
		ePanel.setUnlocks(engineering);
		ePanel.addCMDName("panel");
		ePanel.addCMDName("reader");
		ePanel.addCMDName("scanner");
		ePanel.addCMDName("access");
		ePanel.addCMDName("engineering");
		crewQuarters.addItem(ePanel);
		
		canister = new Item();
		canister.name = "Reactor Fuel Canister";
		canister.longDescription = "A small container with a warning label that reads 'Warning! Reactor Fuel.' This can be used to refuel the ship's reactor.";
		canister.holdable = true;
		canister.addCMDName("fuel");
		
		container = new Item();
		container.name = "Reactor Fuel Container";
		container.longDescription = "A cylindrical container with a large hazard symbol on the side that reads 'Warning! Reactor Fuel - Handle With Caution.'";
		container.lockedDescription = "This container is sealed tight. You're going to need some kind of tool to open it.";
		container.setKeyItem(prybar);
		container.activationDescription = "You wedge the prybar into the lid and use the added leverage to unseal the container.\nInside, you find a number of reactor fuel canisters. You decide to take one.";
		container.addCMDName("container");
		container.addCMDName("drum");
		container.addCMDName("barrel");
		container.addCMDName("cylinder");
		container.contains = canister;
		cargo.addItem(container);
		
		reactor = new Item();
		reactor.name = "Reactor";
		reactor.longDescription = "A large reactor core used to power spacefaring vessels. This one uses volatile reactor fuel to function.";
		reactor.lockedDescription = "This reactor appears to be empty. It needs more fuel before it can be activated.";
		reactor.setKeyItem(canister);
		reactor.activationDescription = "You insert the fuel canister into the slot and power on the reactor.";
		reactor.addCMDName("reactor");
		reactor.addCMDName("engine");
		reactor.addCMDName("generator");
		reactor.addCMDName("core");
		reactor.addCMDName("power");
		engineering.addItem(reactor);
		
		note = new Item();
		note.name = "AI Core Access Code";
		note.longDescription = "The code to access the AI core is 6140. This note also contains AI research instructions.";
		note.holdable = true;
		note.addCMDName("note");
		note.addCMDName("code");
		
		footlocker = new Item();
		crewQuarters.addItem(footlocker);
		footlocker.name = "Footlocker";
		footlocker.longDescription = "A number of locked footlockers are lined up next to the bunks in this room. Your footlocker is the one closest to the engineering compartment.";
		footlocker.activationDescription = "You open your footlocker to reveal a number of personal items. There is also a note that reads AI Core Code: 6140. You take the note.";
		footlocker.contains = note;
		footlocker.addCMDName("footlocker");
		footlocker.addCMDName("locker");
		footlocker.addCMDName("lockers");
		footlocker.addCMDName("footlockers");
		
		aiKeypad = new Keypad();
		engineering.addItem(aiKeypad);
		aiKeypad.name = "AI Core Keypad";
		aiKeypad.longDescription = "A numeric keypad labeled AI Core access.";
		aiKeypad.setLockedCode(6140);
		aiKeypad.setUnlocks(sterilizer);
		aiKeypad.addCMDName("keypad");
		aiKeypad.addCMDName("pad");
		aiKeypad.addCMDName("panel");
		aiKeypad.addCMDName("lock");
		
		sConsole = new Item();
		sterilizer.addItem(sConsole);
		sConsole.name = "Sterilization Console";
		sConsole.longDescription = "A console that reads 'Please begin sterilization procedures' with a flashing button labeled 'STERILIZE.'";
		sConsole.setUnlocks(aiCore);
		sConsole.addCMDName("sterilizer");
		sConsole.addCMDName("sterilize");
		sConsole.addCMDName("console");
		sConsole.addCMDName("computer");
		sConsole.addCMDName("terminal");
		sConsole.addCMDName("button");
		
		coreItem = new Item();
		coreItem.name = "AI Mainframe";
		aiCore.addItem(coreItem);
		coreItem.setActive(false);
		coreItem.name = "AI Core";
		coreItem.longDescription = "This is the AI Mainframe. It is the primary housing for the AI personality. The AI seems to have been damaged.";
		coreItem.addCMDName("core");
		coreItem.addCMDName("mainframe");
		
		processor = new Item();
		processor.name = "Destroyed Processor";
		aiCore.addItem(processor);
		processor.setActive(false);
		processor.name = "Destroyed Processor";
		processor.longDescription = "This processor seems to have been overloaded. It must have been the cause of the errors in the AI system.";
		processor.addCMDName("processor");
		processor.addCMDName("destroyed");
		processor.addCMDName("overloaded");
		
		aiTerminal = new Item();
		aiCore.addItem(aiTerminal);
		aiTerminal.name = "AI Control Terminal";
		aiTerminal.longDescription = "This terminal is directly connected to the AI core. It can be used to reset the AI in case of failure.";
		aiTerminal.setUnlocks(aiObjective);
		aiTerminal.addCMDName("terminal");
		aiTerminal.addCMDName("control");
		aiTerminal.addCMDName("console");
		aiTerminal.addCMDName("computer");
		
		electrical = new Item();
		electrical.name = "Electrical Systems";
		engineering.addItem(electrical);
		electrical.longDescription = "The bulkeads of this compartment is littered with dozens of panels, each with mazelike tangles of wires passing between them and various systems.\nNavigating this compartment takes serious acrobatic skill.";
		electrical.setActive(false);
		electrical.addCMDName("wires");
		electrical.addCMDName("wire");
		electrical.addCMDName("electric");
		electrical.addCMDName("electrical");
		electrical.addCMDName("machine");
		electrical.addCMDName("machinery");
		
		bunks = new Item();
		bunks.name = "Bunks";
		crewQuarters.addItem(bunks);
		bunks.longDescription = "A number of bunks lie adjacent to the bulkhead here. They are in various states of disarray, a testament to the lack of discipline on this ship.";
		bunks.setActive(false);
		bunks.addCMDName("bed");
		bunks.addCMDName("beds");
		bunks.addCMDName("bunk");
		bunks.addCMDName("bunks");
		
		head = new Item();
		head.name = "Head";
		crewQuarters.addItem(head);
		head.setActive(false);
		head.longDescription = "You wonder to yourself why you still work for a captain who's too cheap to buy a ship with a separate compartment for the head.";
		head.addCMDName("toilet");
		head.addCMDName("head");
		head.addCMDName("loo");
				
		navkey = new Item();
		navkey.name = "Navkey";
		captainsQuarters.addItem(navkey);
		navkey.shortDescription = "A key is sitting on the desk next to the bed.";
		navkey.longDescription = "This is the key to the ship's navigational computer. With this, you can override the current destination and set a new course.";
		navkey.holdable = true;
		navkey.addCMDName("key");
		navkey.addCMDName("navkey");
		
		locker = new Item();
		locker.name = "Captain's Locker";
		captainsQuarters.addItem(locker);
		locker.longDescription = "The captain's locker is many times larger than your footlocker. You wonder what he keeps inside.";
		locker.setKeyItem(head); // That's right, you need a toilet to open this locker. Good luck getting one. A key item is required to display the text below.
		locker.lockedDescription = "You really shouldn't be rummaging through your boss' stuff more than you already are.";
		locker.addCMDName("locker");
		locker.addCMDName("lock");
		
		cKeypad = new Keypad();
		cKeypad.name = "Captain's Quarters Access Keypad";
		passageway.addItem(cKeypad);
		cKeypad.longDescription = "A numeric keypad is attached to the wall next to the door labeled 'Captain`s Quarters.'";
		cKeypad.setUnlocks(captainsQuarters);
		cKeypad.setLockedCode(6494);
		cKeypad.addCMDName("keypad");
		cKeypad.addCMDName("pad");
		cKeypad.addCMDName("panel");
		cKeypad.addCMDName("lock");
		
		pods = new Item();
		pods.name = "Suspended Animation Pods";
		cryoBay.addItem(pods);
		pods.longDescription = "There are four suspended animation pods in this compartment. They are as follows:\nPOD 1 - Engineer (ID: 1836)\nPOD 2 - Navgigator (ID: 9352)\nPOD 3 - Quartermaster (ID: 7264)\nPOD 4 - Captain (ID: 6494)\nOnly pod 1 is open right now.";
		pods.setActive(false);
		pods.addCMDName("pod");
		pods.addCMDName("pods");
		pods.addCMDName("suspended");
		pods.addCMDName("animation");
		pods.addCMDName("cryo");
		pods.addCMDName("sleep");
		pods.addCMDName("sleeper");
		
		status = new Item();
		status.name = "Status Panel";
		cryoBay.addItem(status);
		status.longDescription = "A status panel flashes with multiple warnings that read:\nWARNING: Ship AI returning multiple errors, recommend system restart.\nWARNING: Unintended navigational adjustment - vessel is currently off-course. Recommend new coordinate input.\nWARNING: Ship reactor is powered down, running on emergency power. Recommend refueling.";
		status.setActive(false);
		status.addCMDName("panel");
		status.addCMDName("status");
		status.addCMDName("feed");
		status.addCMDName("warning");
		status.addCMDName("terminal");
		status.addCMDName("console");
		status.addCMDName("computer");
		status.addCMDName("light");
		
		viewport = new Item();
		viewport.name = "Viewport";
		bridge.addItem(viewport);
		viewport.longDescription = "You stare out into the black void of outer space and think about something philosophical.";
		viewport.setActive(false);
		viewport.addCMDName("viewport");
		viewport.addCMDName("space");
		viewport.addCMDName("window");
		viewport.addCMDName("port");
		viewport.addCMDName("porthole");
		viewport.addCMDName("outside");
		viewport.addCMDName("star");
		viewport.addCMDName("stars");
		viewport.addCMDName("void");
		
		console = new Item();
		console.name = "Navigation Console";
		bridge.addItem(console);
		console.setKeyItem(navkey);
		console.longDescription = "This is the ship's navigational console. It controls the vessel's propulsion and guides it to its destination.";
		console.lockedDescription = "The navigaton console is flashing with errors. You need to reset the ship's bearing with a navkey.";
		console.activationDescription = "You insert the navkey and reset the vessel's heading. The ship is now back on course.";
		console.addCMDName("console");
		console.addCMDName("computer");
		console.addCMDName("terminal");
		console.addCMDName("controls");
		console.addCMDName("helm");
		console.addCMDName("navigation");
		console.addCMDName("autopilot");
		console.addCMDName("control");
		console.addCMDName("navigator");
		console.addCMDName("nav");
		
		startingItem = eKeycard;
		startingRoom = cryoBay;
	}
	
	public boolean checkVictory() {
		if(!reactor.getActive() && !aiObjective.locked && !console.getActive()) {
			return true;
		}
		return false;
	}
}
