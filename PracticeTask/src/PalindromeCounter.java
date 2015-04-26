import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class PalindromeCounter {
	
    public static void main(String[] args) {
    		
    	File file = null;
    	Scanner read = null;
    	String word = null;
    	ArrayList <String> palindromes = new ArrayList<String>();
    	
    	try {
    		file = new File(args[0]);
    	}
    	catch (ArrayIndexOutOfBoundsException e) {
    		System.out.println("insufficient arguments given");
    		return;
    	}

    	try {
			read = new Scanner(file);	// May throw exception
		} 
    	catch (FileNotFoundException e) {
			System.out.println("file not found");
			return;
		}
    	
    	try {
	    	while (read.hasNextLine()) {
	    		word = read.next();
	    		if (isValid(word) == true) {
	    			if (isPalindrome(word) == true) {
	    				palindromes.add(word);
	    			}
	    		} else continue;
	    	}
	    	read.close();
    	} 
    	catch (NoSuchElementException e) {
    	}
    	
    	Collections.sort(palindromes);
    	
    	for (String palindrome : palindromes) {
    		System.out.println(palindrome);
    	}
    	
    }
    /**
     * Not Valid if it contains a digit
     * @param word
     * @return
     */
    protected static boolean isValid(String word) {
    	
    	 boolean containDigit = false;
         
         if (word != null && !word.isEmpty()) {
         	for (char c : word.toCharArray()) {
         		if (Character.isDigit(c) == true) {
         			return containDigit;
         		}
         	}
         	containDigit = true; // if above iteration hasn't identified a digit
         }
     	return containDigit;
    }
    /**
     * Checks if it's a palindrome
     * @param word
     * @return
     */
    protected static boolean isPalindrome(String word) {
    	
    	boolean isPalindrome = false;
    	
    	/* Checks if symmetrical characters of a word are equal */
    	
    	for (int i = 0; i < word.length(); i++) {
    		if (word.charAt(i) == word.charAt(word.length() - 1 - i)) {
    			continue;
    		} else return isPalindrome;
    	}
    		
    	/* If iteration is succesful, then the word is a palindrome */
    	isPalindrome = true;
    	return isPalindrome;
    }
    
}
