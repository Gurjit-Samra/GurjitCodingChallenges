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
    
    @FXML
    private Slider quizSlider;

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoice;

    @FXML
    void calculateGrade(ActionEvent event) {
    	double courseGrade = 0.0;
    	
    	String projectGrade = projectGradeTextField.getText();
  
    	courseGrade = courseGrade + Double.parseDouble(projectGrade) * 0.5;
    	System.out.println("hello world");
    	System.out.println("Project Grade entered: " + projectGrade + 
    			" Course grade so far: " + courseGrade);
    	
    	int requiredChallengesCompleted = requiredCodingChallengesChoice.getValue();
    	int optionalChallengesCompleted = optionalCodingChallengesChoice.getValue();
    	
    	if (requiredChallengesCompleted < 15){
    		courseGrade += 0;
    		System.out.println("Required Challenges Completed: " + requiredChallengesCompleted + 
        		"/15" + " Course grade so far: " + courseGrade);
    	}
    	else {
    		System.out.println(optionalChallengesCompleted);
    		courseGrade += (((requiredChallengesCompleted + optionalChallengesCompleted)* 100 ) / 20)*0.25;
    		System.out.println("Required Challenges Completed: " + requiredChallengesCompleted + 
        		"/15" + " Optional Challenges Completed: " + optionalChallengesCompleted + "/5" + " Course grade so far: " + courseGrade);
    	}
    	
    	double quizGrade = quizSlider.getValue();
    	
    	courseGrade += (quizGrade * 10) * 0.25;
    	
    	System.out.println("Quiz Grade entered: " + quizGrade + 
    			" Course grade so far: " + courseGrade);

    	// Display result of grade calculation to the user
    	courseGradeLabel.setText(String.format("Your Overall Course Grade is: %.2f", courseGrade));
    }

}
