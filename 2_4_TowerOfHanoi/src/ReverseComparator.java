import java.util.Comparator;

public class ReverseComparator<T extends Comparable<T>> 
								implements Comparator<T>{

	/* This class is like any default comparator. The only difference 
	 * is that the two elements which are going to be compared has
	 * switch places. This switch will result in reverse outcome.
	 */
	public int compare(T element1, T element2) {
		
		int result = 0;
		
		try{
			Comparable<T> element = (Comparable<T>) element2;
			result = element.compareTo(element1);
		}catch(ClassCastException ex){
			ex.getStackTrace();
		}
		
		return result;
	}
}