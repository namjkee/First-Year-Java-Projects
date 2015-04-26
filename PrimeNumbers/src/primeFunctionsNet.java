import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class primeFunctionsNet {
	
	public static ArrayList<String> pageExtract() throws IOException {
		ArrayList<String> page = new ArrayList<String>();
		
		// make a URL (Uniform Resource Locator), a pointer which points to the web address
		URL url = new URL("http://primes.utm.edu/lists/small/10000.txt");
		
		// Get the input stream through URL connection
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		
		// Once you have the input stream, it's just plain old Java IO stuff
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		int counter = 0;
		while ((line = br.readLine()) != null) {
			if (counter < 4) {						// first four lines of url are irrelevant - remove them
				counter++;
				continue;
			}
			else {
				page.add(line);
			}
		}
		return page;
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> page = pageExtract();
		for (int i = 0; i < page.size(); i++) {
			String b = page.get(i).trim();
			System.out.println(b);
		}
	}
}
