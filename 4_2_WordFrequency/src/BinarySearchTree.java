// BinarySearchTree.java

/**********************************************************************

Klassen BinarySearchTree representerar ett bin�rt s�ktr�d.

Tr�det �r bin�rt, som betyder att en nod i tr�det kan maximalt ha tv�
barn. Tr�det �r ocks� ett s�ktr�d, som betyder att en nods element �r
st�rre eller lika med det element som finns i nodens v�nstra barn, och
mindre �n det element som finns i nodens h�gra barn (om s�dana barn
finns). Tack vare dessa egenskaper �r ett bin�rt s�ktr�d en mycket
l�mplig datastruktur i de situationer d�r s�kningen ofta utf�rs.

Element i tr�det kan j�mf�ras med varandra. Vid s�kningen j�mf�rs det
s�kta elementet med det element som finns i en viss nod. Om dessa tv�
element �r likadana, s� har elementet hittats. Om det s�kta elementet
�r mindre, s� forts�tter s�kningen i nodens v�nstra barn. Om det s�kta
elementet �r st�rre �n elementet i noden, s� forts�tter s�kningen i
nodens h�gra barn.

**********************************************************************/

// package fjava.collection.binarytree;

import java.util.*;  // Deque, ArrayDeque


public class BinarySearchTree<E extends Comparable <? super E>>
{
	// klassen Node representerar en nod i tr�det
    private class Node
    {
		public E    element;
		public Node      leftNode;
		public Node      rightNode;

		public Node (E element)
		{
			this.element = element;
			this.leftNode = null;
			this.rightNode = null;
		}
	}


    // olika s�tt att traversera tr�det
    public static final int    PREORDER = -1;
    public static final int    INORDER = 0;
    public static final int    POSTORDER = 1;

    // v�nstra eller h�gra l�nken
    private static final int    LEFT = -1;
    private static final int    RIGHT = 1;


    // roten i tr�det
    private Node    root;


    // skapa ett tomt bin�rt s�ktr�d
    public BinarySearchTree ()
    {
		root = null;
	}


    // isEmpty returnerar true om tr�det �r tomt, annars false
	public boolean isEmpty ()
	{
		return root == null;
	}


    // size returnerar antalet element i tr�det
	public int size ()
	{
	    return size (root);
	}


    // size returnerar antalet element i ett givet tr�d
	private int size (Node tree)
	{
		int    numberOfElements = 0;
		if (tree != null)
		    numberOfElements = 1 +
		                       size (tree.leftNode) +
		                       size (tree.rightNode);

		return numberOfElements;
	}

/*
    // size returnerar antalet element i tr�det
	public int size ()
	{
		int    numberOfElements = 0;

		if (root != null)
        {
			Deque<Node>    queue = new ArrayDeque<Node> ();
			Node    currentNode = null;
			queue.addLast (root);
			while (!queue.isEmpty ())
			{
				currentNode = queue.removeFirst ();
				numberOfElements++;

				if (currentNode.leftNode != null)
				    queue.addLast (currentNode.leftNode);
				if (currentNode.rightNode != null)
				    queue.addLast (currentNode.rightNode);
			}
		}

		return numberOfElements;
	}
*/

    // nodeOf returnerar den nod i tr�det d�r ett element som �r
    // likadant som ett givet element ligger. Om det finns flera s�dana
    // noder, s� returneras en av dem. Om givna elementet inte finns i
    // tr�det s� returneras null.
    protected Node nodeOf (E element)
    {
		Node    currentNode = root;
		Node    elementNode = null; // den nod d�r elementet ligger
		int     compareValue = 0;
		while (currentNode != null)
		// om root == null hoppar man �ver loopen och returnerar null
		{
			compareValue = element.compareTo (currentNode.element);
            if (compareValue == 0)  // noden hittad
            {
				elementNode = currentNode;
				break;
			}
			else if (compareValue < 0)
			// leta i v�nstra subtr�det
			    currentNode = currentNode.leftNode;
            else  // if (compareValue > 0)
            // leta i h�gra subtredet
                currentNode = currentNode.rightNode;
		}

		return elementNode;
	}

/*
    // nodeOf returnerar den nod i tr�det d�r ett element som �r
    // likadant som ett givet element ligger. Om det finns flera s�dana
    // noder, s� returneras en av dem. Om givna elementet inte finns i
    // tr�det s� returneras null.
    protected Node nodeOf (E element)
    {
		return this.nodeOf (element, root);
	}


    // nodeOf returnerar den nod i givna tr�det d�r ett element som �r
    // likadant som ett givet element ligger. Om det finns flera s�dana
    // noder, s� returneras en av dem. Om givna elementet inte finns i
    // tr�det s� returneras null.
    private Node nodeOf (E element, Node tree)
    {
		Node    elementNode = null; // den nod d�r elementet ligger

		if (tree != null)
		{
		    int    compareValue = element.compareTo (tree.element);
            if (compareValue == 0)  // noden hittad
				elementNode = tree;
			else if (compareValue < 0)
			// leta i v�nstra subtr�det
			    elementNode = this.nodeOf (element, tree.leftNode);
            else  // if (compareValue > 0)
            // leta i h�gra subtredet
                elementNode = this.nodeOf (element, tree.rightNode);
	    }

		return elementNode;
	}
*/

