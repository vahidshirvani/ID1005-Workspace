
public class BinaryNode<E extends Comparable<E>> {
	
	private E element;
	private BinaryNode<E> leftNode;
	private BinaryNode<E> rightNode;
	
	public BinaryNode(E element) {
		this.element = element;
	}

	public void setData(E element){
		this.element = element;
	}
	
	public E getData(){
		return this.element;
	}
	
	public void setLeft(BinaryNode<E> l){
		this.leftNode = l;
	}
	
	public void setRight(BinaryNode<E> r){
		this.rightNode = r;
	}
	
	public BinaryNode<E> getLeft(){
		return this.leftNode;
	}
	
	public BinaryNode<E> getRight(){
		return this.rightNode;
	}
}
