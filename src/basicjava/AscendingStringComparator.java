package basicjava;

import java.util.Comparator;

public class AscendingStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		int compareResult = o1.compareToIgnoreCase(o2); // comparing in ascending order
		
		if(compareResult < 0) { // o1 is before o2 in dictionary
			return -1; // to indicate that should be ordered 
		}else if (compareResult > 0) { // o1 is after o2 in the dictionary
			return 1;
		}else {// compareResult = 0: meaning o1 and o2 are the same
			return 0;
		}
		
	}

}
