
public class Multithreading1 extends Thread{
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(getName() + " working on "+i);
			try{
				sleep((long)Math.random()*100);
			}
			catch(InterruptedException e){}
			System.out.println(getName() + " is done.");
		}
	}
	public static void main(String[] args){
		Multithreading1 m1= new Multithreading1();
		Multithreading1 m2= new Multithreading1();
		m1.start();
		m2.start();
	}
}
