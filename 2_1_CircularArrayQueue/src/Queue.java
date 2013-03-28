// Queue.java

interface Queue<E>
{
	boolean isEmpty ();

	boolean isFull ();

	int size ();

	void put (E element) throws FullQueueException;

	E take () throws EmptyQueueException;

	E peek () throws EmptyQueueException;
	
	E peek (int index) throws IndexOutOfBoundsException;
	
	int getFirst();
	
	int getLast();

	void addGraphic(GraphicPanel<E> panel);

}
