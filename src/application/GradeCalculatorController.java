package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GradeCalculatorController {

    @FXML
    private ChoiceBox<Integer> requiredCodingChallengesChoice;

    @FXML
    private TextField projectGradeTextField;
    
    @FXML
    private Label courseGradeLabel;

    @FXML
    private Label projectErrorLabel;
    /**
     * Checks if the valueEntered is a valid project grade. Project grades must be numeric and a 
     * percentage grade (between 0 - 100). if valid, the equivalent double is returned, if not,
     * this method returns 0.
     * @param valueEntered, the value entered for the project grade
     * @return the double value of valueEntered if it is numeric and a valid percentage and 0 otherwise.
     */
    double getProjectGrade(String valueEntered) {
    	// Checking to see if the value entered by user is a valid digit
    	boolean validProjectGrade = true;
    	int decimalCounter = 0;
    	for (char c : valueEntered.toCharArray()) {
    		if(!Character.isDigit(c)) {
    			if (c == '.') {
    				decimalCounter += 1;
    				if(decimalCounter > 1) {
            			validProjectGrade = false;
                		projectErrorLabel.setText("Do not include extra character: "+"'"+c+"'"
                				+" Project Grade should be a %.");
    				}
    			}else {
        			validProjectGrade = false;
            		projectErrorLabel.setText("Do not include character: "+"'"+c+"'"
            				+" Project Grade should be a %.");
    			}
    		}
    	}
    	//Default project grade to 0. if it's valid, convert user entered project grade to a
    	// floating point number.
    	double projectGrade = 0;
    	if (validProjectGrade) {
    		projectGrade = Double.parseDouble(valueEntered);
    	}
    	if (projectGrade < 0 || projectGrade > 100) {
    		projectErrorLabel.setText("Project Grade should be a value between 0% - 100% Invalid project grade: "
    				+ projectGrade);
    		projectGrade = 0;
    	}
		return projectGrade;
    }
    
    @FXML
    private Slider quizSlider;

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoice;

    @FXML
    /**
     * Calculate's an overall grade with project, coding challenges, and quizzes having a weight of
     * 50%, 20% and 30% respectively.
     * @param event, the event is when 'Calculate Grade' button is clicked.
     * @return the overall course grade into the courseGradeLabel
     */
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText(null);
    	double courseGrade = 0.0;
    	
    	String projectValueEntered = projectGradeTextField.getText();
    	
    	// check if user's entered grade is between valid 0-100 threshold. if not, display error message
    	// and exclude from grade calculation
    	// if valid then include in course grade with a weight of 50%
    	double projectGrade = getProjectGrade(projectValueEntered);
    	courseGrade = courseGrade + (projectGrade * 0.5);
    	System.out.println("Project Grade entered: " + projectGrade + 
    			" Course grade so far: " + courseGrade);
    	
    	// assuming that coding challenges are worth 25% towards course grade
    	int requiredChallengesCompleted = requiredCodingChallengesChoice.getValue();
    	int optionalChallengesCompleted = optionalCodingChallengesChoice.getValue();
    	
    	
    	courseGrade += (((requiredChallengesCompleted + optionalChallengesCompleted)* 100) / 20)* (0.25);
    	
    	System.out.println("Required Challenges Completed: " + requiredChallengesCompleted + 
        	"/15" + " Optional Challenges Completed: " + optionalChallengesCompleted + "/5" + 
    		" Course grade so far: " + courseGrade);
 
    	// converting value of quiz grade to a floating point
    	// adding to overall course grade with a weight of 25%
    	double quizGrade = quizSlider.getValue();
    	courseGrade += (quizGrade * 10) * 0.25;
    	System.out.println("Quiz Grade entered: " + quizGrade + 
    			" Course grade so far: " + courseGrade);

    	// Display result of grade calculation to the user
    	courseGradeLabel.setText(String.format("Your Overall Course Grade is: %.2f", courseGrade));
    }

}
