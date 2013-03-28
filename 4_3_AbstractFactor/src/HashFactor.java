

public class HashFactor implements AbstractFactor {
	
	private  HashTable<String, Set<Integer>> hashTable;
	
	HashFactor(int factorsUpTo) {
		this.hashTable = new HashTable<String, Set<Integer>>(factorsUpTo + 20 * factorsUpTo / 100);
		 
		for(int i = 0; i < factorsUpTo; i++) {
			this.hashTable.put(String.valueOf(i), PrimeFactorization.get(i));
		}
	}

	@Override
	public void add(Integer element) {
		this.hashTable.put(element.toString(), PrimeFactorization.get(element.intValue()));
		
	}

	@Override
	public boolean contains(Integer element) {
		return this.hashTable.containsKey(element.toString());
	}

	@Override
	public Set<Integer> get(Integer element) {
		return this.hashTable.get(element.toString());
	}
}
