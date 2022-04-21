package application;

import javafx.fxml.FXML;
import javafx.geometry.HPos;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class AssignmentCreationLayoutController {
	
	private static ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	private String assignmentName = "";
	private String assignmentDesc = "";
	private Calendar dueDate;
	
	@FXML
	private TextField assignmentNameID;
	@FXML
	private TextArea assignmentDescID;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ComboBox<String> hourDropDown;
	@FXML
	private ComboBox<String> minuteDropDown;
	@FXML
	private ComboBox<String> amPmDropDown;
	
	public void initialize() {
		ObservableList<String> hourList = FXCollections.observableArrayList();
		hourList.add("Hr:");
		for(int hr = 1; hr <= 12; hr++) {
			String tempStr = String.format("%02d", hr);
			hourList.add(tempStr);
		}
    	hourDropDown.setItems(hourList);
    	hourDropDown.setValue("Hr:");
    	
    	ObservableList<String> minuteList = FXCollections.observableArrayList();
    	minuteList.add("Min:");
    	for(int min = 0; min <= 59; min++) {
			String tempStr = String.format("%02d", min) + "";
			minuteList.add(tempStr);
		}
    	minuteDropDown.setItems(minuteList);
    	minuteDropDown.setValue("Min:");
    	
    	ObservableList<String> amPmList = FXCollections.observableArrayList();
		amPmList.addAll("AM", "PM");
    	amPmDropDown.setItems(amPmList);
    	amPmDropDown.setValue("AM");
	}
	@FXML
	public void switchToMainScene() {
		MewTwoLayoutController.updateComboBox();
		Main.switchScene(0);
	}
	@SuppressWarnings("deprecation")
	public void initiateAssignment() {//(int month, int day, int year, int hour, int minute, String desc, String name)
		if(!assignmentNameID.getText().equalsIgnoreCase(assignmentName)) {
			assignmentName = assignmentNameID.getText();
		}
		if(!assignmentDescID.getText().equalsIgnoreCase(assignmentDesc)) {
			assignmentDesc = assignmentDescID.getText();
		}
		
		dueDate = Calendar.getInstance();
		dueDate.clear();
		LocalDate date = datePicker.getValue();
		int tempHour = Integer.valueOf(hourDropDown.getValue());
		if(amPmDropDown.getValue().equalsIgnoreCase("PM")) {
			tempHour += 12;
		}
		int tempMin = Integer.valueOf(minuteDropDown.getValue());
		dueDate.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), tempHour, tempMin);
		
		System.out.println(dueDate.getTime().toString());
		
		int tempMonth, tempDay, tempYear;
		tempMonth = dueDate.getTime().getMonth();
		tempDay = dueDate.getTime().getDay();
		tempYear = dueDate.getTime().getYear();
		
		Assignment tempAssign = new Assignment(tempMonth, tempDay, tempYear, tempHour, tempMin, assignmentDesc, assignmentName);
		assignments.add(tempAssign);
		
		resetScene();
	}
	public void resetScene() {
		assignmentNameID.clear();
		assignmentDescID.clear();
		hourDropDown.setValue("Hr:");
		minuteDropDown.setValue("Min:");
		amPmDropDown.setValue("AM");
		datePicker.setValue(null);
	}
	
	public static ArrayList<Assignment> getAssignments(){
		return assignments;
	}
}