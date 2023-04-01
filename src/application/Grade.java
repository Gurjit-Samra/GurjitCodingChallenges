package application;
/**
 * A grade has a value, a maximum possible value (what the grade is out of) and the weight of the grade.
 * @author Gurjit Samra
 *
 */
public class Grade {
	//instance variables
	double value;
	double weight;
	int maxValue = 100;
	/**
	 * Constructor creates instance of a grade
	 * @param gradeValue
	 * @param maxGradeValue
	 * @param weightTowardsCourseGrade
	 */
	public Grade(double gradeValue, int maxGradeValue, double weightTowardsCourseGrade){
		value = gradeValue;
		maxValue = maxGradeValue;
		weight = weightTowardsCourseGrade; 
	}
	
	public Grade(String gradeValue, int maxGradeValue, double weightTowardsCourseGrade) throws InvalidGradeException{
		try {
			value = Double.parseDouble(gradeValue);
			if(value < 0 || value > maxGradeValue){
				value = 0;
	    		throw new InvalidGradeException("Grade should be a value between 0 and " + maxGradeValue + " Invalid grade");
	    		
	    	}
		}catch (NullPointerException npe){
			throw new InvalidGradeException("Please enter a valid numerical value");
		}catch (NumberFormatException nfe) {
			
	    	int decimalCounter = 0;
	    	for (char c : gradeValue.toCharArray()) {
	    		if(!(Character.isDigit(c))) {
	    			if (c == '.') {
	    				decimalCounter += 1;
	    				if(decimalCounter > 1) {
	                		throw new InvalidGradeException("Do not include extra character: " + "'" + c + "'"
	                				+ " Grade should be a number.") ;
	    				}
	    			}else {
	        			throw new InvalidGradeException("Do not include character: " + "'" + c + "'"
	            				+ " Grade should be a number.");
	    			}
	    		}
	    	}
	    }finally {
		maxValue = maxGradeValue;
		weight = weightTowardsCourseGrade; 
		}
	}
	
	
	/**
	 * Gives user the weighted percent value
	 * @return wightedPercentageValue
	 */
	double getWeightedPercentageGrade() {
		return value * 100 * weight / maxValue;
	}
	/**
	 * Checks and sets the value variable to a value given by user
	 * @param valueAsString
	 * @return errorMessage 
	 */
	/*String setValue(String valueAsString) {
		String errorMessage = ""; 
		//Start with assuming no Error
    	// Checking to see if the valueAsString entered by user is a valid double
    	boolean validGrade = true;
    	int decimalCounter = 0;
    	for (char c : valueAsString.toCharArray()) {
    		if(!(Character.isDigit(c))) {
    			if (c == '.') {
    				decimalCounter += 1;
    				if(decimalCounter > 1) {
            			validGrade = false;
                		errorMessage =  "Do not include extra character: " + "'" + c + "'"
                				+ " Grade should be a number." ;
    				}
    			}else {
        			validGrade = false;
            		errorMessage =  "Do not include character: "+"'"+c+"'"
            				+" Grade should be a number.";
    			}
    		}
    	}

    	if (validGrade) {
    		value = Double.parseDouble(valueAsString);
    	}
    	if (value < 0 || value > maxValue) {
    		errorMessage =  String.format("Grade should be a value between 0 and %d. "
    				+ " Invalid grade: %.02f", maxValue, value);
    		value = 0;
    	}
		return errorMessage;
	}*/
	
}
