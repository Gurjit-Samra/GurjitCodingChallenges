package oosequence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Calendar;

import org.junit.Test;

public class FlightTest {
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
	// Testing constructors
	
		@Test
		public void testConstructor_departureBeforeArrival(){
			Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
			Date expectedDeparture = getDate(2018,11,28,10,20);
			Date expectedArrival = getDate(2018,11,28,10,21);
			assertEquals("Departure one minute before arrival, testing departure", expectedDeparture, c.departure);
			assertEquals("Departure one minute before arrival, testing arrival", expectedArrival, c.arrival);
		}

		@Test
		public void testConstructor_departureSameAsArrival(){
			Flight c = new Flight(getDate(2018,11,27,10,20), getDate(2018,11,27,10,20));
			//Date expectedDeparture = getDate(2018,11,27,10,20);
			//Date expectedArrival = null;
			assertNull("testing departure (expecting null)", c.departure);
			assertNull("testing arrival (expecting null)", c.arrival);
		}

		@Test
		public void testConstructor_departureAfterArrival(){
			Flight c = new Flight(getDate(2018,11,28,10,21), getDate(2018,11,28,10,20));
			//Date expectedDeparture = getDate(2018,11,28,10,21);
			//Date expectedArrival = null;
			assertNull("Departure one minute after arrival, testing departure (expecting null)", c.departure);
			assertNull("Departure one minute after arrival, testing arrival (expecting null)", c.arrival);
		}

		@Test
		public void testConstructor_nullDepartureAndArrival(){
			Flight c = new Flight(null,null);
			//Date expectedDeparture = null;
			//Date expectedArrival = null;
			assertNull("testing departure", c.departure);
			assertNull("testing arrival", c.arrival);
		}

		@Test
		public void testCopyConstructor(){
			Flight c = new Flight(getDate(2018,10,28,10,20), getDate(2018,10,29,1,5));
			Flight copy = new Flight(c);
			Date expectedDeparture = getDate(2018,10,28,10,20);
			Date expectedArrival = getDate(2018,10,29,1,5);
			assertEquals("Testing departure", expectedDeparture, copy.departure);
			assertEquals("Testing arrival", expectedArrival, copy.arrival);
		}

		@Test
		public void testCopyConstructor_nullDepartureAndArrival(){
			Flight p = new Flight(null,null);
			Flight p2 = new Flight(p);
			assertNull("testing departure", p2.departure);
			assertNull("testing arrival", p2.arrival);
		}
		@Test
		public void test_length_DepartureOneMinuteBeforeArrival() {
			Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
			long expectedLength = 1;
			assertEquals("Flight is one minute long", expectedLength,c.length());
			
		}
		
		@Test
		public void test_length_DepartureOneHourBeforeArrival() {
			Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
			long expectedLength = 60;
			assertEquals("Flight is one hour long", expectedLength,c.length());
			
		}

		@Test
		public void test_length_DepartureNull() {
			Flight c = new Flight(null, getDate(2018,11,28,11,20));
			long expectedLength = 0;
			assertEquals("Null departure", expectedLength,c.length());
			
		}

		@Test
		public void test_length_ArrivalNull() {
			Flight c = new Flight(getDate(2018,11,28,11,20), null);
			long expectedLength = 0;
			assertEquals("Null arrival", expectedLength,c.length());
			
		}
}
