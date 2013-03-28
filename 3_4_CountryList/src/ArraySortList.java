import java.util.Comparator;


public class ArraySortList<E> extends ArrayList<E>{

	private Comparator<E> comparator;
	// protected E[] elements;
	// protected int lastIndex = -1;
	
	@SuppressWarnings("unchecked")
	public ArraySortList() {
		comparator = new Comparator<E>() {

			@Override
			public int compare(E e1, E e2) {
				int result = 0;
				try {
					Comparable<E> e1c = (Comparable<E>) e1;
					result = e1c.compareTo(e2);
				}catch(ClassCastException ex) {
					ex.getStackTrace();
				}
 				return result;
			}

		};
		
		// INITIAL_CAPACITY = 50;
		elements = (E[]) new Object[50];
	}

	private int findPositionIndex(E element, int fromIndex, int toIndex) {
		int index = 0;
		
		if(fromIndex > toIndex) // toIndex = lastIndex = -1 = empty Array
			index = 0;
		else if(fromIndex == toIndex) {
			int compareValue = this.comparator.compare(element, elements[fromIndex]);
			if(compareValue > 0)
				index = fromIndex + 1;
			else
				index = fromIndex;
		}else if(fromIndex + 1 == toIndex) { // fromIndex < toIndex
			int compareValue = this.comparator.compare(element, elements[fromIndex]);
			if(compareValue > 0)
				index = findPositionIndex(element, toIndex, toIndex);
			else
				index = fromIndex;
		}else { // fromIndex << toIndex
			int middleIndex = (fromIndex + toIndex) / 2;
			int compareValue = this.comparator.compare(element, elements[middleIndex]);
			if(compareValue > 0)
				index = findPositionIndex(element, middleIndex + 1, toIndex);
			else if(compareValue < 0)
				index = findPositionIndex(element, fromIndex, middleIndex - 1);
			else // compareValue == 0
				index = middleIndex;
		}
		
		return index;
	}
	
	@Override
	public void add(E element) {
		if(lastIndex == (elements.length - 1))
			this.enlarge();
			
		int positionIndex = this.findPositionIndex(element, 0, lastIndex);
		this.add(positionIndex, element);
		//lastIndex++; not necessary it gets done by method above
	}
	
	@Override
	public void add(int index, E element) {
		if(index < 0 || index > (lastIndex + 1))
			throw new ArrayIndexOutOfBoundsException();
		
		if(lastIndex == (elements.length - 1))
			this.enlarge();
		
		boolean smallerThanOneElementBefore = false;
		if(index > 0 && elements[index - 1] != null)
			smallerThanOneElementBefore = this.comparator.compare(element, elements[index - 1]) < 0;
		boolean LargerThanOneElementAfter = false;
		if(elements[index] != null)
			LargerThanOneElementAfter = this.comparator.compare(element, elements[index]) > 0;
			
		if(smallerThanOneElementBefore || LargerThanOneElementAfter)
			throw new InterruptOrderException("Not respecting the sort order properties");
		
		for(int shiftIndex = lastIndex; shiftIndex >= index; shiftIndex--)
			elements[shiftIndex + 1] = elements[shiftIndex];
		elements[index] = element;
		lastIndex++;
	}
	
	// smarter than ArrayList. Makes use of sorted properties
	@Override
	public int indexOf(E element) {
		return this.indexOf(element, 0, lastIndex);
	}

	private int indexOf(E element, int fromIndex, int toIndex) {
		int index = -1;
		int middle = (fromIndex + toIndex) / 2;
		int compareValue = this.comparator.compare(element, elements[middle]);

		if(fromIndex < toIndex) {

			if(compareValue > 0)
				index = this.indexOf(element, middle + 1, toIndex);
			else if(compareValue < 0)
				index = this.indexOf(element, fromIndex, middle - 1);
			else if(compareValue == 0)
				index = middle;
			
		}else { // fromIndex == toIndex
			if(compareValue == 0)
				index = middle;
		}
		
		return index;
	}
	
	@Override
	public void set(int index, E element) {
		if(index < 0 || index > lastIndex)
			throw new ArrayIndexOutOfBoundsException("LastIndex is " + lastIndex);
		
		boolean smallerThanOneElementBefore = false;
		if(index > 0 && elements[index - 1] != null)
			smallerThanOneElementBefore = this.comparator.compare(element, elements[index - 1]) < 0;
		boolean LargerThanOneElementAfter = false;
		if(elements[index] != null)
			LargerThanOneElementAfter = this.comparator.compare(element, elements[index + 1]) > 0;
			
		if(smallerThanOneElementBefore || LargerThanOneElementAfter)
			throw new InterruptOrderException("Not respecting the sort order properties");
	
		elements[index] = element;
	}
}
