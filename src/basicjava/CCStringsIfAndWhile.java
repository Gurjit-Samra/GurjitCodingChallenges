package basicjava;

public class CCStringsIfAndWhile {

	public static boolean isDigit(char c) {
		boolean isDigit = false;
		 if(c == '0'){
			 isDigit = true;
		 }else if(c == '1'){
			 isDigit = true;
		 }else if(c == '9'){
			 isDigit = true;
		 }else {
			 isDigit = false;
		 }
		return isDigit;
	}

	public static int count(String string1, String string2) {
		// TODO Auto-generated method stub
		int indexCountString2 = 0;
		int occurences = 0; 
		int lenString2 = string2.length();
		int lenString1 = string1.length();
		while (indexCountString2 < lenString2) {
			String charForCompare = (string2.substring(indexCountString2,
					(indexCountString2 + 1))).toLowerCase();
			int indexCountString1 = 0;
			while(indexCountString1 < lenString1) {
			String charToCompare = (string1.substring(indexCountString1,
					(indexCountString1 + 1))).toLowerCase();
			if(charForCompare.compareTo(charToCompare)== 0) {
				occurences += 1;
				indexCountString1 += 1;
			}else {
				indexCountString1 += 1;
			}
			
			}
			indexCountString2 += 1;
			}

		return occurences;
		}

	public static int smallestDigit(int number) {
		int lowestDigit = 9;
		if(number < 0) {
			number *= -1;
		}else if (number == 0) {
			lowestDigit = 0;
		}
		int currentNumber = number;
		
		while (currentNumber !=0) {
			int currentDigit = currentNumber % 10;
			if (currentDigit < lowestDigit) {
				lowestDigit = currentDigit;
			}
			currentNumber = currentNumber / 10;
		}
		return lowestDigit;
	}

}