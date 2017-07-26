package ch.makery.address.view;

import java.io.File;

import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

//the controller for the root layout
//root layout provides basic app layout containing a menu bar and space
//where other javafx elements can be places

public class RootLayoutController {

	//ref the main app
	private MainApp mainApp;

	//called by the main app to give reference back to itself
	//@param mainApp

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}

	//creates an empty address boo

	@FXML
	private void handleNew(){
		mainApp.getPersonData().clear();
		mainApp.setPersonFilePath(null);
	}

	//opens a filechooser to let the user select an address book to load

	@FXML
	private void handleOpen(){
		FileChooser fileChooser = new FileChooser();

		//set ext filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		//show open file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if(file != null){
			mainApp.loadPersonDataFromFile(file);
		}
	}

	@FXML
	private void handleSave(){
		File personFile = mainApp.getPersonFilePath();
		if(personFile != null){
			mainApp.savePersonDataToFile(personFile);
		} else {
			handleSaveAs();
		}
	}

	//opens filechooser to let the user select a file to save to

	@FXML
	private void handleSaveAs(){
		FileChooser fileChooser = new FileChooser();

		//set ext filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		//show save file dialog

		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if( file != null){
			//make sure correct extension
			if(!file.getPath().endsWith(".xml")){
				file = new File(file.getPath() + ".xml");
			}

			mainApp.savePersonDataToFile(file);
		}
	}

	//opens an about dialog

	@FXML
	private void handleAbout(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author LilYao, Tutorial by Marco Jakob\nWebsite: http://code.makery.ch");

		alert.showAndWait();

	}

	@FXML
	private void handleExit(){
		System.exit(0);
	}
}
