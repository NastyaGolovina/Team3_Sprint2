package UPT_PL.Team_3_GraphUI;

//The purpose of ProductUpdateCreateStage class is to create a modal window or another stage (sub-stage) 
//opens on top of the main stage (or primary stage) and temporarily blocks interaction with the main stage until the modal window is closed.

import UPT_PL.Team_3.model.Product;


import UPT_PL.Team_3_GraphUI.ProductsGridPane.TextFieldName;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductUpdateCreateStage extends Stage {
	//instance variables
	private ProductsGridPane productGridPane;
	private Button btnOK;
	
	/**
	 * Constructor
	 */
	public ProductUpdateCreateStage(Manager manage) {
		buildUI(manage);
	}

	public ProductsGridPane getProductGridPane() {
		return productGridPane;
	}

	public void setProductGridPane(ProductsGridPane productGridPane) {
		this.productGridPane = productGridPane;
	}

	 //This method builds the user interface layout and assigns behavior to the buttons:
	public void buildUI(Manager manager) { 
		this.setTitle("Products");
		this.initModality(Modality.APPLICATION_MODAL); //block interaction with other windows while this one is open.
		
		productGridPane = new ProductsGridPane();
		productGridPane.getGrid().setPadding(new Insets(20, 20, 20, 20));
		productGridPane.getGrid().setAlignment(Pos.TOP_CENTER);
		
		HBox HButtonsBox = new HBox();
		HButtonsBox.setAlignment(Pos.TOP_RIGHT);
		HButtonsBox.setSpacing(10);
		btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");
		
		btnCancel.setOnAction(ae -> {
			this.close();
		});
		
		HButtonsBox.getChildren().addAll(new Button[] {btnOK,btnCancel});
		productGridPane.getGrid().add(HButtonsBox, 1, 4);
		this.setScene(new Scene(productGridPane.getGrid(), 500, 500));
		}
	
		public void createNewProduct(Manager manager) {
			btnOK.setOnAction(ae -> {
				String productID = productGridPane.getValueFromTextField(TextFieldName.productIDField);
				String name = productGridPane.getValueFromTextField(TextFieldName.nameField);
				String expirationInDays = productGridPane.getValueFromTextField(TextFieldName.expirationInDaysField);
				String recommendedRate = productGridPane.getValueFromTextField(TextFieldName.recommendedRateField);
				Product newProduct = new Product();

				String output = manager.getProducts().addProduct(productID, name, expirationInDays, recommendedRate,
						newProduct);
				if (output == "") {
					manager.addProduct(newProduct);
					this.close();
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Failed to add country");
					alert.setContentText(output);
					alert.showAndWait();
					productGridPane.setValueToTextField(TextFieldName.productIDField, "");
					productGridPane.setValueToTextField(TextFieldName.nameField, "");
					productGridPane.setValueToTextField(TextFieldName.expirationInDaysField, "");
					productGridPane.setValueToTextField(TextFieldName.recommendedRateField, "");
				}

			});
			this.showAndWait();
		}
	
		
		public void updateProduct(Manager manager) {
			btnOK.setOnAction(ae -> {
				String productID = productGridPane.getValueFromTextField(TextFieldName.productIDField);
				String name = productGridPane.getValueFromTextField(TextFieldName.nameField);
				String expirationInDays = productGridPane.getValueFromTextField(TextFieldName.expirationInDaysField);
				String recommendedRate = productGridPane.getValueFromTextField(TextFieldName.recommendedRateField);
				Product editedProduct = new Product();
				editedProduct.setProductID(productID);
				String output = manager.getProducts().updateProduct(productID, name, expirationInDays,recommendedRate, editedProduct);
				if(output == "") {
					manager.updateProduct(editedProduct);
					this.close();
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("Failed to update product");
		            alert.setContentText(output);
		            alert.showAndWait();
				}
			});
			this.showAndWait();
		}

	
	
}
