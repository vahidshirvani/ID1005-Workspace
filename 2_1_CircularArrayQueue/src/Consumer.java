import java.util.Random;


public class Consumer implements Runnable {

	private Queue<String> queue;
	private Random random;
	
	public Consumer(Queue<String> queue) {
		this.queue = queue;
		this.random = new Random();
	}

	public void run() {

		int times = 0;
		while(times < 100){
			
			try{
				queue.take();
			}catch(EmptyQueueException e){}
			
			try{
				Thread.sleep(600);//random.nextInt(1000));
			}catch(InterruptedException e){}
			
			times++;
		}


	}

}
