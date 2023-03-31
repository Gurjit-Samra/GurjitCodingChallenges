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
	double totalCodingChallengesCompleted = 0.0;

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

    @FXML
    /**
     * Calculate's an overall grade with project, coding challenges, and quizzes having a weight of
     * 50%, 20% and 30% respectively.
     * @param event, the event is when 'Calculate Grade' button is clicked.
     * @return the overall course grade into the courseGradeLabel
     */
    
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText("");
    	double courseGrade = 0.0;
    	
 
    	
    	try {
    		Grade projectGrade = new Grade(projectGradeTextField.getText(), 100, .5);
    		courseGrade += projectGrade.getWeightedPercentageValue();
        	System.out.println("Project Grade entered: " + projectGrade.value + 
        			" Course grade so far: " + courseGrade);
    	}catch(InvalidGradeException ige){
    		projectErrorLabel.setText(ige.getMessage());
    		Grade projectGrade = new Grade(0, 100, .5);
    		courseGrade += projectGrade.getWeightedPercentageValue();
    	}
    	
    	try {
    		requiredCodingChallengesChoice.getValue();
    		totalCodingChallengesCompleted += requiredCodingChallengesChoice.getValue();
    	}catch (NullPointerException npe) {};
    	
    	try {
    		optionalCodingChallengesChoice.getValue();
    		totalCodingChallengesCompleted += optionalCodingChallengesChoice.getValue();
    	}catch (NullPointerException npe) {};
    	
    	Grade requiredQuizzesGrade = new Grade(averageOfRequiredQuizGrade, 100, 0.1875);
    	Grade optionalQuizzesGrade = new Grade(averageOfOptionalQuizGrade, 100, 0.0625);
    	Grade codingChallengeGrade = new Grade(totalCodingChallengesCompleted, 20, 0.25);
    	
    	courseGrade += 
    			//projectGrade.getWeightedPercentageValue() 
    				  codingChallengeGrade.getWeightedPercentageValue() +
    				  (requiredQuizzesGrade.getWeightedPercentageValue() + 
    				   optionalQuizzesGrade.getWeightedPercentageValue()
    				  );

    	// Display result of grade calculation to the user
    	courseGradeLabel.setText(String.format("Your Overall Course Grade is: %.2f", courseGrade));
    }
    private Label quizGradeErrorLabel;
    
    void calculateAvgRequiredQuizGrade(Scene mainScene, ArrayList<TextField> requiredQuizTextFields) {
    	boolean noErrors = true;
    	averageOfRequiredQuizGrade = 0.0;
    	double weightPerQuiz = 1.0 / 15 ;
    	
    	for(TextField textfield : requiredQuizTextFields) {
    		
    		try {
        		Grade quizGrade = new Grade(textfield.getText(), 10, weightPerQuiz);
        		averageOfRequiredQuizGrade += quizGrade.getWeightedPercentageValue();
        	}catch(InvalidGradeException ige){
    			noErrors = false;
    			System.out.println(noErrors);
        		quizGradeErrorLabel.setText(ige.getMessage());
        	}
    	}
 
    	//(requiredQuizGrade + optionalQuizGrade)/ totalQuizzesComplete;
    	if(noErrors) {
    		double tempHolder = (averageOfRequiredQuizGrade/10);
    		avgOfRequiredQuizzes.setText(String.format("Avg of required quizzes: %.2f /10", tempHolder));
    		applicationStage.setScene(mainScene);
    	}
    }
    
    private Label optionalQuizGradeErrorLabel;
    
    void calculateAvgOptionalQuizGrade(Scene mainScene, ArrayList<TextField> optionalQuizTextFields) {
    	boolean noErrors = true;
    	double weightPerQuiz = 1.0 / 5 ;
    	averageOfOptionalQuizGrade = 0.0;
    
    	
    	//copying double values of textfields in ArrayList of optionalQuizTextFields into a new ArrayList<double>
    	ArrayList<Double> optionalQuizDoubles = new ArrayList<Double>();

    	
    		for(TextField textfield : optionalQuizTextFields) {
    			
    			try {
    				Grade quizGrade = new Grade(textfield.getText(), 10, weightPerQuiz);
    				optionalQuizDoubles.add(quizGrade.value);
    			}catch(InvalidGradeException ige) {
        			noErrors = false;
    				optionalQuizGradeErrorLabel.setText(ige.getMessage());
    			}
       		}
    	
    	
       	if (optionalQuizTextFields.size() < 6) {
       		for(Double grade : optionalQuizDoubles) {
       			Grade quizGrade = new Grade(grade, 10, weightPerQuiz);
       			averageOfOptionalQuizGrade += quizGrade.getWeightedPercentageValue();
            	}
       	}else if (optionalQuizDoubles.size() == 6){
    		optionalQuizDoubles.remove(Collections.min(optionalQuizDoubles));
    		for(Double grade : optionalQuizDoubles) {
       			Grade quizGrade = new Grade(grade, 10, weightPerQuiz);
       			averageOfOptionalQuizGrade += quizGrade.getWeightedPercentageValue();
            	}
    	}else if(optionalQuizDoubles.size() > 6) {
        	optionalQuizDoubles.remove(Collections.min(optionalQuizDoubles));
        	optionalQuizDoubles.remove(Collections.min(optionalQuizDoubles));
        	for(Double grade : optionalQuizDoubles) {
       			Grade quizGrade = new Grade(grade, 10, weightPerQuiz);
       			averageOfOptionalQuizGrade += quizGrade.getWeightedPercentageValue();
            	}
        }
       	
    //	if (numOptionalQuizzes != 0) {
    	//	averageOfOptionalQuizGrade = optionalQuizGrade / numOptionalQuizzes;
    //	}
    	
    	if(noErrors) {
        	avgOfOptionalQuizzes.setText("Avg of optional quizzes: " + Double.toString(averageOfOptionalQuizGrade/10) + " /10");
        	applicationStage.setScene(mainScene);
    	}
    }
    
	@FXML
	void getQuizGrades(ActionEvent enterQuizGradesEvent) {
		Scene mainScene = applicationStage.getScene();
		
		int numberOfRequiredQuizzes = requiredQuizzesChoiceBox.getValue();
		int rowCounter = 0;
		VBox allRows = new VBox();
		ArrayList<TextField> requiredQuizTextFields = new ArrayList<TextField>();
		Label actionMessage = new Label("Entering Required Quiz Grades out of 10");
		
		if(numberOfRequiredQuizzes != 0) {
			allRows.getChildren().add(actionMessage);
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
		quizGradeErrorLabel = new Label();
		allRows.getChildren().add(quizGradeErrorLabel);
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
		optionalQuizGradeErrorLabel = new Label();
		allRows.getChildren().add(optionalQuizGradeErrorLabel);
		Scene quizScene = new Scene(allRows);
		applicationStage.setScene(quizScene);
	}
	
}
