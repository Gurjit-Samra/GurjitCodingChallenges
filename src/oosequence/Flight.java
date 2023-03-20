package oosequence;

import java.util.Date;
/**
 * 
 * Class representing a flight
 *
 */
public class Flight {	
private Date departure;
private Date arrival;

/**
 * Javadoc for constructor
 * @param departureDate
 * @param arrivalDate
 */
public Flight(Date departureDate, Date arrivalDate) {
	if ((departureDate == null || arrivalDate == null) || 
			(departureDate==null && arrivalDate==null)){
		setDeparture(departureDate);
		setArrival(arrivalDate);
	}else {
		if (departureDate.before(arrivalDate)){
			setDeparture(departureDate);
			setArrival(arrivalDate);
		}else {
			setDeparture(null);
			setArrival(null);
		}
		
	}
}

public Flight(Flight flightToCopy) {
	setDeparture(flightToCopy.getDeparture());
	setArrival(flightToCopy.getArrival());
}

long length() {
	if(getDeparture() != null && getArrival() != null) {
		return (getArrival().getTime() - getDeparture().getTime()) / 60000;
	}else {
		return 0;
	}
	
}

public Date getDeparture() {
	return departure;
}

public void setDeparture(Date departureDate) {
	if(departureDate != null) {
		if(departureDate.before(this.arrival)) {
			this.departure = departureDate;
		}
	}
}

public Date getArrival() {
	return arrival;
}

public void setArrival(Date arrivalDate) {
	if(arrivalDate != null) {
		if(departure.before(arrivalDate)) {
			this.arrival = arrivalDate;
		}
	}
}


}
