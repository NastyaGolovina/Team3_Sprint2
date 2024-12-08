package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Country;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;


public class CountryGridPane extends GridPane {
	
	
	private GridPane grid;
	private TextField countryIdField;
	private TextField nameField;
	private TextField populationField;
	
	
	
	public CountryGridPane() {
		buildUI();
	}
	
	
	
	
	/**
	 * @return the grid
	 */
	public GridPane getGrid() {
		return grid;
	}




	/**
	 * @param grid the grid to set
	 */
	public void setGrid(GridPane grid) {
		this.grid = grid;
	}




	/**
	 * @return the countryIdField
	 */
	public TextField getCountryIdField() {
		return countryIdField;
	}




	/**
	 * @param countryIdField the countryIdField to set
	 */
	public void setCountryIdField(TextField countryIdField) {
		this.countryIdField = countryIdField;
	}




	/**
	 * @return the nameField
	 */
	public TextField getNameField() {
		return nameField;
	}




	/**
	 * @param nameField the nameField to set
	 */
	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}




	/**
	 * @return the populationField
	 */
	public TextField getPopulationField() {
		return populationField;
	}




	/**
	 * @param populationField the populationField to set
	 */
	public void setPopulationField(TextField populationField) {
		this.populationField = populationField;
	}




	public void buildUI() {
		Label countryIdLabel = new Label("Country Id");
		Label nameLabel = new Label("Name");
		Label populationLabel = new Label("Population");
		this.countryIdField = new TextField();
		this.nameField = new TextField();
		this.populationField = new TextField();
		
		this.grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(5);

		grid.add(countryIdLabel, 0, 0);
		grid.add(nameLabel, 0, 1);
		grid.add(populationLabel, 0, 2);
		grid.add(this.countryIdField, 1, 0);
		grid.add(this.nameField, 1, 1);
		grid.add(this.populationField, 1, 2);

		GridPane.setHgrow(this.countryIdField, Priority.ALWAYS);
		GridPane.setHgrow(this.nameField, Priority.ALWAYS);
		GridPane.setHgrow(this.populationField, Priority.ALWAYS);
	}
	
	
	public void fillGrid(String newValue, Manager manager) {
		int countryPos = manager.getCountries().searchCountry(newValue);
		if(countryPos != -1) {
			Country country = manager.getCountries().getCountries().get(countryPos);
			this.countryIdField.setText(country.getCountryId()); ;
			this.nameField.setText(country.getName());
			this.populationField.setText(String.valueOf(country.getPopulation()));
		}
		
	}
}
