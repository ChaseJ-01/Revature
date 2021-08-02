import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileMain {

	public static void main(String[] args) throws IOException {
		File f = new File("db");
		int count = 0;
		FileWriter fw = new FileWriter("Second.txt", false);
		
		System.out.println(f.exists());
		f.mkdir();
		System.out.println(f.exists());
		
		String[] list = f.list();
		for(String s: list) {
			count++;
			System.out.println(s);
		}
		System.out.println("Number of files: " + count);
		
		fw.write(100);
		fw.write("Hello\n");
		fw.write("String");
		fw.flush();
		fw.close();
		
		
	}
}
