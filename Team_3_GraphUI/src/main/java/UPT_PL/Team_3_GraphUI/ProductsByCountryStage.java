package UPT_PL.Team_3_GraphUI;
import java.util.Optional;

import UPT_PL.Team_3.model.Country;

import UPT_PL.Team_3.model.Product;

import UPT_PL.Team_3_GraphUI.ProductsByCountryGridPane;

import UPT_PL.Team_3_GraphUI.ProductsByCountryGridPane.TextFieldName;

import UPT_PL.Team_3.model.ProductsByCountry;

import javafx.application.Platform;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.ButtonType;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.control.SelectionMode;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.Priority;

import javafx.scene.layout.VBox;

import javafx.stage.Modality;

import javafx.stage.Stage;



public class ProductsByCountryStage extends Stage {

	private ListView<String> listViewDisplay;

	private ProductsByCountryGridPane productsByCountryGridPane;

	//Constructor

	public ProductsByCountryStage(Manager manager, Country country) {

		listViewDisplay = new ListView<String>();

		buildUI(manager, country);

	}



	    //This method builds the user interface layout and assigns behavior to the buttons:

		public void buildUI(Manager manager, Country country) { 

			this.setTitle("Products by Country");

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

			

			Button btnNew = new Button("New Product by Country");

			Button btnEdit = new Button("Edit Product by Country");

			Button btnDelete = new Button("Delete Product by Country");

			

			VBox VBoxDisplayInfor = new VBox();   //VBox to display the information of that product

			VBoxDisplayInfor.setAlignment(Pos.TOP_RIGHT);

			

			// Button new

						btnNew.setOnAction(ae -> {

							ProductsByCountryUpdateCreateStage productByCountryCreateStage = new ProductsByCountryUpdateCreateStage(manager,country);

							productByCountryCreateStage.createNewProductsByCountry(manager, country);

	

							

							fillListView(manager, country);

							

							

							if(!country.getProducts().isEmpty()) {

								listViewDisplay.requestFocus();

								listViewDisplay.getSelectionModel().select(0);

							}



						});

						

						//Button Edit

						btnEdit.setOnAction(ae -> {

							String selectedProductsByCountry = listViewDisplay.getSelectionModel().getSelectedItem();

							int selectedProductPos = listViewDisplay.getSelectionModel().getSelectedIndex();

							if (selectedProductsByCountry != null) {

								ProductsByCountryUpdateCreateStage  productByCountryCreateStage = new ProductsByCountryUpdateCreateStage(manager,country);

								

								productByCountryCreateStage.getProductsByCountryGridPane().getProductsByCountryComboBox().setDisable(true);

								productByCountryCreateStage.getProductsByCountryGridPane().fillGrid(selectedProductsByCountry,manager, country);

								

								productByCountryCreateStage.updateProductsByCopuntry(manager, country);

								

								fillListView(manager, country);

								if(!country.getProducts().isEmpty()) {

									listViewDisplay.requestFocus();

									listViewDisplay.getSelectionModel().select(selectedProductPos);

								}

							} else {



								Alert alert = new Alert(Alert.AlertType.WARNING);

								alert.setTitle("Warning");

								alert.setHeaderText("No product by country selected");

								alert.setContentText("Please select a product by country to edit.");

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

									String output = country.deleteProductsByCountry(selectedProduct);

									if (output == "") {

										manager.deleteProductsByCountry(selectedProduct);

										fillListView(manager, country);



										if (!country.getProducts().isEmpty()) {  // if the ProductList is not empty

											listViewDisplay.requestFocus(); 

											listViewDisplay.getSelectionModel().select(0); //Selects the first item (index 0) in the list view.

										} else {

											productsByCountryGridPane.setValueToTextField(TextFieldName.productionField, "");

											productsByCountryGridPane.setValueToTextField(TextFieldName.priceField, "");

											productsByCountryGridPane.setValueToTextField(TextFieldName.ProductsByCountryComboBox, "");

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

						fillListView(manager, country);

						listViewDisplay.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

						



						HBox.setMargin(listViewDisplay, new Insets(0,10,10,10));

						productsByCountryGridPane = buildUIProductsByCountryFields();

						VBoxDisplayInfor.getChildren().add(productsByCountryGridPane.getMyGridPane());

						

						listViewDisplay.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

				            if (newValue != null) {

				            	productsByCountryGridPane.fillGrid(String.valueOf(newValue), manager, country);

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

				public void fillListView(Manager manager, Country country) {

					listViewDisplay.getItems().clear();

					for(Product c : manager.getProducts().getProductList()) {

						listViewDisplay.getItems().add(c.getProductID()); 

					}

				}

				

				public ProductsByCountryGridPane buildUIProductsByCountryFields() {

					ProductsByCountryGridPane productsByCountryGridPane = new ProductsByCountryGridPane();

					

					productsByCountryGridPane.getProductByCountryIdField().setEditable(true);

					productsByCountryGridPane.getProductionField().setEditable(true);

					productsByCountryGridPane.getPriceField().setEditable(true);

					productsByCountryGridPane.getProductsByCountryComboBox().setDisable(true);

					

					return productsByCountryGridPane;

				}

				

}