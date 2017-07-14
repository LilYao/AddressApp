package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Label streetLabel;

	@FXML
	private Label postalCodeLabel;

	@FXML
	private Label cityLabel;

	@FXML
	private Label birthdayLabel;

	//refer main app

	private MainApp mainApp;

	//constructor, called before the initialize() method

	public PersonOverviewController(){

	}

	//initializes controller class, method is auto after fxml is loaded

	@FXML
	private void initialize(){
		//initialize person table with two columns
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		//clear person details
		showPersonDetails(null);

		//listen for selection changes and show the person details when changed
		personTable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	//is called by the main app to give reference back to itself

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;


		//add observable list data to the table
		personTable.setItems(mainApp.getPersonData());
	}

	//fills all text fields to show details about the person
	//if specified person is null, textfields are cleared
	private void showPersonDetails(Person person){
		if(person != null){
			//fill the labels with info from person object
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));

			//birthdayLabel.setText(...);
		} else {
			//person is null, remove all text;
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	//called when the user clicks the new button.
	// opens a dialog to edit details for a new person

	@FXML
	private void handleNewPerson(){
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if(okClicked){
			mainApp.getPersonData().add(tempPerson);
		}
	}

	//called when the user clicks the edit button. opens a dialog to edit
	//details for the selected person

	@FXML
	private void handlEditPerson(){
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if(selectedPerson != null){
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if(okClicked){
				showPersonDetails(selectedPerson);
			}
		} else {
			//nothing selected
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	//called when the user clicks the delete button

	@FXML
	private void handleDeletePerson(){
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0){
			personTable.getItems().remove(selectedIndex);

		} else {
			//nothing selected
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No selection");
			alert.setHeaderText("No person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}


	}
}













