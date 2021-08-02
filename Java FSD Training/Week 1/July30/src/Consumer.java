
public class Consumer extends Thread {
	ProducerConsumer company;
	
	public Consumer(ProducerConsumer company) {
		this.company = company;
	}
	
	public void run() {
		this.company.consume();
		this.company.consume();
		this.company.consume();
	}
}
