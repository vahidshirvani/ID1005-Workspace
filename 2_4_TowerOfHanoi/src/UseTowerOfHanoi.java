
public class UseTowerOfHanoi {

	public static void main(String[] args) {
		
		ReverseComparator<Integer> rComp = new ReverseComparator<Integer>();
		
		HeapSort<Integer>[] heapTower = new HeapSort[3];
		heapTower[0] = new HeapSort<Integer>(rComp);
		heapTower[1] = new HeapSort<Integer>(rComp);
		heapTower[2] = new HeapSort<Integer>(rComp);
		
		Graphic<Integer> graph = new Graphic<Integer>(heapTower);
		
		// Filling the first tower
		for(int i = 9; i >= 0; i--)
			heapTower[0].enqueue(new Integer(i));
		
		graph.updateTowers();

	}

}
