import java.util.Stack;


public class BinarySearchTree<T extends Comparable<T>> {

	private BinaryNode<T> root;
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	public BinarySearchTree(BinaryNode<T> node){
		this.root = node;
	}
	
	public boolean isEmpty(){
		return root == null;
	}

	public int recSize(BinaryNode<T> node){
		if(node == null)
			return 0;
		else
			return recSize(node.getLeft()) + recSize(node.getRight()) + 1;
	}
	
	public int stackSize(BinaryNode<T> node){
		int count = 0;
		
		if(root != null){
			Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
			stack.push(root);
			BinaryNode<T> currentNode;
			
			while(!stack.isEmpty()){
				currentNode = stack.pop();
				count++;
				if(currentNode.getLeft() != null)
					stack.push(currentNode.getLeft());
				if(currentNode.getRight() != null)
					stack.push(currentNode.getRight());
			}
		}
		
		return count;
	}
	
	public int size(){
		return recSize(root);
		// OR return stackSize(root);
	}
	
	public T recGet(T element, BinaryNode<T> node){
		
		if(node == null)
			return null;
		else if(element.compareTo(node.getData()) < 0)
			return recGet(element, node.getLeft());
		else if(element.compareTo(node.getData()) > 0)
			return recGet(element, node.getRight());
		else
			return node.getData();
	}
	
	public T get(T element){
		return recGet(element, root);
	}
	
	public BinaryNode<T> recAdd(T element, BinaryNode<T> node){
		
		if(node == null)
			node = new BinaryNode<T>(element);
		else if(element.compareTo(node.getData()) <= 0)
			node.setLeft(recAdd(element, node.getLeft()));
		else //if(element.compareTo(node.getData()) > 0)
			node.setRight(recAdd(element, node.getRight()));
		
		return node;
	}
	
	public void add(T element){
		root = recAdd(element, root);
	}
}
