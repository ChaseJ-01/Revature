
public class Producer extends Thread {
	ProducerConsumer company;
	
	Producer(ProducerConsumer company){
		this.company = company;
	}
	
	public void run() {
		this.company.produce("Bathtub");
		this.company.produce("Toilet");
		this.company.produce("Sink");
	}
}