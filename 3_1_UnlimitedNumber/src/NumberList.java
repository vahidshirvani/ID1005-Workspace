import java.util.NoSuchElementException;


public class NumberList {

	/* every digit represents a node in the list.
	 * the element which node holds is a byte and
	 * will therefore always be between 0-9.
	 * the node/digit has a reference to the previous
	 * and next node in order to be able to iterate 
	 * through the list.
	 */
	protected class Digit {
		byte element; 
		Digit rightDigit;
		Digit leftDigit;
	};
	
	/* the list nedd to have a reference to the first
	 * and last node/digit which are called most 
	 * significance digit and least significance digit.
	 * The iterator is implemented in the list and is 
	 * a part of the list and not a separate inner class.
	 * the two referencer of the iterators are called
	 * left to right and right to left.
	 * the signs "-" and "+" are represented by boolean.
	 */
	Digit mostSig;
	Digit leastSig;
	Digit leftToRight;
	Digit rightToLeft;
	int numberOfDigits = 0;
	boolean sign = true;
	
	NumberList(){
		mostSig = null;
		leastSig = null;
		numberOfDigits = 0;
	}
	
	public void setOppositeSign(){
		this.sign = !this.sign;
	}
	
	public void resetLeftToRight(){
		leftToRight = mostSig;
	}
	
	public void resetRightToLeft(){
		rightToLeft = leastSig;
	}
	
	public boolean hasNextRight(){
		return leftToRight != leastSig.rightDigit;
	};
	
	public boolean hasNextLeft(){
		return rightToLeft != mostSig.leftDigit;
	}
	
	public byte getNextRight(){
		if(!hasNextRight())
			throw new NoSuchElementException();
		byte temp = leftToRight.element;
		leftToRight = leftToRight.rightDigit;
		return temp;
	}
	
	public byte getNextLeft(){
		if(!hasNextLeft())
			throw new NoSuchElementException();
		byte temp = rightToLeft.element;
		rightToLeft = rightToLeft.leftDigit;
		return temp;
	}
	
	public void appendToRight(byte element){
		Digit newDigit = new Digit();
		newDigit.element = element;
		newDigit.rightDigit = null;
		newDigit.leftDigit = leastSig;
		
		if(leastSig == null){
			leastSig = newDigit;
			mostSig = newDigit;
		}else{
			leastSig.rightDigit = newDigit;
			leastSig = newDigit;
		}
		numberOfDigits++;
	}
	
	public void appendToLeft(byte element){
		Digit newDigit = new Digit();
		newDigit.element = element;
		newDigit.rightDigit = mostSig;
		newDigit.leftDigit = null;
		
		if(mostSig == null){
			mostSig = newDigit;
			leastSig = newDigit;
		}else{
			mostSig.leftDigit = newDigit;
			mostSig = newDigit;
		}
		numberOfDigits++;
	}
	
	
	
}
	