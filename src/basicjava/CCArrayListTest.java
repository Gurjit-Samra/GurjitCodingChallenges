package basicjava;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CCArrayListTest {

	@Test
	public void test_indexOfIgnoreCase_AtIndex0() {
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("One");
		strs.add("Two");
		strs.add("Three");
		strs.add("Four");
		strs.add("One");
		int expectedIndex = 0;
		int actualIndex = CCArrayList.indexOfIgnoreCase(strs, "one");
		assertEquals("The String 'one' is at index 0 in list {'One','Two','Three','Four','One'}", expectedIndex, actualIndex);
	}

	@Test
	public void test_indexOfIgnoreCase_AtLastIndex() {
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("Zero");
		strs.add("Two");
		strs.add("Three");
		strs.add("Four");
		strs.add("One");
		int expectedIndex = 4;
		int actualIndex = CCArrayList.indexOfIgnoreCase(strs, "one");
		assertEquals("The String 'one' is at index 4 in list {'Zero','Two','Three','Four','One'}", expectedIndex, actualIndex);
	}

	@Test
	public void test_indexOfIgnoreCase_InMiddle() {
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("SIX");
		strs.add("FOUR");
		strs.add("NINE");
		strs.add("TWO");
		strs.add("SIX");
		strs.add("THREE");
		strs.add("SOMETHING ELSE");
		int expectedIndex = 3;
		int actualIndex = CCArrayList.indexOfIgnoreCase(strs, "Two");
		assertEquals("The String 'Two' is at index 3 in list {'SIX','FOUR','NINE','TWO','SIX', 'THREE', 'SOMETHING ELSE'}", expectedIndex, actualIndex);
	}

	@Test
	public void test_indexOfIgnoreCase_NotInList() {
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("SIX");
		strs.add("FOUR");
		strs.add("NINE");
		strs.add("TWO");
		strs.add("SIX");
		strs.add("THREE");
		strs.add("SOMETHING ELSE");
		int expectedIndex = -1;
		int actualIndex = CCArrayList.indexOfIgnoreCase(strs, "one");
		assertEquals("The String 'one' is not in list {'SIX','FOUR','NINE','TWO','SIX', 'THREE', 'SOMETHING ELSE'}", expectedIndex, actualIndex);
	}

	@Test
	public void test_indexOfIgnoreCase_ListIsEmpty() {
		ArrayList<String> strs = new ArrayList<String>();

		int expectedIndex = -1;
		int actualIndex = CCArrayList.indexOfIgnoreCase(strs, "Two");
		assertEquals("The list is empty", expectedIndex, actualIndex);
	}

	@Test
	public void test_insert_middleOfFullArray() {
		ArrayList<Double> nums = new ArrayList<Double>(5);
		nums.add(1.0);
		nums.add(2.2);
		nums.add(3.5);
		nums.add(7.5);
		nums.add(4.3);
		CCArrayList.insert(nums, 45.1, 3);
 
		assertEquals("Inserted 45.1 at index 3 into {1.0,2.2,3.5,7.5,4.3}, expected size to have grown", 6, nums.size());
		assertEquals("Inserted 45.1 at index 3 into {1.0,2.2,3.5,7.5,4.3}, expected 1.0 at index 0", 1.0, nums.get(0), 0.00001);
		assertEquals("Inserted 45.1 at index 3 into {1.0,2.2,3.5,7.5,4.3}, expected 2.2 at index 1", 2.2, nums.get(1), 0.00001);
		assertEquals("Inserted 45.1 at index 3 into {1.0,2.2,3.5,7.5,4.3}, expected 3.5 at index 2", 3.5, nums.get(2), 0.00001);
		assertEquals("Inserted 45.1 at index 3 into {1.0,2.2,3.5,7.5,4.3}, expected 45.1 at index 3", 45.1, nums.get(3), 0.00001);
		assertEquals("Inserted 45.1 at index 3 into {1.0,2.2,3.5,7.5,4.3}, expected 7.5 at index 4", 7.5, nums.get(4), 0.00001);
		assertEquals("Inserted 45.1 at index 3 into {1.0,2.2,3.5,7.5,4.3}, expected 3.4 at index 5", 4.3, nums.get(5), 0.00001);
	}

	@Test
	public void test_insert_middleOfEmptyArray() {
		ArrayList<Double> nums = new ArrayList<Double>();
		CCArrayList.insert(nums, 45.1, 3);
 
		assertEquals("Inserted 45.1 at index 3 into empty list, expected content to remain unchanged and size to remain unchanged", 0, nums.size());
	}	
}
