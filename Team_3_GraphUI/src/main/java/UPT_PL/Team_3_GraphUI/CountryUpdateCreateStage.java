package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Country;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CountryUpdateCreateStage extends Stage {
	
	private TextField countryIdField;
	private TextField nameField;
	private TextField populationField;
	
	/**
	 * Constructor
	 */
	public CountryUpdateCreateStage(Manager manage) {
		buildUI(manage);
	}
	
	public void buildUI(Manager manager) {
		this.setTitle("Create Country"); 
		this.initModality(Modality.APPLICATION_MODAL);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(5);
		grid.setPadding(new Insets(20, 20, 20, 20));
		
		Label countryIdLabel = new Label("Country Id");
		Label nameLabel = new Label("Name");
		Label populationLabel = new Label("Population");
		this.countryIdField = new TextField();
		this.nameField = new TextField();
		this.populationField = new TextField();
		
		HBox HButtonsBox = new HBox();
		HButtonsBox.setAlignment(Pos.TOP_RIGHT);
		HButtonsBox.setSpacing(10);
		Button btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");
		HButtonsBox.getChildren().addAll(new Button[] {btnOK,btnCancel});
		
		btnCancel.setOnAction(ae -> {
			this.close();
		});
		
		btnOK.setOnAction(ae -> {
			
		});


		grid.add(countryIdLabel, 0, 0);
		grid.add(nameLabel, 0, 1);
		grid.add(populationLabel, 0, 2);
		grid.add(this.countryIdField, 1, 0);
		grid.add(this.nameField, 1, 1);
		grid.add(this.populationField, 1, 2);
		grid.add(HButtonsBox, 1, 3);

		GridPane.setHgrow(this.countryIdField, Priority.ALWAYS);
		GridPane.setHgrow(this.nameField, Priority.ALWAYS);
		GridPane.setHgrow(this.populationField, Priority.ALWAYS);
		
		
		
		this.setScene(new Scene(grid, 500, 500));
	}
	
	
	
}
