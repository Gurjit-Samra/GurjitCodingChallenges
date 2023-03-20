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
    	
    	Grade projectGrade = new Grade(0, 100, .5);
    	
    	projectErrorLabel.setText(projectGrade.setValue(projectGradeTextField.getText()));
    	
    	Grade requiredQuizzesGrade = new Grade(averageOfRequiredQuizGrade, 100, 0.1875);
    	Grade optionalQuizzesGrade = new Grade(averageOfOptionalQuizGrade,10, 0.0625);
    	Grade codingChallengeGrade = new Grade((requiredCodingChallengesChoice.getValue() + optionalCodingChallengesChoice.getValue()),
    			20, 0.25);
    	
    	courseGrade = projectGrade.getWeightedPercentageValue() +
    				  codingChallengeGrade.getWeightedPercentageValue() +
    				  (requiredQuizzesGrade.getWeightedPercentageValue() + 
    				   optionalQuizzesGrade.getWeightedPercentageValue()
    				  );
    	
    	System.out.println("Project Grade entered: " + projectGrade + 
    			" Course grade so far: " + courseGrade);

    	// Display result of grade calculation to the user
    	courseGradeLabel.setText(String.format("Your Overall Course Grade is: %.2f", courseGrade));
    }
    private Label quizGradeErrorLabel;
    
    void calculateAvgRequiredQuizGrade(Scene mainScene, ArrayList<TextField> requiredQuizTextFields) {
    	double requiredQuizGrade = 0.0;
    	boolean noErrors = true;
    	averageOfRequiredQuizGrade = 0.0;
    	double weightPerQuiz = 1.0 / 15 ;
    	
    	for(TextField textfield : requiredQuizTextFields) {
    		Grade quizGrade = new Grade(0, 10,weightPerQuiz);
    		String errorMessage = quizGrade.setValue(textfield.getText());
    		if (!errorMessage.equals("")) {
    			noErrors = false;
    			quizGradeErrorLabel.setText(errorMessage);
    		}
    		averageOfRequiredQuizGrade += quizGrade.getWeightedPercentageValue();
    	}
 
    	//(requiredQuizGrade + optionalQuizGrade)/ totalQuizzesComplete;
    	if(noErrors) {
    		avgOfRequiredQuizzes.setText("Avg of required quizzes: " + Double.toString(averageOfRequiredQuizGrade/10) + " /10");
    		applicationStage.setScene(mainScene);
    	}
    	System.out.print(averageOfRequiredQuizGrade);

    }
    
    void calculateAvgOptionalQuizGrade(Scene mainScene, ArrayList<TextField> optionalQuizTextFields) {
    	double optionalQuizGrade = 0.0;
    	int numOptionalQuizzes = 5;
    	double weightPerQuiz = 1.0 / 5 ;
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
		Scene quizScene = new Scene(allRows);
		applicationStage.setScene(quizScene);
	}
	
}
