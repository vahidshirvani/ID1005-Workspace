
public class UseAllSubsets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Set<Integer> setOfInts = new ArraySet<Integer>(10);
		for(int i = 1; i <= 4; i++)
			setOfInts.add(i);
		
		Set<Integer> setOfInts2 = new ArraySet<Integer>(10);
		for(int i = 3; i <= 6; i++)
			setOfInts2.add(i);
		
		Set<Set<Integer>> allSubs = Subsets.getAll(setOfInts);
		System.out.println("All the subsets to " + setOfInts + " are");
		System.out.println(allSubs);
		
		System.out.println();
		System.out.println("The union between " + setOfInts + " and " + setOfInts2 + 
							" is " + setOfInts.union(setOfInts2));
		
		System.out.println();
		System.out.println("The intersection between " + setOfInts + " and " + setOfInts2 + 
				" is " + setOfInts.intersection(setOfInts2));
		
		System.out.println();
		System.out.println("The difference between " + setOfInts + " and " + setOfInts2 + 
				" is " + setOfInts.difference(setOfInts2));
	}

}
