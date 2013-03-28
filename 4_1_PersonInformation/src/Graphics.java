import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Graphics extends JFrame {

	private JTextField key;
	private JTextField name;
	private JTextField lastname;
	private JTextField address;
	private JTextField postcode;
	private JTextField city;
	private JTextField length;
	private JButton bAddSet;
	private JButton bRemove;
	private ListFrame listFrame;
	// private JList list;
	private HashTable<String, Person> hashTable;

	public Graphics(HashTable<String, Person> hashTable) {

		key = new JTextField("YYYYMMDD-NNNN", 13);
		name = new JTextField("YYYYMMDD-NNNN", 13);
		lastname = new JTextField("YYYYMMDD-NNNN", 13);
		address = new JTextField("YYYYMMDD-NNNN", 13);
		postcode = new JTextField("YYYYMMDD-NNNN", 13);
		city = new JTextField("YYYYMMDD-NNNN", 13);
		length = new JTextField("YYYYMMDD-NNNN", 13);
		bAddSet = new JButton("Add/Set");
		bRemove = new JButton("Remove");

		ActionListener textListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				if (event.getSource() == bRemove) {
					if (Graphics.this.hashTable.containsKey(Graphics.this.key
							.getText())) {
						Graphics.this.hashTable.remove(Graphics.this.key
								.getText());
						// TODO refresh list
					}
				} else { // Either its the add button or enter on textfield
					Person modifiedPerson = Graphics.this.hashTable.get(key
							.getText());
					// TODO if the person behind key not exist

					if (modifiedPerson == null) {
						Person newPerson = new Person();
						newPerson.name = name.getText();
						newPerson.lastname = lastname.getText();
						newPerson.address = address.getText();
						newPerson.postcode = postcode.getText();
						newPerson.city = city.getText();
						newPerson.length = length.getText();
						Graphics.this.hashTable.put(key.getText(), newPerson);
					} else {
						modifiedPerson.name = name.getText();
						modifiedPerson.lastname = lastname.getText();
						modifiedPerson.address = address.getText();
						modifiedPerson.postcode = postcode.getText();
						modifiedPerson.city = city.getText();
						modifiedPerson.length = length.getText();
					}
				}

				listFrame.refreshList();

				// TODO trigger outputstream
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							new FileOutputStream("data.dat"));
					oos.writeObject(Graphics.this.hashTable);
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		key.addActionListener(textListener);
		name.addActionListener(textListener);
		lastname.addActionListener(textListener);
		address.addActionListener(textListener);
		postcode.addActionListener(textListener);
		city.addActionListener(textListener);
		length.addActionListener(textListener);
		bAddSet.addActionListener(textListener);
		bRemove.addActionListener(textListener);

		this.setLayout(new GridLayout(8, 2));
		this.add(new JLabel("Sec No. (Key): "));
		this.add(key);
		this.add(new JLabel("Name: "));
		this.add(name);
		this.add(new JLabel("Lastname: "));
		this.add(lastname);
		this.add(new JLabel("Address: "));
		this.add(address);
		this.add(new JLabel("Postcode: "));
		this.add(postcode);
		this.add(new JLabel("City: "));
		this.add(city);
		this.add(new JLabel("Length: "));
		this.add(length);
		this.add(bAddSet);
		this.add(bRemove);
		this.setBounds(400, 100, 240, 230);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.hashTable = hashTable;

		listFrame = new ListFrame();
	}

	private class ListFrame extends JFrame {
		private JList list;
		private JLabel sizeLabel;
		
		ListFrame() {
			this.list = new JList(Graphics.this.hashTable.keySet().toArray());
			this.sizeLabel = new JLabel("The size of the table is: " + Graphics.this.hashTable.size());
			this.setLayout(new BorderLayout());
			this.add(list, BorderLayout.CENTER);
			this.add(sizeLabel, BorderLayout.SOUTH);
			this.setBounds(100, 100, 200, 500);
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			this.list.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					String key = (String) list.getSelectedValue();
					if (key != null) {
						Person p = Graphics.this.hashTable.get(key);

						Graphics.this.key.setText(key);
						Graphics.this.name.setText(p.name);
						Graphics.this.lastname.setText(p.lastname);
						Graphics.this.address.setText(p.address);
						Graphics.this.postcode.setText(p.postcode);
						Graphics.this.city.setText(p.city);
						Graphics.this.length.setText(p.length);
					}
				}
			});
		}

		// Accessed from above when updating list is necessarily to apply
		// modification
		public void refreshList() {
			this.list.setListData(Graphics.this.hashTable.keySet().toArray());
			this.sizeLabel.setText("The size of the table is: " + Graphics.this.hashTable.size());
		}
	}
}
