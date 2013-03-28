// BinarySearchTree.java

/**********************************************************************

Klassen BinarySearchTree representerar ett binärt sökträd.

Trädet är binärt, som betyder att en nod i trädet kan maximalt ha två
barn. Trädet är också ett sökträd, som betyder att en nods element är
större eller lika med det element som finns i nodens vänstra barn, och
mindre än det element som finns i nodens högra barn (om sådana barn
finns). Tack vare dessa egenskaper är ett binärt sökträd en mycket
lämplig datastruktur i de situationer där sökningen ofta utförs.

Element i trädet kan jämföras med varandra. Vid sökningen jämförs det
sökta elementet med det element som finns i en viss nod. Om dessa två
element är likadana, så har elementet hittats. Om det sökta elementet
är mindre, så fortsätter sökningen i nodens vänstra barn. Om det sökta
elementet är större än elementet i noden, så fortsätter sökningen i
nodens högra barn.

**********************************************************************/

// package fjava.collection.binarytree;

import java.util.*;  // Deque, ArrayDeque


public class BinarySearchTree<E extends Comparable <? super E>>
{
	// klassen Node representerar en nod i trädet
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


    // olika sätt att traversera trädet
    public static final int    PREORDER = -1;
    public static final int    INORDER = 0;
    public static final int    POSTORDER = 1;

    // vänstra eller högra länken
    private static final int    LEFT = -1;
    private static final int    RIGHT = 1;


    // roten i trädet
    private Node    root;


    // skapa ett tomt binärt sökträd
    public BinarySearchTree ()
    {
		root = null;
	}


    // isEmpty returnerar true om trädet är tomt, annars false
	public boolean isEmpty ()
	{
		return root == null;
	}


    // size returnerar antalet element i trädet
	public int size ()
	{
	    return size (root);
	}


    // size returnerar antalet element i ett givet träd
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
    // size returnerar antalet element i trädet
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

    // nodeOf returnerar den nod i trädet där ett element som är
    // likadant som ett givet element ligger. Om det finns flera sådana
    // noder, så returneras en av dem. Om givna elementet inte finns i
    // trädet så returneras null.
    protected Node nodeOf (E element)
    {
		Node    currentNode = root;
		Node    elementNode = null; // den nod där elementet ligger
		int     compareValue = 0;
		while (currentNode != null)
		// om root == null hoppar man över loopen och returnerar null
		{
			compareValue = element.compareTo (currentNode.element);
            if (compareValue == 0)  // noden hittad
            {
				elementNode = currentNode;
				break;
			}
			else if (compareValue < 0)
			// leta i vänstra subträdet
			    currentNode = currentNode.leftNode;
            else  // if (compareValue > 0)
            // leta i högra subtredet
                currentNode = currentNode.rightNode;
		}

		return elementNode;
	}

/*
    // nodeOf returnerar den nod i trädet där ett element som är
    // likadant som ett givet element ligger. Om det finns flera sådana
    // noder, så returneras en av dem. Om givna elementet inte finns i
    // trädet så returneras null.
    protected Node nodeOf (E element)
    {
		return this.nodeOf (element, root);
	}


    // nodeOf returnerar den nod i givna trädet där ett element som är
    // likadant som ett givet element ligger. Om det finns flera sådana
    // noder, så returneras en av dem. Om givna elementet inte finns i
    // trädet så returneras null.
    private Node nodeOf (E element, Node tree)
    {
		Node    elementNode = null; // den nod där elementet ligger

		if (tree != null)
		{
		    int    compareValue = element.compareTo (tree.element);
            if (compareValue == 0)  // noden hittad
				elementNode = tree;
			else if (compareValue < 0)
			// leta i vänstra subträdet
			    elementNode = this.nodeOf (element, tree.leftNode);
            else  // if (compareValue > 0)
            // leta i högra subtredet
                elementNode = this.nodeOf (element, tree.rightNode);
	    }

		return elementNode;
	}
*/

