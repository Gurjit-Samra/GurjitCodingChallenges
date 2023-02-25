package basicjava;

import java.util.ArrayList;

public class CCArrayList {

	public static int indexOfIgnoreCase(ArrayList<String> strs, String string) {
		// TODO Auto-generated method stub
		int indexOfMatchingString = -1;
		int arrayListLength = strs.size();
		
		for (int i = 0; i < arrayListLength; i++) {
			String stringAtIndex = strs.get(i);
			strs.remove(i);
			strs.add(i, stringAtIndex.toLowerCase());
		}
		
		for(int index = 0; index < arrayListLength; index ++) {
			if(strs.contains(string.toLowerCase())) {
				indexOfMatchingString = strs.indexOf(string.toLowerCase());
			}
		}
		return indexOfMatchingString;
	}

	public static void insert(ArrayList<Double> nums, double d, int i) {
		// TODO Auto-generated method stub
	}

}
