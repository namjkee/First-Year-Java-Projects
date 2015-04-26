import java.util.ArrayList;
import java.util.List;


public class SimplePasswordManager {
	// instance variables
	private ChainingHashMap<String, Long> hashMap;
	
	// construction
	public SimplePasswordManager() {
		// construct a SimplePasswordManager with 4000 places and default hash parameters - multiplier = 1 and modulus = 4271
		hashMap = new ChainingHashMap<>(1, 4271);
	}
	
	public SimplePasswordManager(int size, int multiplier, int modulus) {
		// construct a SimplePasswordManager with size number of places
		hashMap = new ChainingHashMap<>(size, multiplier, modulus);
	}
	
	// hashing - djb2 algorithm
	public Long hash(String password) {
		long hash = 5381;										// hash value of an empty string
		
		for (int i = 0; i < password.length(); i++) {
			hash = ((hash << 5) + hash) + password.charAt(i);	// hash * 33 + c (character value in ASCII)
		}
		
		return hash;
	}
	
	// interface methods
	public List<String> listUsers() {
		// return an array of the usernames of the users currently stored
		return hashMap.keys();
	}
	
	public boolean authenticate(String username, String password) {
		if (hashMap.get(username) == null) {						// if true - username wasn't found
			System.out.println("No such user exists.");
			return false;											
		}
		if (hashMap.get(username).equals(hash(password)) ) {		// if true - hashed password same as the stored password for the user
			return true;
		} 
		else {
			return false;
		}
	}
	
	public void addNewUser(String username, String password) {
		if (hashMap.get(username) != null) {						// if true - there's a username already stored
			System.out.println("User already exists.");
		}
		else {														// username available to store
			hashMap.put(username, hash(password));
		}
	}
	
	public void deleteUser(String username, String password) {
		if (hashMap.get(username) == null) {						// if true - username wasn't found
			System.out.println("No such user exists.");
		}
		else if (authenticate(username, password)) {				// if authenticate return true - delete
			hashMap.remove(username);
		}
		else {														// unauthenticated user
			System.out.println("Failed to authenticate user.");
		}
	}
	
	public void resetPassword(String username, String oldPassword, String newPassword) {
		if (hashMap.get(username) == null) {						// if true - username wasn't found
			System.out.println("No such user exists.");				
		}
		else if (authenticate(username, oldPassword)) {				// if authenticate return true - update password
			hashMap.put(username, hash(newPassword));				// overwrites hashed password
		}
		else {														// unauthenticated user
			System.out.println("Failed to authenticate user.");
		}
	}
	
}
