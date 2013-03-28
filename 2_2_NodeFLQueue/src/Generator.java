import java.util.Random;

/*
 * This class takes a queue as the in parameter and fills and empty
 * it at random. The odds for taking and putting element can be 
 * manipulated by user from gui and passed into this class.
 * The generator is only able to generating Character as element
 * and is lock in that sense.
 */
public class Generator implements Runnable {

	private FLQueue<Character> queue;
	private boolean flag = true;
	private Random rand;
	private int oddsForTakeFirst = 50;
	private int oddsForTakeLast = 50;
	private int oddsForPut = 50;
	private int x;
	private int w;
	
	public Generator(FLQueue<Character> queue) {
		this.queue = queue;
		rand = new Random();
	}

	/* This run method is the main part of the class. The thread will be
	 * started from outside and will continue until the flag set to false.
	 * The method has four parts. Two taking and one putting element and 
	 * after that sleeping.
	 */
	@Override
	public void run() {
		int i;
		Character ch;
		while(flag) {
			
			i = rand.nextInt(100);
			if (i < oddsForTakeFirst) {
				try {
					ch = queue.takeFirst();
					x++;
					System.out.println(ch + " were taken from first and there is " + queue.size() + " elements left in queue");
				}catch(EmptyQueueException ex) {
					System.out.println("Nothing to take on first!");
				}
			}
			
			i = rand.nextInt(100);
			if (i < oddsForTakeLast) {
				try {
					ch = queue.takeLast();
					w--;
					System.out.println(ch + " were taken from last and there is " + queue.size() + " elements left in queue");
				}catch(EmptyQueueException ex) {
					System.out.println("Nothing to take on last!");
				}
			}
			
			i = rand.nextInt(100);
			if (i < oddsForPut) {
				try {
					ch = new Character((char) (rand.nextInt(26) + 65));
					queue.put(ch);
					w++;
					System.out.println(ch + " were put at last and there is " + queue.size() + " elements now in queue");
				}catch(FullQueueException ex) {
					System.out.println("Putting went wrong!");
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setFlag(boolean b) {
		this.flag = b;
	}

	public void setOdds(int otf, int otl, int op) {
		this.oddsForTakeFirst = otf;
		this.oddsForTakeLast = otl;
		this.oddsForPut = op;
	}

	/* These two get methods will be use in the gui. The x will 
	 * define the position of the rectangle and the w defines the 
	 * width of the rectangle.
	 */
	public int getX() {
		return x;
	}
	
	public int getW() {
		return w;
	}
}
