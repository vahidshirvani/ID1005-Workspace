
public class UnlimitedNumber {
	
	private NumberList numberList;
	
	UnlimitedNumber(String elements){
		numberList = new NumberList();
		
		numberList.sign = elements.charAt(0) == '+';
		
		for(int i = 1; i < elements.length(); i++)
			numberList.appendToRight(
					(byte) Character.digit(
							elements.charAt(i), 10));
	}
	
	public NumberList getNumberList() {
		return this.numberList;
	}
	
	public void addition(UnlimitedNumber num) {

		NumberList firstOperandNumber = this.numberList;
		NumberList secondOperandNumber = num.getNumberList();
		NumberList resultOfOperationNumber = new NumberList();
	
		if(firstOperandNumber.sign != secondOperandNumber.sign) {
			secondOperandNumber.setOppositeSign();
			this.subtraction(num);
		}else {
		
			firstOperandNumber.resetRightToLeft();
			secondOperandNumber.resetRightToLeft();
			
			boolean carry = false;
			byte firstOperandDigit;
			byte secondOperandDigit;
			byte resultForOneDigit;
			
			while(firstOperandNumber.hasNextLeft() || secondOperandNumber.hasNextLeft()) {
				
				firstOperandDigit = 0;
				secondOperandDigit = 0;
				resultForOneDigit = 0;
				
				if(firstOperandNumber.hasNextLeft())
					firstOperandDigit = firstOperandNumber.getNextLeft();
				
				if(secondOperandNumber.hasNextLeft())
					secondOperandDigit = secondOperandNumber.getNextLeft();
				
				if(carry)
					resultForOneDigit = (byte) (firstOperandDigit + secondOperandDigit + 1);
				else
					resultForOneDigit = (byte) (firstOperandDigit + secondOperandDigit);
		
				if(resultForOneDigit > 9)
					carry = true;
				
				resultForOneDigit = (byte) (resultForOneDigit % 10);
				
				resultOfOperationNumber.appendToLeft(resultForOneDigit);
			}
			
			this.numberList = resultOfOperationNumber;
			this.numberList.sign = secondOperandNumber.sign;
		}
		
	}
	
	public void subtraction(UnlimitedNumber num) {
		
		NumberList firstOperandNumber = this.numberList;
		NumberList secondOperandNumber = num.getNumberList();
		NumberList resultOfOperationNumber = new NumberList();
	
		if(firstOperandNumber.sign != secondOperandNumber.sign) {
			secondOperandNumber.setOppositeSign();
			this.addition(num);
		}else {
			
			firstOperandNumber.resetRightToLeft();
			secondOperandNumber.resetRightToLeft();
			
			boolean carry = false;
			byte firstOperandDigit;
			byte secondOperandDigit;
			byte resultForOneDigit;
			
			while(firstOperandNumber.hasNextLeft() || secondOperandNumber.hasNextLeft()) {
				
				firstOperandDigit = 0;
				secondOperandDigit = 0;
				resultForOneDigit = 0;
				
				if(firstOperandNumber.hasNextLeft())
					firstOperandDigit = firstOperandNumber.getNextLeft();
				
				if(secondOperandNumber.hasNextLeft())
					secondOperandDigit = secondOperandNumber.getNextLeft();
				
				if(carry)
					secondOperandDigit++;
				if(firstOperandDigit < secondOperandDigit) {
					firstOperandDigit += 10;
					carry = true;
				}
				
				resultForOneDigit = (byte) (firstOperandDigit - secondOperandDigit);
		
				resultOfOperationNumber.appendToLeft(resultForOneDigit);
			}
			
			this.numberList = resultOfOperationNumber;
			this.numberList.sign = secondOperandNumber.sign;
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(numberList.sign == true)
			sb.append('+');
		else
			sb.append('-');
		numberList.resetLeftToRight();
		while(numberList.hasNextRight())
			sb.append(numberList.getNextRight());
		return sb.toString();
	}
	
	
	
}
