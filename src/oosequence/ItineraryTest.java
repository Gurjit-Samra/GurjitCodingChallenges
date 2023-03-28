package oosequence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ItineraryTest {
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
	
	
		@Test
		public void test_ConstructorAndGetter() {
			Itinerary c = new Itinerary("Test Constructor and Getter");
			assertEquals("Testing constructor and getter", "Test Constructor and Getter", c.getName());
		}
		
		@Test
		public void test_addFlight_addingOne() {
			Itinerary c = new Itinerary("Test");
			Flight m = new Flight(getDate(2019,1,1,10,30),getDate(2019,1,1,18,1));
			c.addFlight(m);
			ArrayList<Flight> list = c.getFlights();
			Flight m2 = null;
			
			if (list.size() > 0){
				m2 = list.get(0);
			}
			
			
			assertEquals("Itinerary only has one flight - testing size.", 1, list.size());
			assertEquals("Itinerary only has one flight - testing departure.", getDate(2019,1,1,10,30), m2.getDeparture());
			assertEquals("Itinerary only has one flight - testing arrival.", getDate(2019,1,1,18,1), m2.getArrival());
		}

		@Test
		public void test_addflight_addingManyInOrder() {
			Itinerary c = new Itinerary("Test");
			Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0));
			Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
			Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35));
			Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59));
			Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21));
			Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21));
			c.addFlight(m1);
			c.addFlight(m2);
			c.addFlight(m3);
			c.addFlight(m4);
			c.addFlight(m5);
			c.addFlight(m6);
			
			ArrayList<Flight> list = c.getFlights();
			
			assertEquals("Expected list of size 6 after adding 6 flights", 6, list.size());			
			assertEquals("flight 1 test - testing departure", getDate(2019,1,1,10,0), list.get(0).getDeparture());
			assertEquals("flight 2 test - testing departure", getDate(2019,1,1,11,30), list.get(1).getDeparture());
			assertEquals("flight 3 test - testing departure", getDate(2019,1,1,14,50), list.get(2).getDeparture());
			assertEquals("flight 4 test - testing departure", getDate(2019,1,1,23,30), list.get(3).getDeparture());
			assertEquals("flight 5 test - testing departure", getDate(2019,1,2,4,40), list.get(4).getDeparture());
			assertEquals("flight 6 test - testing departure", getDate(2019,1,2,9,40), list.get(5).getDeparture());
		}

		@Test
		public void test_addflight_addingOverlappingFlight() {
			Itinerary c = new Itinerary("Test");
			Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0));
			Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
			Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35));
			Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59));
			Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21));
			Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21));
			c.addFlight(m1);
			c.addFlight(m2);
			c.addFlight(m3);
			c.addFlight(m4);
			c.addFlight(m5);
			c.addFlight(m6);
			
			Flight overlapping = new Flight(getDate(2019,1,1,15,30), getDate(2019,1,1,20,00));
			c.addFlight(overlapping);
			
			ArrayList<Flight> list = c.getFlights();
			
			assertEquals("Expected list of size 6 after adding 7 flights, with one overlapping flight", 6, list.size());			
			assertEquals("flight 1 test - testing departure", getDate(2019,1,1,10,0), list.get(0).getDeparture());
			assertEquals("flight 2 test - testing departure", getDate(2019,1,1,11,30), list.get(1).getDeparture());
			assertEquals("flight 3 test - testing departure", getDate(2019,1,1,14,50), list.get(2).getDeparture());
			assertEquals("flight 4 test - testing departure", getDate(2019,1,1,23,30), list.get(3).getDeparture());
			assertEquals("flight 5 test - testing departure", getDate(2019,1,2,4,40), list.get(4).getDeparture());
			assertEquals("flight 6 test - testing departure", getDate(2019,1,2,9,40), list.get(5).getDeparture());
		}

		@Test
		public void test_addflight_addingReverseOrder() {
			Itinerary c = new Itinerary("Test");
			Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0));
			Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
			Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35));
			Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59));
			Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21));
			Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21));
			c.addFlight(m6);
			c.addFlight(m5);
			c.addFlight(m4);
			c.addFlight(m3);
			c.addFlight(m2);
			c.addFlight(m1);
			
			ArrayList<Flight> list = c.getFlights();
			
			assertEquals("Expected list of size 6 after adding 6 flights", 6, list.size());			
			assertEquals("flight 1 test - testing departure", getDate(2019,1,1,10,0), list.get(0).getDeparture());
			assertEquals("flight 2 test - testing departure", getDate(2019,1,1,11,30), list.get(1).getDeparture());
			assertEquals("flight 3 test - testing departure", getDate(2019,1,1,14,50), list.get(2).getDeparture());
			assertEquals("flight 4 test - testing departure", getDate(2019,1,1,23,30), list.get(3).getDeparture());
			assertEquals("flight 5 test - testing departure", getDate(2019,1,2,4,40), list.get(4).getDeparture());
			assertEquals("flight 6 test - testing departure", getDate(2019,1,2,9,40), list.get(5).getDeparture());
		}
		
		@Test
		public void test_addflight_addingRandomOrder() {
			Itinerary c = new Itinerary("Test");
			Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0));
			Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
			Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35));
			Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59));
			Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21));
			Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21));
			c.addFlight(m4);
			c.addFlight(m6);
			c.addFlight(m1);
			c.addFlight(m5);
			c.addFlight(m2);
			c.addFlight(m3);
			
			ArrayList<Flight> list = c.getFlights();
			
			assertEquals("Expected list of size 6 after adding 6 flights", 6, list.size());			
			assertEquals("Insertion order: 4,6,1,5,2,3, flight 1 test - testing departure", getDate(2019,1,1,10,0), list.get(0).getDeparture());
			assertEquals("Insertion order: 4,6,1,5,2,3, flight 2 test - testing departure", getDate(2019,1,1,11,30), list.get(1).getDeparture());
			assertEquals("Insertion order: 4,6,1,5,2,3, flight 3 test - testing departure", getDate(2019,1,1,14,50), list.get(2).getDeparture());
			assertEquals("Insertion order: 4,6,1,5,2,3, flight 4 test - testing departure", getDate(2019,1,1,23,30), list.get(3).getDeparture());
			assertEquals("Insertion order: 4,6,1,5,2,3, flight 5 test - testing departure", getDate(2019,1,2,4,40), list.get(4).getDeparture());
			assertEquals("Insertion order: 4,6,1,5,2,3, flight 6 test - testing departure", getDate(2019,1,2,9,40), list.get(5).getDeparture());
		}
		
		@Test
		public void test_totalLayover_OneFlight() {
			Itinerary c = new Itinerary("Test");
			Flight m = new Flight(getDate(2019,1,1,10,30),getDate(2019,1,1,18,1));
			c.addFlight(m);
			
			long expectedLayover = 0;
			long actualLayover = c.getTotalLayover();
			
			assertEquals("Layover in itinerary with one flight is 0", expectedLayover, actualLayover);
		}

		@Test
		public void test_totalLayover_TwoFlights() {
			Itinerary c = new Itinerary("Test");
			Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0));
			Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
			c.addFlight(m1);
			c.addFlight(m2);
			
			long expectedLayover = 30;
			long actualLayover = c.getTotalLayover();
			
			assertEquals("Expected layover with two flights is number of minutes between arrival of first and departure of second", expectedLayover, actualLayover);
		}
		
		@Test
		public void test_totalLayover_ManyFlights() {
			Itinerary c = new Itinerary("Test");
			Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0));
			Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
			Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35));
			Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59));
			Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21));
			Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21));
			c.addFlight(m1);
			c.addFlight(m2);
			c.addFlight(m3);
			c.addFlight(m4);
			c.addFlight(m5);
			c.addFlight(m6);
			
			long expectedLayover = 690;
			long actualLayover = c.getTotalLayover();
			
			assertEquals(expectedLayover, actualLayover);
		}
		
		@Test
		public void test_totalLayover_NoFlights() {
			Itinerary c = new Itinerary("Test");
			
			long expectedLayover = 0;
			long actualLayover = c.getTotalLayover();
			
			assertEquals("Layover in itinerary with no flights is 0", expectedLayover, actualLayover);
		}
}
