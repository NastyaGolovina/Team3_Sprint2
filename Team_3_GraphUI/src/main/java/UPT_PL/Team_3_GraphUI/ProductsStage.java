package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Product;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductsStage extends Stage {
	//instance variables
	private ListView<String> listViewDisplay;
	private ProductsGridPane productGridPane;
	
	//Constructor
	public ProductsStage(Manager manager) {
		listViewDisplay = new ListView<String>();
		buildUI(manager);
	}
	
	    //This method builds the user interface layout and assigns behavior to the buttons:
		public void buildUI(Manager manager) { 
			this.setTitle("Products");
			this.initModality(Modality.APPLICATION_MODAL); //block interaction with other windows while this one is open.
			
			VBox root = new VBox();
			root.setSpacing(10);                // Spacing among child nodes
			root.setAlignment(Pos.TOP_LEFT);	//The alignment determines where these elements are positioned within the VBox.	
			
			//HBox for button options layout
			HBox HBoxButton = new HBox();   // for the button options
			HBoxButton.setSpacing(10);
			HBoxButton.setAlignment(Pos.TOP_CENTER); // for display the information about Product
			HBox HBoxInfor = new HBox();
			HBoxInfor.setSpacing(10);
			
			Button btnNew = new Button("New Product");
			Button btnEdit = new Button("Edit Product");
			Button btnDelete = new Button("Delete Product");
			
			VBox VBoxDisplayInfor = new VBox();   //VBox to display the information of that product
			
			btnNew.setOnAction(ae -> {
				ProductUpdateCreateStage productCreateStage = new ProductUpdateCreateStage();
			
				
			}
			
				
		}
	}
