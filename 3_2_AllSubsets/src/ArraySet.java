// ArraySet.java

/**********************************************************************

Klassen ArraySet representerar en mängd av typen Set. En mängd av den
här typen skapas men en viss kapacitet, men kapaciteten utökas
automatiskt om behovet uppstår.

Klassen ArraySet använder en sekvens som organisationsmodell för
mängdens element. Sekvensen implementeras med en vektor av den inbyggda
typen.

******************************* ***************************************/

// package fjava.collection.set;

import java.util.*;  // Iterator, NoSuchElementException
import java.io.*;    // Serializable


public class ArraySet<E> implements Set<E>, Serializable
{
	// mängdens förvalda start-kapacitet
	public static final int    DEFAULT_INITIAL_CAPACITY = 100;

	// en konstant som anger hur mycket den aktuella kapaciteten
	// ska utökas vid ändringen
    public static final int    ENLARGE_VALUE = 25;  // 25%

    // element i mängden
    private E[]    elements;

    // index av sista element i mängden
    private int    lastIndex = -1;


    // ArraySet skapar en tom mängd med en förvald start-kapacitet
    // (representerad med konstanten DEFAULT_INITIAL_CAPACITY)
    public ArraySet ()
    {
        elements = (E[]) (new Object[DEFAULT_INITIAL_CAPACITY]);
	}


    // ArraySet skapar en tom mängd med en given start-kapacitet
    public ArraySet (int initialCapacity)
    {
        elements = (E[]) (new Object[initialCapacity]);
	}


    // ArraySet skapar en mängd som är likadan som en given mängd.
    public ArraySet (Set<E> set)
    {
        elements = (E[]) (new Object[set.size ()]);
        int    index = 0;
        for (E element : set)
			elements[index++] = element;

		lastIndex = set.size () - 1;
	}


	// isEmpty returnerar true om mängden är tom, annars false
	public boolean isEmpty ()
	{
		return lastIndex == -1;
	}


	// size returnerar antalet element i mängden
	public int size ()
	{
		return lastIndex + 1;
	}


    // enlarge utökar mängdens kapacitet med en förvald procent
    // (angiven med konstanten ENLARGE_VALUE) + en ytterligare plats
    protected void enlarge ()
    {
		int    newLength = 1 + elements.length
		                 + ENLARGE_VALUE * elements.length / 100;
		E[]    newElements = (E[]) new Object[newLength];

		for (int index = 0; index <= lastIndex; index++)
		    newElements[index] = elements[index];

		elements = newElements;
	}


    // indexOf returnerar index av första element i vektorn som är
    // likadant som ett givet element. Om ett sådant element inte
    // finns, så returneras -1.
    protected int indexOf (E element)
    {
		int    indexOfElement = -1;
		for (int index = 0; index <= lastIndex; index++)
		{
		    if (element.equals (elements[index]))
		    {
				indexOfElement = index;
				break;
			}
		}

		return indexOfElement;
	}


    // contains returnerar true om mängden innehåller ett element som
    // är likadant som ett givet element, annars false.
    public boolean contains (E element)
    {
		int    indexOfElement = this.indexOf (element);
		boolean    elementIsInSet = indexOfElement != -1;

		return elementIsInSet;
	}


	// add lägger till ett givet element till mängden. Om ett sådant
	// element redan finns i mängden, så gör metoden ingenting.
	public void add (E element)
	{
		if (!this.contains (element))
		{
		    // om vektorn med element är full skapa en ny, större
		    // vektor och kopiera element dit
		    if (lastIndex == elements.length - 1)
		        this.enlarge ();

		    // lägg till elementet till mängden
		    lastIndex = lastIndex + 1;
		    elements[lastIndex] = element;
		}
	}
	
	public void addAll (Set<E> set){
        
        while((this.size() + set.size()) > elements.length)
        	this.enlarge();
        
        int    index = lastIndex + 1;
        for (E element : set)
			elements[index++] = element;

		lastIndex = lastIndex + set.size ();
		
	}


    // remove tar bort det element i mängden som är likadant som
    // ett givet element. Om ett sådant element inte finns i mängden,
    // så gör metoden ingenting.
	public void remove (E element)
	{
		int    indexOfElement = this.indexOf (element);
		if (indexOfElement != -1)
		{
		    for (int index = indexOfElement + 1;
		             index <= lastIndex; index++)
		        elements[index - 1] = elements[index];
		    elements[lastIndex] = null;
		    lastIndex--;
	    }
	}


