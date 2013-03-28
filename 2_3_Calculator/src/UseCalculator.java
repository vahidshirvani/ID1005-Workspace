import java.util.Scanner;

import javax.swing.JFrame;


public class UseCalculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GraphicalUserInterface panel = new GraphicalUserInterface();
		JFrame frame = new JFrame("Calculator");
		frame.add(panel);
		frame.setBounds(100, 100, 300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		/*
		Scanner in = new Scanner(System.in);
		
		System.out.println("Please input in postfix form");
		String expression = in.nextLine();
		
		InToPost converter = new InToPost(expression);
		expression = converter.doTransfer();
		
		System.out.println(expression);
		
		int result = PostFixEvaluator.evaluate(expression);
		System.out.println(result);
		*/
	}
}
