package UPT_PL.Team_3_GraphUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Manager manager = new Manager();
		manager.readFromDB();
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		Button btnCountries = new Button("Countries");
		Button btnProducts = new Button("Products");
		Button btnTransports = new Button("Transports");
		Button btnChains = new Button("Logistics Supply Chains");
		Button btnRouteCalculations= new Button("Route Calculations");
		
		btnCountries.setPrefWidth(250);
		btnProducts.setPrefWidth(250);
		btnTransports.setPrefWidth(250);
		btnChains.setPrefWidth(250);
		btnRouteCalculations.setPrefWidth(250);
		
		Text message = new Text("");

		
		btnCountries.setOnAction(ae -> {
			CountriesStage countriesStage = new CountriesStage(manager);
			countriesStage.show();
		});
		
		btnProducts.setOnAction(ae -> {
			ProductsStage productsStage = new ProductsStage(manager);
			productsStage.show();
		});
		
		

		btnTransports.setOnAction(ae -> {
			TransportStage transportStage = new TransportStage(manager);
			transportStage.show();
		});

		
		btnChains.setOnAction(ae -> {
			LogisticsSupplyChainsStage logisticsSupplyChainsStage = new LogisticsSupplyChainsStage(manager);
			logisticsSupplyChainsStage.show();
		});
		
		btnRouteCalculations.setOnAction(ae -> {
			CalculationsStage calculationsStage = new CalculationsStage(manager);
			calculationsStage.show();
		});
		
		
		root.getChildren().addAll(new Button[] { btnCountries, btnProducts, btnTransports, btnChains, btnRouteCalculations});
		
		root.getChildren().add(message);
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
