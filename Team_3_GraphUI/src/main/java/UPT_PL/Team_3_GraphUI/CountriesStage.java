package UPT_PL.Team_3_GraphUI;

import java.util.Optional;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3_GraphUI.CountryGridPane.TextFieldName;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CountriesStage extends Stage {

	private ListView<String> listViewCtrl;
	private CountryGridPane countryGridPane;

	/**
	 * Constructor
	 */
	public CountriesStage(Manager manage) {
		listViewCtrl = new ListView<String>();
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
			countryCreateStage.createNewCountry(manager);

			fillListView(manager);

			if (!manager.getCountries().getCountries().isEmpty()) {
				listViewCtrl.requestFocus();
				listViewCtrl.getSelectionModel().select(0);
			} 

		});

		btnEdit.setOnAction(ae -> {
			String selectedCountry = listViewCtrl.getSelectionModel().getSelectedItem();
			int selectedCountryPos = listViewCtrl.getSelectionModel().getSelectedIndex();
			if (selectedCountry != null) {
				CountryUpdateCreateStage countryUpdateStage = new CountryUpdateCreateStage(manager);
				countryUpdateStage.getCountryGridPane().getCountryIdField().setEditable(false);
				countryUpdateStage.getCountryGridPane().fillGrid(selectedCountry, manager);

				countryUpdateStage.updateCountry(manager);

				fillListView(manager);
				if (!manager.getCountries().getCountries().isEmpty()) {
					listViewCtrl.requestFocus();
					listViewCtrl.getSelectionModel().select(selectedCountryPos);
				}
			} else {

				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No country selected");
				alert.setContentText("Please select a country to update.");
				alert.showAndWait();

			}

		});

		btnDelete.setOnAction(ae -> {

			Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
			deleteAlert.setTitle("Confirmation Dialog");
			deleteAlert.setHeaderText("Are you sure you want to delete this country? ");
			deleteAlert.setContentText("This action cannot be undone.");

			Optional<ButtonType> deleteResult = deleteAlert.showAndWait();

			if (deleteResult.isPresent()) {
				if (deleteResult.get() == ButtonType.OK) {
					String selectedCountry = listViewCtrl.getSelectionModel().getSelectedItem();
					if (selectedCountry != null) {
						String output = manager.getCountries().deleteCountry(selectedCountry);
						if (output == "") {
							manager.deleteCountry(selectedCountry);
							fillListView(manager);

							if (!manager.getCountries().getCountries().isEmpty()) {
								listViewCtrl.requestFocus();
								listViewCtrl.getSelectionModel().select(0);
							} else {
								countryGridPane.setValueToTextField(TextFieldName.CountryIdField, "");
								countryGridPane.setValueToTextField(TextFieldName.NameField, "");
								countryGridPane.setValueToTextField(TextFieldName.PopulationField, "");
							}
						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Failed to delete country");
							alert.setContentText(output);
							alert.showAndWait();
						}
					} else {

						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("No country selected");
						alert.setContentText("Please select a country to delete.");
						alert.showAndWait();

					}
				}
			}

		});

		btnSite.setOnAction(ae -> {
			String selectedCountry = listViewCtrl.getSelectionModel().getSelectedItem();
			if (selectedCountry != null) { 
				Country country = manager.getCountries().getCountries().get(manager.getCountries().searchCountry(selectedCountry));
				LogisticsSitesStage logisticsSitesStage = new LogisticsSitesStage(manager, country);
				logisticsSitesStage.show();
			} else {

				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No country selected");
				alert.setContentText("Please select a country to delete.");
				alert.showAndWait();

			}
		});

		btnProductsByCountry.setOnAction(ae -> {
			String selectedCountry = listViewCtrl.getSelectionModel().getSelectedItem();
			if (selectedCountry != null) {
				Country country = manager.getCountries().getCountries().get(manager.getCountries().searchCountry(selectedCountry));
			} else {

				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No country selected");
				alert.setContentText("Please select a country to delete.");
				alert.showAndWait();

			}
		});

		root.getChildren().addAll(new HBox[] { HButtonsBox, HInfoBox });
		VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));
		VBox.setVgrow(HInfoBox, Priority.ALWAYS);

		HButtonsBox.getChildren().addAll(new Button[] { btnNew, btnEdit, btnDelete, btnSite, btnProductsByCountry });
		// HInfoBox.getChildren().addAll(new VBox[] {VBoxList,VBoxResult});
		fillListView(manager);
		listViewCtrl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HInfoBox.getChildren().add(listViewCtrl);
		HInfoBox.getChildren().add(VBoxResult);
		HBox.setMargin(listViewCtrl, new Insets(0, 10, 10, 10));
		// VBoxList.getChildren().add(listView);
		// VBoxList.setPadding(new Insets(0, 0, 0, 20));
		countryGridPane = buildUICountryFilds();
		VBoxResult.getChildren().add(countryGridPane.getMyGrid());

		listViewCtrl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				countryGridPane.fillGrid(String.valueOf(newValue), manager);
			}
		});

		this.setScene(new Scene(root, 750, 750));

		this.setOnShown(event -> {
			Platform.runLater(() -> {
				if (!manager.getCountries().getCountries().isEmpty()) {
					listViewCtrl.requestFocus();
					listViewCtrl.getSelectionModel().select(0);
				}
			});
		});
	}

	public void fillListView(Manager manager) {
		listViewCtrl.getItems().clear();
		for (Country c : manager.getCountries().getCountries()) {
			listViewCtrl.getItems().add(c.getCountryId());
		}
	}

	public CountryGridPane buildUICountryFilds() {
		CountryGridPane countryGridPane = new CountryGridPane();

		countryGridPane.getCountryIdField().setEditable(false);
		countryGridPane.getNameField().setEditable(false);
		countryGridPane.getPopulationField().setEditable(false);

		return countryGridPane;
	}

}
