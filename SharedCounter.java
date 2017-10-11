class CounterThread extends Thread{
	int counterValue=0;
	CounterThread(int counterValue){
		this.counterValue=counterValue;
	}
	public void run(){
		try {
			sleep((long)Math.random()*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		counterValue=10*counterValue;
	}
}
public class SharedCounter {
	public static void main(String[] args) throws InterruptedException{
		int counter = 0;
		CounterThread[] ct = new CounterThread[10];
		for(int i=0;i<10;i++){
			ct[i]=new CounterThread(i+1);
			ct[i].start();
		}
		for(int i=0;i<10;i++){
			ct[i].join();
			counter = ct[i].counterValue;
		}
		
		System.out.println("Shared counter Value: " + counter);
	}
}
