
public class UseNodeFLQueue {

	/*
	 * This program does visualize the FLQueue which is a combination 
	 * of both FIFO (First in First Out) and LIFO (Last in First out)
	 * queue. The under lying structure are nodes which means no
	 * bounds on queue.
	 * It all begins by creating a queue and putting a object in this
	 * case a Character as element in it. The queue is given as a 
	 * parameter to the generator which will generate more random 
	 * Characters and put them as well take them out from queue based
	 * on odds controller by user. The generator passes as a parameter 
	 * to visuals which will draw everything on canvas.
	 */
	public static void main(String[] args) {	
		new Visuals(new Generator(new NodeFLQueue<Character>('A')));
	}
}
