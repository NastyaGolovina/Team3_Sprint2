package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3_GraphUI.CountryGridPane.TextFieldName;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CountryUpdateCreateStage extends Stage {
	
	private CountryGridPane countryGridPane;
	private Button btnOK;
	/**
	 * Constructor
	 */
	public CountryUpdateCreateStage(Manager manage) {
		buildUI(manage);
	}
	
	
	
	/**
	 * @return the countryGridPane
	 */
	public CountryGridPane getCountryGridPane() {
		return countryGridPane;
	}



	/**
	 * @param countryGridPane the countryGridPane to set
	 */
	public void setCountryGridPane(CountryGridPane countryGridPane) {
		this.countryGridPane = countryGridPane;
	}



	public void buildUI(Manager manager) {
		this.setTitle("Create Country"); 
		this.initModality(Modality.APPLICATION_MODAL);
		
		countryGridPane = new CountryGridPane();
		countryGridPane.getMyGrid().setPadding(new Insets(20, 20, 20, 20));
		countryGridPane.getMyGrid().setAlignment(Pos.TOP_CENTER);
		

		HBox HButtonsBox = new HBox();
		HButtonsBox.setAlignment(Pos.TOP_RIGHT);
		HButtonsBox.setSpacing(10);
		btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");
		
		
		btnCancel.setOnAction(ae -> {
			this.close();
		});
		
		
		
		HButtonsBox.getChildren().addAll(new Button[] {btnOK,btnCancel});
		countryGridPane.getMyGrid().add(HButtonsBox, 1, 3);
		this.setScene(new Scene(countryGridPane.getMyGrid(), 500, 500));
	}
	
	public void createNewCountry(Manager manager) {
		btnOK.setOnAction(ae -> {
			String countryId = countryGridPane.getValueFromTextField(TextFieldName.CountryIdField);
			String name = countryGridPane.getValueFromTextField(TextFieldName.NameField);
			String population = countryGridPane.getValueFromTextField(TextFieldName.PopulationField);
			Country newCountry = new Country();
			String output = manager.getCountries().addCountry(countryId, name, population,newCountry);
			if(output == "") {
				manager.addCountry(newCountry);
				this.close();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Failed to add country");
	            alert.setContentText(output);
	            alert.showAndWait();
	            countryGridPane.setValueToTextField(TextFieldName.CountryIdField, "");
	    		countryGridPane.setValueToTextField(TextFieldName.NameField, "");
	    		countryGridPane.setValueToTextField(TextFieldName.PopulationField,"");
			}
		});
		this.showAndWait();
	}
	
	public void updateCountry(Manager manager) {
		btnOK.setOnAction(ae -> {
			String countryId = countryGridPane.getValueFromTextField(TextFieldName.CountryIdField);
			String name = countryGridPane.getValueFromTextField(TextFieldName.NameField);
			String population = countryGridPane.getValueFromTextField(TextFieldName.PopulationField);
			Country editedCountry = new Country();
			editedCountry.setCountryId(countryId);
			String output = manager.getCountries().updateCountry(countryId, name, population,editedCountry);
			if(output == "") {
				manager.updateCountry(editedCountry);
				this.close();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Failed to add country");
	            alert.setContentText(output);
	            alert.showAndWait();
			}
		});
		this.showAndWait();
	}

	
}
