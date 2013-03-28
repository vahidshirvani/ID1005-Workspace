import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* The gui consist of two separate windows. Upper window is where the drawing
 * takes place and the lower has the controllers for manipulating the values.
 * The drawing frame rate is carried by a thread that will call the repaint
 * regularly. 
 */
public class Visuals {

	private JPanel drawPanel;
	private boolean repaintFlag = true;
	private Generator generator;
	
	private class DrawThread implements Runnable {

		@Override
		public void run() {
			while(repaintFlag) {
				drawPanel.repaint();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.exit(0);
		}
	}
	
	private class Drawer extends JFrame {
		public Drawer() {
			
			drawPanel = new JPanel() {
				private Rectangle2D rect = 
						new Rectangle2D.Double(
								generator.getX(), 10, generator.getW(), 10); 	
				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setColor(Color.BLUE);
					rect.setRect(generator.getX(), 10, generator.getW(), 40);
					//rect.setRect(10, 10, 10, 10);
					g2d.fill(rect);
				}
			};
		
			this.add(drawPanel);
			this.setBounds(100, 100, 500, 100);
			this.setVisible (true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
	
	/* the controller consist of three sliders which they change 
	 * the value of probability of takes and puts.
	 * Even a button that stops both the ongoing threads and ends 
	 * the program as well.
	 */
	private class Controller extends JFrame {
		
		private JSlider takeFirstCon;
		private JSlider takeLastCon;
		private JSlider putCon;
		private JButton stopThreadFlag;
		
		public Controller() {
			
			takeFirstCon = new JSlider();
			takeLastCon = new JSlider();
			putCon = new JSlider();
			stopThreadFlag = new JButton("StopThreadFlag");
			
			ChangeListener cl = new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent arg0) {
					generator.setOdds(
							takeFirstCon.getValue(),
							takeLastCon.getValue(),
							putCon.getValue());
				}
				
			};
			this.takeFirstCon.addChangeListener(cl);
			this.takeLastCon.addChangeListener(cl);
			this.putCon.addChangeListener(cl);
			this.stopThreadFlag.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					repaintFlag = false;
					generator.setFlag(false);
				}
			});
			
			this.setLayout(new GridLayout(2, 4));
			this.setBounds(100, 300, 500, 150);
			this.setVisible(true);
			this.add(new JLabel("Take first"));
			this.add(new JLabel("Take last"));
			this.add(new JLabel("put last"));
			this.add(new JLabel("Stop and quit"));
			this.add(takeFirstCon);
			this.add(takeLastCon);
			this.add(putCon);
			this.add(stopThreadFlag);
		}
	}
	
	/* In this constructor the two windows are created 
	 * and the threads are started. 
	 */
	public Visuals(Generator gen) {	
		this.generator = gen;
		Drawer drawer = new Drawer(); 
		Controller controller = new Controller();
		new Thread(new DrawThread()).start();
		new Thread(this.generator).start();
	}
}
