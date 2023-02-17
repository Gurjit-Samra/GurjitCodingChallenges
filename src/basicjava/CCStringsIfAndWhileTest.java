package basicjava;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CCStringsIfAndWhileTest {
	@Test
	public void test_isDigit_0() {
		boolean expected = true;
		boolean actual = CCStringsIfAndWhile.isDigit('0');
		
		assertEquals("Checking if 0 is a digit", expected, actual);
	}

	@Test
	public void test_isDigit_1() {
		boolean expected = true;
		boolean actual = CCStringsIfAndWhile.isDigit('1');
		
		assertEquals("Checking if 1 is a digit", expected, actual);
	}

	@Test
	public void test_isDigit_9() {
		boolean expected = true;
		boolean actual = CCStringsIfAndWhile.isDigit('9');
		
		assertEquals("Checking if 9 is a digit", expected, actual);
	}

	@Test
	public void test_isDigit_$() {
		boolean expected = false;
		boolean actual = CCStringsIfAndWhile.isDigit('$');
		
		assertEquals("Checking if # is a digit", expected, actual);
	}

	@Test
	public void test_isDigit_a() {
		boolean expected = false;
		boolean actual = CCStringsIfAndWhile.isDigit('a');
		
		assertEquals("Checking if a is a digit", expected, actual);
	}

	@Test
	public void test_count_bothEmptyString() {
		int expected = 0;
		int actual = CCStringsIfAndWhile.count("", "");
		
		assertEquals("Testing count - both empty string", expected, actual);
	}

	@Test
	public void test_count_firstEmptyString() {
		int expected = 0;
		int actual = CCStringsIfAndWhile.count("", "abcdefghijklmnopqrstuvwxyz");
		
		assertEquals("Testing count - first is empty string", expected, actual);
	}

	@Test
	public void test_count_secondEmptyString() {
		int expected = 0;
		int actual = CCStringsIfAndWhile.count("This is a test", "");
		
		assertEquals("Testing count - second is empty string", expected, actual);
	}

	@Test
	public void test_count_one() {
		int expected = 1;
		int actual = CCStringsIfAndWhile.count("This is a test", "abc");
		
		assertEquals("Testing count - 'This is a test', 'abc'", expected, actual);
	}

	@Test
	public void test_count_many() {
		int expected = 6;
		int actual = CCStringsIfAndWhile.count("This is a test", "sapqi");
		
		assertEquals("Testing count - 'This is a test', 'sapqi'", expected, actual);
	}

	@Test
	public void test_count_upperAndLowerCase() {
		int expected = 7;
		int actual = CCStringsIfAndWhile.count("This is another test", "stpq");
		
		assertEquals("Testing count - 'This is another test', 'stpq'", expected, actual);
	}
	@Test
	public void test_smallestDigit_firstIsSmallest() {
		int expected = 1;
		int actual = CCStringsIfAndWhile.smallestDigit(12345);
		
		assertEquals("testing 12345", expected, actual);
	}
	
	@Test
	public void test_smallestDigit_0() {
		int expected = 0;
		int actual = CCStringsIfAndWhile.smallestDigit(0);
		
		assertEquals("testing 0", expected, actual);
	}

	@Test
	public void test_smallestDigit_smallestLast() {
		int expected = 3;
		int actual = CCStringsIfAndWhile.smallestDigit(876543);
		
		assertEquals("testing 876543", expected, actual);
	}
	
	@Test
	public void test_smallestDigit_smallestRepeated() {
		int expected = 2;
		int actual = CCStringsIfAndWhile.smallestDigit(57232923);
		
		assertEquals("testing 57232923", expected, actual);
	}

	@Test
	public void test_smallestDigit_negativeNumber() {
		int expected = 3;
		int actual = CCStringsIfAndWhile.smallestDigit(-7345);
		
		assertEquals("testing -7345", expected, actual);
	}


}
