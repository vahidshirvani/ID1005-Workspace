import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class Graphics extends JFrame {

	private JList listAZ;
	private JList list09;
	
	public Graphics() {
		
		listAZ = new JList();
		list09 = new JList();
		
		this.setVisible(true);
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 2));
		this.add(new JScrollPane(listAZ, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		this.add(new JScrollPane(list09, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		//this.add(listAZ);
		//this.add(list09);
	}
	
	public void fillListAZ(Object[] obj) {
		listAZ.setListData(obj);
	}
	
	public void fillList09(Object[] obj) {
		list09.setListData(obj);
	}
}

