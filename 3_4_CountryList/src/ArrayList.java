import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayList<E> implements List<E> {

	protected E[] elements;
	protected int lastIndex = -1;
	
	private class JListIterator implements ListIterator<E>{

		private int currentIndex = 0;
		private int lastReturnedIndex = -1;
		
		@Override
		public void add(E element) {
			
			/*
			if(lastIndex == (elements.length - 1))
				ArrayList.this.enlarge();
			
			for(int index = lastIndex; index >= currentIndex; index--)
				elements[index + 1] = elements[index];
			elements[currentIndex] = element;
			*/
			ArrayList.this.add(currentIndex, element);
			currentIndex++;
			lastReturnedIndex = -1;
		}

		@Override
		public boolean hasNext() {
			return currentIndex <= lastIndex;
		}

		@Override
		public boolean hasPrevious() {
			return currentIndex > 0;
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException("Iterator has reached the end of list");
			
			E element = elements[currentIndex];
			lastReturnedIndex = currentIndex;
			currentIndex++;
			return element;
		}

		@Override
		public int nextIndex() {
			return currentIndex;
		}

		@Override
		public E previous() {
			if(!hasPrevious())
				throw new NoSuchElementException("Iterator has reached the beginning of list");
			
			E element = elements[currentIndex - 1];
			currentIndex--;
			lastReturnedIndex = currentIndex;
			return element;
		}

		@Override
		public int previousIndex() {
			return currentIndex - 1;
		}

		@Override
		public void remove() {
			if(lastReturnedIndex == -1)
				throw new IllegalStateException("No lastReturnedElement could be found");
			
			ArrayList.this.remove(lastReturnedIndex);
			currentIndex--;
			lastReturnedIndex = -1;
		}

		@Override
		public void set(E element) {
			if(lastReturnedIndex == -1)
				throw new IllegalStateException("No lastReturnedElement could be found");
			
			elements[lastReturnedIndex] = element;
			lastReturnedIndex = -1;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	ArrayList() {
		// INITIAL_CAPACITY = 50;
		elements = (E[]) new Object[50];
	}
	
	@SuppressWarnings("unchecked")
	ArrayList(int capacity) {
		elements = (E[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	protected void enlarge() {
		int ENLARGE_VALUE = 50;
		int newSize = 1 + elements.length + elements.length * ENLARGE_VALUE / 100;
		E[] tmp = (E[]) new Object[newSize];
		for(int index = 0; index < elements.length; index++)
			tmp[index] = elements[index];
		elements = tmp;
	}
	
	@Override
	public void add(E element) {
		if(lastIndex == (elements.length - 1))
			this.enlarge();
			
		elements[lastIndex + 1] = element;
		lastIndex++;
	}
	
	//aka insert
	@Override
	public void add(int index, E element) {
		if(index < 0 || index > (lastIndex + 1))
			throw new ArrayIndexOutOfBoundsException();
		
		if(lastIndex == (elements.length - 1))
			this.enlarge();
		
		for(int shiftIndex = lastIndex; shiftIndex >= index; shiftIndex--)
			elements[shiftIndex + 1] = elements[shiftIndex];
		elements[index] = element;
		lastIndex++;
	}

	@Override
	public void clear() {
		for(int index = 0; index <= lastIndex; index++)
			elements[index] = null;
		lastIndex = -1;
	}

	@Override
	public int indexOf(E element) {
		int index = -1;
		for(int loopIndex = 0; loopIndex <= lastIndex; loopIndex++)
			if(element.equals(elements[loopIndex])) {
				index = loopIndex;
				break;
		}
		return index;
	}
	
	@Override
	public boolean contains(E element) {
		boolean found = false;
		if(indexOf(element) != -1)
			found = true;
		return found;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index > lastIndex)
			throw new ArrayIndexOutOfBoundsException("LastIndex is " + lastIndex);
		return elements[index];
	}

	@Override
	public boolean isEmpty() {
		return lastIndex == -1;
	}

	@Override
	public void remove(int index) {
		if(index < 0 || index > lastIndex)
			throw new ArrayIndexOutOfBoundsException("LastIndex is " + lastIndex);
		
		for(int shiftIndex = index; shiftIndex < lastIndex; shiftIndex++)
			elements[shiftIndex] = elements[shiftIndex + 1];
		elements[lastIndex] = null;
		lastIndex--;
	}

	@Override
	public void remove(E element) {
		int index = indexOf(element);
		if(index != -1)
			remove(index);
	}

	@Override
	public void set(int index, E element) {
		if(index < 0 || index > lastIndex)
			throw new ArrayIndexOutOfBoundsException("LastIndex is " + lastIndex);
		elements[index] = element;
	}

	@Override
	public int size() {
		return lastIndex + 1;
	}

	@Override
	public Iterator<E> iterator() {
		return this.new JListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return this.new JListIterator();
	}
}
