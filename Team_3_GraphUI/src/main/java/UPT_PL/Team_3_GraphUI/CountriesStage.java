package UPT_PL.Team_3_GraphUI;


import UPT_PL.Team_3.model.Country;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CountriesStage extends Stage {

	private ListView<String> listViewCtrl;
	
	/**
	 * Constructor
	 */
	public CountriesStage(Manager manage) {
		buildUI(manage);
	}

	public void buildUI(Manager manager) {
		this.setTitle("Countries"); 
		this.initModality(Modality.APPLICATION_MODAL);
		
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.TOP_LEFT);
		
		HBox HButtonsBox = new HBox();
		HButtonsBox.setSpacing(10);
		HButtonsBox.setAlignment(Pos.CENTER);
		HBox HInfoBox = new HBox();
		HInfoBox.setSpacing(10);
		
		Button btnNew = new Button("New");
		Button btnEdit = new Button("Edit");
		Button btnDelete = new Button("Delete");
		Button btnSite = new Button("Site");
		Button btnProductsByCountry = new Button("ProductsByCountry");
		
//		VBox VBoxList = new VBox();
		VBox VBoxResult = new VBox();
		
		

		btnNew.setOnAction(ae -> {
			CountryUpdateCreateStage countryCreateStage = new CountryUpdateCreateStage(manager);
			countryCreateStage.show();
		});
		
		btnEdit.setOnAction(ae -> {
			
		});
		
		btnDelete.setOnAction(ae -> {
			
		});
		
		btnSite.setOnAction(ae -> {
			
		});
		
		btnProductsByCountry.setOnAction(ae -> {
			
		});
		
		root.getChildren().addAll(new HBox[] { HButtonsBox, HInfoBox});
		VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));
		VBox.setVgrow(HInfoBox, Priority.ALWAYS);
		
		HButtonsBox.getChildren().addAll(new Button[] {btnNew, btnEdit, btnDelete, btnSite, btnProductsByCountry});
		//HInfoBox.getChildren().addAll(new VBox[] {VBoxList,VBoxResult});
		listViewCtrl = fillListView(manager);
		listViewCtrl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HInfoBox.getChildren().add(listViewCtrl);
		HInfoBox.getChildren().add(VBoxResult);
		HBox.setMargin(listViewCtrl, new Insets(0,10,10,10));
		//VBoxList.getChildren().add(listView);
		//VBoxList.setPadding(new Insets(0, 0, 0, 20));
		CountryGridPane countryGridPane = buildUICountryFilds();
		VBoxResult.getChildren().add(countryGridPane.getGrid());
		
		listViewCtrl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
            	countryGridPane.fillGrid(String.valueOf(newValue), manager);
            }
		});
		
		this.setScene(new Scene(root,750,750));
		
		this.setOnShown(event -> {
			Platform.runLater(() -> {
				listViewCtrl.requestFocus();
				listViewCtrl.getSelectionModel().select(0);
			});
		});
	}
	
	public ListView<String> fillListView(Manager manager) {
		ListView<String> listView = new ListView<String>();
		for(Country c : manager.getCountries().getCountries()) {
			listView.getItems().add(c.getCountryId()); 
		}
		return listView;
	}
	
	public CountryGridPane buildUICountryFilds() {
		CountryGridPane countryGridPane = new CountryGridPane();
		
		countryGridPane.getCountryIdField().setEditable(false);
		countryGridPane.getNameField().setEditable(false);
		countryGridPane.getPopulationField().setEditable(false);
		
//		Label countryIdLabel = new Label("Country Id");
//		Label nameLabel = new Label("Name");
//		Label populationLabel = new Label("Population");
//		this.countryIdField = new TextField();
//		this.countryIdField.setEditable(false);
//		this.nameField = new TextField();
//		this.nameField.setEditable(false);
//		this.populationField = new TextField();
//		this.populationField.setEditable(false);
//		
//		GridPane grid = new GridPane();
//		grid.setHgap(10);
//		grid.setVgap(5);
//
//		grid.add(countryIdLabel, 0, 0);
//		grid.add(nameLabel, 0, 1);
//		grid.add(populationLabel, 0, 2);
//		grid.add(this.countryIdField, 1, 0);
//		grid.add(this.nameField, 1, 1);
//		grid.add(this.populationField, 1, 2);
//
//		GridPane.setHgrow(this.countryIdField, Priority.ALWAYS);
//		GridPane.setHgrow(this.nameField, Priority.ALWAYS);
//		GridPane.setHgrow(this.populationField, Priority.ALWAYS);

		return countryGridPane;
	}
	
	
	
}

