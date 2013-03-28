
public interface ListIterator<E> extends java.util.Iterator<E> {
	

	void add(E element);
	
	boolean hasNext();
	
	boolean hasPrevious();
	
	E next();
	
	int nextIndex();
	
	E previous();
	
	int previousIndex();
	
	void remove();
	
	void set(E element);
}
