
public class Table {

	public void printTable(){
		for (int i=0; i<10;i++){
			synchronized(this) {
				System.out.println(i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
