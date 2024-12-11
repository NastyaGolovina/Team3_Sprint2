package UPT_PL.Team_3_GraphUI;


import UPT_PL.Team_3.model.Calculation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalculationsStage extends Stage {
	
	private ListView<String> calculationsListView;
	
	/**
	 * Constructor
	 */
	public CalculationsStage(Manager manage) {
		calculationsListView = new ListView<String>();
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
		Button btnDelete = new Button("Delete");
		Button btnLoadCalculation = new Button("Load Calculation");
		

		
		

		btnCalculate.setOnAction(ae -> {
			

		});
		
		
		btnDelete.setOnAction(ae -> {
			

		});
		
		btnLoadCalculation.setOnAction(ae -> {
			

		});
		
		
		root.getChildren().add(HButtonsBox);
		VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));

		
		HButtonsBox.getChildren().addAll(new Button[] {btnCalculate, btnDelete, btnLoadCalculation});
		
	

		this.setScene(new Scene(root,750,750));
		
		
	}


}
