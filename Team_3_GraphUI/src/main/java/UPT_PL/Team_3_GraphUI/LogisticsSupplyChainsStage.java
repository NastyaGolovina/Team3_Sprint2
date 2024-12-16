package UPT_PL.Team_3_GraphUI;

import java.util.Optional;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3_GraphUI.CountryGridPane.TextFieldName;
import UPT_PL.Team_3_GraphUI.LogisticsSupplyChainGridPane.ChainTextFieldName;
import UPT_PL.Team_3_GraphUI.LogisticsSupplyChainGridPane.DisplayType;
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

public class LogisticsSupplyChainsStage extends Stage {
	
	
	private ListView<String> listViewCtrl;
	private LogisticsSupplyChainGridPane logisticsSupplyChainGridPane;

	/**
	 * Constructor
	 */
	public LogisticsSupplyChainsStage(Manager manage) {
		listViewCtrl = new ListView<String>();
		buildUI(manage);
	}

	public void buildUI(Manager manager) {
		this.setTitle("Logistics Supply Chains");
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

//		VBox VBoxList = new VBox();
		VBox VBoxResult = new VBox();

		btnNew.setOnAction(ae -> {
			LogisticsSupplyChainUpdateCreateStage logisticsSupplyChainUpdateCreateStage = new LogisticsSupplyChainUpdateCreateStage(manager);
			logisticsSupplyChainUpdateCreateStage.createNewLogisticsSupplyChain(manager);
			
			fillListView(manager);

			if (!manager.getLogisticsSupplyChains().getSupplyChains().isEmpty()) {
				listViewCtrl.requestFocus();
				listViewCtrl.getSelectionModel().select(0);
			}
		});

		btnEdit.setOnAction(ae -> {
			String selectedChain = listViewCtrl.getSelectionModel().getSelectedItem();
			int selectedChainPos = listViewCtrl.getSelectionModel().getSelectedIndex();
			if (selectedChain != null) {
				LogisticsSupplyChainUpdateCreateStage logisticsSupplyChainUpdateCreateStage = new LogisticsSupplyChainUpdateCreateStage(manager);
				logisticsSupplyChainUpdateCreateStage.getLogisticsSupplyChainGridPane().getChainIdField().setEditable(false);
				logisticsSupplyChainUpdateCreateStage.getLogisticsSupplyChainGridPane().fillGrid(selectedChain, manager);
				

				logisticsSupplyChainUpdateCreateStage.updateLogisticsSupplyChain(manager);

				fillListView(manager);
				if (!manager.getLogisticsSupplyChains().getSupplyChains().isEmpty()) {
					listViewCtrl.requestFocus();
					listViewCtrl.getSelectionModel().select(selectedChainPos);
				}
			} else {

				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No logistics supply chain selected");
				alert.setContentText("Please select a logistics supply chain to update.");
				alert.showAndWait();

			}

		});

		btnDelete.setOnAction(ae -> {
			Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
			deleteAlert.setTitle("Confirmation Dialog");
			deleteAlert.setHeaderText("Are you sure you want to delete this logistics supply chain? ");
			deleteAlert.setContentText("This action cannot be undone.");

			Optional<ButtonType> deleteResult = deleteAlert.showAndWait();

			if (deleteResult.isPresent()) {
				if (deleteResult.get() == ButtonType.OK) {
					String selectedChain = listViewCtrl.getSelectionModel().getSelectedItem();
					if (selectedChain != null) {
						String output = manager.getLogisticsSupplyChains().deleteSupplyChainById(selectedChain);
						if (output == "") {
							manager.deleteLogisticsSupplyChain(selectedChain);
							fillListView(manager);

							if (!manager.getCountries().getCountries().isEmpty()) {
								listViewCtrl.requestFocus();
								listViewCtrl.getSelectionModel().select(0);
							} else {
								logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.ChainIdField, "");
								logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.SenderComboBox,
										"0");
								logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.ReceiverComboBox,
										"0");
								logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.TransportComboBox,
										"0");
								logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.CostPerTonField,
										"");
								logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.DurationInDaysField,
										"");
							}
						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Failed to delete logistics supply chain");
							alert.setContentText(output);
							alert.showAndWait();
						}
					} else {

						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("No logistics supply chain selected");
						alert.setContentText("Please select a logistics supply chain to delete.");
						alert.showAndWait();

					}
				}
			}
		});

		

		root.getChildren().addAll(new HBox[] { HButtonsBox, HInfoBox });
		VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));
		VBox.setVgrow(HInfoBox, Priority.ALWAYS);

		HButtonsBox.getChildren().addAll(new Button[] { btnNew, btnEdit, btnDelete});
		
		fillListView(manager);
		listViewCtrl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HInfoBox.getChildren().add(listViewCtrl);
		HInfoBox.getChildren().add(VBoxResult);
		HBox.setMargin(listViewCtrl, new Insets(0, 10, 10, 10));
		
		logisticsSupplyChainGridPane = buildUILogisticsSupplyChainGridPane();
		VBoxResult.getChildren().add(logisticsSupplyChainGridPane.getLogisticsSupplyChainGrid());

		listViewCtrl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				logisticsSupplyChainGridPane.fillGrid(String.valueOf(newValue), manager);
			}
		});

		this.setScene(new Scene(root, 750, 750));

		this.setOnShown(event -> {
			Platform.runLater(() -> {
				if (!manager.getLogisticsSupplyChains().getSupplyChains().isEmpty()) {
					listViewCtrl.requestFocus();
					listViewCtrl.getSelectionModel().select(0);
				}
			});
		});
	}

	public void fillListView(Manager manager) {
		listViewCtrl.getItems().clear();
		for (LogisticsSupplyChain chain : manager.getLogisticsSupplyChains().getSupplyChains()) {
			listViewCtrl.getItems().add(chain.getChainId());
		}
	}

	public LogisticsSupplyChainGridPane buildUILogisticsSupplyChainGridPane() {
		LogisticsSupplyChainGridPane logisticsSupplyChainsGrid = new LogisticsSupplyChainGridPane(DisplayType.View);

		return logisticsSupplyChainsGrid;
	}
	
	
	
}
