package ch.makery.address.view;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField streetField;

	@FXML
	private TextField postalCodeField;

	@FXML
	private TextField cityField;

	@FXML
	private TextField birthdayField;

	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;

	/*iniatilizes controller class.
	method called
	after fxml is loaded*/

	@FXML
	private void initialize() {

	}

	// sets the stage of this dialog
	// @param stage

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// sets the person to be edited in the dialog
	// @param person

	public void setPerson(Person person) {
		this.person = person;

		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getPostalCode()));
		cityField.setText(person.getCity());
		birthdayField.setText(DateUtil.format(person.getBirthday()));
		birthdayField.setPromptText("dd.mm.yyyy");

	}

	// returns true if the user clicked ok, false otherwise

	public boolean isOkClicked() {
		return okClicked;
	}

	// called when the user clicks ok

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			person.setBirthday(DateUtil.parse(birthdayField.getText()));

			okClicked = true;
			dialogStage.close();

		}
	}

	// called when the user clicks cancel

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	// validates the user input in textFields

	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";

		}

		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}

		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code !\n";
		} else {
			//try to parse postal code into an int
			try {
				Integer.parseInt(postalCodeField.getText());
			}catch (NumberFormatException e){
				errorMessage += "No valid postal code(must be an integer)!\n";
			}
		} if(cityField.getText() == null || cityField.getText().length() == 0){
			errorMessage += "No valid city!\n";
		} else {
			if(!DateUtil.validDate(birthdayField.getText())){
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}

		if(errorMessage.length() == 0){
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}

	}

}
