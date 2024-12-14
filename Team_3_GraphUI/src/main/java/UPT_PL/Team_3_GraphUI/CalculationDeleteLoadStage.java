package UPT_PL.Team_3_GraphUI;

import java.util.ArrayList;
import java.util.Optional;

import UPT_PL.Team_3.model.Calculation;
import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3_GraphUI.CountryGridPane.TextFieldName;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalculationDeleteLoadStage extends Stage {

	private Button btnOK;
	private TableView<Calculation> calculationTableView;

	/*
	 * Constructor
	 */
	public CalculationDeleteLoadStage(Manager manage) {
		calculationTableView = new TableView<Calculation>();
		buildUI(manage);
	}

	public void buildUI(Manager manager) {
		this.setTitle("Calculations");
		this.initModality(Modality.APPLICATION_MODAL);

		VBox calculationVBox = new VBox();

		HBox HButtonsBox = new HBox();
		HButtonsBox.setAlignment(Pos.TOP_CENTER);
		HButtonsBox.setSpacing(10);
		btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");

		btnCancel.setOnAction(ae -> {
			this.close();
		});

		buildCalculationTableView();
		calculationTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		ScrollPane scrollPane = new ScrollPane(calculationTableView);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);

		VBox.setVgrow(scrollPane, Priority.ALWAYS);

		HButtonsBox.getChildren().addAll(new Button[] { btnOK, btnCancel });
		calculationVBox.getChildren().addAll(HButtonsBox);
		VBox.setMargin(HButtonsBox, new Insets(10, 10, 10, 10));
		calculationVBox.getChildren().addAll(scrollPane);
		fillTableView(manager);
		this.setScene(new Scene(calculationVBox, 500, 500));

		this.setOnShown(event -> {
			Platform.runLater(() -> {
				if (manager.readCalculations() != null) {
					calculationTableView.requestFocus();
					calculationTableView.getSelectionModel().select(0);
				}
			});
		});
	}

	public void buildCalculationTableView() {
		// Columns
		TableColumn<Calculation, String> columnCalculationId = new TableColumn<>("Calculation Id");
		columnCalculationId.setCellValueFactory(new PropertyValueFactory<>("calculationId"));

		TableColumn<Calculation, String> columnCalculationDate = new TableColumn<>("Calculation Date");
		columnCalculationDate.setCellValueFactory(new PropertyValueFactory<>("calculationDate"));

		TableColumn<Calculation, String> columnCalculationDescription = new TableColumn<>("Calculation Description");
		columnCalculationDescription.setCellValueFactory(new PropertyValueFactory<>("calculationDescription"));

		TableColumn<Calculation, String> columnSortBy = new TableColumn<>("SortBy");
		columnSortBy.setCellValueFactory(new PropertyValueFactory<>("sortBy"));

		// set width
		columnCalculationId.setPrefWidth(98);
		columnCalculationDate.setPrefWidth(98);
		columnCalculationDescription.setPrefWidth(200);
		columnSortBy.setPrefWidth(98);

		columnCalculationId.setResizable(true);
		columnCalculationDate.setResizable(true);
		columnCalculationDescription.setResizable(true);
		columnSortBy.setResizable(true);

		// Adding columns to the table
		calculationTableView.getColumns().add(columnCalculationId);
		calculationTableView.getColumns().add(columnCalculationDate);
		calculationTableView.getColumns().add(columnCalculationDescription);
		calculationTableView.getColumns().add(columnSortBy);

	}

	private void fillTableView(Manager manager) {
		calculationTableView.getItems().clear();
		ArrayList<Calculation> calculation = manager.readCalculations();
		if (calculation != null) {
			for (Calculation c : calculation) {
				calculationTableView.getItems().add(c);
			}
		}

	}

	public void loadCalculation(Manager manager) {
		btnOK.setOnAction(ae -> {
			Calculation selectedCalculation = calculationTableView.getSelectionModel().getSelectedItem();
			if (selectedCalculation != null) {
				if (!manager.getLogisticsProcessor().isCurrentСalculationEmpty()) {
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Confirmation Dialog");
					alert.setHeaderText("The previous calculation will be deleted before adding to the database  ");
					alert.setContentText("Do you want to continue?");

					Optional<ButtonType> result = alert.showAndWait();

					if (result.isPresent()) {
						if (result.get() == ButtonType.CANCEL) {
							this.close();
						}
						if (result.get() == ButtonType.OK) {
							manager.readRouteLines(selectedCalculation);
							this.close();
						}
					}
				} else {
					manager.readRouteLines(selectedCalculation);
					this.close();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Failed to load calculation");
				alert.showAndWait();
			}
		});
		this.showAndWait();
	}

	public void deleteCalculation(Manager manager) {
		btnOK.setOnAction(ae -> {
			Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
			deleteAlert.setTitle("Confirmation Dialog");
			deleteAlert.setHeaderText("Are you sure you want to delete this calculation? ");
			deleteAlert.setContentText("This action cannot be undone.");

			Optional<ButtonType> deleteResult = deleteAlert.showAndWait();

			if (deleteResult.isPresent()) {
				if (deleteResult.get() == ButtonType.OK) {
					Calculation selectedCalculation = calculationTableView.getSelectionModel().getSelectedItem();
					if (selectedCalculation != null ) {
						if (!manager.getLogisticsProcessor().isCurrentСalculationEmpty() && selectedCalculation.getCalculationId().
								equalsIgnoreCase(manager.getLogisticsProcessor().getCurrentСalculation().getCalculationId())) {
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Confirmation Dialog");
							alert.setHeaderText(
									"The previous calculation will be deleted before adding to the database  ");
							alert.setContentText("Do you want to continue?");

							Optional<ButtonType> result = alert.showAndWait();

							if (result.isPresent()) {
								if (result.get() == ButtonType.CANCEL) {
									this.close();
								}
								if (result.get() == ButtonType.OK) {
									manager.deleteCalculation(selectedCalculation.getCalculationId());
									this.close();
								}
							}
						} else {
							manager.deleteCalculation(selectedCalculation.getCalculationId());
							this.close();
						}
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Failed to delete calculation");
						alert.showAndWait();
					}
				}
			}
		});
		this.showAndWait();
	}
}
