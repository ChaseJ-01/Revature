
public class FurnitureFactory {

	public static Furniture getFurniture(String type) {
		switch(type){
			case "Couch":{
				return new Couch();
			}
			case "Bed":{
				return new Bed();
			}
			default:{
				System.out.println("Invalid furniture type.");
				return null;
			}
		}
	}
}
