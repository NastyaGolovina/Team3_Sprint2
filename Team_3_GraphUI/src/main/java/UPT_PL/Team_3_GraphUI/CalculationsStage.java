package UPT_PL.Team_3_GraphUI;

import java.util.ArrayList;

import UPT_PL.Team_3.model.Calculation;
import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.model.Product;
import UPT_PL.Team_3.model.RouteLine;
import UPT_PL.Team_3.model.Transport;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalculationsStage extends Stage {

	private TableView<RouteLine> routeLineTableView;

	/**
	 * Constructor
	 */
	public CalculationsStage(Manager manage) {
		routeLineTableView = new TableView<>();
		buildUI(manage);
	}

	public void buildUI(Manager manager) {
		this.setTitle("Calculations");
		this.initModality(Modality.APPLICATION_MODAL);
		this.setMaximized(true);

		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.TOP_LEFT);

		HBox HButtonsBox = new HBox();
		HButtonsBox.setSpacing(10);
		HButtonsBox.setAlignment(Pos.CENTER);

		Button btnCalculate = new Button("Calculate");
		Button btnSaveCalculation = new Button("Save Current Calculation");
		Button btnDelete = new Button("Delete");
		Button btnLoadCalculation = new Button("Load Calculation");

		ScrollPane scrollPane = new ScrollPane(routeLineTableView);

		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);

		btnCalculate.setOnAction(ae -> {
			RouteLineCalculateStage routeLineCalculateStage = new RouteLineCalculateStage(manager);
			routeLineCalculateStage.showAndWait();
			fillTableView(manager);

		});

		btnSaveCalculation.setOnAction(ae -> {
			if(!manager.getLogisticsProcessor().isCurrentСalculationEmpty()) {
				manager.writeLogisticsProcessorInDB();
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No calculation to send to database");
				alert.showAndWait();
				
			}
			fillTableView(manager);
		});
		
		btnLoadCalculation.setOnAction(ae -> {
			CalculationDeleteLoadStage calculationDeleteLoadStage = new CalculationDeleteLoadStage(manager);
			calculationDeleteLoadStage.loadCalculation(manager);
			fillTableView(manager);
		});
		
		btnDelete.setOnAction(ae -> {
			CalculationDeleteLoadStage calculationDeleteLoadStage = new CalculationDeleteLoadStage(manager);
			calculationDeleteLoadStage.deleteCalculation(manager);
			fillTableView(manager);
		});

		

		buildRouteLineTableView();
		fillTableView(manager);
		root.getChildren().add(HButtonsBox);

		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		root.getChildren().add(scrollPane);
		VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));

		HButtonsBox.getChildren()
				.addAll(new Button[] { btnCalculate, btnSaveCalculation, btnLoadCalculation, btnDelete });

		this.setScene(new Scene(root, 750, 750));

	}

	public void buildRouteLineTableView() {
		// Columns
		TableColumn<RouteLine, String> columnRouteLineID = new TableColumn<>("Route Line ID");
		columnRouteLineID.setCellValueFactory(new PropertyValueFactory<>("routeLineID"));

		TableColumn<RouteLine, String> columnVersion = new TableColumn<>("Version");
		columnVersion.setCellValueFactory(new PropertyValueFactory<>("version"));

		TableColumn<RouteLine, Calculation> columnCurrentCalculation = new TableColumn<>("Calculation");
		columnCurrentCalculation.setCellValueFactory(new PropertyValueFactory<>("currentCalculation"));

		TableColumn<RouteLine, Country> columnCountrySender = new TableColumn<>("Country Sender");
		columnCountrySender.setCellValueFactory(new PropertyValueFactory<>("countrySender"));

		TableColumn<RouteLine, LogisticsSite> columnLogisticsSiteSender = new TableColumn<>("Logistics Site Sender");
		columnLogisticsSiteSender.setCellValueFactory(new PropertyValueFactory<>("logisticsSiteSender"));

		TableColumn<RouteLine, Country> columnCountryReceiver = new TableColumn<>("Country Receiver");
		columnCountryReceiver.setCellValueFactory(new PropertyValueFactory<>("countryReceiver"));

		TableColumn<RouteLine, LogisticsSite> columnLogisticsSiteReceiver = new TableColumn<>(
				"Logistics Site Receiver");
		columnLogisticsSiteReceiver.setCellValueFactory(new PropertyValueFactory<>("logisticsSiteReceiver"));

		TableColumn<RouteLine, Product> columnProduct = new TableColumn<>("Product");
		columnProduct.setCellValueFactory(new PropertyValueFactory<>("product"));

		TableColumn<RouteLine, Transport> columnTransport = new TableColumn<>("Transport");
		columnTransport.setCellValueFactory(new PropertyValueFactory<>("transport"));

		TableColumn<RouteLine, Double> columnQuantity = new TableColumn<>("Quantity");
		columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		TableColumn<RouteLine, Double> columnRequestedQuantity = new TableColumn<>("Requested Quantity");
		columnRequestedQuantity.setCellValueFactory(new PropertyValueFactory<>("requestedQuantity"));

		TableColumn<RouteLine, Double> columnAmountProduct = new TableColumn<>("Amount Product");
		columnAmountProduct.setCellValueFactory(new PropertyValueFactory<>("amountProduct"));

		TableColumn<RouteLine, Double> columnAmountTransport = new TableColumn<>("Amount Transport");
		columnAmountTransport.setCellValueFactory(new PropertyValueFactory<>("amountTransport"));

		TableColumn<RouteLine, Double> columnTotalAmount = new TableColumn<>("Total Amount");
		columnTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

		TableColumn<RouteLine, Double> columnDurationInDays = new TableColumn<>("Duration (Days)");
		columnDurationInDays.setCellValueFactory(new PropertyValueFactory<>("durationInDays"));

		TableColumn<RouteLine, Boolean> columnCovered = new TableColumn<>("Covered");
		columnCovered.setCellValueFactory(new PropertyValueFactory<>("covered"));

		TableColumn<RouteLine, Boolean> columnIsOptimalChain = new TableColumn<>("Optimal Chain");
		columnIsOptimalChain.setCellValueFactory(new PropertyValueFactory<>("optimalChain"));

		// set width
		columnRouteLineID.setPrefWidth(150);
		columnVersion.setPrefWidth(150);
		columnCurrentCalculation.setPrefWidth(150);
		columnCountrySender.setPrefWidth(150);
		columnLogisticsSiteSender.setPrefWidth(150);
		columnCountryReceiver.setPrefWidth(150);
		columnLogisticsSiteReceiver.setPrefWidth(150);
		columnProduct.setPrefWidth(150);
		columnTransport.setPrefWidth(150);
		columnQuantity.setPrefWidth(150);
		columnRequestedQuantity.setPrefWidth(150);
		columnAmountProduct.setPrefWidth(150);
		columnAmountTransport.setPrefWidth(150);
		columnTotalAmount.setPrefWidth(150);
		columnDurationInDays.setPrefWidth(150);
		columnCovered.setPrefWidth(150);
		columnIsOptimalChain.setPrefWidth(150);

		columnRouteLineID.setResizable(true);
		columnVersion.setResizable(true);
		columnCurrentCalculation.setResizable(true);
		columnCountrySender.setResizable(true);
		columnLogisticsSiteSender.setResizable(true);
		columnCountryReceiver.setResizable(true);
		columnLogisticsSiteReceiver.setResizable(true);
		columnProduct.setResizable(true);
		columnTransport.setResizable(true);
		columnQuantity.setResizable(true);
		columnRequestedQuantity.setResizable(true);
		columnAmountProduct.setResizable(true);
		columnAmountTransport.setResizable(true);
		columnTotalAmount.setResizable(true);
		columnDurationInDays.setResizable(true);
		columnCovered.setResizable(true);
		columnIsOptimalChain.setResizable(true);

		// Adding columns to the table
		routeLineTableView.getColumns().add(columnRouteLineID);
		routeLineTableView.getColumns().add(columnVersion);
		routeLineTableView.getColumns().add(columnCurrentCalculation);
		routeLineTableView.getColumns().add(columnCountrySender);
		routeLineTableView.getColumns().add(columnLogisticsSiteSender);
		routeLineTableView.getColumns().add(columnCountryReceiver);
		routeLineTableView.getColumns().add(columnLogisticsSiteReceiver);
		routeLineTableView.getColumns().add(columnProduct);
		routeLineTableView.getColumns().add(columnTransport);
		routeLineTableView.getColumns().add(columnQuantity);
		routeLineTableView.getColumns().add(columnRequestedQuantity);
		routeLineTableView.getColumns().add(columnAmountProduct);
		routeLineTableView.getColumns().add(columnAmountTransport);
		routeLineTableView.getColumns().add(columnTotalAmount);
		routeLineTableView.getColumns().add(columnDurationInDays);
		routeLineTableView.getColumns().add(columnCovered);
		routeLineTableView.getColumns().add(columnIsOptimalChain);

	}

	private void fillTableView(Manager manager) {
		routeLineTableView.getItems().clear();
		ArrayList<RouteLine> routeLine = manager.getLogisticsProcessor().getLogisticsRoutes();
		for (RouteLine r : routeLine) {
			routeLineTableView.getItems().add(r);
		}
	}
}
