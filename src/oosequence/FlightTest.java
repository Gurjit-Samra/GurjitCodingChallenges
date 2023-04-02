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
		assertEquals("Departure one minute before arrival, testing departure", expectedDeparture, c.getDeparture());
		assertEquals("Departure one minute before arrival, testing arrival", expectedArrival, c.getArrival());
	}

	@Test
	public void testConstructor_departureSameAsArrival(){
		Flight c = new Flight(getDate(2018,11,27,10,20), getDate(2018,11,27,10,20));
		//Date expectedDeparture = getDate(2018,11,27,10,20);
		//Date expectedArrival = null;
		assertNull("testing departure (expecting null)", c.getDeparture());
		assertNull("testing arrival (expecting null)", c.getArrival());
	}

	@Test
	public void testConstructor_departureAfterArrival(){
		Flight c = new Flight(getDate(2018,11,28,10,21), getDate(2018,11,28,10,20));
		//Date expectedDeparture = getDate(2018,11,28,10,21);
		//Date expectedArrival = null;
		assertNull("Departure one minute after arrival, testing departure (expecting null)", c.getDeparture());
		assertNull("Departure one minute after arrival, testing arrival (expecting null)", c.getArrival());
	}

	@Test
	public void testConstructor_nullDepartureAndArrival(){
		Flight c = new Flight(null,null);
		//Date expectedDeparture = null;
		//Date expectedArrival = null;
		assertNull("testing departure", c.getDeparture());
		assertNull("testing arrival", c.getArrival());
	}

	@Test
	public void testCopyConstructor(){
		Flight c = new Flight(getDate(2018,10,28,10,20), getDate(2018,10,29,1,5));
		Flight copy = new Flight(c);
		Date expectedDeparture = getDate(2018,10,28,10,20);
		Date expectedArrival = getDate(2018,10,29,1,5);
		assertEquals("Testing departure", expectedDeparture, copy.getDeparture());
		assertEquals("Testing arrival", expectedArrival, copy.getArrival());
	}

	@Test
	public void testCopyConstructor_nullDepartureAndArrival(){
		Flight p = new Flight(null,null);
		Flight p2 = new Flight(p);
		assertNull("testing departure", p2.getDeparture());
		assertNull("testing arrival", p2.getArrival());
	}


	@Test
	public void testConstructor_DepartureEncapsulation(){
		Date argumentDeparture = getDate(2019,11,28,10,25);
		Flight c = new Flight(argumentDeparture,null);

		// Change argument
		argumentDeparture.setTime(getDate(2020,10,20,14,0).getTime());

		// Verify that flight departure date is unchanged.
		Date expectedDeparture = getDate(2019,11,28,10,25);

		assertEquals("Changed argument after creation from 2019:11:20:10:25 to 2020:10:20:14:00, expect Flight departure to remain unchanged.", expectedDeparture, c.getDeparture());
	}

	// Testing setter and getters

	@Test
	public void test_setter_and_getter_departure_NewDepartureBeforeArrival(){
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setDeparture(getDate(2018,11,28,8,0));
		Date expectedDeparture = getDate(2018,11,28,8,0);
		Date expectedArrival = getDate(2018,11,28,10,21);
		assertEquals("Changed departure to 8am from 10:20am, testing departure", expectedDeparture, c.getDeparture());
		assertEquals("Changed departure to 8am from 10:20am, testing arrival", expectedArrival, c.getArrival());			
	}

	@Test
	public void test_setter_and_getter_departure_fromNullDeparture(){
		Flight c = new Flight(null, getDate(2018,11,28,10,21));
		c.setDeparture(getDate(2018,11,28,8,0));
		Date expectedDeparture = getDate(2018,11,28,8,0);
		Date expectedArrival = getDate(2018,11,28,10,21);
		assertEquals("Changed departure from null, testing departure", expectedDeparture, c.getDeparture());
		assertEquals("Changed departure from null, testing arrival", expectedArrival, c.getArrival());			
	}

	@Test
	public void test_setter_and_getter_departure_toLaterThanArrival(){
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setDeparture(getDate(2018,11,29,8,0));
		Date expectedDeparture = getDate(2018,11,28,10,20);
		Date expectedArrival = getDate(2018,11,28,10,21);
		assertEquals("Changed departure to later than arrival, expected unchanged departure, testing departure", expectedDeparture, c.getDeparture());
		assertEquals("Changed departure to later than arrival, testing arrival", expectedArrival, c.getArrival());			
	}

	@Test
	public void test_setter_and_getter_departure_NullArrival(){
		Flight c = new Flight(getDate(2018,11,28,10,20), null);
		c.setDeparture(getDate(2018,11,28,8,0));
		Date expectedDeparture = getDate(2018,11,28,8,0);
		assertEquals("Changed departure to 8am from 10:20am, testing departure", expectedDeparture, c.getDeparture());
		assertNull("Changed departure to 8am from 10:20am, testing arrival", c.getArrival());			
	}

	@Test
	public void test_setter_and_getter_departure_getterEncapsulation(){
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		Date dep = c.getDeparture();
		dep.setTime(getDate(2018,11,28,8,0).getTime());
		Date expectedDeparture = getDate(2018,11,28,10,20);
		assertEquals("Changed departure time returned from getDeparture to 8am from 10:20am, testing that departure in flight unchanged", expectedDeparture, c.getDeparture());
	}

	@Test
	public void test_setter_and_getter_departure_setterEncapsulation(){
		Date dep = getDate(2019,11,28,10,20);
		Flight c = new Flight(dep, getDate(2019,11,28,11,21));

		dep.setTime(getDate(2018,11,28,8,0).getTime());

		Date expectedDeparture = getDate(2019,11,28,10,20);
		assertEquals("Changed departure time provided as argument to setDeparture to 2018 from 2019, testing that departure in flight unchanged", expectedDeparture, c.getDeparture());
	}

	@Test
	public void test_setter_and_getter_arrival_NewArrivalAfterDeparture(){
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setArrival(getDate(2018,11,29,8,0));
		Date expectedDeparture = getDate(2018,11,28,10,20);
		Date expectedArrival = getDate(2018,11,29,8,0);
		assertEquals("Changed arrival to 8am on the 29th from 10:21am on the 28th, testing departure", expectedDeparture, c.getDeparture());
		assertEquals("Changed arrival to 8am on the 29th from 10:21am on the 28th", expectedArrival, c.getArrival());			
	}

	@Test
	public void test_setter_and_getter_arrival_fromNullArrival(){
		Flight c = new Flight(getDate(2018,11,28,10,21), null);
		c.setArrival(getDate(2018,11,29,8,0));
		Date expectedDeparture = getDate(2018,11,28,10,21);
		Date expectedArrival = getDate(2018,11,29,8,0);
		assertEquals("Changed arrival from null, testing departure", expectedDeparture, c.getDeparture());
		assertEquals("Changed arrival from null, testing arrival", expectedArrival, c.getArrival());			
	}

	@Test
	public void test_setter_and_getter_arrival_toEarlierThanDeparture(){
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setArrival(getDate(2018,11,27,8,0));
		Date expectedDeparture = getDate(2018,11,28,10,20);
		Date expectedArrival = getDate(2018,11,28,10,21);
		assertEquals("Changed departure to later than arrival, testing departure", expectedDeparture, c.getDeparture());
		assertEquals("Changed departure to later than arrival, expected unchanged arrival, testing arrival", expectedArrival, c.getArrival());			
	}

	@Test
	public void test_setter_and_getter_arrival_NullDeparture(){
		Flight c = new Flight(null, getDate(2018,11,28,10,20));
		c.setArrival(getDate(2018,11,28,8,0));
		Date expectedArrival = getDate(2018,11,28,8,0);
		assertEquals("Changed arrival to 8am from 10:20am, testing arrival", expectedArrival, c.getArrival());
		assertNull("Changed arrival to 8am from 10:20am, testing departure", c.getDeparture());			
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
