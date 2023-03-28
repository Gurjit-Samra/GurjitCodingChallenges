package oosequence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Itinerary {

private ArrayList<Flight> flights;
private String name;

public Itinerary(String nameEntered) {
	name = nameEntered;
	flights = new ArrayList<Flight>(0);
}

public void addFlight(Flight newFlight) {
	
	boolean flightAdded = false;
	if(flights.isEmpty()) {
		flights.add(newFlight);
	}else{
		int index = 0;
		while((index < flights.size()) && (!flightAdded)) {
			boolean lastIndex = false;
			if(index == (flights.size() - 1)) {
				lastIndex = true;
			}
			if(!flightAdded) {
				if(newFlight.getDeparture().before(flights.get(index).getDeparture())) {
					flights.add(index, newFlight);
					flightAdded = true;
				}else {
					if (lastIndex) {
						flights.add(newFlight);
						flightAdded = true;
					}else {index++;}
				}
			}
		}
	}
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
