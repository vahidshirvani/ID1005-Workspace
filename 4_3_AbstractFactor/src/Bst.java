import java.util.Deque;


public interface Bst<E extends Comparable <? super E>> {

	public boolean isEmpty();
	
	public int size ();
	
    public void add (E element);
    
    public boolean contains (E element);
    
    public E get (E element);
    
    public void remove (E element);
    
	public void clear ();
	
	public String toString ();

	public Deque<E> toQueue (int order);
	
	public void balance ();
}
