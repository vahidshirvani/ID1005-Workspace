
public class Subsets {

	public static <E> Set<Set<E>> getAll(Set<E> set){
		
		Set<Set<E>> allSubsets = new ArraySet<Set<E>>(); 
		
		if(set.size() == 0){
			allSubsets.add(new ArraySet<E>(1));
		}else{
			Set<E> reduced = new ArraySet<E>(set);
			
			E element = reduced.iterator().next();
			reduced.remove(element);
			Set<Set<E>> allSubsetsOfReduced = getAll(reduced);
			
			allSubsets.addAll(allSubsetsOfReduced);
			
			allSubsetsOfReduced = getAll(reduced);
			for(Set<E> sub: allSubsetsOfReduced)
				sub.add(element);
			allSubsets.addAll(allSubsetsOfReduced);
		}
		return allSubsets;
	}
}
