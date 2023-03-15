package application;

public class Grade {
	//attributes/data
	double value;
	double weight;
	int maxValue = 100;
	
	Grade(double gradeValue, int maxGradeValue, double weightTowardsCourseGrade){
		value = gradeValue;
		maxValue = maxGradeValue;
		weight = weightTowardsCourseGrade; 
	}
	
	
	double getWeightedPercentageValue() {
		return value * 100 * weight / maxValue;
	}
	
	//actions
	String setValue(String valueAsString) {
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
	}
	
}
