import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class SimplePasswordManagerTest {
	
	public static void printHashCollisions(String pathToFile) throws FileNotFoundException, IOException {
		HashMap<Long, List<String>> a = new HashMap<Long, List<String>>(50000, 1, 56897);
		SimplePasswordManager s = new SimplePasswordManager();
		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		
		try {
			String line = br.readLine();
			
			while (line != null) {
				String password = line.trim();
				Long passwordHash = s.hash(password);
				// if passwordHash in a, add password to its list value
				// else, instantiate a new ArrayList and add password to it
				if (a.get(passwordHash) != null) {
					List<String> value = a.get(passwordHash);
					value.add(password);
					a.put(passwordHash, value);
				}
				else {
					List<String> value = new ArrayList<String>(Arrays.asList(password));
					a.put(passwordHash, value);
				}
//				if (a.get(passwordHash) != null && a.get(passwordHash).contains((password))) {
//					List<String> passwordList = a.get(passwordHash);
//					passwordList.add(password);
//					a.put(passwordHash, passwordList);
//				}
//				else if (a.get(passwordHash) == null) {
//					a.put(passwordHash, new ArrayList<String>(Arrays.asList(password)));
//				}
				
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		
		int numOfCollisions = 0;
		
		List<Long> hashes = a.keys();
		for (Long hash: hashes) {
			List<String> passwords = a.get(hash);
			if (passwords.size() > 1) {
				// all passwords in this list have the same hash representation
				int collision = passwords.size() - 1;
				numOfCollisions += collision;
			}
		}
		System.out.println(numOfCollisions);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		printHashCollisions("C:\\Users\\Clifford\\Desktop\\10k-common-passwords.txt");
	}
	
//	SimplePasswordManager test = new SimplePasswordManager();
//	
//	@Test
//	public void testListUsers() {
//		test.addNewUser("Bill", "rabbits");
//		test.addNewUser("Don", "dogs");
//		test.addNewUser("Rory", "theracingcar");
//		assertEquals(Arrays.asList("Don", "Rory", "Bill"), test.listUsers());
//	}
//	
//	@Test
//	public void testAuthenticate() {
//		test.addNewUser("Bill", "rabbits");
//		test.addNewUser("Don", "dogs");
//		test.addNewUser("Rory", "theracingcar");
//		assertTrue(test.authenticate("Bill", "rabbits"));
//		assertTrue(test.authenticate("Don", "dogs"));
//		assertTrue(test.authenticate("Rory", "theracingcar"));
//	}
//	
//	@Test
//	public void testDeleteUser() {
//		test.addNewUser("Bill", "rabbits");
//		test.addNewUser("Don", "dogs");
//		test.addNewUser("Rory", "theracingcar");
//		test.deleteUser("Bill", "rabbits");
//		test.deleteUser("Don", "dogs");
//		test.deleteUser("Rory", "theracingcar");
//		assertEquals(Arrays.asList(), test.listUsers());
//		test.deleteUser("SFS", "SFSF");
//		test.addNewUser("Don", "dogs");
//		test.deleteUser("Don", "dragon");
//	}
//	
//	@Test
//	public void testResetPassword() {
//		test.addNewUser("Bill", "rabbits");
//		test.addNewUser("Don", "dogs");
//		test.addNewUser("Rory", "theracingcar");
//		test.resetPassword("Bill", "rabbits", "donkeys");
//		assertTrue(test.authenticate("Bill", "donkeys"));
//		test.resetPassword("SDFS", "SDF", "SDF");
//		test.resetPassword("Don", "dog", "dragon");
//	}

}
