package oosequence;

import java.util.ArrayList;

public class Itinerary {

private ArrayList<Flight> flights;
private String name;

public Itinerary(String nameEntered) {
	name = nameEntered;
	flights = new ArrayList<Flight>(0);
}

public void addFlight(Flight f) {
	flights.add(f);
}

/**
 * @return the flights
 */
public ArrayList<Flight> getFlights() {
	return flights;
}

/**
 * @return the name
 */
public String getName() {
	return name;
}

public long getTotalLayover() {
	// TODO Auto-generated method stub
	return 0;
}
}
