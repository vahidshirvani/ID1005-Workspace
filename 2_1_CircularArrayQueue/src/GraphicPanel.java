import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

// GraphicPaneljava



public class GraphicPanel<E> extends JPanel
{
	Rectangle2D[] rect = new Rectangle2D[14];
	Line2D[] first = new Line2D[3];
	Line2D[] last = new Line2D[3];
	/*
	String[] elements= {
			"00", "10", "20", "30", "40",
			"50", "60", "70", "80", "90",
			"50", "60", "70", "80"};
	*/
	private Queue<E> queue;
	int square = 70;
	//int firstIndex = 1 * square;
	//int lastIndex = 7 * square;
	
	public GraphicPanel(Queue<E> queue){
		this.queue = queue;
		//for(int index = 0; index < queue.size(); index++)
			//elements[index] = queue.peek(index).toString();
	}

	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D graphics = (Graphics2D) gr;
		this.setBackground(Color.BLACK);
		graphics.setColor(Color.GRAY);
		
	
		int firstIndex = queue.getFirst() * square;
		int lastIndex = queue.getLast() * square;
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		graphics.drawString("F", 110 + firstIndex, 250);
		graphics.drawString("L", 110 + lastIndex, 250);
		
		// Pointers
		first[0] = new Line2D.Double(100 + firstIndex, 150, 90 + firstIndex, 160);
		first[1] = new Line2D.Double(100 + firstIndex, 150, 100 + firstIndex, 250);
		first[2] = new Line2D.Double(100 + firstIndex, 150, 110 + firstIndex, 160);
		last[0] = new Line2D.Double(100 + lastIndex, 150, 90 + lastIndex, 160);
		last[1] = new Line2D.Double(100 + lastIndex, 150, 100 + lastIndex, 250);
		last[2] = new Line2D.Double(100 + lastIndex, 150, 110 + lastIndex, 160);
		for (int i = 0; i < 3; i++) {
			graphics.draw(first[i]);
			graphics.draw(last[i]);
		}

		// Draw squares and fill inside
		for (int index = 0; index < 14; index++) {
			rect[index] = new Rectangle2D.Double(
					square + index * square, square, square, square);
			graphics.draw(rect[index]);
		}
		
		int from = queue.getFirst();
		int to = queue.getFirst() + queue.size();
		for(int index = from; index < to; index++)
			graphics.drawString(queue.peek(index % 14).toString(), 80 + (index%14) * square, 120);
	}
}