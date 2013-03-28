import java.util.Random;


public class Producer implements Runnable {

	private Queue<String> queue;
	private Random random;
	
	public Producer(Queue<String> queue){
		this.queue = queue;
		this.random = new Random();
	}
	
	public void run() {
		int times = 0;
		while(times < 100){
	
			char a = (char) (65 + random.nextInt(20));
			char b = (char) (65 + random.nextInt(20));
			try{
				queue.put("" + a + b);
				
			}catch(FullQueueException e){}
			
			try{
				Thread.sleep(random.nextInt(1000));
			}catch(InterruptedException e){}
	
			times++;
		}
		

	}

}
