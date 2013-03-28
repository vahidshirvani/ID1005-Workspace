import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.Scanner;


public class UseWordFrequency {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner book = null;
		try {
			book = new Scanner(new File("Book.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BinarySearchTree<WordFrequency> binSrch = new BinarySearchTree<WordFrequency>();
		WordFrequency wf;
		int balanceCountdown = 50;
		while(book.hasNext()) {
			wf = new WordFrequency(book.next(), 1);
			if(binSrch.contains(wf))
				binSrch.get(wf).frequency++;
			else
				binSrch.add(wf);
			
			balanceCountdown--;
			if(balanceCountdown < 1) {
				balanceCountdown = 50;
				binSrch.balance();
			}
		}
		
		Graphics show = new Graphics();
		show.fillListAZ(binSrch.toQueue(BinarySearchTree.INORDER).toArray());
		
		Deque<WordFrequency> queue = binSrch.toQueue(BinarySearchTree.INORDER);
		for(WordFrequency element: queue)
			element.compareBasedOnFrequency();
		binSrch.balance();
		show.fillList09(binSrch.toQueue(BinarySearchTree.INORDER).toArray());
		
			
	}

}
