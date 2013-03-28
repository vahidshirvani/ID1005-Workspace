import java.util.Scanner;


public class PostFixEvaluator {
	public static int evaluate (String expression) {
		
		ArrayStack<Integer> stack = new ArrayStack<Integer> ();
		
		int value;
		String operator;
		
		int operand1;
		int operand2;
		
		int result = 0;
		Scanner tokenizer = new Scanner (expression);
		
		while (tokenizer.hasNext()){
		
			// As long as its a number just push it into stack
			if (tokenizer.hasNextInt()){
				value = tokenizer.nextInt();
				if (stack.isFull ())
					throw new PostFixException("Too many operands - stack overflow");
				stack.push(value);
			}
			else{
				
				/* But when its a operator then load the two latest
				 * element on the stack and find out by comparison
				 * which operator it is to do the respective operation
				 */
				operator = tokenizer.next();
				
				if (stack.isEmpty())
					throw new PostFixException("Not enough operands - stack underflow");
				operand2 = stack.pop();
				
				if (stack.isEmpty())
					throw new PostFixException("not enough operands - stack underflow");
				operand1 = stack.pop();
				
				if (operator.equals("/"))
					result = operand1 / operand2;
				else if (operator.equals("*"))
					result = operand1 * operand2;
				else if (operator.equals("+"))
					result = operand1 + operand2;
				else if (operator.equals("-"))
					result = operand1 - operand2;
				else
					throw new PostFixException("Illegal symbol: " + operator);
				
				stack.push(result);
			} 					
		}
		
		/* This part is the last part. In this part the code checks if
		 * it only exists one element on stack which has to be the result.
		 * if the testing fails then exception will be thrown.
		 */
		if (stack.isEmpty())
			throw new PostFixException("Not enough operands - stack underflow");
		result = stack.pop();
		
		if (!stack.isEmpty())
			throw new PostFixException("too many operands - operands left over");
		
		return result;		
	}
}
