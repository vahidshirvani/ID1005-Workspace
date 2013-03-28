// NodeSet.java

/**********************************************************************

Klassen NodeSet representerar en m�ngd av typen Set. En m�ngd av den
h�r typen har en obegr�nsad kapacitet.

Klassen NodeSet anv�nder en sekvens som organisationsmodell f�r
m�ngdens element. Sekvensen implementeras med l�nkade noder.

**********************************************************************/

// package fjava.collection.set;

import java.util.*;  // Iterator, NoSuchElementException
import java.io.*;    // Serializable


public class NodeMultiSet<E> implements Set<E>, Serializable
{
	private static final long serialVersionUID = 7243389424904531156L;



	// klassen Node representerar en nod i sekvensen
    private class Node implements Serializable
    // �ven klassen Node beh�ver implementera gr�nssnittet Serializable
    {
		private static final long serialVersionUID = 5197214322408057608L;
		public E       element;
		public Node    nextNode;

		public Node (E element)
		{
			this.element = element;
			this.nextNode = null;
		}
	}



    // f�rsta nod i sekvensen
    private Node    firstNode;


    // NodeSet skapar en tom m�ngd
    public NodeMultiSet ()
    {
        firstNode = null;
	}


    // NodeSet skapar en m�ngd som �r likadan som en given m�ngd.
    public NodeMultiSet (Set<E> set)
    {
		firstNode = null;
		Node    newNode = null;
        for (E element : set)
        {
		    newNode = new Node (element);
		    newNode.nextNode = firstNode;
		    firstNode = newNode;
		}
	}


	// isEmpty returnerar true om m�ngden �r tom, annars false
	public boolean isEmpty ()
	{
		return firstNode == null;
	}


	// size returnerar antalet element i m�ngden
	public int size ()
	{
		int     numberOfElements = 0;
		Node    currentNode = firstNode;
		while (currentNode != null)
		{
			numberOfElements++;
			currentNode = currentNode.nextNode;
		}

		return numberOfElements;
	}


    // contains returnerar true om m�ngden inneh�ller ett element som
    // �r likadant som ett givet element, annars false.
    public boolean contains (E element)
    {
		Node    currentNode = firstNode;
		boolean    compareValue = false;
		boolean    elementIsInSet = false;
		while (currentNode != null)
		// om firstNode == null hoppar man �ver loopen och
		// returnerar false
		{
			compareValue = element.equals (currentNode.element);
            if (compareValue == true)  // elementet hittad
            {
				elementIsInSet = true;
				break;
			}
			else
			    currentNode = currentNode.nextNode;
		}

		return elementIsInSet;
	}


	// add l�gger till ett givet element till m�ngden. Om ett s�dant
	// element redan finns i m�ngden, s� g�r metoden ingenting.
	public void add (E element)
	{
	    Node    newNode = new Node (element);

	    newNode.nextNode = firstNode;
	    firstNode = newNode;
	    
	    // This has been changed due to multiset functionaleties
		/*
		if (!this.contains (element))
		{
		    Node    newNode = new Node (element);

		    newNode.nextNode = firstNode;
		    firstNode = newNode;
		}
		*/
	}
	
	public void addAll(Set<E> set){
		Node    newNode = null;
        for (E element : set)
        {
		    newNode = new Node (element);
		    newNode.nextNode = firstNode;
		    firstNode = newNode;
		}
	}


    // remove tar bort det element i m�ngden som �r likadant som
    // ett givet element. Om ett s�dant element inte finns i m�ngden,
    // s� g�r metoden ingenting.
    public void remove (E element)
    {
		Node    currentNode = firstNode;
		Node    previousNode = null;
		boolean    elementFound = false;
	    boolean    compareValue = false;

        // leta efter den nod d�r elementet ligger och den nodens
        // f�reg�ngare
		while (currentNode != null)
		{
			compareValue = element.equals (currentNode.element);
			if (compareValue == true)
			{
			    elementFound = true;
			    break;
			}
			else
			{
				previousNode = currentNode;
			    currentNode = currentNode.nextNode;
			}
		}

		// i fall att elementet hittats, ta bort det (genom att
		// exkludera dess nod ur sekvensen)
		if (elementFound)
		{
		    if (currentNode == firstNode)
		        firstNode = firstNode.nextNode;
		    else
                previousNode.nextNode = currentNode.nextNode;

		    currentNode.nextNode = null;
		    currentNode.element = null;
		}
	}


