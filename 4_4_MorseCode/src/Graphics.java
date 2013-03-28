import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Graphics extends JFrame {

	private JTextField alphabet;
	private JTextField code;
	private JButton toCode;
	private JButton toAlphabet;
	private MorseCode mc;
	
	public Graphics(MorseCode mc) {
		alphabet = new JTextField();
		toCode = new JButton("toCode");
		JPanel aPanel = new JPanel(new BorderLayout());
		aPanel.add(alphabet, BorderLayout.CENTER);
		aPanel.add(toCode, BorderLayout.EAST);
		code = new JTextField();
		toAlphabet = new JButton("toAlphabet");
		JPanel cPanel = new JPanel(new BorderLayout());
		cPanel.add(code, BorderLayout.CENTER);
		cPanel.add(toAlphabet, BorderLayout.EAST);
		Font font = new Font(Font.DIALOG, Font.BOLD, 30);
		alphabet.setFont(font);
		code.setFont(font);
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == Graphics.this.toCode || 
						event.getSource() == Graphics.this.alphabet) {
					String letter = Graphics.this.alphabet.getText();
					Graphics.this.mc.setAlphabet(letter);
					Graphics.this.code.setText(Graphics.this.mc.getMorse());
				}else { // (event.getSource() == Graphics.this.toAlphabet || 
					           //event.getSource() == Graphics.this.code)
					String code = Graphics.this.code.getText();
					Graphics.this.mc.setMorse(code);
					Graphics.this.alphabet.setText(Graphics.this.mc.getAlphabet());
				}		
			}
		};
		
		this.mc = mc;
		this.alphabet.addActionListener(listener);
		this.code.addActionListener(listener);
		this.toCode.addActionListener(listener);
		this.toAlphabet.addActionListener(listener);
		this.add(aPanel);
		this.add(cPanel);
		this.setLayout(new GridLayout(2, 1));
		this.setVisible(true);
		this.setBounds(100, 100, 500, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}


}
