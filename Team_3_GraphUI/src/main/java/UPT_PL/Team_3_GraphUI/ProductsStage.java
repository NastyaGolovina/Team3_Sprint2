package UPT_PL.Team_3_GraphUI;

import java.util.Optional;



import UPT_PL.Team_3.model.Product;

import UPT_PL.Team_3_GraphUI.ProductsGridPane;
import UPT_PL.Team_3_GraphUI.ProductsGridPane.TextFieldName;
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
			HBox HBoxInfor = new HBox();    // where contain ListView 
			HBoxInfor.setSpacing(10);
			HBoxInfor.setAlignment(Pos.TOP_LEFT);
			
			Button btnNew = new Button("New Product");
			Button btnEdit = new Button("Edit Product");
			Button btnDelete = new Button("Delete Product");
			
			VBox VBoxDisplayInfor = new VBox();   //VBox to display the information of that product
			VBoxDisplayInfor.setAlignment(Pos.TOP_RIGHT);
			
			// Button new
			btnNew.setOnAction(ae -> {
				ProductUpdateCreateStage productCreateStage = new ProductUpdateCreateStage(manager);
				productCreateStage.createNewProduct(manager);
				
				
				fillListView(manager);
				
				
				if(!manager.getProducts().getProductList().isEmpty()) {
					listViewDisplay.requestFocus();
					listViewDisplay.getSelectionModel().select(0);
				}

			});
			
			//Button Edit
			btnEdit.setOnAction(ae -> {
				String selectedProduct = listViewDisplay.getSelectionModel().getSelectedItem();
				int selectedProductPos = listViewDisplay.getSelectionModel().getSelectedIndex();
				if (selectedProduct != null) {
					ProductUpdateCreateStage propductUpdateStage = new ProductUpdateCreateStage(manager);
					propductUpdateStage.getProductGridPane().getProductIDField().setEditable(false);
					propductUpdateStage.getProductGridPane().fillGrid(selectedProduct,manager);
					
					propductUpdateStage.updateProduct(manager);
					
					fillListView(manager);
					if(!manager.getProducts().getProductList().isEmpty()) {
						listViewDisplay.requestFocus();
						listViewDisplay.getSelectionModel().select(selectedProductPos);
					}
				} else {

					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("No product selected");
					alert.setContentText("Please select a product to edit.");
					alert.showAndWait();

				}

			});

			btnDelete.setOnAction(ae -> {

				Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
				deleteAlert.setTitle("Confirmation Dialog");
				deleteAlert.setHeaderText("Are you sure you want to delete this product? ");
				deleteAlert.setContentText("This action cannot be undone.");

				Optional<ButtonType> deleteResult = deleteAlert.showAndWait();

				if (deleteResult.isPresent()) { // Check user confirmation
					String selectedProduct = listViewDisplay.getSelectionModel().getSelectedItem(); // Get selected
																									// product from
																									// ListView

					if (selectedProduct != null) { // Delete the product using manager's deleteProduct method
						String output = manager.getProducts().deleteProduct(selectedProduct, manager.getCountries().getCountries());
						if (output == "") {
							manager.deleteProduct(selectedProduct);
							fillListView(manager);

							if (!manager.getProducts().getProductList().isEmpty()) {  // if the ProductList is not empty
								listViewDisplay.requestFocus(); 
								listViewDisplay.getSelectionModel().select(0); //Selects the first item (index 0) in the list view.
							} else {
								productGridPane.setValueToTextField(TextFieldName.productIDField, "");
								productGridPane.setValueToTextField(TextFieldName.nameField, "");
								productGridPane.setValueToTextField(TextFieldName.expirationInDaysField, "");
								productGridPane.setValueToTextField(TextFieldName.recommendedRateField, "");
							}

						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Failed to delete product");
							alert.setContentText(output);
							alert.showAndWait();
						}
					} else {

						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("No product selected");
						alert.setContentText("Please select a product to delete.");
						alert.showAndWait();

					}
				}

			});
			
			root.getChildren().addAll(new HBox[] { HBoxButton, HBoxInfor, });

			VBox.setMargin(HBoxButton, new Insets(10, 0, 0, 0));
			VBox.setVgrow(HBoxInfor, Priority.ALWAYS);
			
			HBoxButton.getChildren().addAll(new Button[] {btnNew, btnEdit, btnDelete});
			HBoxInfor.getChildren().add(listViewDisplay);
			HBoxInfor.getChildren().addAll(VBoxDisplayInfor);
			fillListView(manager);
			listViewDisplay.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			

			HBox.setMargin(listViewDisplay, new Insets(0,10,10,10));
			productGridPane = buildUIProductFields();
			VBoxDisplayInfor.getChildren().add(productGridPane.getGrid());
			
			listViewDisplay.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null) {
	            	productGridPane.fillGrid(String.valueOf(newValue), manager);
	            }
			});
			
			this.setScene(new Scene(root,750,750));
			
			this.setOnShown(event -> {
				Platform.runLater(() -> {
					if(!manager.getCountries().getCountries().isEmpty()) {
						listViewDisplay.requestFocus();
						listViewDisplay.getSelectionModel().select(0);
					}
				});
			});
				
		}
		
		// refresh the listViewDisplay component with product IDs from the list of products
		//updating the UI whenever the product list changes
		public void fillListView(Manager manager) {
			listViewDisplay.getItems().clear();
			for(Product c : manager.getProducts().getProductList()) {
				listViewDisplay.getItems().add(c.getProductID()); 
			}
		}
		
		public ProductsGridPane buildUIProductFields() {
			ProductsGridPane productGridPane = new ProductsGridPane();
			
			productGridPane.getProductIDField().setEditable(true);
			productGridPane.getNameField().setEditable(true);
			productGridPane.getExpirationInDaysField().setEditable(true);
			productGridPane.getRecommendedRateField().setEditable(true);
			
			return productGridPane;
		}
		
	}
