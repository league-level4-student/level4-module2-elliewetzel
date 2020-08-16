package StringMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		//return null;
		if(s1.length() > s2.length()) {
			return s1;
		}
		else {
			return s2;
		}
	}

	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		//return null;
		String under = "";
		if(s.contains("underscores")) {
			under = s.replace(" ", "_");
		}
		else {
			under = s;
		}
		return under;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		String[] p1 = s1.split(" ");
		String[] p2 = s2.split(" ");
		String[] p3 = s3.split(" ");
		
		if(p1[1].compareTo(p2[1]) < 0) {
			if(p1[1].compareTo(p3[1]) < 0) {
				return s1;
			}
		}
		
		if(p2[1].compareTo(p1[1]) < 0) {
			if(p2[1].compareTo(p3[1]) < 0) {
				return s2;
			}
		}
		
		if(p3[1].compareTo(p1[1]) < 0) {
			if(p3[1].compareTo(p2[1]) < 0) {
				return s3;
			}
		}
		return null;
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				sum+=Character.getNumericValue(s.charAt(i));
			}
		}
		return sum;
	}
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		if(s.contains(substring)) {
			int counter = 0;
			int fromIndex = 0;
			
			while((fromIndex = s.indexOf(substring, fromIndex)) != -1) {
				counter++;
				fromIndex++;
			}
			return counter;
		}
		return 0;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		byte[] bytes = s.getBytes();
		return Utilities.encrypt(bytes, (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key) ;
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		String[] str = s.split(" ");
		int counter = 0;
		for(int i = 0; i < str.length; i++) {
			if(str[i].endsWith(substring)) {
				counter++;
			}
			
		}
		
		return counter;
	}
	
	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		if(s.contains(substring)) {
			int fromIndex = 0;
			ArrayList<Integer> fromIndexes = new ArrayList<Integer>();
			while((fromIndex = s.indexOf(substring, fromIndex)) != -1) {
				fromIndex++;
				fromIndexes.add(fromIndex);
			}
			int difference = fromIndexes.get(fromIndexes.size()-1) - fromIndexes.get(0) - substring.length();
		
		return difference;
		}
		return 0;
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String backwards = "";
		String forwards = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ':' && s.charAt(i) != '?' && s.charAt(i) != ',' && s.charAt(i) != '.' && s.charAt(i) != ' ' && s.charAt(i) != '–') {
				
				forwards += s.charAt(i);
			}
		}
		forwards = forwards.toLowerCase();

		for (int i = forwards.length() - 1; i >= 0; i--) {
			backwards += forwards.charAt(i);
		}

		if (backwards.equals(forwards)) {
			return true;
		}
		return false;
	}

}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
