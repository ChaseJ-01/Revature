
public class ThreadPools implements Runnable {
	
	private String message;
	public ThreadPools(String message) {
		this.message = message;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "Start Message" + message);
		this.processMessage();
		System.out.println(Thread.currentThread().getName() + "End Message" + message);
	}
	
	private void processMessage() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
