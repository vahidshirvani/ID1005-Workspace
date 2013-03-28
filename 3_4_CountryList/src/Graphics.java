import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class Graphics extends JFrame{

	private List<String> leftList;
	private List<String> rightList;
	private JList leftJList;
	private JPanel middlePanel;
	private JList rightJList;
	private JLabel out;
	private JTextField inputElement;
	private JButton toLeft;
	private JButton toRight;
	private JRadioButton[] rdio;
	private JButton iteratorB;
	private JRadioButton[] rdioIterator;
	private ListIterator<String> iterator;
	
	private String[] names = {	"Add Element", "Add to index", "Clear Array", 
			"Index Of Element", "Contains Element ?", 
			"Get Element On Index", "Remove On Index",
			"Remove Element","Set Element"};
	private String[] namesIterator = {	"Add Element", "Forward", "Backward", 
			"Remove", "Set"};

	
	Graphics(List leftList, List rightList) {
		this.leftList = leftList;
		this.rightList = rightList;
		this.iterator = leftList.listIterator();
		
		leftJList = new JList();
		middlePanel = new JPanel(new GridLayout(19, 1));
		rightJList = new JList();
		out = new JLabel();
		inputElement = new JTextField();
		toLeft = new JButton(" << ");
		toRight = new JButton(" >> ");
		iteratorB = new JButton("Iterator Button");
		
		
		//middlePanel.add(new JLabel("Output: "));
		//middlePanel.add(out);
		JPanel topSmalPanel = new JPanel();
		topSmalPanel.add(new JLabel("Output: "));
		topSmalPanel.add(out);
		middlePanel.add(topSmalPanel);
		middlePanel.add(inputElement);
		middlePanel.add(toLeft);
		middlePanel.add(toRight);
		ButtonGroup group = new ButtonGroup();
		rdio = new JRadioButton[10];
		for(int i = 0; i < 9; i++) {
			rdio[i] = new JRadioButton(names[i]);
			group.add(rdio[i]);
			middlePanel.add(rdio[i]);
		}
		
		// Iterator section
		middlePanel.add(iteratorB);
		ButtonGroup groupIterator = new ButtonGroup();
		rdioIterator = new JRadioButton[5];
		for(int i = 0; i < 5; i++) {
			rdioIterator[i] = new JRadioButton(namesIterator[i]);
			groupIterator.add(rdioIterator[i]);
			middlePanel.add(rdioIterator[i]);
		}
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object source =  e.getSource();
				
				int leftOrRight = 0;
				if(source == toRight)
					leftOrRight = 10;
				
				for(int i = 0; i < 9; i++)
					if(rdio[i].isSelected()){
						leftOrRight += i;
						break;
					}
				
				String sout;
				switch(leftOrRight) {
				case 0: 
					Graphics.this.leftList.add(inputElement.getText()); 
					break;
				case 1: 
					try {
						Graphics.this.leftList.add(leftJList.getSelectedIndex(), inputElement.getText()); 
					}catch(InterruptOrderException ex) {
						out.setText(ex.getMessage());
					}
					break;
				case 2: 
					Graphics.this.leftList.clear();
					break;
				case 3: 
					out.setText("" + Graphics.this.leftList.indexOf(inputElement.getText())); 
					break;
				case 4: 
					// TODO returns boolean
					out.setText("" + Graphics.this.leftList.contains(inputElement.getText())); 
					break;
				case 5: 
					try {
						sout = Graphics.this.leftList.get(Integer.parseInt(inputElement.getText()));
					}catch(ArrayIndexOutOfBoundsException ex) {
						sout = "404 Not Found";
					}
					out.setText(sout); 
					break;
				case 6: 
					Graphics.this.leftList.remove(leftJList.getSelectedIndex()); 
					break;
				case 7: 
					Graphics.this.leftList.remove(inputElement.getText()); 
					break;
				case 8: 
					try {
						Graphics.this.leftList.set(leftJList.getSelectedIndex(), inputElement.getText()); 
					}catch(InterruptOrderException ex) {
						out.setText(ex.getMessage());
					}
					break;
				case 10: 
					Graphics.this.rightList.add(inputElement.getText()); 
					break;
				case 11: 
					try {
						Graphics.this.rightList.add(rightJList.getSelectedIndex(), inputElement.getText()); 
					}catch(InterruptOrderException ex) {
						out.setText(ex.getMessage());
					}
					break;
				case 12: 
					Graphics.this.rightList.clear();
					break;
				case 13: 
					out.setText("" + Graphics.this.rightList.indexOf(inputElement.getText())); 
					break;
				case 14: 
					// TODO returns boolean
					out.setText("" + Graphics.this.rightList.contains(inputElement.getText())); 
					break;
				case 15: 
					try {
						sout = Graphics.this.rightList.get(Integer.parseInt(inputElement.getText()));
					}catch(ArrayIndexOutOfBoundsException ex) {
						sout = "404 Not Found";
					}
					out.setText(sout); 
					break;
				case 16: 
					Graphics.this.rightList.remove(rightJList.getSelectedIndex()); 
					break;
				case 17: 
					Graphics.this.rightList.remove(inputElement.getText()); 
					break;
				case 18: 
					try {
						Graphics.this.rightList.set(rightJList.getSelectedIndex(), inputElement.getText());
					}catch(InterruptOrderException ex) {
						out.setText(ex.getMessage());
					}
					break;
				}	
				Graphics.this.refreshing();
			}
		};
		
		toLeft.addActionListener(listener);
		toRight.addActionListener(listener);
		
		ActionListener iteratorListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int radioSelected = 0;
				for(int i = 0; i < 5; i++)
					if(rdioIterator[i].isSelected()){
						radioSelected = i;
						break;
					}
				
				switch(radioSelected) {
				case 0: 
					Graphics.this.iterator.add(inputElement.getText()); 
					break;
				case 1:
					if(Graphics.this.iterator.hasNext())
						out.setText(Graphics.this.iterator.next());
					else
						out.setText("Iterator has reached the end of list");
					break;
				case 2:
					if(Graphics.this.iterator.hasPrevious())
						out.setText(Graphics.this.iterator.previous());
					else
						out.setText("Iterator has reached the beginning of list");
					break;
				case 3:
					try {
						Graphics.this.iterator.remove();
					}catch(IllegalStateException ex) {
						out.setText(ex.getMessage());
					}
					break;
				case 4:
					try {
						Graphics.this.iterator.set(inputElement.getText());
					}catch(IllegalStateException ex) {
						out.setText(ex.getMessage());
					}
					break;
				}
				Graphics.this.refreshing();
			}
			
		};
		iteratorB.addActionListener(iteratorListener);
		
		Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		leftJList.setBorder(BorderFactory.createTitledBorder(
				etchedBorder, "ArrayList", TitledBorder.LEFT, TitledBorder.TOP));
		rightJList.setBorder(BorderFactory.createTitledBorder(
				etchedBorder, "ArraySortedList", TitledBorder.LEFT, TitledBorder.TOP));
		
		this.setLayout(new GridLayout(1, 3));
		this.setVisible(true);
		this.setBounds(100, 100, 900, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new JScrollPane(leftJList));
		this.add(middlePanel);
		this.add(new JScrollPane(rightJList));
		this.refreshing();
	}
	
	private void refreshing() {
	
		int i = 0;
		String[] elements = new String[leftList.size()];
		for(String element: leftList) {
			elements[i++] = element;
		}
		
		leftJList.setListData(elements);
		
		i = 0;
		elements = new String[rightList.size()];
		for(String element: rightList) {
			elements[i++] = element;
		}
		
		rightJList.setListData(elements);
	}
}
