package basicjava;

import static org.junit.Assert.*;

import org.junit.Test;

public class CCArraysTest {

	@Test
	public void test_replace_emptyArray() {
		String str = new String();
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'a', 'b');
		assertEquals("An empty list should be unchanged and still have expected length of 0.", 0, chars.length);
	}

	@Test
	public void test_replace_onlyCharacterShouldBeReplaced() {
		String str = "a";
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'a', 'b');
		assertEquals("Length array ['a'] should be unchanged.", 1, chars.length);
		assertEquals("Array ['a'] should have first element changed to 'b'.", 'b', chars[0]);
	}

	@Test
	public void test_replace_onlyCharacterDifferentCaseShouldBeReplaced() {
		String str = "a";
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'A', 'b');
		assertEquals("Length array ['a'] should be unchanged.", 1, chars.length);
		assertEquals("Array ['a'] should have first element changed to 'b'.", 'b', chars[0]);
	}
	
	@Test
	public void test_replace_onlyCharacterShouldNotBeReplaced() {
		String str = "a";
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'c', 'b');
		assertEquals("Length array ['a'] should be unchanged.", 1, chars.length);
		assertEquals("Array ['a'] should not be changed.", 'a', chars[0]);
	}

	@Test
	public void test_replace_allCharactersShouldBeReplaced() {
		String str = "aaaaaaa";
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'a', 'b');
		assertEquals("Length array ['a','a','a','a','a','a','a'] should be unchanged.", 7, chars.length);
		assertEquals("Array ['a','a','a','a','a','a','a'] should have first element changed to 'b'.", 'b', chars[0]);
		assertEquals("Array ['a','a','a','a','a','a','a'] should have second element changed to 'b'.", 'b', chars[1]);
		assertEquals("Array ['a','a','a','a','a','a','a'] should have third element changed to 'b'.", 'b', chars[2]);
		assertEquals("Array ['a','a','a','a','a','a','a'] should have fourth element changed to 'b'.", 'b', chars[3]);
		assertEquals("Array ['a','a','a','a','a','a','a'] should have fifth element changed to 'b'.", 'b', chars[4]);
		assertEquals("Array ['a','a','a','a','a','a','a'] should have sixth element changed to 'b'.", 'b', chars[5]);
		assertEquals("Array ['a','a','a','a','a','a','a'] should have seventh element changed to 'b'.", 'b', chars[6]);
	}

	@Test
	public void test_replace_allCharacterDifferentCasesShouldBeReplaced() {
		String str = "aAaAaAa";
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'A', 'b');
		assertEquals("Length array ['a','A','a','A','a','A','a'] should be unchanged.", 7, chars.length);
		assertEquals("Array ['a','A','a','A','a','A','a'] should have first element changed to 'b'.", 'b', chars[0]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should have second element changed to 'b'.", 'b', chars[1]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should have third element changed to 'b'.", 'b', chars[2]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should have fourth element changed to 'b'.", 'b', chars[3]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should have fifth element changed to 'b'.", 'b', chars[4]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should have sixth element changed to 'b'.", 'b', chars[5]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should have seventh element changed to 'b'.", 'b', chars[6]);
	}
	
	@Test
	public void test_replace_noCharactersShouldBeReplaced() {
		String str = "aAaAaAa";
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'c', 'b');
		assertEquals("Length array ['a','A','a','A','a','A','a'] should be unchanged.", 7, chars.length);
		assertEquals("Array ['a','A','a','A','a','A','a'] should not changed first character.", 'a', chars[0]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should not changed second character.", 'A', chars[1]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should not changed third character.", 'a', chars[2]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should not changed fourth character.", 'A', chars[3]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should not changed fifth character.", 'a', chars[4]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should not changed fixth character.", 'A', chars[5]);
		assertEquals("Array ['a','A','a','A','a','A','a'] should not changed seventh character.", 'a', chars[6]);
	}

	@Test
	public void test_replace_someCharactersShouldBeReplaced() {
		String str = "aAbBcCdDAa";
		char[] chars = str.toCharArray();
		CCArrays.replace(chars, 'a', 'b');
		assertEquals("Length array ['a','A','b','B','c','C','d','D','A','a'] should be unchanged.", 10, chars.length);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should change first character.", 'b', chars[0]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should change second character.", 'b', chars[1]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should not changed third character.", 'b', chars[2]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should not changed fourth character.", 'B', chars[3]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should not changed fifth character.", 'c', chars[4]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should not changed fixth character.", 'C', chars[5]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should not changed seventh character.", 'd', chars[6]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should not changed eigth character.", 'D', chars[7]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should changed nineth character.", 'b', chars[8]);
		assertEquals("Array ['a','A','b','B','c','C','d','D','A','a'] should changed tenthth character.", 'b', chars[9]);
	}
	
	@Test
	public void test_sortAlphabetic_emptyList() {
		String[] strs = new String[0];
		CCArrays.sortAlphabetic(strs);
		assertEquals("Sorting an empty list should leave the list unchanged with length 0.", 0, strs.length);
	}

	@Test
	public void test_sortAlphabetic_sortedList() {
		String[] strs = {"abc", "def", "ghi","jkl","mno", "pqr","stu","vwxyz"};
		CCArrays.sortAlphabetic(strs);
		assertEquals("Sorting a list should leave length unchanged.", 8, strs.length);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "abc", strs[0]);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "def", strs[1]);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "ghi", strs[2]);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "jkl", strs[3]);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "mno", strs[4]);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "pqr", strs[5]);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "stu", strs[6]);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "vwxyz", strs[7]);
	}

	@Test
	public void test_sortAlphabetic_sortedListMixedCase() {
		String[] strs = {"abc", "Def", "ghi","Jkl","Mno", "Pqr","Stu","vwxyz"};
		CCArrays.sortAlphabetic(strs);
		assertEquals("Sorting a list should leave length unchanged.", 8, strs.length);
		assertEquals("Sorting a list that is already in sorted order, should leave first element unchanged.", "abc", strs[0]);
		assertEquals("Sorting a list that is already in sorted order, should leave second element unchanged.", "Def", strs[1]);
		assertEquals("Sorting a list that is already in sorted order, should leave third element unchanged.", "ghi", strs[2]);
		assertEquals("Sorting a list that is already in sorted order, should leave fourth element unchanged.", "Jkl", strs[3]);
		assertEquals("Sorting a list that is already in sorted order, should leave fifth element unchanged.", "Mno", strs[4]);
		assertEquals("Sorting a list that is already in sorted order, should leave sixth element unchanged.", "Pqr", strs[5]);
		assertEquals("Sorting a list that is already in sorted order, should leave sevent element unchanged.", "Stu", strs[6]);
		assertEquals("Sorting a list that is already in sorted order, should leave eight element unchanged.", "vwxyz", strs[7]);
	}

	@Test
	public void test_sortAlphabetic_unSortedListMixedCase() {
		String[] strs = {"Mno", "Def","abc", "ghi", "Pqr","Stu","vwxyz", "Jkl"};
		CCArrays.sortAlphabetic(strs);
		assertEquals("Sorting a list should leave length unchanged.", 8, strs.length);
		assertEquals("Sorting an unsorted list, testing first element.", "abc", strs[0]);
		assertEquals("Sorting an unsorted list, testing second element.", "Def", strs[1]);
		assertEquals("Sorting an unsorted list, testing third element.", "ghi", strs[2]);
		assertEquals("Sorting an unsorted list, testing fourth element.", "Jkl", strs[3]);
		assertEquals("Sorting an unsorted list, testing fifth element.", "Mno", strs[4]);
		assertEquals("Sorting an unsorted list, testing sixth element.", "Pqr", strs[5]);
		assertEquals("Sorting an unsorted list, testing seventh element.", "Stu", strs[6]);
		assertEquals("Sorting an unsorted list, testing eighth element.", "vwxyz", strs[7]);
	}

}
