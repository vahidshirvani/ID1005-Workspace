
public class UseUnlimitedNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UnlimitedNumber firstNumber;
		UnlimitedNumber secondNumber; 
		
		// positive + positive
		firstNumber = new UnlimitedNumber("+10000000000");
		secondNumber = new UnlimitedNumber("+1000000000");
		firstNumber.addition(secondNumber);
		System.out.println(firstNumber);
		
		// positive + negative
		firstNumber = new UnlimitedNumber("+10000000000");
		secondNumber = new UnlimitedNumber("-1000000000");
		firstNumber.addition(secondNumber);
		System.out.println(firstNumber);

		// negative + positive
		firstNumber = new UnlimitedNumber("-10000000000");
		secondNumber = new UnlimitedNumber("+1000000000");
		firstNumber.addition(secondNumber);
		System.out.println(firstNumber);
		
		// negative + negative
		firstNumber = new UnlimitedNumber("-10000000000");
		secondNumber = new UnlimitedNumber("-1000000000");
		firstNumber.addition(secondNumber);
		System.out.println(firstNumber);
		
		// positive - positive
		firstNumber = new UnlimitedNumber("+10000000000");
		secondNumber = new UnlimitedNumber("+1000000000");
		firstNumber.subtraction(secondNumber);
		System.out.println(firstNumber);
		
		// positive - negative
		firstNumber = new UnlimitedNumber("+10000000000");
		secondNumber = new UnlimitedNumber("-1000000000");
		firstNumber.subtraction(secondNumber);
		System.out.println(firstNumber);

		// negative - positive
		firstNumber = new UnlimitedNumber("-10000000000");
		secondNumber = new UnlimitedNumber("+1000000000");
		firstNumber.subtraction(secondNumber);
		System.out.println(firstNumber);
		
		// negative - negative
		firstNumber = new UnlimitedNumber("-10000000000");
		secondNumber = new UnlimitedNumber("-1000000000");
		firstNumber.subtraction(secondNumber);
		System.out.println(firstNumber);
	}
}
