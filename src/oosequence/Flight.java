package oosequence;

import java.util.Date;
/**
 * 
 * Class representing a flight
 *
 */
public class Flight {	
Date departure;
Date arrival;

/**
 * Javadoc for constructor
 * @param departureDate
 * @param arrivalDate
 */
public Flight(Date departureDate, Date arrivalDate) {
	if ((departureDate == null || arrivalDate == null) || 
			(departureDate==null && arrivalDate==null)){
		departure = departureDate;
		arrival = arrivalDate;
	}else {
		if (departureDate.before(arrivalDate)){
			departure = departureDate;
			arrival = arrivalDate;
		}else {
			departure = null;
			arrival = null;
			System.out.print("Departure must be before arrival");
		}
		
	}
}

public Flight(Flight flightToCopy) {
	departure = flightToCopy.departure;
	arrival = flightToCopy.arrival;
}

long length() {
	
	return 0;
}


}
