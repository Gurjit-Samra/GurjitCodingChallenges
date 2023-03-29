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
 * Creates an instance of a flight
 * @param departureDate
 * @param arrivalDate
 */
public Flight(Date departureDate, Date arrivalDate) {
	boolean bothDatesValid = true;
	if ((departureDate == null || arrivalDate == null) || (departureDate==null && arrivalDate==null)){
		bothDatesValid = false;
		setDeparture(departureDate);
		setArrival(arrivalDate);
	}
	if(bothDatesValid) {
		if (departureDate.before(arrivalDate)) {
			setDeparture(departureDate);
			setArrival(arrivalDate);
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
	boolean bothDatesValid = true;
	
	if ((departureDate == null || arrival == null) || (departureDate==null && arrival==null)){
		this.departure = departureDate;
		bothDatesValid = false;
	}
	
	if(bothDatesValid) {
		if (departureDate.before(arrival)) {
			departure = departureDate;
		}
	}
}

public Date getArrival() {
	return arrival;
}

public void setArrival(Date arrivalDate) {
	boolean bothDatesValid = true;
	
	if ((departure == null || arrivalDate == null) || (departure==null && arrivalDate==null)){
		this.arrival = arrivalDate;
		bothDatesValid = false;
	}
	
	if(bothDatesValid) {
		if (this.departure.before(arrivalDate)) {
			arrival = arrivalDate;
		}
	}
}


}
