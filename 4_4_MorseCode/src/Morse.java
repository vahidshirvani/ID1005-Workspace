import java.util.NoSuchElementException;


public class Morse implements Comparable<Morse> {

	private char alphabet;
	private String code;
	
	public Morse(){
		this.alphabet = ' ';
		this.code = "";
	}
	
	public Morse(String code){
		this.alphabet = ' ';
		this.code = code;
	}
	public Morse(char alphabet, String code) {
		this.alphabet = alphabet;
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public char getAlphabet(){
		return this.alphabet;
	}
	
	public int compareTo(Morse morse) {
		
		int relation = 0; // they are equal
		int wanted = this.code.length();
		int current = morse.getCode().length();
		char direction;
		
		// if the length is same then we have found it.
		if(wanted > current){
			direction = this.code.charAt(current);
			if(direction == '.'){
				relation = -1;	// less value so turn left
			}else if(direction == '-'){
				relation = 1;	// higher value so turn right
			}else{
				throw new NoSuchElementException("Not Morse Code!");
			}
		}
		
		return relation;
	}
}