    // add l�gger till ett givet element till tr�det
    public void add (E element)
    {
		// packa elementet i en nod
		Node    newNode = new Node (element);

        // placera noden p� r�tt st�lle i tr�det
        // (noden placeras som ett l�v, men p� s� s�tt att tr�det
        // beh�ller sin s�kegenskap)
		if (root == null)  // om tr�det tomt
		    root = newNode;
		else
		{
			// hitta r�tt plats f�r noden, och l�gg noden d�r
		    Node    currentNode = root;
			boolean    elementAdded = false;
			int    compareValue = 0;
		    while (!elementAdded)
		    {
				compareValue = element.compareTo (currentNode.element);
			    if (compareValue <= 0)
			    {
					if (currentNode.leftNode == null)
					{
					    currentNode.leftNode = newNode;
					    elementAdded = true;
					}
					else
					    currentNode = currentNode.leftNode;
				}
				else // if (compareValue > 0)
				{
					if (currentNode.rightNode == null)
					{
					    currentNode.rightNode = newNode;
					    elementAdded = true;
					}
					else
					    currentNode = currentNode.rightNode;
				}
			}
		}
	}

/*
    // add l�gger till ett givet element till tr�det
    public void add (E element)
    {
        this.root = this.add (element, root);
	}


    // add l�gger till ett givet element till ett givet tr�d, och
    // returnerar (en referens till) det nya tr�det
    private Node add (E element, Node tree)
    {
        // placera noden p� r�tt st�lle i tr�det
        // (noden placeras som ett l�v, men p� s� s�tt att tr�det
        // beh�ller sin s�kegenskap)
		if (tree == null)  // om tr�det tomt
		    tree = new Node (element);
		else if (element.compareTo (tree.element) <= 0)
			tree.leftNode = this.add (element, tree.leftNode);
		else // if (element.compareTo (tree.element) > 0)
			tree.rightNode = this.add (element, tree.rightNode);

		return tree;
	}
*/

    // contains returnerar true om tr�det inneh�ller ett givet element,
    // annars false
    public boolean contains (E element)
    {
		return this.nodeOf (element) != null;
	}


    // get returnerar det element i tr�det som �r likadant som ett
    // givet element. Om det finns flera s�dana element, s� returneras
    // ett av dessa element. Om givna elementet inte finns i tr�det, s�
    // returneras null.
    public E get (E element)
    {
		// hitta den nod d�r givna elementet ligger
		Node    elementNode = this.nodeOf (element);

        // om en s�dan nod finns s� returneras dess element, annars
        // returneras null
		E    treeElement =
		      (elementNode != null)? elementNode.element : null;

		return treeElement;
	}


