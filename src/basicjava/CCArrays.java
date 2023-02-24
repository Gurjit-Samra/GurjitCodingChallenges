package basicjava;

import java.util.Arrays;

public class CCArrays {
	/**
	 * method "replace" with the following parameters replaces the chosen char with a given char.  
	 * @param charArray
	 * @param toReplace
	 * @param replaceWith
	 */
	 public static void replace(char[] charArray, char toReplace, char replaceWith) {
		 int lengthOfArray = charArray.length;
		 String letterToReplace = (Character.toString(toReplace)).toLowerCase();
		 int index = 0;
		 while (index < lengthOfArray) {
			 String currentLetter = (Character.toString(charArray[index])).toLowerCase();
			 if ( currentLetter.compareTo(letterToReplace) == 0) {
				 charArray[index] = replaceWith;
			 }
			 index++;
		 }		 
		 
	 }
	 /**
	  * method sortAlphabetic sorts an Array of string in alphabetical order regardless of case.
	  * @param strArray
	  */
	 public static void sortAlphabetic(String[] strArray) {
		 Arrays.sort(strArray, new AscendingStringComparator());
	 }

}