	// clear tar bort alla element från mängden
	public void clear ()
	{
		for (int index = 0; index <= lastIndex; index++)
		    elements[index] = null;
		lastIndex = -1;
	}


	// isSubsetOf returnerar true om mängden är en delmängd av en given
	// mängd, annars false
	public boolean isSubsetOf (Set<E> set)
	{
		boolean isSubset = true;
		for (int index = 0; index <= lastIndex; index++)
		    if (!set.contains (elements[index]))
		    {
				isSubset = false;
				break;
			}

		return isSubset;
	}


	// union returnerar unionen av mängden och en given mängd.
	public Set<E> union (Set<E> set)
	{
		Set<E>    u = new ArraySet<E> (set);
		for (int index = 0; index <= lastIndex; index++)
			u.add (elements[index]);

        return u;
	}


	// intersection returnerar snittet av mängden och en given mängd.
	public Set<E> intersection (Set<E> set)
	{
		Set<E>    i = new ArraySet<E> ();
		for (int index = 0; index <= lastIndex; index++)
            if (set.contains(elements[index]))
			    i.add (elements[index]);

        return i;
	}


	// difference returnerar differensen av mängden och en given mängd.
	public Set<E> difference (Set<E> set)
	{
		Set<E>    d = new ArraySet<E> ();
		for (int index = 0; index <= lastIndex; index++)
            if (!set.contains(elements[index]))
			    d.add (elements[index]);

        return d;
	}



	// Klassen SetIterator representerar en iterator till mängden.
	// Iteratorn gör möjligt att iterera genom mängdens element, och
	// att ta bort dessa element under iterationen.
	private class SetIterator implements Iterator<E>
	{
		// index av nästa element i iterationen
		protected int    nextIndex;
		// pekar till det element som näst ska returneras

		// Index av det element som senast returnerats med den här
		// iteratorn. Värdet -1 på indexet signalerar att inget element
		// returnerats, eller att det returnerade elementet togs sedan
		// bort med iteratorn.
		protected int    lastReturnedIndex;
		// pekar till det element som näst ska tas bort
		// (både pekarfunktion och låsningsfunktion - låser en viss
		// position så att det inte går att ta bort motsvarande
		// element)


        // SetIterator skapar en iterator för (bara) en iteration genom
        // mängden.
		public SetIterator ()
		{
            nextIndex = 0;
            lastReturnedIndex = -1;
		}


		// hasNext returnerar true om det finns nästa element i
		// iterationen, annars false
		public boolean hasNext ()
		{
			return nextIndex <= lastIndex;
		}


		// next returnerar nästa element i mängden i den här
		// iterationen. Om ett sådant element inte finns, kastas
		// ett undantag av typen java.util.NoSuchElementException.
		public E next () throws NoSuchElementException
		{
			if (!this.hasNext ())
			    throw new NoSuchElementException (
					                "end of the iteration");

			E    element = elements[nextIndex];
			lastReturnedIndex = nextIndex;
			nextIndex++;

			return element;
		}


		// remove tar bort från mängden det element som senast
		// returnerats med iteratorn. Om ett sådant element inte finns,
		// kastas ett undantag av typen java.lang.IllegalStateException.
		public void remove () throws IllegalStateException
		{
			if (lastReturnedIndex == -1)
			    throw new IllegalStateException (
					"improper iterator state for remove operation");

            // ta bort elementet
		    for (int index = lastReturnedIndex + 1;
		         index <= lastIndex; index++)
		        elements[index - 1] = elements[index];
		    elements[lastIndex] = null;
		    lastIndex--;

            // anpassa index av nästa element efter borttagningen
            nextIndex--;
            // föregående element togs bort, nu är det ett element
            // mindre framför pekaren (pekaren pekar fortfarande till
            // samma element)

            // efter borttagningen kan inte något annat element tas
            // bort före än iteratorn returnerar ett nytt element
            lastReturnedIndex = -1;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("{");
		
		int count = 1;
		for(E element : this){
			sb.append(element);
			if(count++ != this.size())
				sb.append(", ");
		}
		sb.append("}");
		
		return sb.toString();
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
