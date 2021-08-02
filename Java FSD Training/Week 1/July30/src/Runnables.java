
public class Runnables implements Runnable {

	@Override
	public void run() {
		System.out.println("Runnable Interface Thread");
	}
	
	public void start() {
		this.run();
	}
}
