
public class WordFrequency implements Comparable<WordFrequency>{

	public String word;
	public Integer frequency;
	private boolean toggle = true;
	
	public WordFrequency(String word, Integer frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	
	// As default
	public void compareBasedOnWord() {
		this.toggle = true;
	}
	
	public void compareBasedOnFrequency() {
		this.toggle = false;
	}

	@Override
	public int compareTo(WordFrequency another) {
		int value = 0;
		if(toggle)
			value = this.word.compareTo(another.word);
		else
			value = this.frequency.compareTo(another.frequency);
		return value;
	}
	
	@Override
	public String toString() {
		return this.frequency + " " + this.word;
	}
}