	// clear tar bort alla element fr�n m�ngden
	public void clear ()
	{
		Node    currentNode = firstNode;
		Node    nextNode;
		while (currentNode != null)
		{
			nextNode = currentNode.nextNode;
			currentNode.nextNode = null;
			currentNode.element = null;
			currentNode = nextNode;
		}

        firstNode = null;
	}


	// isSubsetOf returnerar true om m�ngden �r en delm�ngd av en given
	// m�ngd, annars false
	public boolean isSubsetOf (Set<E> set)
	{
		boolean isSubset = true;
		for (E element : this)
		    if (!set.contains (element))
		    {
				isSubset = false;
				break;
			}

		return isSubset;
	}


	// union returnerar unionen av m�ngden och en given m�ngd.
	public Set<E> union (Set<E> set)
	{
		Set<E>    u = new NodeMultiSet<E> (this);
		for (E element : set)
			u.add (element);

        return u;
	}


	// intersection returnerar snittet av m�ngden och en given m�ngd.
	public Set<E> intersection (Set<E> set)
	{
		Set<E>    i = new NodeMultiSet<E> ();
		for (E element : this)
            if (set.contains(element))
			    i.add (element);

        return i;
	}


	// difference returnerar differensen av m�ngden och en given m�ngd.
	public Set<E> difference (Set<E> set)
	{
		Set<E>    d = new NodeMultiSet<E> ();
		Set<E>    setModified = new NodeMultiSet<E> (set);
		for (E element : this) {
            if (!setModified.contains(element))
			    d.add (element);
            else
            	setModified.remove(element);
        }

        return d;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(E e: this)
			sb.append(e.toString() + " ");
		
		return sb.toString();
	}

	// Klassen SetIterator representerar en iterator till m�ngden.
	// Iteratorn g�r m�jligt att iterera genom m�ngdens element, och
	// att ta bort dessa element under iterationen.
	private class SetIterator implements Iterator<E>
	{
		// noden f�r det element som n�st ska returneras
		private Node    nextNode;

        // noden f�r det element som n�st ska tas bort
		private Node    lastReturnedNode;
		// (b�de pekarfunktion och l�sningsfunktion - l�ser en viss
		// position s� att det inte g�r att ta bort motsvarande
		// element)

        // SetIterator skapar en iterator f�r (bara) en iteration
        // genom m�ngden
		public SetIterator ()
		{
            nextNode = firstNode;
            lastReturnedNode = null;
		}


		// hasNext returnerar true om det finns n�sta element i
		// iterationen, annars false
		public boolean hasNext ()
		{
			return nextNode != null;
		}


		// next returnerar n�sta element i m�ngden i den h�r
		// iterationen. Om ett s�dant element inte finns, kastas
		// ett undantag av typen java.util.NoSuchElementException.
		public E next () throws NoSuchElementException
		{
			if (!this.hasNext ())
			    throw new NoSuchElementException (
					                "end of the iteration");

			E    element = nextNode.element;
			lastReturnedNode = nextNode;
			nextNode = nextNode.nextNode;

			return element;
		}


		// remove tar bort fr�n m�ngden det element som senast
		// returnerats med iteratorn. Om ett s�dant element inte finns,
		// kastas ett undantag av typen java.lang.IllegalStateException.
		public void remove () throws IllegalStateException
		{
			if (lastReturnedNode == null)
			    throw new IllegalStateException (
					"improper iterator state for remove operation");

            // ta bort elementet
            NodeMultiSet.this.remove (lastReturnedNode.element);

            // efter borttagningen kan inte n�got annat element tas
            // bort f�re �n iteratorn returnerar ett nytt element
            lastReturnedNode = null;
		}
	}

	// iterator returnerar en iterator till m�ngden.
	// Den returnerade iteratorn kan anv�ndas f�r iteration genom
	// m�ngden, och f�r borttagning av element fr�n m�ngden.
	// Medan iteratorn anv�nds f�r iteration genom m�ngden, ska
	// m�ngden inte �ndras p� n�got s�tt (f�rutom m�jligen genom
	// sj�lva iteratorn).
	public Iterator<E> iterator ()
	{
		return this.new SetIterator ();
	}
}
