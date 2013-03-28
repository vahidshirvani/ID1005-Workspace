

public class BinFactor implements AbstractFactor {

	private class IntAndSet implements Comparable<IntAndSet> {
		public Integer number;
		public Set<Integer> set;
		
		IntAndSet(Integer number, Set<Integer> set) {
			this.number = number;
			this.set = set;
		}
		
		@Override
		public int compareTo(IntAndSet another) {
			return number.compareTo(another.number);
		}
	}
	
	private BinarySearchTree<IntAndSet> binSchTree;
	
	BinFactor(int factorsUpTo) {
		this.binSchTree = new BinarySearchTree<IntAndSet>();

		Set<Integer> set;
		IntAndSet[] array = new IntAndSet[factorsUpTo];
		for(int i = 0; i < factorsUpTo; i++) {
			set = PrimeFactorization.get(i);
			array[i] = new IntAndSet(i, set);
		}
		this.binSchTree.add(array, 0, factorsUpTo - 1);
	}
	
	@Override
	public void add(Integer element) {
		Set<Integer> set = PrimeFactorization.get(element);
		IntAndSet newInteAndSet = new IntAndSet(element, set);
		this.binSchTree.add(newInteAndSet);
	}

	@Override
	public boolean contains(Integer element) {
		IntAndSet newInteAndSet = new IntAndSet(element, null);
		return this.binSchTree.contains(newInteAndSet);
	}

	@Override
	public Set<Integer> get(Integer element) {
		IntAndSet newInteAndSet = new IntAndSet(element, null);
		return this.binSchTree.get(newInteAndSet).set;
	}
}
