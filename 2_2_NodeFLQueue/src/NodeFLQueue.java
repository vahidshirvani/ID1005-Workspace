
/* NodeFLQueue.java
 * 
 * This class implements the FLQueue interface and by that overrides
 * all the defined method. 
 * As it is obvious from the name this queue has Nodes as its underlying
 * structure. The queue is not bound to any type and uses generics to be
 * flexible for storing any kind of objects as elements.
 */

public class NodeFLQueue<E> implements FLQueue<E> {

	/*
	 * This private inner class is hidden from outside and only is available
	 * to super class. Every element is packed into one node. Each node has 
	 * three references. One for the element, one for next node in queue and
	 * the third for the previous node. The FIFO, LIFO behavior of the queue 
	 * requires respectively forward and backward references.
	 */
	private class Node {
		public E element;
		public Node nextNode;
		public Node previousNode;
		
		public Node(E element) {
			this.element = element;
			nextNode = null;
			previousNode = null;
		}
	}
	
	private Node firstNode;
	private Node lastNode;
	
	public NodeFLQueue(E element) {
		Node newNode = new Node(element);
		firstNode = newNode;
		lastNode = newNode;
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public boolean isFull() {
		// Because its nodes
		return false;
	}

	/* The method counts the size of the queue by iterating through
	 * the whole queue till the end and increasing the value of an 
	 * integer by one each time. 
	 * This could have been solved in another way by just simply
	 * increasing and decreasing the value of integer in methods 
	 * put and take.
	 */
	@Override
	public int size() {
		
		int numberOfElements = 0;
		Node currentNode = firstNode;
		
		while(currentNode != null) {
			numberOfElements++;
			currentNode = currentNode.nextNode;
		}
		return numberOfElements;
	}

	/* This put method will never throw a FullQueueException until the
	 * memory of the running machine is gets overflow.
	 * The incoming element will be packed into a node connected to the
	 * last node in the queue and then becoming the last one.
	 * The is a extreme situation were there the queue is empty.
	 * See the code for more info! problem?
	 */
	@Override
	public void put(E element) throws FullQueueException {
		
		Node newNode = new Node(element);
		
		if(this.isEmpty()) {
			firstNode = newNode;
			lastNode = newNode;
		}else{
			lastNode.nextNode = newNode;
			newNode.previousNode = lastNode;
			lastNode = newNode;
		}
	}
	
	/* Taking will fail and an exception will be thrown if
	 * queue is empty and any attempt for taking takes place.
	 * The extreme scenario is when queue has only one element
	 * left and take will take place. In that case analyze the code 
	 * for more info.
	 */
	@Override
	public E takeFirst() throws EmptyQueueException {
		
		if(this.isEmpty())
			throw new EmptyQueueException("No element left to take!");
	
		E element = firstNode.element;
		firstNode = firstNode.nextNode;
		
		if(firstNode == null)
			lastNode = null;
		else
			firstNode.previousNode = null;
 
		return element;
	}

	/* Same as above
	 */
	@Override
	public E takeLast() throws EmptyQueueException {
		
		if (this.isEmpty())
			throw new EmptyQueueException("No element left to take!");
		
		E element = lastNode.element;
		lastNode = lastNode.previousNode;
		
		if(lastNode == null)
			firstNode = null;
		else
			lastNode.nextNode = null;
		
		return element;
	}

	/* These to peek methods only return the element and does not
	 * remove the node. Of course there has to be at least one
	 * element left in queue otherwise exception will be thrown.
	 */
	@Override
	public E peekFirst() throws EmptyQueueException {
		
		if (this.isEmpty())
			throw new EmptyQueueException("No element left to take!");
		
		return firstNode.element;
	}

	@Override
	public E peekLast() throws EmptyQueueException {
		
		if (this.isEmpty())
			throw new EmptyQueueException("No element left to take!");
		
		return lastNode.element;
	}

}
