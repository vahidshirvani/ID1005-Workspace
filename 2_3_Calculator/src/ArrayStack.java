/**
 * 
 */

/**
 * @author Vahid Shirvani
 *
 */
public class ArrayStack<E> {
	
	protected final int CAPACITY = 	100;
	protected E[] stack;
	protected int topIndex = -1;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		stack = (E[]) new Object[CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		stack = (E[]) new Object[capacity];
	}	
	
	public boolean isEmpty (){
		return topIndex == -1;			
	}

	public boolean isFull (){
		return (topIndex == (stack.length - 1))? true : false;
	}

	public void push (E element) throws FullQueueException{
		if ( !isFull() ){
			stack[++topIndex] = element;
		}
		else
			throw new FullQueueException ("stack overflow");
	}

	public E pop () throws EmptyQueueException{
		E element = null;
		if ( !isEmpty () ){
			element = stack[topIndex--];
		}
		else
			throw new EmptyQueueException ("stack underflow");
		return element;
	}

	public E peek () throws EmptyQueueException{
		E element = null;
		if ( !isEmpty () ){
			element = stack[topIndex];
		}
		else
			throw new EmptyQueueException ("stack underflow");
		return element;
	}
}
