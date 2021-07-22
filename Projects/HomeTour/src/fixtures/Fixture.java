package fixtures;

import java.util.ArrayList;
import java.util.List;

public class Fixture {
	public String name;
	public String shortDescription;
	public String longDescription;
	public String activationDescription;
	public String lockedDescription = "You don't think there's anything you can do with this.";
	private List<String> acceptedNames = new ArrayList<String>(); //list used to provide possible names for command prompts
	
	Fixture(String name, String shortDescription, String longDescription){
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
	
	Fixture(){
		return;
	}
	
	public void addCMDName(String cmdname) {
		acceptedNames.add(cmdname);
	}
	
	//Checks input to see if it matches any accepted names.
	public boolean nameCompare(String string) {
		for(int i=0; i < acceptedNames.size(); i++) {
			if(acceptedNames.get(i).equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}
	
	public void unlock() {
		return;
	}
}
