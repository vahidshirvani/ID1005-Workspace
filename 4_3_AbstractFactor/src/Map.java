import java.util.Set;

/* All the abstract methods in this interface are chosen from java.util.map
 * but I didn't chose to implements the map interface from standard library.
 * The reason I didn't take map interface from standard library was customization.
 * The map interface from standard library has Object as input parameters instead
 * of Generics. The other reason was the three methods that are out commented.
 * I had no use of them.
 */
public interface Map<K, V> {
	
	public void clear();

	public boolean containsKey(K key);
	
	public boolean containsValue(V value);
	
	//public Set<java.util.Map.Entry<K, V>> entrySet();
	
	public V get(K key);
	
	public boolean isEmpty();
	
	public Set<K> keySet();
	
	public V put(K key, V value);
	
	//public void putAll(Map<? extends K, ? extends V> map);
	
	public V remove(K key);
	
	public int size();
	
	//public Collection<V> values();
}
