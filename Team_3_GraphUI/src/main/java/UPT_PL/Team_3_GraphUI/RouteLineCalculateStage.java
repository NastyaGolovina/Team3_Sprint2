package UPT_PL.Team_3_GraphUI;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class RouteLineCalculateStage extends Stage {

	/**
	 * Constructor
	 */
	public RouteLineCalculateStage(Manager manage) {
		buildUI(manage);
	}

	public void buildUI(Manager manager) {
		this.setTitle("Calculate");
		this.initModality(Modality.APPLICATION_MODAL);

		GridPane calculationGrid = new GridPane();
		calculationGrid.setPadding(new Insets(20, 20, 20, 20));
		calculationGrid.setHgap(10);
		calculationGrid.setVgap(5);

		Label descriptionLabel = new Label("Description");
		Label comboBoxLabel = new Label("Select sort type");
		Label sortByLabel = new Label("Sort by");
		TextField descriptionField = new TextField("");
		TextField sortByField = new TextField("");
		sortByField.setEditable(false);

		ComboBox<String> sortComboBox = new ComboBox<>();
		sortComboBox.getItems().addAll("All", "Country", "Product");

		sortComboBox.setValue("All");

		sortComboBox.setOnAction(event -> {
			sortByField.setText("");
			if (sortComboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("All")) {
				sortByField.setEditable(false);
			} else {
				sortByField.setEditable(true);
			}
		});

		HBox HButtonsBox = new HBox();
		HButtonsBox.setAlignment(Pos.TOP_RIGHT);
		HButtonsBox.setSpacing(10);

		Button btnCalculate = new Button("Calculate");
		Button btnCancel = new Button("Cancel");

		btnCancel.setOnAction(ae -> {
			this.close();
		});

		btnCalculate.setOnAction(ae -> {
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
						manager.calculateLogisticsRoute(descriptionField.getText(),
								sortComboBox.getSelectionModel().getSelectedItem(), sortByField.getText());
						this.close();
					}
				}

			} else {
				manager.calculateLogisticsRoute(descriptionField.getText(),
						sortComboBox.getSelectionModel().getSelectedItem(), sortByField.getText());
				this.close();
			}
			
		});

		HButtonsBox.getChildren().addAll(new Button[] { btnCalculate, btnCancel });

		calculationGrid.add(descriptionLabel, 0, 0);
		calculationGrid.add(comboBoxLabel, 0, 1);
		calculationGrid.add(sortByLabel, 0, 2);

		calculationGrid.add(descriptionField, 1, 0);
		calculationGrid.add(sortComboBox, 1, 1);
		calculationGrid.add(sortByField, 1, 2);

		calculationGrid.add(HButtonsBox, 1, 3);

		GridPane.setHgrow(descriptionField, Priority.ALWAYS);
		GridPane.setHgrow(sortComboBox, Priority.ALWAYS);
		GridPane.setHgrow(sortByField, Priority.ALWAYS);

		this.setScene(new Scene(calculationGrid, 500, 500));
	}

}
