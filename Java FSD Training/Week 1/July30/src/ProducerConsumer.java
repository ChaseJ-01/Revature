
public class ProducerConsumer {
	String item;
	boolean consuming = false;
	
	synchronized void produce(String item) {
		if(consuming) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.item = item;
		System.out.println("Produced " + this.item);
		consuming = true;
		notify();
	}
	
	synchronized String consume() {
		if(!consuming) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(this.item + " consumed");
		notify();
		consuming = false;
		return this.item;
	}
}
