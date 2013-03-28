import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JLabel;


public class HeapSort<T extends Comparable<T>> {
	
	/* This local class which is an comparator acts as
	 * the default comparator if no other comparator
	 * replaces it. It will compare two elements in right
	 * order and return the result. Of course need the
	 * elements be of the type comparable so that the class 
	 * can support itself on that.
	 */
	private class DefaultComparator implements Comparator<T>{

		public int compare(T element1, T element2) {
			
			int result = 0;
			
			try{
				Comparable<T> element = (Comparable<T>) element1;
				result = element.compareTo(element2);
			}catch(ClassCastException ex){
				ex.getStackTrace();
			}
			
			return result;
		}
	}

	public final int DEFAULT_CAPACITY = 20;
	public final int ENLARGE_VALUE = 20; // %20
	private ArrayList<T> elements;
	private int lastIndex;
	private int size = DEFAULT_CAPACITY;
	private Comparator<T> comparator = null;
	private JLabel message;
	
	public HeapSort() {
		elements = new ArrayList<T>(DEFAULT_CAPACITY);
		lastIndex = -1;
		this.comparator = new DefaultComparator();
	}
	
	public HeapSort(Comparator<T> comp){
		elements = new ArrayList<T>(DEFAULT_CAPACITY);
		lastIndex = -1;
		this.comparator = comp;
	}
	
	public boolean isEmpty(){
		return (lastIndex == - 1);
	}
	
	public boolean isFull(){
		return lastIndex == (size - 1);
	}
	
	/* This method return how many element there are
	 * in the elements and NOT the capacity of it
	 */
	public int size(){
		return elements.size();
	}
	
	private void enlarge(){
		
		size = 1 + size + 
				ENLARGE_VALUE * size / 100; 
		ArrayList<T> tempArray = new ArrayList<T>(size);
		for(int i = 0; i < elements.size(); i++)
			tempArray.add(i, elements.get(i));
		elements = tempArray;
	}
	
	/* This enqueue method does differentiate itself from 
	 * regular enqueue methods. It does follow the rules 
	 * of the Tower of Hanoi game, Which is a larger block
	 * or in this case larger number can't sit above smaller
	 * number. If opposite tries an exception will be thrown.
	 *  
	 */
	public void enqueue(T element){
		
		if(element != null){
		
			if(!this.isEmpty())
				if(comparator.compare(element, elements.get(0)) < 0){
					message.setText("Incorrect move, try again!");
					throw new IllegalStateException();
				}
			
			lastIndex++;
			elements.add(lastIndex, element);
			reheapUp(element);
		}
	}

	/* When an element has been added to the heap sort algorithm,
	 * the element will be places at the last index. Then this method
	 * tries to shift up the element in order to find the right place
	 * for it. The method will use the comparator to compare the child
	 * with its parent and decide either its going up or stay at its place.
	 */
	private void reheapUp(T element) {
		int hole = lastIndex;
		while(	(hole > 0) && 
				comparator.compare(element, elements.get((hole - 1) / 2)) > 0 )
		{
			elements.set(hole, elements.get((hole - 1) / 2));
			hole = (hole - 1) / 2;
		}
		elements.set(hole, element);
	}

	/* If elements is not empty the the top element
	 * will be return. If its empty null will be returned.
	 */
	public T dequeue(){
		T hold = null;
		T toMove;
		
		if (this.isEmpty()){
			message.setText("This tower is empty!");
		}else{
			hold = elements.get(0);
			toMove = elements.remove(lastIndex);
			lastIndex--;
			if(!this.isEmpty())
				reheapDown(toMove);
		}
		return hold;
	}
	
	/* When the element on the root dequeues away a new element
	 * has to fill it place. This method has this job. By 
	 * looking at the child and using the comparator to decide  
	 * if the hole shall stay or 
	 */
	private void reheapDown(T element) {
		int hole = 0;
		int newHole = newHole(hole, element);
		
		while(newHole != hole){
			elements.set(hole, elements.get(newHole));
			hole = newHole;
			newHole = newHole(hole, element);
		}
		elements.set(hole, element);
	}

	private int newHole(int hole, T element) {
		int left = (2 * hole) + 1;
		int right = (2 * hole) + 2;
		
		if(left > lastIndex){
			return hole;
		}else if(left == lastIndex){
			if(comparator.compare(element, elements.get(left)) < 0){
				return left;
			}else{
				return hole;
			}
		}else if(comparator.compare(
				elements.get(left), 
				elements.get(right)) < 0){
			if(comparator.compare(elements.get(right), element) <= 0){
				return hole;
			}else{
				return right;
			}
		}else if(comparator.compare(
				elements.get(left), 
				element) <= 0){
			return hole;
		}else{
			return left;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("The heap is \n");
		for(int i = 0; i <= lastIndex; i++)
			sb.append(i + ": " + elements.get(i) + "\n");
		return sb.toString();
	}
	
	public void setMessage(JLabel msg){
		this.message = msg;
	}
}
