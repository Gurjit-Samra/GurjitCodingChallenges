package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeCalculatorController {
	
	Stage applicationStage;
	double averageOfRequiredQuizGrade = 0.0;
	double averageOfOptionalQuizGrade = 0.0;

    @FXML
    private ChoiceBox<Integer> requiredCodingChallengesChoice;
    
    @FXML
    private Label avgOfRequiredQuizzes;
    
    @FXML
    private Label avgOfOptionalQuizzes;
    
    @FXML
    private ChoiceBox<Integer> requiredQuizzesChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> optionalQuizzesChoiceBox;

    @FXML
    private TextField projectGradeTextField;
    
    @FXML
    private Label courseGradeLabel;

    @FXML
    private Label projectErrorLabel;
    
    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoice;
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
    	//double quizGrade = averageQuizGrade;
    	courseGrade += (averageOfRequiredQuizGrade* 10 * 0.1875) + (averageOfOptionalQuizGrade * 10 * 0.0625);
    	System.out.println("Quiz Grade entered: " //+ quizGrade + 
    			+ " Course grade so far: " + courseGrade);

    	// Display result of grade calculation to the user
    	courseGradeLabel.setText(String.format("Your Overall Course Grade is: %.2f", courseGrade));
    }
    
    void calculateAvgRequiredQuizGrade(Scene mainScene, ArrayList<TextField> requiredQuizTextFields) {
    	double requiredQuizGrade = 0.0;
    	averageOfRequiredQuizGrade = 0.0;
    	
    	for(TextField textfield : requiredQuizTextFields) {
        	requiredQuizGrade += Double.parseDouble(textfield.getText());
    	}
    	
    	if (requiredQuizTextFields.size() != 0) {
    		averageOfRequiredQuizGrade = requiredQuizGrade / 15;
    	}
 
    	//(requiredQuizGrade + optionalQuizGrade)/ totalQuizzesComplete;
    	avgOfRequiredQuizzes.setText("Avg of required quizzes: " + Double.toString(averageOfRequiredQuizGrade) + " /10");
    	applicationStage.setScene(mainScene);
    }
    
    void calculateAvgOptionalQuizGrade(Scene mainScene, ArrayList<TextField> optionalQuizTextFields) {
    	double optionalQuizGrade = 0.0;
    	int numOptionalQuizzes = 5;
    	averageOfOptionalQuizGrade = 0.0;
    
    	
    	//copying double values of textfields in ArrayList of optionalQuizTextFields into a new ArrayList<double>
    	ArrayList<Double> optionalQuizDoubles = new ArrayList<Double>();
       	for(TextField textfield : optionalQuizTextFields) {
       		optionalQuizDoubles.add(Double.parseDouble(textfield.getText()));
       	}
       	
       	if (optionalQuizTextFields.size() < 6) {
        	for(TextField textfield : optionalQuizTextFields) {
            	optionalQuizGrade += Double.parseDouble(textfield.getText());
        	}
    	}else if (optionalQuizDoubles.size() == 6){
    		optionalQuizDoubles.remove(Collections.min(optionalQuizDoubles));
    		for(Double grade : optionalQuizDoubles) {
            	optionalQuizGrade += grade;
            	}
    	}else if(optionalQuizDoubles.size() > 6) {
        	optionalQuizDoubles.remove(Collections.min(optionalQuizDoubles));
        	optionalQuizDoubles.remove(Collections.min(optionalQuizDoubles));
        	for(Double grade : optionalQuizDoubles) {
            	optionalQuizGrade += grade;
        	}
        }
       	
    	if (numOptionalQuizzes != 0) {
    		averageOfOptionalQuizGrade = optionalQuizGrade / numOptionalQuizzes;
    	}
    	
    	
    	avgOfOptionalQuizzes.setText("Avg of optional quizzes: " + Double.toString(averageOfOptionalQuizGrade) + " /10");
    	applicationStage.setScene(mainScene);
    }
    
	@FXML
	void getQuizGrades(ActionEvent enterQuizGradesEvent) {
		Scene mainScene = applicationStage.getScene();
		
		int numberOfRequiredQuizzes = requiredQuizzesChoiceBox.getValue();
		int rowCounter = 0;
		VBox allRows = new VBox();
		ArrayList<TextField> requiredQuizTextFields = new ArrayList<TextField>();
		Label requiredQuizLabel = new Label("Entering Required Quiz Grades out of 10");
		
		if(numberOfRequiredQuizzes != 0) {
			allRows.getChildren().add(requiredQuizLabel);
		}
		
		while (rowCounter < numberOfRequiredQuizzes) {
			rowCounter ++;
			HBox quizRow = new HBox();
	
			Label quizLabel = new Label("Quiz " + rowCounter + " grade");
			TextField quizGradeTextField = new TextField();
			Label maxMarksLabel = new Label(" /10");
			requiredQuizTextFields.add(quizGradeTextField);
			
			quizRow.getChildren().addAll(quizLabel,quizGradeTextField, maxMarksLabel);			
			allRows.getChildren().add(quizRow);
			
		}

		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> calculateAvgRequiredQuizGrade(mainScene, requiredQuizTextFields));
		allRows.getChildren().add(doneButton);
		Scene quizScene = new Scene(allRows);
		applicationStage.setScene(quizScene);
		
	}
	
	public void getOptionalQuizGrades(ActionEvent EnterOptionalQuizGrades) {
		Scene mainScene = applicationStage.getScene();
		
		int rowCounter = 0;
		int numberOfOptionalQuizzes = optionalQuizzesChoiceBox.getValue();
		VBox allRows = new VBox();
		ArrayList<TextField> optionalQuizTextFields = new ArrayList<TextField>();
		Label optionalQuizLabel = new Label("Entering Optional Quiz Grades out of 10");
		Label errorLabel = new Label("Please make sure to select more than one optional quiz completed");
		
		
		if(numberOfOptionalQuizzes != 0) {
			allRows.getChildren().add(optionalQuizLabel);
		}else{
			allRows.getChildren().add(errorLabel);
		}
		
		
		while (rowCounter < numberOfOptionalQuizzes) {
			rowCounter ++;
			HBox quizRow = new HBox();
	
			Label quizLabel = new Label("Quiz " + rowCounter + " grade");
			TextField quizGradeTextField = new TextField();
			Label maxMarksLabel = new Label(" /10");
			optionalQuizTextFields.add(quizGradeTextField);
			quizRow.getChildren().addAll(quizLabel,quizGradeTextField,maxMarksLabel );			
			allRows.getChildren().add(quizRow);
			
		}
		
		Button doneButton = new Button("Done");
		doneButton.setOnAction(doneEvent -> calculateAvgOptionalQuizGrade(mainScene, optionalQuizTextFields));
		allRows.getChildren().add(doneButton);
		Scene quizScene = new Scene(allRows);
		applicationStage.setScene(quizScene);
	}
	
}
