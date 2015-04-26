
public class PhoneBook {
	private SimpleHashMap phonebook;

	/**
	 * Creates a new phonebook
	 */
	public PhoneBook(){
		phonebook = new SimpleHashMap(50);
	}
	
	/**
	 * Saves the given person's name and phone number
	 */
	public void addNumber(String name, Integer phoneNumber) {
		phonebook.put(name, phoneNumber);
	}
	
	/**
	 * Gets the phone number for the person with the given name
	 * Returns null if no person exists with that name
	 */
	public Integer getNumber(String name) {
		return (Integer) phonebook.get(name);
	}
	
	/**
	 * Deletes the contact with the given name from the book
	 */
	public void deleteContact(String name) {
		phonebook.remove(name);
	}
}
