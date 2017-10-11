import java.util.Random;
class MaxThread extends Thread{
	int[] arr;
	int beg=0,end=0,res=0;
	MaxThread(int[] arr, int beg, int end){
		this.arr=arr;
		this.beg=beg;
		this.end=end;
	}
	public void run(){
		System.out.println(getName() +" beg: "+beg+" end: "+end);
		for(int i=beg;i<end;i++){
			res=Math.max(res, arr[i]);
		}
	}
}
public class MaxValueMultithreading {
	public static int findMax(int[] arr) throws InterruptedException{
		int len = arr.length, res=0;
		MaxThread[] mt=new MaxThread[4];
		for(int i=0;i<4;i++){
			mt[i]=new MaxThread(arr,i*len/4, (i+1)*len/4);
			mt[i].start();
		}
		for(int i=0;i<mt.length;i++){
			mt[i].join();
			res=Math.max(res, mt[i].res);
		}
		return res;	
	}
	public static void main(String[] args) throws InterruptedException{
		int[] arr = new int[10];
		Random rand = new Random();
		for(int i=0;i<arr.length;i++){
			arr[i]=rand.nextInt(100);
			System.out.print(" " +arr[i]);
		}
		System.out.println();
		System.out.println(" Max value: "+findMax(arr));
	}
}
