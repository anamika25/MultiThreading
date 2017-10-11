import java.util.ArrayList;
import java.util.List;

class Producer implements Runnable{
	private List<Integer> list;
	private final int size;
	Producer(List<Integer> list, int size){
		this.list=list;
		this.size=size;
	}
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("Producer is producing item: "+i);
			try{
				produce(i);
			}
			catch(InterruptedException e){}
		}
	}
	public void produce(int i) throws InterruptedException{
		while(list.size()==size){
			synchronized(list){
				System.out.println("queue is full, "+Thread.currentThread().getName()+" is waiting,size "+list.size());
				list.wait();
			}
		}
		synchronized(list){
			list.add(i);
			list.notifyAll();
		}
	}
}
class Consumer implements Runnable{
	private List<Integer> list;
	private final int size;
	private final Thread producer;
	Consumer(List<Integer> list, int size, Thread producer){
		this.list=list;
		this.size=size;
		this.producer=producer;
	}
	public void run(){
		while(true){
			try{
				System.out.println("Consumer is consuming item: ");
				if(consume()==-1)
					break;
				Thread.sleep(50);
			}
			catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}
	public int consume() throws InterruptedException{
		while(list.isEmpty()){
			if(!producer.isAlive())
				return -1;
			synchronized(list){
				System.out.println("queue is empty, "+Thread.currentThread().getName()+" is waiting for new element to be inserted,size "+list.size());
				list.wait();
			}
		}
		synchronized(list){
			list.notifyAll();
			return list.remove(0);
		}
	}
}

public class ProducerConsumer {
	public static void main(String[] args){
		List<Integer> list=new ArrayList<Integer>();
		int size =3;
		Thread Producer = new Thread(new Producer(list,size), "Producer");
		Thread Consumer = new Thread(new Consumer(list,size,Producer), "Consumer");
		Producer.start();
		Consumer.start();
	}
}
