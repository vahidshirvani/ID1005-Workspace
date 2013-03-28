// UseCircularArrayQueue.java

import javax.swing.*;


class UseCircularArrayQueue
{
	public static void main (String[] args)
	{

		Queue<String> queue = new CircularArrayQueue<String>(14);
		//for(int index = 0; index < 14; index++)
			//queue.put(-1);
		GraphicPanel<String> panel = new GraphicPanel<String> (queue);
		queue.addGraphic(panel);

		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();
		
		JFrame    frame = new JFrame ("CircleQueue");
		frame.setBounds (80, 100, 1150, 350);
		frame.add (panel);
		frame.setVisible (true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}