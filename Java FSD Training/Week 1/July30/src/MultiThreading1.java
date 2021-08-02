
public class MultiThreading1 extends Thread {

	@Override
	public void run() {
		System.out.println(this.getName());
		System.out.println(this.getPriority());
		
		for(int i=0; i<100; i++) {
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}
