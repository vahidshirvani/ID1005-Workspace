import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;



public class HashTable<K, V> implements Map<K, V>, Serializable {

	private static final long serialVersionUID = -1821535697203180776L;

	@SuppressWarnings("hiding")
	private class Node<K, V> implements Serializable{
		private static final long serialVersionUID = 4841790330869621344L;
		public K key;
		public V value;
		public Node<K, V> nextNode;
	}
	
	Node<K, V>[] table;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		int DEFAULT_RANGE = 100;
		table = new Node[DEFAULT_RANGE]; //(Node[]) new Object[DEFAULT_RANGE];
	}

	@SuppressWarnings("unchecked")
	public HashTable(int range) {
		table = new Node[range]; //(Node[]) new Object[range];
	}
	
	/* Just helping garbage collector
	 */
	@Override
	public void clear() {
		Node<K, V> currentNode = null;
		Node<K, V> previousNode = null;
		for(int i = 0; i < table.length; i++) {
			currentNode = table[i];
			table[i]= null;
			while(currentNode != null) {
				previousNode = currentNode;
				currentNode = currentNode.nextNode;
				previousNode.key = null;
				previousNode.value = null;
				previousNode.nextNode = null;
			}
		}
	}

	@Override
	public boolean containsKey(K key) {
		if(key == null)
			throw new IllegalArgumentException("The key was unacceptable");

		return this.get(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		if(value == null)
			throw new IllegalArgumentException("The value was unacceptable");
		
		Node<K, V> currentNode = null;
		for(int index = 0; index < table.length; index++) {
			currentNode = table[index];
			while(currentNode != null) {
				if(currentNode.value.equals(value)) {
					return true; // found = true; break;
				}
				currentNode = currentNode.nextNode;
			}
		}
		
		return false;
	}

	@Override
	public V get(K key) {
		if(key == null)
			throw new IllegalArgumentException("The key was unacceptable");
		
		int index = Math.abs(key.toString().hashCode()) % this.table.length;
		Node<K, V> currentNode = this.table[index];
		while(currentNode != null) {
			if(currentNode.key.equals(key)) {
				return currentNode.value;
			}
			currentNode = currentNode.nextNode;
		}
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.size() <= 0;
	}
	
	@Override
	public Set<K> keySet() {
		Set<K> set = new TreeSet<K>();
		Node<K, V> currentNode = null;
		for(int index = 0; index < table.length; index++) {
			currentNode = table[index];
			while(currentNode != null) {
				set.add(currentNode.key);
				currentNode = currentNode.nextNode;
			}
		}
		return set;
	}
	
	/* If an old value exist with the same key, the new value will
	 * replace the old value with same key and the old value will
	 * be returned.
	 */
	@Override
	public V put(K key, V value) {
		if(key == null || value == null)
			throw new IllegalArgumentException("The key or value was unacceptable");
		
		int index = Math.abs(key.toString().hashCode()) % this.table.length;
		Node<K, V> currentNode = this.table[index];
		while(currentNode != null) {
			if(currentNode.key.equals(key)) {
				V oldValue = currentNode.value;
				currentNode.value = value;
				// Will be end here
				return oldValue;
			}
			currentNode = currentNode.nextNode;
		}
		
		Node<K, V> newNode = new Node<K, V>();
		newNode.key = key;
		newNode.value = value;
		newNode.nextNode = this.table[index];
			this.table[index] = newNode;
		
		return null;
	}

	@Override
	public V remove(K key) {
		if(key == null)
			throw new IllegalArgumentException("The key was unacceptable");
		
		int index = Math.abs(key.toString().hashCode()) % this.table.length;
		Node<K, V> currentNode = this.table[index];
		Node<K, V> previousNode = null;
		while(currentNode != null) {
			if(currentNode.key.equals(key)) {
				if(previousNode == null)
					this.table[index] = currentNode.nextNode;
				else
					previousNode.nextNode = currentNode.nextNode;
				V value = currentNode.value;
				currentNode = null;
				return value;
			}
			previousNode = currentNode;
			currentNode = currentNode.nextNode;
		}
		
		return null;
	}

	@Override
	public int size() {
		int count = 0;
		Node<K, V> currentNode = null;
		for(int index = 0; index < this.table.length; index++) {
			currentNode = this.table[index];
			while(currentNode != null) {
				count++;
				currentNode = currentNode.nextNode;
			}
		}
		return count;
	}
	
	// TODO iterator
}
