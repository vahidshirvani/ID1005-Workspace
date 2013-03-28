
/* FLQueue.java
 * 
 * This interface defines the abstract methods of a FLQueue
 * (FIFO LIFO Queue). As it is obvious there is only one
 * put method which puts a element on at the end of queue.
 * but there are two take methods which each are able to
 * either take an element from the beginning of queue (FIFO)
 * or from end (LIFO).
 */
interface FLQueue<E>
{
	boolean isEmpty ();

	boolean isFull ();

	int size ();

	void put (E element) throws FullQueueException;

	E takeFirst () throws EmptyQueueException;
	
	E takeLast () throws EmptyQueueException;

	E peekFirst () throws EmptyQueueException;
	
	E peekLast () throws EmptyQueueException;
}
