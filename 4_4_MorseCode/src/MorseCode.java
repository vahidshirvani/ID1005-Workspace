import java.util.Hashtable;
import java.util.Scanner;


public class MorseCode implements MorseTables {
	
	private String morseText;
	private String alphabetText;
	
	private Hashtable<Character, String> hs;
	private BinarySearchTree<Morse> bst;
	
	MorseCode(){
	
		hs = new Hashtable<Character, String>(inOrderMorse.length);
		for(int index = 0; index < inOrderMorse.length; index++)
			hs.put(inOrderAlphabet[index],  inOrderMorse[index]);
		
		Morse morseCode = new Morse();
		bst = new BinarySearchTree<Morse>( // Root is an empty Morse code
				new BinaryNode<Morse>(
					morseCode));
		
		for(int index = 0; index < preOrderMorse.length; index++){
			morseCode = new Morse(preOrderAlphabet[index], preOrderMorse[index]);
			bst.add(morseCode);
		}
	}

	public String getMorse(){
		return morseText;
	}
	
	public String getAlphabet(){
		return alphabetText;
	}
	
	public void setMorse(String code){
		
		this.morseText = code;
		
		Scanner in = new Scanner(morseText);
		StringBuffer letter = new StringBuffer();
		Morse morseWord;
				
		while(in.hasNext()){
			morseWord = new Morse(in.next());
			letter.append(bst.get(morseWord).getAlphabet());
		}
		
		this.alphabetText = letter.toString();
	} 
	
	public void setAlphabet(String letter){
				
		this.alphabetText = letter;
		
		Scanner in = new Scanner(alphabetText);
		StringBuffer code = new StringBuffer();
		String word;

		while(in.hasNext()){
			word = in.next();
			for(int index = 0; index < word.length(); index++)
				code.append(      // Add to code which is output
					hs.get(       // The key is char and returns Morse code
						Character.toUpperCase( // lower case dosen't exist
							word.charAt(index))// position of the char in word
						) + " "); // Space between Morse letters
			code.append(" ");     // Additional space between Morse words
		}

		morseText = code.toString();
	}
}
