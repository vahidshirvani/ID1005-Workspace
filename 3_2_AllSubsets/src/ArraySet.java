// ArraySet.java

/**********************************************************************

Klassen ArraySet representerar en m�ngd av typen Set. En m�ngd av den
h�r typen skapas men en viss kapacitet, men kapaciteten ut�kas
automatiskt om behovet uppst�r.

Klassen ArraySet anv�nder en sekvens som organisationsmodell f�r
m�ngdens element. Sekvensen implementeras med en vektor av den inbyggda
typen.

******************************* ***************************************/

// package fjava.collection.set;

import java.util.*;  // Iterator, NoSuchElementException
import java.io.*;    // Serializable


public class ArraySet<E> implements Set<E>, Serializable
{
	// m�ngdens f�rvalda start-kapacitet
	public static final int    DEFAULT_INITIAL_CAPACITY = 100;

	// en konstant som anger hur mycket den aktuella kapaciteten
	// ska ut�kas vid �ndringen
    public static final int    ENLARGE_VALUE = 25;  // 25%

    // element i m�ngden
    private E[]    elements;

    // index av sista element i m�ngden
    private int    lastIndex = -1;


    // ArraySet skapar en tom m�ngd med en f�rvald start-kapacitet
    // (representerad med konstanten DEFAULT_INITIAL_CAPACITY)
    public ArraySet ()
    {
        elements = (E[]) (new Object[DEFAULT_INITIAL_CAPACITY]);
	}


    // ArraySet skapar en tom m�ngd med en given start-kapacitet
    public ArraySet (int initialCapacity)
    {
        elements = (E[]) (new Object[initialCapacity]);
	}


    // ArraySet skapar en m�ngd som �r likadan som en given m�ngd.
    public ArraySet (Set<E> set)
    {
        elements = (E[]) (new Object[set.size ()]);
        int    index = 0;
        for (E element : set)
			elements[index++] = element;

		lastIndex = set.size () - 1;
	}


	// isEmpty returnerar true om m�ngden �r tom, annars false
	public boolean isEmpty ()
	{
		return lastIndex == -1;
	}


	// size returnerar antalet element i m�ngden
	public int size ()
	{
		return lastIndex + 1;
	}


    // enlarge ut�kar m�ngdens kapacitet med en f�rvald procent
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


    // indexOf returnerar index av f�rsta element i vektorn som �r
    // likadant som ett givet element. Om ett s�dant element inte
    // finns, s� returneras -1.
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


    // contains returnerar true om m�ngden inneh�ller ett element som
    // �r likadant som ett givet element, annars false.
    public boolean contains (E element)
    {
		int    indexOfElement = this.indexOf (element);
		boolean    elementIsInSet = indexOfElement != -1;

		return elementIsInSet;
	}


	// add l�gger till ett givet element till m�ngden. Om ett s�dant
	// element redan finns i m�ngden, s� g�r metoden ingenting.
	public void add (E element)
	{
		if (!this.contains (element))
		{
		    // om vektorn med element �r full skapa en ny, st�rre
		    // vektor och kopiera element dit
		    if (lastIndex == elements.length - 1)
		        this.enlarge ();

		    // l�gg till elementet till m�ngden
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


    // remove tar bort det element i m�ngden som �r likadant som
    // ett givet element. Om ett s�dant element inte finns i m�ngden,
    // s� g�r metoden ingenting.
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


	// clear tar bort alla element fr�n m�ngden
	public void clear ()
	{
		for (int index = 0; index <= lastIndex; index++)
		    elements[index] = null;
		lastIndex = -1;
	}


	// isSubsetOf returnerar true om m�ngden �r en delm�ngd av en given
	// m�ngd, annars false
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


	// union returnerar unionen av m�ngden och en given m�ngd.
	public Set<E> union (Set<E> set)
	{
		Set<E>    u = new ArraySet<E> (set);
		for (int index = 0; index <= lastIndex; index++)
			u.add (elements[index]);

        return u;
	}


	// intersection returnerar snittet av m�ngden och en given m�ngd.
	public Set<E> intersection (Set<E> set)
	{
		Set<E>    i = new ArraySet<E> ();
		for (int index = 0; index <= lastIndex; index++)
            if (set.contains(elements[index]))
			    i.add (elements[index]);

        return i;
	}


	// difference returnerar differensen av m�ngden och en given m�ngd.
	public Set<E> difference (Set<E> set)
	{
		Set<E>    d = new ArraySet<E> ();
		for (int index = 0; index <= lastIndex; index++)
            if (!set.contains(elements[index]))
			    d.add (elements[index]);

        return d;
	}



	// Klassen SetIterator representerar en iterator till m�ngden.
	// Iteratorn g�r m�jligt att iterera genom m�ngdens element, och
	// att ta bort dessa element under iterationen.
	private class SetIterator implements Iterator<E>
	{
		// index av n�sta element i iterationen
		protected int    nextIndex;
		// pekar till det element som n�st ska returneras

		// Index av det element som senast returnerats med den h�r
		// iteratorn. V�rdet -1 p� indexet signalerar att inget element
		// returnerats, eller att det returnerade elementet togs sedan
		// bort med iteratorn.
		protected int    lastReturnedIndex;
		// pekar till det element som n�st ska tas bort
		// (b�de pekarfunktion och l�sningsfunktion - l�ser en viss
		// position s� att det inte g�r att ta bort motsvarande
		// element)


        // SetIterator skapar en iterator f�r (bara) en iteration genom
        // m�ngden.
		public SetIterator ()
		{
            nextIndex = 0;
            lastReturnedIndex = -1;
		}


		// hasNext returnerar true om det finns n�sta element i
		// iterationen, annars false
		public boolean hasNext ()
		{
			return nextIndex <= lastIndex;
		}


		// next returnerar n�sta element i m�ngden i den h�r
		// iterationen. Om ett s�dant element inte finns, kastas
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


		// remove tar bort fr�n m�ngden det element som senast
		// returnerats med iteratorn. Om ett s�dant element inte finns,
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

            // anpassa index av n�sta element efter borttagningen
            nextIndex--;
            // f�reg�ende element togs bort, nu �r det ett element
            // mindre framf�r pekaren (pekaren pekar fortfarande till
            // samma element)

            // efter borttagningen kan inte n�got annat element tas
            // bort f�re �n iteratorn returnerar ett nytt element
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
