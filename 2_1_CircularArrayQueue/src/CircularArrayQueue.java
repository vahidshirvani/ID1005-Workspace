// CircularArrayQueue.java



class CircularArrayQueue<E> implements Queue<E>
{
	final private int    DEFAULT_CAPACITY = 10;

	private E[]    queue;
	private int    first = 0;
	private int    last = 0;
	
	private GraphicPanel<E> panel;
	
	@SuppressWarnings("unchecked")
	CircularArrayQueue ()
	{
		queue = (E[]) new Object[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	CircularArrayQueue (int    capacity)
	{
		queue = (E[]) new Object[capacity];
	}

	public boolean isEmpty ()
	{
		return first == last;
	}

	public boolean isFull ()
	{
		return ((last + 1) % queue.length) == first;
	}

	public int size ()
	{
		int numberOfElements;
		if (first <= last)
			numberOfElements = last - first;
		else
			numberOfElements = queue.length - first + last;
		return numberOfElements;
	}

	public void put (E element)
	{
		if (isFull ())
			throw new FullQueueException ();
			// OR enlarge () with unboundedArrays
		
		panel.repaint();
		queue[last] = element;
		last = (last + 1) % queue.length;
	}

	public E take ()
	{
		if (isEmpty ())
			throw new EmptyQueueException ();
		
		int index = first;
		first = (first + 1) % queue.length;
		
		panel.repaint();
		return queue[index];
		// TODO queue[index] = -1 then return
	}

	public E peek ()
	{
		if (isEmpty ())
			throw new EmptyQueueException ();
	
		return queue[first];
	}
	
	public E peek (int index)
	{
		if (index < 0 || index >= queue.length)
			throw new IndexOutOfBoundsException ("Large Index!");
	
		return queue[index];
	}
	
	public int getFirst(){
		return first;
	}
	
	public int getLast(){
		return last;
	}

	public void addGraphic(GraphicPanel<E> panel) {
		this.panel = panel;
	}
}


