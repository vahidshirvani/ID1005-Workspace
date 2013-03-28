
public class InToPost {
	
	/* Stack will only be used to store parentheses 
	 * and operators. Input is an expression in infix
	 * format. The input has no space between the numbers
	 * and operators. But the space is critical to the output.
	 */
	private ArrayStack<Character> stack;
	private String input;
	private String output = "";
	
	public InToPost(String in) {
		input = in;
		stack = new ArrayStack<Character>(input.length());
	}
	
	
	/* This method will go though the expression char by char
	 * and based on what each character is take different ways.
	 * The numbers will directly be placed on output. The '(' 
	 * will directly be pushed to stack. On the other hand the 
	 * priorities on operators has to be considered before 
	 * pushing it to stack. 
	 */
	public String doTransfer() {
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			switch (ch) {
			case '+':
			case '-':
				gotOperator(ch, 1);
				break;
			case'*':
			case'/':
				gotOperator(ch, 2);
				break;
			case '(':
				stack.push(ch);
				break;
			case ')':
				gotCloseParenthese();
				break;
			default:
				output = output + " " + ch;
				break;
			}
		}
		while (!stack.isEmpty())
			output = output + " " + stack.pop();
		System.out.println(output);
		return output;
	}
	
	/* When an operator comes in before pushing it directly to the 
	 * stack, the top element on stack has to be loaded first. If
	 * it is a '(' the the operator can be pushed with no problem.
	 * but if the top element is an operator then the priority of
	 * it needs to be listed out. i.e. '*' has higher priority then
	 * '+', in this case top element which is '+' will be pushed back
	 * into stack to be stored for later on. But in case '*' comes in
	 * and top element is '*' which makes them at same level of 
	 * priority then top element will be kicked out from stack into 
	 * the output and the incoming operator which was '*' will be 
	 * pushed on top of the stack. 
	 */
	public void gotOperator(char inOperator, int inPriority) {
		while (!stack.isEmpty()) {
			char top = stack.pop();
			if (top == '(') {
				stack.push(top);
				break;
			}
			else {
				int topPriority = (top == '+' || top == '-')? 1 : 2;
				if (topPriority < inPriority) {
					stack.push(top);
					break;
				} 
				else
					output = output + " " + top;
			}
		}
		stack.push(inOperator);
	}
	
	/* When reaching a ')' the last operator 
	 * in the stack has to be placed on output 
	 * and the '(' on stack needs to be removed
	 */
	public void gotCloseParenthese() {
		while (!stack.isEmpty()) {
			char ch = stack.pop();
			if (ch == '(')
				break;
			else
				output = output + " " + ch;
		}
	}
}
