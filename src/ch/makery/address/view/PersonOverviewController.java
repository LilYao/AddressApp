package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
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
			lastNameLabel.setText(person.getFirstName());
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
			birthdayLabel.setText("");
		}
	}

	//called when the user clicks the delete button

	@FXML
	private void handleDeletePerson(){
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		personTable.getItems().remove(selectedIndex);

	}
}













