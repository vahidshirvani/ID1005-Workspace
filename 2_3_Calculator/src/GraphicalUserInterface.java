import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GraphicalUserInterface extends JPanel {
	
	private final int rows = 4;
	private final int columns = 4;
	private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
	private JTextField textField;
	private JPanel buttonPanel;
	private JButton[][] buttons = new JButton[rows][columns];
	private JButton equal;
		
	GraphicalUserInterface() {
		
		textField = new JTextField();
		textField.setFont(font);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(rows, columns));
		
		this.fillButtons();
		
		this.setLayout(new BorderLayout());
		this.add(textField, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(equal, BorderLayout.SOUTH);
	}
	
	private void fillButtons() {
		String[][] names = {
				{"7", "8", "9", "/"},
				{"4", "5", "6", "*"},
				{"1", "2", "3", "-"},
				{"(", "0", ")", "+"}};
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				JButton pushedButton = (JButton) event.getSource();
				
				// If its '='
				if(pushedButton == equal){
					String expression = textField.getText();
					InToPost converter = new InToPost(expression);
					expression = converter.doTransfer();
					
					System.out.println(expression);
					
					int result = PostFixEvaluator.evaluate(expression);
					System.out.println(result);
					textField.setText("" + result);
				}else{
					for (int i = 0; i < rows; i++)
						for (int j = 0; j < columns; j++) {
							if (pushedButton.equals(buttons[i][j]))
								textField.setText(
										textField.getText() + 
										buttons[i][j].getText());
						}
				}
			}
		};
		
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				buttons[i][j] = new JButton(names[i][j]);
				buttons[i][j].setFont(font);
				buttons[i][j].addActionListener(listener);
				buttonPanel.add(buttons[i][j]);
			}
		
		equal = new JButton("=");
		equal.setFont(font);
		equal.addActionListener(listener);
	}
}
