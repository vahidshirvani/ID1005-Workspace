// UseSet.java

/**********************************************************************

Klassen UseSet anv�nder en m�ngd av typen Set p� olika s�tt.

**********************************************************************/

// import fjava.collection.set.*;  // Set, HashtableSet, NodeSet

import java.util.*; // Iterator
import java.io.*;   // FileOutputStream, ObjectOutputStream,
                    // FileInputStream, ObjectInputStream
import static java.lang.System.out;


class UseSet
{
    public static void main (String[] args) throws Exception
    {
		// en m�ngd med teckenstr�ngar
        Set<String>    set = null;
        set = new HashtableSet<String> ();
        // set = new HashtableSet<String> (2);
        // set = new NodeSet<String> ();

        // m�ngdens egenskaper
        out.println ("number of elements in the set: " + set.size ());
        out.println ("the set is empty: " + set.isEmpty ());
        out.println ("A is in the set: " + set.contains ("A"));
        out.println ();


        // l�gg till n�gra element till m�ngden
        out.println ("C is in the set: " + set.contains ("C"));
        set.add ("C");
        out.println ("C is in the set: " + set.contains ("C"));

        out.println ("B is in the set: " + set.contains ("B"));
        set.add ("B");
        out.println ("B is in the set: " + set.contains ("B"));

        out.println ("D is in the set: " + set.contains ("D"));
        set.add ("D");
        out.println ("D is in the set: " + set.contains ("D"));

        out.println ("A is in the set: " + set.contains ("A"));
        set.add ("A");
        out.println ("A is in the set: " + set.contains ("A"));

        out.println ("number of elements in the set: " + set.size ());
        out.println ("the set is empty: " + set.isEmpty ());
        out.println ();

        // "for each loop" i samband med m�ngden
        // (m�ngden �r Iterable)
        out.println ("the set:");
        for (String s : set)
            out.println (s);
        out.println ();

        // kopieringskonstruktor
        Set<String>    set1 = null;
        set1 = new HashtableSet<String> (set);
        // set1 = new NodeSet<String> (set);
        out.println ("the set1:");
        for (String s : set1)
            out.println (s);
        out.println ();


        // spara m�ngden i en fil
        // (m�ngden �r Serializable)
        out.println ("create one file and save the set there");
        ObjectOutputStream    fout = new ObjectOutputStream (
			                      new FileOutputStream ("file.dat"));
	    fout.writeObject (set);
	    fout.close ();
        out.println ("the set saved in the file");

        // l�s in m�ngden fr�n filen
        ObjectInputStream    fin = new ObjectInputStream (
			                      new FileInputStream ("file.dat"));
	    set = (Set<String>) fin.readObject ();
	    fin.close ();
        out.println ("the set read from the file");
        out.println ();


        // ta bort ett element fr�n m�ngden
        out.println ("remove D from the set");
        set.remove ("D");
        out.println ("D is in the set: " + set.contains ("D"));

        // f�rs�k att ta bort ett element som inte finns i m�ngden
        out.println ("E is in the set: " + set.contains ("E"));
        out.println ("try to remove E from the set");
        set.remove ("E");
        out.println ("E is in the set: " + set.contains ("E"));

        out.println ("number of elements in the set: " + set.size ());
        out.println ();

        // ta bort alla element fr�n m�ngden
        out.println ("remove all the elements from the set");
        set.clear ();
        out.println ("number of elements in the set: " + set.size ());
        out.println ("the set is empty: " + set.isEmpty ());
        out.println ("try to remove A from the set");
        set.remove ("A");
        out.println ("number of elements in the set: " + set.size ());
		out.println ();
		out.println ();


		// l�gg till n�gra element till m�ngden
		String[]    elements = {"A", "B", "C", "D", "E"};
		for (String e : elements)
		    set.add (e);

		// iterera genom m�ngden
		out.println ("use iterator");
		Iterator<String>    iterator = set.iterator ();
		while (iterator.hasNext ())
		    out.print (iterator.next () + " ");
		out.println ();
		// iterator.next ();
		// prova att inkludera f�reg�ende sats

		// ta bort n�gra element med en iterator
		iterator = set.iterator ();
		String    element = null;
		element = iterator.next ();
		// f�rs�k att bortkommentera f�reg�ende sats
		iterator.remove ();
		element = iterator.next ();
		element = iterator.next ();
		element = iterator.next ();
		iterator.remove ();

		// iterera genom m�ngden efter �ndringen
		iterator = set.iterator ();
		while (iterator.hasNext ())
		    out.print (iterator.next () + " ");
		out.println ();
		out.println ();

		// anv�nd iterator p� en tom m�ngd
		set.clear ();
		iterator = set.iterator ();
		// element = iterator.next ();
		// iterator.remove ();
		// f�rs�k att inkludera de f�reg�ende satserna, en i taget


		// operationer i samband med tv� m�ngder
        out.println ("operations with two sets");
		int[]    n1 = {1, 2, 3, 4, 5};
		int[]    n2 = {4, 5, 6, 7, 8};
		Set<Integer>    s1 = null;
		Set<Integer>    s2 = null;
		s1 = new HashtableSet<Integer> ();
		// s1 = new NodeSet<Integer> ();
		s2 = new HashtableSet<Integer> ();
		// s2 = new NodeSet<Integer> ();
		for (int n : n1)
		    s1.add (n);
		for (int n : n2)
		    s2.add (n);
		out.print ("set s1: ");
		for (int n : s1)
		    out.print (n + " ");
		out.println ();
		out.print ("set s2: ");
		for (int n : s2)
		    out.print (n + " ");
		out.println ();

        boolean    isSubset = s1.isSubsetOf (s2);
		out.print ("s1 is subset of s2: " + isSubset);
		out.println ();
        Set<Integer>    u = s1.union (s2);
		out.print ("union: ");
		for (int n : u)
		    out.print (n + " ");
		out.println ();
        Set<Integer>    i = s1.intersection (s2);
		out.print ("intersection: ");
		for (int n : i)
		    out.print (n + " ");
		out.println ();
        Set<Integer>    d = s1.difference (s2);
		out.print ("difference: ");
		for (int n : d)
		    out.print (n + " ");
		out.println ();
	}
}
