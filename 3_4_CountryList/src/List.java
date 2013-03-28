import java.util.Iterator;


public interface List<E> extends java.lang.Iterable<E> {

	void add(E element);

	void add(int index, E element);
	
	void clear();
	
	int indexOf(E element);
	
	boolean contains(E element);
	
	E get(int index);
	
	boolean isEmpty();
	
	void remove(int index);
	
	void remove(E element);
	
	void set(int index, E element);
	
	int size();
	
	Iterator<E> iterator();
	
	ListIterator<E> listIterator();
}
