import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashtableSet<E> implements Set<E>, Serializable {

	public static final int DEFAULT_RANGE = 100;
	
	public static final int EXPAND_RANGE = 25;
	
	Hashtable<Integer, NodeSet<E>> table;
	
	public HashtableSet() {
		table  = new Hashtable<Integer, NodeSet<E>>(DEFAULT_RANGE);
	}
	
	public HashtableSet(int range) {
		table  = new Hashtable<Integer, NodeSet<E>>(range);
	}
	
	public HashtableSet(Set<E> set) {
		table  = new Hashtable<Integer, NodeSet<E>>(DEFAULT_RANGE);
		int key;
		
		for(E element : set){
			key = hashCode(element);
			if(table.get(key) == null)
				table.put(key, new NodeSet<E>());
			table.get(key).add(element);
		}
	}
	
	public int hashCode(E element){
		return (element.toString().hashCode() * 31) % DEFAULT_RANGE;
	}

	public boolean isEmpty() {
		return table.isEmpty();
	}

	public int size() {
		int size = 0;
		Enumeration<NodeSet<E>> enmr = table.elements();
		while(enmr.hasMoreElements())
			size += enmr.nextElement().size();
		return size;
	}

	public boolean contains(E element) {
		int key = hashCode(element);
		if(!table.containsKey(key))
			return false;
		return table.get(key).contains(element);
	}

	public void add(E element) {
		int key = hashCode(element);
		if(table.get(key) == null)
			table.put(key, new NodeSet<E>());
		table.get(key).add(element);
	}

	public void remove(E element) {
		int key = hashCode(element);
		if(table.containsKey(key))
			table.get(key).remove(element);
	}

	public void clear() {
		// helping garbage collector
		Enumeration<NodeSet<E>> enmr = table.elements();
		while(enmr.hasMoreElements())
			enmr.nextElement().clear();
		table.clear();
	}

	public boolean isSubsetOf(Set<E> set) {

		boolean isSubset = true;
		Enumeration<NodeSet<E>> enmr = table.elements();
		while(enmr.hasMoreElements())
			if(!enmr.nextElement().isSubsetOf(set)){
				isSubset = false;
				break;
			}
		
		return isSubset;
	}

	public Set<E> union(Set<E> set) {
		
		Set<E>    u = new NodeSet<E> ();
		Enumeration<NodeSet<E>> enmr = table.elements();
		while(enmr.hasMoreElements())
			u.addAll(enmr.nextElement().union(set));
		
		return u;
	}

	public Set<E> intersection(Set<E> set) {
		
		Set<E>    i = new NodeSet<E> ();
		Enumeration<NodeSet<E>> enmr = table.elements();
		while(enmr.hasMoreElements())
			i.addAll(enmr.nextElement().intersection(set));
		
        return i;
	}
	
	public Set<E> difference(Set<E> set) {
		
		Set<E>    d = new NodeSet<E> ();
		Enumeration<NodeSet<E>> enmr = table.elements();
		while(enmr.hasMoreElements())
			d.addAll(enmr.nextElement().difference(set));
        
		return d;
	}
	
	private class SetIterator implements Iterator<E>{

		Enumeration<NodeSet<E>> enmr;
		Iterator<E> nodeIter;
		E lastReturnedElement = null;
		
		SetIterator(){
			enmr = table.elements();
			if(enmr.hasMoreElements())
				nodeIter = enmr.nextElement().iterator();
		}
		
		@Override
		public boolean hasNext() {
			
			boolean hasMore = false;
			
			while(enmr.hasMoreElements()){
				if(nodeIter.hasNext()){
					hasMore = true;
					break;
				}
				nodeIter = enmr.nextElement().iterator();
			}
			
			return hasMore;
		}

		public E next() {
			if(!this.hasNext())
			    throw new NoSuchElementException (
                	"end of the iteration");
			lastReturnedElement = nodeIter.next();
			return lastReturnedElement;
		}

		public void remove() {
			if(lastReturnedElement == null)
			    throw new IllegalStateException (
			    	"improper iterator state for remove operation");
			HashtableSet.this.remove(lastReturnedElement);
		}
	}
	
	public Iterator<E> iterator() {
		return this.new SetIterator ();
	}

	public void addAll(Set<E> set) {
		int key;
		
		for(E element : set){
			key = hashCode(element);
			if(table.get(key) == null)
				table.put(key, new NodeSet<E>());
			table.get(key).add(element);
		}
	}

	

}
