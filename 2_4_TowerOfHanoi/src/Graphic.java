import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Graphic<T extends Comparable<T>> extends JFrame {

	private JLabel message;
	//private DefaultListModel[] model = new DefaultListModel[3];
	private JList towers[] = new JList[3];;
	private JButton movers[] = new JButton[6];
	private HeapSort<T>[] heapTowers;
	private final String[] imageNames = {
			"red.png", "green.png", 
			"blue.png", "yellow.png",
			"orange.png", "pink.png",
			"brown.png", "purple.png",
			"grey.png", "black.png"};
	
	public Graphic( HeapSort<T>[] ht){
	
		this.heapTowers = ht;
		message = new JLabel("Please do your move!");
		String[] labels = {
				">",  ">", "<",
				">>", "<", "<<"};
		
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		JPanel buttom = new JPanel();
		
		top.add(message);
		middle.setLayout(new GridLayout(1, 3, 3, 0));
		buttom.setLayout(new GridLayout(2, 3));
		
		/* This listener class which is local does the 
		 * decision making when any button being pushed.
		 * Every button tries to enqueue the top element
		 * on one tower and dequeue it into other tower.
		 * If it fails due to the restrictions, An 
		 * exception will be thrown and the tower will
		 * take back the element.
		 */
		ActionListener bListener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				T hold = null;
				if(source == movers[0]){
					try{
						hold = heapTowers[0].dequeue();
						heapTowers[1].enqueue(hold);
					}catch(IllegalStateException ex){
						heapTowers[0].enqueue(hold);
					}
				}else if(source == movers[1]){
					try{
						hold = heapTowers[1].dequeue();
						heapTowers[2].enqueue(hold);
					}catch(IllegalStateException ex){
						heapTowers[1].enqueue(hold);
					}
				}else if(source == movers[2]){
					try{
						hold = heapTowers[2].dequeue();
						heapTowers[1].enqueue(hold);
					}catch(IllegalStateException ex){
						heapTowers[2].enqueue(hold);
					}
				}else if(source == movers[3]){
					try{
						hold = heapTowers[0].dequeue();
						heapTowers[2].enqueue(hold);
					}catch(IllegalStateException ex){
						heapTowers[0].enqueue(hold);
					}
				}else if(source == movers[4]){
					try{
						hold = heapTowers[1].dequeue();
						heapTowers[0].enqueue(hold);
					}catch(IllegalStateException ex){
						heapTowers[1].enqueue(hold);
					}
				}else{ // if(source == movers[5]){
					try{
						hold = heapTowers[2].dequeue();
						heapTowers[0].enqueue(hold);	
					}catch(IllegalStateException ex){
						heapTowers[2].enqueue(hold);
					}
				}
				
				updateTowers();
			}
		};

		for(int i = 0; i < 3; i++ ){
			towers[i] = new JList();
			middle.add(towers[i]);
			heapTowers[i].setMessage(message);
		}
		
		for(int i = 0; i < 6; i++){
			movers[i] = new JButton(labels[i]);
			buttom.add(movers[i]);
			movers[i].addActionListener(bListener);
		}
		
		this.setBounds(500, 250, 350, 230);
		this.setTitle("Tower of Hanoi");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.add(top, BorderLayout.NORTH);
		this.add(middle, BorderLayout.CENTER);
		this.add(buttom, BorderLayout.SOUTH);
	
	}
	
	public void updateTowers(){
		updateTower(0);
		updateTower(1);
		updateTower(2);
	}
	
	/* This method does empty the the relative tower into a temporarily
	 * array. It chooses the right image to be shown and afterwards rolls
	 * back the data back to the tower.
	 */
	public void updateTower(int idNumber){
		ArrayList<T> tempArray = new ArrayList<T>(20);
		int size = heapTowers[idNumber].size();
		Object[] image = new ImageIcon[size];
		
		for(int i = 0; i < size; i++){
			tempArray.add(i, heapTowers[idNumber].dequeue());
			image[i] = new ImageIcon(
					this.getClass().getResource(
							imageNames[(Integer)tempArray.get(i)]));
		}
		for(int i = size - 1 ; i >= 0; i--)
			heapTowers[idNumber].enqueue(tempArray.get(i));
		towers[idNumber].setListData(image);
	}
	
	public JLabel getMessageBar(){
		return message;
	}
}