    // remove tar bort det element i tr�det som �r likadant som
    // ett givet element, om ett s�dant element finns i tr�det.
    // Om det finns flera s�dana element, s� tas bort ett av dem.
    public void remove (E element)
    {
		Node    elementNode = root;
		Node    elementParent = null;
	    int     compareValue = 0;
	    int     leftOrRight = 0;
	    // f�r att kunna veta vilken av de tv� l�nkarna som ska
	    // uppdateras (v�nstra eller h�gra)

		// hitta det element som ska tas bort, om elementet finns
		while (elementNode != null)
		{
            // leta efter den nod d�r elementet ligger och den nodens
            // f�r�lder
			compareValue = element.compareTo (elementNode.element);
			if (compareValue == 0)  // elementet hittat
			    break;
			else if (compareValue < 0)
			{
				elementParent = elementNode;
			    elementNode = elementNode.leftNode;
			    leftOrRight = LEFT; // till v�nster
			}
			else // if (compareValue > 0)
			{
				elementParent = elementNode;
			    elementNode = elementNode.rightNode;
			    leftOrRight = RIGHT;  // till h�ger
			}
		}

		// om givna elementet inte finns i tr�det
		if (elementNode == null)
			return;

		// ta bort det hittade elementet
		// (men tr�det m�ste f�rbli ett bin�rt s�ktr�d)

		// om givna elementet ligger i roten
		if (elementNode == root)
		{
			if (root.leftNode == null)
			    root = root.rightNode;
			else
			{
		        Node    node = root.leftNode;
		        Node    parentNode = root;

		        // hita rotelementets logiska f�reg�ngare
		        while (node.rightNode != null)
		        {
			        parentNode = node;
			        node = node.rightNode;
			    }

                // l�t f�reg�ngaren ta plats i roten
			    if (parentNode == root)
			    {
			        node.rightNode = root.rightNode;
			        root = node;
				}
				else
				{
			        root.element = node.element;
			        parentNode.rightNode = node.leftNode;
				}
			}
		}

		// i fall att noden med elementet har maximalt ett barn

		// uppdatera f�r�lderns motsvarande l�nk s� att den hoppar
		// �ver den nod d�r givna elementet ligger - exkludera noden
		else if (elementNode.leftNode == null)
		// �ven om b�da l�nkarna null
		{
	        if (leftOrRight == LEFT)
		        elementParent.leftNode = elementNode.rightNode;
		    else  // if (leftOrRight == RIGHT)
		        elementParent.rightNode = elementNode.rightNode;
		}
		else if (elementNode.rightNode == null)
		{
	        if (leftOrRight == LEFT)
		        elementParent.leftNode = elementNode.leftNode;
		    else // if (leftOrRight == RIGHT)
		        elementParent.rightNode = elementNode.leftNode;
		}

        // i fall att noden med elementet har tv� barn

		// Kopiera elementets logiska f�reg�ngare till den aktuella
		// noden, och p� s� s�tt ta bort givna elementet. Ta sedan
		// bort den nod d�r f�reg�ngaren l�g.
		else
		{
            // hitta elementets logiska f�reg�ngare
            // (det element i nodens v�nstra subtr�d som �r n�rmast
            // till givna elementet - tr�det beh�ller sin s�kegenskap
            // om f�reg�ngaren ers�tter det borttagna elementet)
            Node    node = elementNode.leftNode;
            Node    nodeParent = elementNode;
            while (node.rightNode != null)
            {
		        nodeParent = node;
                node = node.rightNode;
			}

			// ta bort elementet genom att l�gga f�reg�ngaren p� dess
			// plats (tr�det beh�ller sin s�kegenskap - alla element i
			// en nods v�nstra subtr�d �r mindre eller lika som det
			// element som ligger i noden, alla element i nodens h�gra
			// subtr�d �r st�rre �n detta element)
			elementNode.element = node.element;

            // ta bort den nod d�r f�reg�ngaren l�g
            if (nodeParent == elementNode)
                nodeParent.leftNode = node.leftNode;
            else
                nodeParent.rightNode = node.leftNode;
		}
	}


	// clear tar bort alla element fr�n tr�det.
	public void clear ()
	{
		root = null;
	}


	// toString returnerar en str�ng som representerar tr�det.
	public String toString ()
	{
		StringBuilder    string = new StringBuilder ("");
		toString (root, string);

		return string.toString ();
	}


	// toString l�gger till de element som ligger i ett givet tr�d
	// till en given str�ng. Tr�dets element l�ggs till enligt
	// "inorder" ordningen.
	private void toString (Node tree, StringBuilder string)
	{
		if (tree != null)
		{
            toString (tree.leftNode, string);
			string.append (tree.element + " ");
            toString (tree.rightNode, string);
	    }
	}

/*
	// toString returnerar en str�ng som representerar tr�det.
	public String toString ()
	{
		StringBuilder    string = new StringBuilder ("");

		if (root != null)
        {
			Deque<Node>    queue = new ArrayDeque<Node> ();
			Node    currentNode = null;
			queue.addLast (root);
			while (!queue.isEmpty ())
			{
				currentNode = queue.removeFirst (); // niv� efter niv�
				// currentNode = queue.removeLast ();
				string.append (currentNode.element + " ");

				if (currentNode.leftNode != null)
				    queue.addLast (currentNode.leftNode);
				if (currentNode.rightNode != null)
				    queue.addLast (currentNode.rightNode);
			}
		}

		return string.toString ();
	}
*/

