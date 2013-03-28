// NodeSet.java

/**********************************************************************

Klassen NodeSet representerar en mängd av typen Set. En mängd av den
här typen har en obegränsad kapacitet.

Klassen NodeSet använder en sekvens som organisationsmodell för
mängdens element. Sekvensen implementeras med länkade noder.

**********************************************************************/

// package fjava.collection.set;

import java.util.*;  // Iterator, NoSuchElementException
import java.io.*;    // Serializable


public class NodeMultiSet<E> implements Set<E>, Serializable
{
	private static final long serialVersionUID = 7243389424904531156L;



	// klassen Node representerar en nod i sekvensen
    private class Node implements Serializable
    // även klassen Node behöver implementera gränssnittet Serializable
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



    // första nod i sekvensen
    private Node    firstNode;


    // NodeSet skapar en tom mängd
    public NodeMultiSet ()
    {
        firstNode = null;
	}


    // NodeSet skapar en mängd som är likadan som en given mängd.
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


	// isEmpty returnerar true om mängden är tom, annars false
	public boolean isEmpty ()
	{
		return firstNode == null;
	}


	// size returnerar antalet element i mängden
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


    // contains returnerar true om mängden innehåller ett element som
    // är likadant som ett givet element, annars false.
    public boolean contains (E element)
    {
		Node    currentNode = firstNode;
		boolean    compareValue = false;
		boolean    elementIsInSet = false;
		while (currentNode != null)
		// om firstNode == null hoppar man över loopen och
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


	// add lägger till ett givet element till mängden. Om ett sådant
	// element redan finns i mängden, så gör metoden ingenting.
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


    // remove tar bort det element i mängden som är likadant som
    // ett givet element. Om ett sådant element inte finns i mängden,
    // så gör metoden ingenting.
    public void remove (E element)
    {
		Node    currentNode = firstNode;
		Node    previousNode = null;
		boolean    elementFound = false;
	    boolean    compareValue = false;

        // leta efter den nod där elementet ligger och den nodens
        // föregångare
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


	// clear tar bort alla element från mängden
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


	// isSubsetOf returnerar true om mängden är en delmängd av en given
	// mängd, annars false
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


	// union returnerar unionen av mängden och en given mängd.
	public Set<E> union (Set<E> set)
	{
		Set<E>    u = new NodeMultiSet<E> (this);
		for (E element : set)
			u.add (element);

        return u;
	}


	// intersection returnerar snittet av mängden och en given mängd.
	public Set<E> intersection (Set<E> set)
	{
		Set<E>    i = new NodeMultiSet<E> ();
		for (E element : this)
            if (set.contains(element))
			    i.add (element);

        return i;
	}


	// difference returnerar differensen av mängden och en given mängd.
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

	// Klassen SetIterator representerar en iterator till mängden.
	// Iteratorn gör möjligt att iterera genom mängdens element, och
	// att ta bort dessa element under iterationen.
	private class SetIterator implements Iterator<E>
	{
		// noden för det element som näst ska returneras
		private Node    nextNode;

        // noden för det element som näst ska tas bort
		private Node    lastReturnedNode;
		// (både pekarfunktion och låsningsfunktion - låser en viss
		// position så att det inte går att ta bort motsvarande
		// element)

        // SetIterator skapar en iterator för (bara) en iteration
        // genom mängden
		public SetIterator ()
		{
            nextNode = firstNode;
            lastReturnedNode = null;
		}


		// hasNext returnerar true om det finns nästa element i
		// iterationen, annars false
		public boolean hasNext ()
		{
			return nextNode != null;
		}


		// next returnerar nästa element i mängden i den här
		// iterationen. Om ett sådant element inte finns, kastas
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


		// remove tar bort från mängden det element som senast
		// returnerats med iteratorn. Om ett sådant element inte finns,
		// kastas ett undantag av typen java.lang.IllegalStateException.
		public void remove () throws IllegalStateException
		{
			if (lastReturnedNode == null)
			    throw new IllegalStateException (
					"improper iterator state for remove operation");

            // ta bort elementet
            NodeMultiSet.this.remove (lastReturnedNode.element);

            // efter borttagningen kan inte något annat element tas
            // bort före än iteratorn returnerar ett nytt element
            lastReturnedNode = null;
		}
	}

	// iterator returnerar en iterator till mängden.
	// Den returnerade iteratorn kan användas för iteration genom
	// mängden, och för borttagning av element från mängden.
	// Medan iteratorn används för iteration genom mängden, ska
	// mängden inte ändras på något sätt (förutom möjligen genom
	// själva iteratorn).
	public Iterator<E> iterator ()
	{
		return this.new SetIterator ();
	}
}
