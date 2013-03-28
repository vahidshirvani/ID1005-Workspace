// Set.java

/**********************************************************************

Gr�nssnitet Set definierar en m�ngd med obegr�nsad kapacitet.

En m�ngd �r en samling element, d�r ett element inte kan f�rekomma mer
�n en g�ng. Element i m�ngden �ndras inte p� n�got s�tt - de �r d�r som
de �r.

******************************* ***************************************/

// package fjava.collection.set;

import java.util.*;  // Iterable, Iterator


public interface Set<E> extends Iterable<E>
{
	// isEmpty returnerar true om m�ngden �r tom, annars false
	boolean isEmpty ();


	// size returnerar antalet element i m�ngden
	int size ();


    // contains returnerar true om m�ngden inneh�ller ett element som
    // �r likadant som ett givet element, annars false.
    boolean contains (E element);


	// add l�gger till ett givet element till m�ngden. Om ett s�dant
	// element redan finns i m�ngden, s� g�r metoden ingenting.
	void add (E element);
	
	void addAll (Set<E> set);


    // remove tar bort det element i m�ngden som �r likadant som
    // ett givet element. Om ett s�dant element inte finns i m�ngden,
    // s� g�r metoden ingenting.
	void remove (E element);


	// clear tar bort alla element fr�n m�ngden
	void clear ();


	// isSubsetOf returnerar true om m�ngden �r en delm�ngd av en given
	// m�ngd, annars false
	boolean isSubsetOf (Set<E> set);


	// union returnerar unionen av m�ngden och en given m�ngd.
	Set<E> union (Set<E> set);


	// intersection returnerar snittet av m�ngden och en given m�ngd.
	Set<E> intersection (Set<E> set);


	// difference returnerar differensen av m�ngden och en given m�ngd.
	Set<E> difference (Set<E> set);


	// iterator returnerar en iterator till m�ngden.
	// Den returnerade iteratorn kan anv�ndas f�r iteration genom
	// m�ngden, och f�r borttagning av element fr�n m�ngden.
	// Medan iteratorn anv�nds f�r iteration genom m�ngden, ska
	// m�ngden inte �ndras p� n�got s�tt (f�rutom m�jligen genom
	// sj�lva iteratorn).
	Iterator<E> iterator ();
}