    // add lägger till ett givet element till trädet
    public void add (E element)
    {
		// packa elementet i en nod
		Node    newNode = new Node (element);

        // placera noden på rätt ställe i trädet
        // (noden placeras som ett löv, men på så sätt att trädet
        // behåller sin sökegenskap)
		if (root == null)  // om trädet tomt
		    root = newNode;
		else
		{
			// hitta rätt plats för noden, och lägg noden där
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
    // add lägger till ett givet element till trädet
    public void add (E element)
    {
        this.root = this.add (element, root);
	}


    // add lägger till ett givet element till ett givet träd, och
    // returnerar (en referens till) det nya trädet
    private Node add (E element, Node tree)
    {
        // placera noden på rätt ställe i trädet
        // (noden placeras som ett löv, men på så sätt att trädet
        // behåller sin sökegenskap)
		if (tree == null)  // om trädet tomt
		    tree = new Node (element);
		else if (element.compareTo (tree.element) <= 0)
			tree.leftNode = this.add (element, tree.leftNode);
		else // if (element.compareTo (tree.element) > 0)
			tree.rightNode = this.add (element, tree.rightNode);

		return tree;
	}
*/

    // contains returnerar true om trädet innehåller ett givet element,
    // annars false
    public boolean contains (E element)
    {
		return this.nodeOf (element) != null;
	}


    // get returnerar det element i trädet som är likadant som ett
    // givet element. Om det finns flera sådana element, så returneras
    // ett av dessa element. Om givna elementet inte finns i trädet, så
    // returneras null.
    public E get (E element)
    {
		// hitta den nod där givna elementet ligger
		Node    elementNode = this.nodeOf (element);

        // om en sådan nod finns så returneras dess element, annars
        // returneras null
		E    treeElement =
		      (elementNode != null)? elementNode.element : null;

		return treeElement;
	}


    // remove tar bort det element i trädet som är likadant som
    // ett givet element, om ett sådant element finns i trädet.
    // Om det finns flera sådana element, så tas bort ett av dem.
    public void remove (E element)
    {
		Node    elementNode = root;
		Node    elementParent = null;
	    int     compareValue = 0;
	    int     leftOrRight = 0;
	    // för att kunna veta vilken av de två länkarna som ska
	    // uppdateras (vänstra eller högra)

		// hitta det element som ska tas bort, om elementet finns
		while (elementNode != null)
		{
            // leta efter den nod där elementet ligger och den nodens
            // förälder
			compareValue = element.compareTo (elementNode.element);
			if (compareValue == 0)  // elementet hittat
			    break;
			else if (compareValue < 0)
			{
				elementParent = elementNode;
			    elementNode = elementNode.leftNode;
			    leftOrRight = LEFT; // till vänster
			}
			else // if (compareValue > 0)
			{
				elementParent = elementNode;
			    elementNode = elementNode.rightNode;
			    leftOrRight = RIGHT;  // till höger
			}
		}

		// om givna elementet inte finns i trädet
		if (elementNode == null)
			return;

		// ta bort det hittade elementet
		// (men trädet måste förbli ett binärt sökträd)

		// om givna elementet ligger i roten
		if (elementNode == root)
		{
			if (root.leftNode == null)
			    root = root.rightNode;
			else
			{
		        Node    node = root.leftNode;
		        Node    parentNode = root;

		        // hita rotelementets logiska föregångare
		        while (node.rightNode != null)
		        {
			        parentNode = node;
			        node = node.rightNode;
			    }

                // låt föregångaren ta plats i roten
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

		// uppdatera förälderns motsvarande länk så att den hoppar
		// över den nod där givna elementet ligger - exkludera noden
		else if (elementNode.leftNode == null)
		// även om båda länkarna null
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

        // i fall att noden med elementet har två barn

		// Kopiera elementets logiska föregångare till den aktuella
		// noden, och på så sätt ta bort givna elementet. Ta sedan
		// bort den nod där föregångaren låg.
		else
		{
            // hitta elementets logiska föregångare
            // (det element i nodens vänstra subträd som är närmast
            // till givna elementet - trädet behåller sin sökegenskap
            // om föregångaren ersätter det borttagna elementet)
            Node    node = elementNode.leftNode;
            Node    nodeParent = elementNode;
            while (node.rightNode != null)
            {
		        nodeParent = node;
                node = node.rightNode;
			}

			// ta bort elementet genom att lägga föregångaren på dess
			// plats (trädet behåller sin sökegenskap - alla element i
			// en nods vänstra subträd är mindre eller lika som det
			// element som ligger i noden, alla element i nodens högra
			// subträd är större än detta element)
			elementNode.element = node.element;

            // ta bort den nod där föregångaren låg
            if (nodeParent == elementNode)
                nodeParent.leftNode = node.leftNode;
            else
                nodeParent.rightNode = node.leftNode;
		}
	}


	// clear tar bort alla element från trädet.
	public void clear ()
	{
		root = null;
	}


	// toString returnerar en sträng som representerar trädet.
	public String toString ()
	{
		StringBuilder    string = new StringBuilder ("");
		toString (root, string);

		return string.toString ();
	}


	// toString lägger till de element som ligger i ett givet träd
	// till en given sträng. Trädets element läggs till enligt
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
	// toString returnerar en sträng som representerar trädet.
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
				currentNode = queue.removeFirst (); // nivå efter nivå
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

	// toQueue returnerar en kö med trädets aktuella element.
	// Ordning av element i kön beror på metodens argument.
	// Konstanterna PREORDER, INORDER och POSTORDER är tillåtna
	// argument. Om något annat värde anges, så kastas ett undantag
	// av typen java.lang.IllegalArgumentException.
	public Deque<E> toQueue (int order)
	                                throws IllegalArgumentException
	{
		if (order != PREORDER  &&  order != INORDER  &&
		    order != POSTORDER)
		    throw new IllegalArgumentException (
				                "illegal argument: " + order);

        // skapa en kö och fyll den med trädets element enligt den
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


	// preOrder sätter in element som finns i ett givet träd i en given
	// kö. Trädets element sätts in enligt "preorder" ordningen.
	protected void preOrder (Node tree, Deque<E> queue)
	{
		if (tree != null)
		{
            queue.addLast (tree.element);
            preOrder (tree.leftNode, queue);
            preOrder (tree.rightNode, queue);
	    }
	}


	// inOrder sätter in element som finns i ett givet träd i en given
	// kö. Trädets element sätts in enligt "inorder" ordningen.
	protected void inOrder (Node tree, Deque<E> queue)
	{
		if (tree != null)
		{
            inOrder (tree.leftNode, queue);
            queue.addLast (tree.element);
            inOrder (tree.rightNode, queue);
	    }
	}


	// postOrder sätter in element som finns i ett givet träd i en given
	// kö. Trädets element sätts in enligt "postorder" ordningen.
	protected void postOrder (Node tree, Deque<E> queue)
	{
		if (tree != null)
		{
            postOrder (tree.leftNode, queue);
            postOrder (tree.rightNode, queue);
            queue.addLast (tree.element);
	    }
	}


	// balance restrukturerar det underliggande trädet så att trädet
	// blir optimalt balanserat.
	public void balance ()
	{
		// trädets element i stigande ordning i en kö
		Deque<E>    queue = this.toQueue (INORDER);

        // trädets element i en vektor
		Object[]    array = queue.toArray ();
		// element i vektorn är ordnade i stigande ordning

		// ta bort alla element från trädet
		root = null;

		// lägg till vektorns element till trädet på så sätt att trädet
		// blir optimalt balanserat
		this.add (array, 0, array.length - 1);
	}


    // add lägger till de element som ligger mellen två givna index
    // (inklusive dessa index) i en given vektor till trädet. Element
    // läggs i en viss ordning (mittersta element läggs först), så att
    // trädet blir optimalt balanserat.
	private void add (Object[] array, int lowIndex, int highIndex)
	{
		// Om det är bara ett eller två element i den givna delen
		// av vektorn, lägg dem till trädet.
	    if (lowIndex == highIndex)
	        this.add ((E) array[lowIndex]);
	    else if (lowIndex + 1 == highIndex)
	    {
	        this.add ((E) array[lowIndex]);
	        this.add ((E) array[highIndex]);
		}
        // Lägg först till det element som ligger i mitten av vektorn.
        // Upprepa sedan givna proceduren på den delen av vektorn som
        // finns före detta element och på den delen som finns efter
        // detta element.
		else
		{
			int    middleIndex = (lowIndex + highIndex) / 2;
			this.add ((E) array[middleIndex]);

			this.add (array, lowIndex, middleIndex - 1);
			this.add (array, middleIndex + 1, highIndex);
		}
	}


    // Man ska inte ändra ett element i ett binärt sökträd. Om ett
    // element ändras kan sökegenskapen hos trädet rubbas.
    // I fall att ett element behöver ändras, ska detta element
    // tas bort från trädet, modifieras, och sedan läggas tillbaka
    // i trädet. På så sätt hamnar elementet på rätt ställe i
    // trädet.
    //
    // Man kan lagra par av formen (nyckel, element) i ett binärt
    // sökträd. Ett element innehåller nyttig information, men
    // elementet är tillgänglig via dess nyckel. Ett par placeras
    // i trädet enligt värdet på dess nyckel. Därför ska nycklarna
    // inte ändras. Å andra sidan kan element i trädet ändras,
    // eftersom det påverkar inte sökegenskapen hos trädet på
    // något sätt.
}
