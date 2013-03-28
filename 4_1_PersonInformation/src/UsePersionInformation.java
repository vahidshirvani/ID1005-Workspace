import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class UsePersionInformation {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		HashTable<String, Person> hashTable = new HashTable<String, Person>();
		
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"));
			hashTable = (HashTable<String, Person>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
			new File("data.dat");
		}
	
		// read from file
		//Person vahid = new Person();
		//HashTable<String, Person> hashTable = new HashTable<String, Person>();
		//hashTable.put("840323-3456", vahid);
		//Graphics gui = new Graphics(hashTable);
		new Graphics(hashTable);
	}
}
