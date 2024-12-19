package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.ProductsByCountry;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.Product;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.Priority;

public class ProductsByCountryGridPane {

	private GridPane gridPane;

	private TextField productByCountryIdField;

	private TextField productionField;

	private TextField priceField;

	private ComboBox<String> ProductsByCountryComboBox;

	/**
	 * 
	 * CalcType
	 * 
	 * The enum is like a list of choices or options you can pick from.
	 * 
	 */

	public enum TextFieldName {

		productByCountryIdField, productionField, priceField, ProductsByCountryComboBox

	}

	public ProductsByCountryGridPane() {

		buildUI();

	}

	public GridPane getMyGridPane() {

		return gridPane;

	}

	public void setMyGridPane(GridPane gridPane) {

		this.gridPane = gridPane;

	}

	public TextField getProductByCountryIdField() {

		return productByCountryIdField;

	}

	public void setProductByCountryIdField(TextField productByCountryId) {

		this.productByCountryIdField = productByCountryId;

	}

	public TextField getProductionField() {

		return productionField;

	}

	public void setProductionField(TextField productionField) {

		this.productionField = productionField;

	}

	public TextField getPriceField() {

		return priceField;

	}

	public void setPriceField(TextField priceField) {

		this.priceField = priceField;

	}

	public ComboBox<String> getProductsByCountryComboBox() {

		return ProductsByCountryComboBox;

	}

	public void setProductsByCountryComboBox(ComboBox<String> productsByCountryComboBox) {

		ProductsByCountryComboBox = productsByCountryComboBox;

	}

	// creating the graphical user interface

	public void buildUI() {

		// Create Label that display the names for each input field

		Label productByCountryIdLabel = new Label("Product by country ID");

		Label productionLabel = new Label("Production");

		Label priceLabel = new Label("Price");

		Label ProductsByCountryComboBoxLabel = new Label("Product ");

		// actual boxes (TextField) where users can type in the ID, Name..

		this.productByCountryIdField = new TextField();

		this.productionField = new TextField();

		this.priceField = new TextField();

		this.ProductsByCountryComboBox = new ComboBox<String>();

		this.gridPane = new GridPane();

		gridPane.setHgap(10); // sets the horizontal space between columns.

		gridPane.setVgap(5); // sets the vertical space between rows.

		// places each element in a specific cell in the grid , (0, 0) represent the
		// column and row where the element should appear.

		// Label

		gridPane.add(productByCountryIdLabel, 0, 0);

		gridPane.add(productionLabel, 0, 1);

		gridPane.add(priceLabel, 0, 2);

		gridPane.add(ProductsByCountryComboBoxLabel, 0, 3);

		// Input field

		gridPane.add(this.productByCountryIdField, 1, 0);

		gridPane.add(this.productionField, 1, 1);

		gridPane.add(this.priceField, 1, 2);

		gridPane.add(this.ProductsByCountryComboBox, 1, 3);

		// ensure that the TextField elements stretch horizontally to fill extra space
		// if the window is resized.

		GridPane.setHgrow(this.productByCountryIdField, Priority.ALWAYS);

		GridPane.setHgrow(this.productionField, Priority.ALWAYS);

		GridPane.setHgrow(this.priceField, Priority.ALWAYS);

		GridPane.setHgrow(this.ProductsByCountryComboBox, Priority.ALWAYS);

	}

//	public void fillFromProductsByCountry(ProductsByCountry productByCountry) {

//        this.productByCountryIdField.setText(productByCountry.getProductByCountryId());

//        this.productionField.setText(String.valueOf(productByCountry.getProduction()));

//        this.priceField.setText(String.valueOf(productByCountry.getPrice()));

//    }

	public void fillGrid(String newValue, Manager manager, Country country) {

		int productsByCountryPos = country.searchProductByCountryID(newValue);

		if (productsByCountryPos != -1) {

			ProductsByCountry productsByCountry = country.getProducts().get(productsByCountryPos);

			this.productByCountryIdField.setText(productsByCountry.getProductByCountryId());

			this.productionField.setText((String.valueOf(productsByCountry.getProduction())));

			this.priceField.setText((String.valueOf(productsByCountry.getPrice())));

			this.ProductsByCountryComboBox.setValue(productsByCountry.getProduct().getProductID());

		}

	}

	public void fillProductsByCountryComboBox(Manager manager, Country country) {

		if (country.getProducts() != null) {

			for (Product p : manager.getProducts().getProductList()) {

				ProductsByCountryComboBox.getItems().add(p.getProductID());

			}

		}

		if (!ProductsByCountryComboBox.getItems().isEmpty()) {

			ProductsByCountryComboBox.setValue(ProductsByCountryComboBox.getItems().get(0));

		}

	}

	public String getValueFromTextField(TextFieldName textFieldName) {

		switch (textFieldName) {

		case productByCountryIdField:

			return productByCountryIdField.getText();

		case productionField:

			return productionField.getText();

		case priceField:

			return priceField.getText();

		case ProductsByCountryComboBox:

			return ProductsByCountryComboBox.getSelectionModel().getSelectedItem();

		default:

			return "";

		}

	}

	public void setValueToTextField(TextFieldName textFieldName, String text) {

		switch (textFieldName) {

		case productByCountryIdField:

			productByCountryIdField.setText(text);

		case productionField:

			productionField.setText(text);

		case priceField:

			priceField.setText(text);

		case ProductsByCountryComboBox:

			if (!ProductsByCountryComboBox.getItems().isEmpty()) {

				if (text.matches("-?\\d+")) {

					int pos = Integer.parseInt(text);

					if ((pos <= ProductsByCountryComboBox.getItems().size()) && (pos >= 0)) {

						ProductsByCountryComboBox.setValue(ProductsByCountryComboBox.getItems().get(pos));

					}

				}

			}

		default:

		}

	}

}