	// toQueue returnerar en k� med tr�dets aktuella element.
	// Ordning av element i k�n beror p� metodens argument.
	// Konstanterna PREORDER, INORDER och POSTORDER �r till�tna
	// argument. Om n�got annat v�rde anges, s� kastas ett undantag
	// av typen java.lang.IllegalArgumentException.
	public Deque<E> toQueue (int order)
	                                throws IllegalArgumentException
	{
		if (order != PREORDER  &&  order != INORDER  &&
		    order != POSTORDER)
		    throw new IllegalArgumentException (
				                "illegal argument: " + order);

        // skapa en k� och fyll den med tr�dets element enligt den
        // givna ordningen
		Deque<E>    queue = new ArrayDeque<E> ();
        switch (order)
        {
		case PREORDER:
		    preOrder (root, queue);
		    break;
		case INORDER:
		    inOrder (root, queue);
		    break;
		case POSTORDER:
		    postOrder (root, queue);
		    break;
		}

        return queue;
	}


	// preOrder s�tter in element som finns i ett givet tr�d i en given
	// k�. Tr�dets element s�tts in enligt "preorder" ordningen.
	protected void preOrder (Node tree, Deque<E> queue)
	{
		if (tree != null)
		{
            queue.addLast (tree.element);
            preOrder (tree.leftNode, queue);
            preOrder (tree.rightNode, queue);
	    }
	}


	// inOrder s�tter in element som finns i ett givet tr�d i en given
	// k�. Tr�dets element s�tts in enligt "inorder" ordningen.
	protected void inOrder (Node tree, Deque<E> queue)
	{
		if (tree != null)
		{
            inOrder (tree.leftNode, queue);
            queue.addLast (tree.element);
            inOrder (tree.rightNode, queue);
	    }
	}


	// postOrder s�tter in element som finns i ett givet tr�d i en given
	// k�. Tr�dets element s�tts in enligt "postorder" ordningen.
	protected void postOrder (Node tree, Deque<E> queue)
	{
		if (tree != null)
		{
            postOrder (tree.leftNode, queue);
            postOrder (tree.rightNode, queue);
            queue.addLast (tree.element);
	    }
	}


	// balance restrukturerar det underliggande tr�det s� att tr�det
	// blir optimalt balanserat.
	public void balance ()
	{
		// tr�dets element i stigande ordning i en k�
		Deque<E>    queue = this.toQueue (INORDER);

        // tr�dets element i en vektor
		Object[]    array = queue.toArray ();
		// element i vektorn �r ordnade i stigande ordning

		// ta bort alla element fr�n tr�det
		root = null;

		// l�gg till vektorns element till tr�det p� s� s�tt att tr�det
		// blir optimalt balanserat
		this.add (array, 0, array.length - 1);
	}


    // add l�gger till de element som ligger mellen tv� givna index
    // (inklusive dessa index) i en given vektor till tr�det. Element
    // l�ggs i en viss ordning (mittersta element l�ggs f�rst), s� att
    // tr�det blir optimalt balanserat.
	private void add (Object[] array, int lowIndex, int highIndex)
	{
		// Om det �r bara ett eller tv� element i den givna delen
		// av vektorn, l�gg dem till tr�det.
	    if (lowIndex == highIndex)
	        this.add ((E) array[lowIndex]);
	    else if (lowIndex + 1 == highIndex)
	    {
	        this.add ((E) array[lowIndex]);
	        this.add ((E) array[highIndex]);
		}
        // L�gg f�rst till det element som ligger i mitten av vektorn.
        // Upprepa sedan givna proceduren p� den delen av vektorn som
        // finns f�re detta element och p� den delen som finns efter
        // detta element.
		else
		{
			int    middleIndex = (lowIndex + highIndex) / 2;
			this.add ((E) array[middleIndex]);

			this.add (array, lowIndex, middleIndex - 1);
			this.add (array, middleIndex + 1, highIndex);
		}
	}


    // Man ska inte �ndra ett element i ett bin�rt s�ktr�d. Om ett
    // element �ndras kan s�kegenskapen hos tr�det rubbas.
    // I fall att ett element beh�ver �ndras, ska detta element
    // tas bort fr�n tr�det, modifieras, och sedan l�ggas tillbaka
    // i tr�det. P� s� s�tt hamnar elementet p� r�tt st�lle i
    // tr�det.
    //
    // Man kan lagra par av formen (nyckel, element) i ett bin�rt
    // s�ktr�d. Ett element inneh�ller nyttig information, men
    // elementet �r tillg�nglig via dess nyckel. Ett par placeras
    // i tr�det enligt v�rdet p� dess nyckel. D�rf�r ska nycklarna
    // inte �ndras. � andra sidan kan element i tr�det �ndras,
    // eftersom det p�verkar inte s�kegenskapen hos tr�det p�
    // n�got s�tt.
}
