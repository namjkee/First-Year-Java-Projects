import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FriendshipsTest {
	private Friendships f;
	@Before
	public void setup() {
		f = new Friendships();
	    
	    f.addPerson("Harry");
	    f.addPerson("Hermione");
	    f.addPerson("Ron");
	    f.addPerson("Dumbledore");
	    f.addPerson("Hagrid");
	    f.addPerson("Voldemort");
	    f.addPerson("Lucius");
	    f.addPerson("Draco");
	    
	    f.setFriends("Harry", "Hermione");
	    f.setFriends("Hermione", "Ron");
	    f.setFriends("Harry", "Ron");
	    f.setFriends("Dumbledore", "Harry");
	    f.setFriends("Hagrid", "Dumbledore");
	    f.setFriends("Voldemort", "Lucius");
	    f.setFriends("Lucius", "Draco");
	}
	
	
	@Test
	public void test() {
		assertEquals(Arrays.asList("Hermione", "Ron", "Dumbledore"), f.getFriends("Harry"));
		assertEquals(Arrays.asList("Harry", "Ron"), f.getFriends("Hermione"));
		assertEquals(Arrays.asList("Hermione", "Harry"), f.getFriends("Ron"));
		assertEquals(Arrays.asList("Harry", "Hagrid"), f.getFriends("Dumbledore"));
		assertEquals(Arrays.asList("Dumbledore"), f.getFriends("Hagrid"));
		assertEquals(Arrays.asList("Lucius"), f.getFriends("Voldemort"));
		assertEquals(Arrays.asList("Lucius"), f.getFriends("Draco"));
		assertNull(f.getFriends("SDFS"));
		
	}
	
	@Test
	public void testReachability() {
		List<String> result1 = f.getAllFriends("Ron");
		assertEquals(4, result1.size());
		result1.contains("Hagrid");
		result1.contains("Harry");
		result1.contains("Hermione");
		result1.contains("Dumbledore");
		
		List<String> result2 = f.getAllFriends("Voldemort");
		assertEquals(2, result2.size());
		result2.contains("Lucius");
		result2.contains("Draco");
		
		assertEquals(Arrays.asList(), f.getAllFriends("SDFS"));
		
	}

}
