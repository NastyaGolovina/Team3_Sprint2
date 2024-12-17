package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.ProductsByCountry;

import UPT_PL.Team_3.model.Product;

import UPT_PL.Team_3.model.Country;

import UPT_PL.Team_3_GraphUI.ProductsByCountryGridPane.TextFieldName;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import javafx.stage.Modality;

import javafx.stage.Stage;

public class ProductsByCountryUpdateCreateStage extends Stage {

	// instance variables

	private ProductsByCountryGridPane productsByCountryGridPane;

	private Button btnOK;

	/**
	 * 
	 * Constructor
	 * 
	 */

	public ProductsByCountryUpdateCreateStage(Manager manager, Country country) {

		buildUI(manager, country);

	}

	// This method builds the user interface layout and assigns behavior to the
	// buttons:

	public ProductsByCountryGridPane getProductsByCountryGridPane() {

		return productsByCountryGridPane;

	}

	public void setProductsByCountryGridPane(ProductsByCountryGridPane productsByCountryGridPane) {

		this.productsByCountryGridPane = productsByCountryGridPane;

	}

	public void buildUI(Manager manager, Country country) {

		this.setTitle("Products by Country");

		this.initModality(Modality.APPLICATION_MODAL); // block interaction with other windows while this one is open.

		productsByCountryGridPane = new ProductsByCountryGridPane();
		productsByCountryGridPane.getMyGridPane().setPadding(new Insets(20, 20, 20, 20));
		productsByCountryGridPane.getMyGridPane().setAlignment(Pos.TOP_CENTER);
		productsByCountryGridPane.fillProductsByCountryComboBox(manager,country);
		productsByCountryGridPane.getProductByCountryIdField().setEditable(false);
		HBox HButtonsBox = new HBox();

		HButtonsBox.setAlignment(Pos.TOP_RIGHT);

		HButtonsBox.setSpacing(10);

		btnOK = new Button("OK");

		Button btnCancel = new Button("Cancel");

		btnCancel.setOnAction(ae -> {

			this.close();

		});

		HButtonsBox.getChildren().addAll(new Button[] { btnOK, btnCancel });

		productsByCountryGridPane.getMyGridPane().add(HButtonsBox, 1, 4);

		this.setScene(new Scene(productsByCountryGridPane.getMyGridPane(), 500, 500));

	}

	public void createNewProductsByCountry(Manager manager, Country country) {
		ProductsByCountry newProductsByCountry = new ProductsByCountry();
		newProductsByCountry.setProductByCountryIdForUI();
		productsByCountryGridPane.getProductByCountryIdField().setText(newProductsByCountry.getProductByCountryId());
		btnOK.setOnAction(ae -> {

			String production = productsByCountryGridPane.getValueFromTextField(TextFieldName.productionField);

			String price = productsByCountryGridPane.getValueFromTextField(TextFieldName.priceField);

			String productID = productsByCountryGridPane.getValueFromTextField(TextFieldName.ProductsByCountryComboBox);

//		productsByCountryGridPane.getProductsByCountryComboBox().setDisable(true);

			int productPos = manager.getProducts().searchProduct(productID);

			Product newProduct = manager.getProducts().getProductList().get(productPos);

			

			String output = country.addProductsByCountry(production, price, newProduct, newProductsByCountry);

			if (output == "") {

				manager.addProductsByCountry(newProductsByCountry);

				this.close();

			} else {

				Alert alert = new Alert(Alert.AlertType.ERROR);

				alert.setTitle("Error");

				alert.setHeaderText("Failed to add country");

				alert.setContentText(output);

				alert.showAndWait();

				productsByCountryGridPane.setValueToTextField(TextFieldName.productionField, "");

				productsByCountryGridPane.setValueToTextField(TextFieldName.priceField, "");

				productsByCountryGridPane.setValueToTextField(TextFieldName.ProductsByCountryComboBox, "");

			}

		});

		this.showAndWait();

	}

	public void updateProductsByCopuntry(Manager manager, Country country) {

		btnOK.setOnAction(ae -> {

			String production = productsByCountryGridPane.getValueFromTextField(TextFieldName.productionField);

			String price = productsByCountryGridPane.getValueFromTextField(TextFieldName.priceField);

			String productByCountryId = productsByCountryGridPane
					.getValueFromTextField(TextFieldName.productByCountryIdField);

			productsByCountryGridPane.getProductsByCountryComboBox().setDisable(false);

			ProductsByCountry editedProductsByCountry = new ProductsByCountry();

			editedProductsByCountry.setProductByCountryId(productByCountryId);

			String output = country.updateProductsByCountry(productByCountryId, production, price,
					editedProductsByCountry);

			if (output == "") {

				manager.updateProductsByCountry(editedProductsByCountry);

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