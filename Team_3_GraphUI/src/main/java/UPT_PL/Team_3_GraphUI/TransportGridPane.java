package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Transport;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TransportGridPane extends GridPane {

    private GridPane grid;
    private TextField transportIdField;
    private TextField nameField;
    private TextField pricePerTonField;

    /**
     * TextFieldName enum for identifying fields.
     */
    public enum TextFieldName {
        TransportIdField, NameField, PricePerTonField;
    }

    public TransportGridPane() {
        buildUI();
    }

    /**
     * @return the grid
     */
    public GridPane getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    /**
     * @return the transportIdField
     */
    public TextField getTransportIdField() {
        return transportIdField;
    }

    /**
     * @param transportIdField the transportIdField to set
     */
    public void setTransportIdField(TextField transportIdField) {
        this.transportIdField = transportIdField;
    }

    /**
     * @return the nameField
     */
    public TextField getNameField() {
        return nameField;
    }

    /**
     * @param nameField the nameField to set
     */
    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    /**
     * @return the pricePerTonField
     */
    public TextField getPricePerTonField() {
        return pricePerTonField;
    }

    /**
     * @param pricePerTonField the pricePerTonField to set
     */
    public void setPricePerTonField(TextField pricePerTonField) {
        this.pricePerTonField = pricePerTonField;
    }

    /**
     * Builds the UI for TransportGridPane.
     */
    public void buildUI() {
        Label transportIdLabel = new Label("Transport Id");
        Label nameLabel = new Label("Name");
        Label pricePerTonLabel = new Label("Price Per Ton");

        this.transportIdField = new TextField();
        this.nameField = new TextField();
        this.pricePerTonField = new TextField();

        this.grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.add(transportIdLabel, 0, 0);
        grid.add(nameLabel, 0, 1);
        grid.add(pricePerTonLabel, 0, 2);
        grid.add(this.transportIdField, 1, 0);
        grid.add(this.nameField, 1, 1);
        grid.add(this.pricePerTonField, 1, 2);

        GridPane.setHgrow(this.transportIdField, Priority.ALWAYS);
        GridPane.setHgrow(this.nameField, Priority.ALWAYS);
        GridPane.setHgrow(this.pricePerTonField, Priority.ALWAYS);
    }

    /**
     * Fills the grid with transport data.
     *
     * @param newValue the transport ID to look up
     * @param manager  the manager instance
     */
    public void fillGrid(String newValue, Manager manager) {
        int transportPos = manager.getTransports().searchTransport(newValue);
        if (transportPos != -1) {
            Transport transport = manager.getTransports().getTransports().get(transportPos);
            this.transportIdField.setText(transport.getTransportId());
            this.nameField.setText(transport.getName());
            this.pricePerTonField.setText(String.valueOf(transport.getPricePerTon()));
        }
    }

    /**
     * Gets the value from a specific TextField.
     *
     * @param textFieldName the field to fetch data from
     * @return the value of the field as a string
     */
    public String getValueFromTextField(TextFieldName textFieldName) {
        switch (textFieldName) {
            case TransportIdField:
                return transportIdField.getText();
            case NameField:
                return nameField.getText();
            case PricePerTonField:
                return pricePerTonField.getText();
            default:
                return "";
        }
    }

    /**
     * Sets a value into a specific TextField.
     *
     * @param textFieldName the field to set data into
     * @param text          the value to set
     */
    public void setValueToTextField(TextFieldName textFieldName, String text) {
        switch (textFieldName) {
            case TransportIdField:
                transportIdField.setText(text);
            case NameField:
                nameField.setText(text);
            case PricePerTonField:
                pricePerTonField.setText(text);
            default:
        }
    }
}
