package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Product;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ProductsGridPane {
	private GridPane grid;
	private TextField productIDField;
	private TextField nameField;
	private TextField expirationInDaysField;
	private TextField recommendedRateField;

	/**
	 * CalcType The enum is like a list of choices or options you can pick from.
	 */
	public enum TextFieldName {
		productIDField, nameField, expirationInDaysField, recommendedRateField
	}

	// Constructor
	public ProductsGridPane() {
		buildUI();
	}

	public GridPane getGrid() {
		return grid;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	public TextField getProductIDField() {
		return productIDField;
	}

	public void setProductIDField(TextField productIDField) {
		this.productIDField = productIDField;
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public TextField getExpirationInDaysField() {
		return expirationInDaysField;
	}

	public void setExpirationInDaysField(TextField expirationInDaysField) {
		this.expirationInDaysField = expirationInDaysField;
	}

	public TextField getRecommendedRateField() {
		return recommendedRateField;
	}

	public void setRecommendedRateField(TextField recommendedRateField) {
		this.recommendedRateField = recommendedRateField;
	}

	// creating the graphical user interface
	public void buildUI() {
		// Create Label that display the names for each input field
		Label productIDLabel = new Label("Product ID");
		Label nameLabel = new Label("Product's Name");
		Label expirationInDaysLabel = new Label("The amount of days till expiration date");
		Label recommendedRateLabel = new Label("Recommended Rate per year ");

		// actual boxes (TextField) where users can type in the ID, Name..
		this.productIDField = new TextField();
		this.nameField = new TextField();
		this.expirationInDaysField = new TextField();
		this.recommendedRateField = new TextField();

		this.grid = new GridPane();
		grid.setHgap(10); // sets the horizontal space between columns.
		grid.setVgap(5); // sets the vertical space between rows.

		// places each element in a specific cell in the grid , (0, 0) represent the
		// column and row where the element should appear.
		// Label
		grid.add(productIDLabel, 0, 0);
		grid.add(nameLabel, 0, 1);
		grid.add(expirationInDaysLabel, 0, 2);
		grid.add(recommendedRateLabel, 0, 3);
		// Input field
		grid.add(this.productIDField, 1, 0);
		grid.add(this.nameField, 1, 1);
		grid.add(this.expirationInDaysField, 1, 2);
		grid.add(this.recommendedRateField, 1, 3);

		// ensure that the TextField elements stretch horizontally to fill extra space
		// if the window is resized.
		GridPane.setHgrow(this.productIDField, Priority.ALWAYS);
		GridPane.setHgrow(this.nameField, Priority.ALWAYS);
		GridPane.setHgrow(this.expirationInDaysField, Priority.ALWAYS);
		GridPane.setHgrow(this.recommendedRateField, Priority.ALWAYS);

	}

	// used to update text fields in the UI with information about a specific
	// product.
	public void fillGrid(String newValue, Manager manager) {
		int productPos = manager.getProducts().searchProduct(newValue); // Search for the Product
		if (productPos != -1) { // if existed
			Product product = manager.getProducts().getProductList().get(productPos); // Retrieve the Product:
			this.productIDField.setText(product.getProductID()); // Update the Text Fields
			this.nameField.setText(product.getName());
			this.expirationInDaysField.setText(String.valueOf(product.getExpirationInDays()));
			this.recommendedRateField.setText(String.valueOf(product.getRecommendedRate()));
		}

	}

	public String getValueFromTextField(TextFieldName textFieldName) {
		switch (textFieldName) {
		case productIDField:
			return productIDField.getText();
		case nameField:
			return nameField.getText();
		case expirationInDaysField:
			return expirationInDaysField.getText();
		case recommendedRateField:
			return recommendedRateField.getText();
		default:
			return "";
		}
	}

	public void setValueToTextField(TextFieldName textFieldName, String text) {
		switch (textFieldName) {
		case productIDField:
			productIDField.setText(text);
		case nameField:
			nameField.setText(text);
		case expirationInDaysField:
			expirationInDaysField.setText(text);
		case recommendedRateField:
			recommendedRateField.setText(text);
		default:
		}
	}

}
