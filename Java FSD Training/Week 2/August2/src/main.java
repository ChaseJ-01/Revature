
public class main {

	public static void main(String[] args) {
		Single singleton = Single.getSingleton();
		Single singleton2 = Single.getSingleton();
		Single singleton3 = Single.getSingleton();
		
		Furniture couch = FurnitureFactory.getFurniture("Couch");
		Furniture bed = FurnitureFactory.getFurniture("Bed");
		Furniture table = FurnitureFactory.getFurniture("Table");
		
		Lambda lambda = () -> {
			return "This is a block of lambda code.";
		};
		
		System.out.println(lambda.output());
	}
}
