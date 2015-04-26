import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MyProgram {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String[] states = null;
		String[] inputs = null;
		String[] startState = null;
		String[] finalStates = null;
		String[][] DFA;
		ArrayList<String[]> list = new ArrayList<String[]>();	// used to help each row of the transition function table
		
		if (args.length == 0) return;
		
		/* no filename given - use the default minimal DFA */
		if (args.length == 1) {
			states = new String[] {"q0", "q1", "q2", "q3","q4","q5","q6","q7"};
			inputs = new String[] {"0", "1"};
			startState = new String[] {"q0"};
			finalStates = new String[] {"q0", "q1", "q2","q3","q4","q5","q7"};

			// create 2D array representing transition function and set its values
			DFA = new String[states.length][inputs.length];
			DFA[0][0] = "q1";
			DFA[0][1] = "q3";
			DFA[1][0] = "q0";
			DFA[1][1] = "q2";
			DFA[2][0] = "q5";
			DFA[2][1] = "q3";
			DFA[3][0] = "q4";
			DFA[3][1] = "q2";
			DFA[4][0] = "q1";
			DFA[4][1] = "q7";
			DFA[5][0] = "q0";
			DFA[5][1] = "q6";
			DFA[6][0] = "q7";
			DFA[6][1] = "q7";
			DFA[7][0] = "q6";
			DFA[7][1] = "q6";
		}
		
		else {
			/* filename given - use that DFA */	
			BufferedReader br = new BufferedReader(new FileReader(args[1]));
			
			int counter = 0;	// to distinguish information from lines
			try {											
				String line = br.readLine();						
				counter++;
				
				while (line != null) {
					switch(counter) {
					case 1:	// states
						states = line.split(",");
						break;
					case 2: // input symbols
						inputs = line.split(",");
						break;
					case 3: // start state
						startState = line.split(",");
						break;
					case 4: // final states
						finalStates = line.split(",");
						break;
					}
					
					if (counter > 4) {	// transition function values
						String[] temp = line.split(",");
						list.add(temp);
					}
					
					line = br.readLine();
					counter++;
				} 
			} finally {
				br.close();
			}
			
			// create 2D array representing transition function table, rows representing states and columns representing inputs
			DFA = new String[states.length][inputs.length];
			
			// initializing the transition function 2D Array
			for (int i = 0; i < states.length; i++) {
				for (int j = 0; j < inputs.length; j++) {
					DFA[i][j] = list.get(i)[j];
				}
			}
		}
		
		// variables to help trace
		StringBuilder scannedInputs = new StringBuilder(args[0].length() * 2);		// one on the left
		StringBuilder unscannedInputs = new StringBuilder(args[0]);					// one of the right
		
		// Adding whitespaces where appropriate for printing out a clean trace
		for (int i = 0; i < args[0].length() * 2; i++) scannedInputs.append(' ');
		while (unscannedInputs.length() != scannedInputs.length()) unscannedInputs.insert(0, ' ');
		
		// variables to help trace
		String currentState = null;
		boolean started = false;
		
		// k and j are used to help position the insertion or deletion of characters in the scanned and unscanned inputs
		int j = 1;
		int k = args[0].length();		
		
		// Printing the trace
		for (int i = 0; i < args[0].length(); i++) {
			int input = Character.getNumericValue(args[0].charAt(i));
			
			if (!started) {
				started = true;
				
				// find position of the currentState in 'states' array before using it as first parameter in DFA transition function, q0 may not be startState
				currentState = startState[0];
				int stateNumber = Arrays.asList(states).indexOf(currentState);
				
				unscannedInputs.deleteCharAt(k);
				System.out.println(scannedInputs + currentState + " -- " + input + " --> " + DFA[stateNumber][input] + unscannedInputs);
				scannedInputs.replace(i, j++, Integer.toString(input));
				
				// currentState is redefined as the output of the transition function table after the given input
				currentState = DFA[stateNumber][input];
			}
			else {
				// similar as before but now the currentState is already defined
				int stateNumber = Arrays.asList(states).indexOf(currentState);
				
				unscannedInputs.deleteCharAt(k);
				System.out.println(scannedInputs + currentState + " -- " + input + " --> " + DFA[stateNumber][input] + unscannedInputs);
				scannedInputs.replace(i, j++, Integer.toString(input));
				
				// similar as before
				currentState = DFA[stateNumber][input];
			}
			
			// once all inputs are traced, the DFA then determines whether to accept the given string
			if (i == args[0].length() - 1) {
				if (Arrays.asList(finalStates).contains(currentState)) {
					System.out.print("accepted");
				}
				else System.out.print("rejected");
			}
		}		
	}
	
}