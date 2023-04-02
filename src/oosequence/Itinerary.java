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
	boolean overlappingFlight = false;
	Date copyOfFlightDeparture = (Date) newFlight.getDeparture().clone();
	Date copyOfFlightArrival = (Date) newFlight.getArrival().clone();
	Flight copyOfFlight = new Flight(copyOfFlightDeparture, copyOfFlightArrival);
	
	if(flights.isEmpty()) {
		flights.add(copyOfFlight);
	}else{
		for(Flight f : flights) {
			if ((f.getDeparture().before(newFlight.getDeparture())) 
				&& 
				(newFlight.getDeparture().before(f.getArrival()))) {
				overlappingFlight = true;
			}
		}
		
		int index = 0;
		while((index < flights.size()) && (!flightAdded && !overlappingFlight)) {
			boolean lastIndex = false;
			if(index == (flights.size() - 1)) {
				lastIndex = true;
			}
			if(!flightAdded) {
				if(newFlight.getDeparture().before(flights.get(index).getDeparture())) {
					flights.add(index, copyOfFlight);
					flightAdded = true;
				}else {
					if (lastIndex) {
						flights.add(copyOfFlight);
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
	long totalLayover = 0;
	int index = 0;
	
	
	
	while( index < flights.size() ) {
		boolean lastIndex = false;
		if(index == (flights.size() - 1)) {
			lastIndex = true;
		}
		
		if(!lastIndex) {
			long layoverTimeBetweenCurrentAndNextFlight = (flights.get(index+1).getDeparture()).getTime() - (flights.get(index).getArrival()).getTime();
			totalLayover += layoverTimeBetweenCurrentAndNextFlight/60000;
			index++;
		}else {index++;}
		
	}
	return totalLayover;
}

public ArrayList<Flight> getFlightList() {
		return flights;
}
}
