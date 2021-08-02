import java.util.concurrent.Executors;

public class ThreadsMain {

	public static void main(String[] args) {
		MultiThreading1 thread1 = new MultiThreading1();
		Runnables runnable = new Runnables();
		runnable.start();
		
		MultiThreading1 thread2 = new MultiThreading1();
		thread1.start();
		try {
			thread1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread2.start();
		runnable.start();
		
		Table table1 = new Table();
		Table table2 = new Table();
		table1.printTable();
		table2.printTable();
		
		System.out.println("Producer-Consumer");
		ProducerConsumer company = new ProducerConsumer();
		Consumer consumer = new Consumer(company);
		Producer producer = new Producer(company);
		producer.start();
		consumer.start();
	}
}
