package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Product;
import UPT_PL.Team_3.model.Transport;
import UPT_PL.Team_3_GraphUI.TransportGridPane.TextFieldName;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TransportUpdateCreateStage extends Stage {

    private TransportGridPane transportGridPane;
    private Button btnOK;

    /**
     * Constructor
     */
    public TransportUpdateCreateStage(Manager manager) {
        buildUI(manager);
    }

    /**
     * @return the transportGridPane
     */
    public TransportGridPane getTransportGridPane() {
        return transportGridPane;
    }

    /**
     * @param transportGridPane the transportGridPane to set
     */
    public void setTransportGridPane(TransportGridPane transportGridPane) {
        this.transportGridPane = transportGridPane;
    }

    public void buildUI(Manager manager) {
        this.initModality(Modality.APPLICATION_MODAL);

        transportGridPane = new TransportGridPane();
        transportGridPane.getGrid().setPadding(new Insets(20, 20, 20, 20));
        transportGridPane.getGrid().setAlignment(Pos.TOP_CENTER);

        HBox HButtonsBox = new HBox();
        HButtonsBox.setAlignment(Pos.TOP_RIGHT);
        HButtonsBox.setSpacing(10);
        btnOK = new Button("OK");
        Button btnCancel = new Button("Cancel");

        btnCancel.setOnAction(ae -> {
            this.close();
        });

        HButtonsBox.getChildren().addAll(new Button[] { btnOK, btnCancel });
        transportGridPane.getGrid().add(HButtonsBox, 1, 3);
        this.setScene(new Scene(transportGridPane.getGrid(), 500, 500));
    }

    public void createNewTransport(Manager manager) {
        this.setTitle("Create Transport");
        btnOK.setOnAction(ae -> {
            String transportId = transportGridPane.getValueFromTextField(TextFieldName.TransportIdField);
            String name = transportGridPane.getValueFromTextField(TextFieldName.NameField);
            String pricePerTon = transportGridPane.getValueFromTextField(TextFieldName.PricePerTonField);

            Transport newTransport = new Transport();
            String output = manager.getTransports().addTransport(transportId, name, pricePerTon, newTransport);
            if (output == "") { 
                manager.addTransport(newTransport);
                this.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to add transport");
                alert.setContentText(output);
                alert.showAndWait();
                transportGridPane.setValueToTextField(TextFieldName.TransportIdField, "");
                transportGridPane.setValueToTextField(TextFieldName.NameField, "");
                transportGridPane.setValueToTextField(TextFieldName.PricePerTonField, "");
            }
        });
        this.showAndWait();
    }

	public void updateTransport(Manager manager) {
		btnOK.setOnAction(ae -> {
			String transportID = transportGridPane.getValueFromTextField(TextFieldName.TransportIdField);
			String name = transportGridPane.getValueFromTextField(TextFieldName.NameField);
			String priceperton = transportGridPane.getValueFromTextField(TextFieldName.PricePerTonField);
			Transport editedTransport = new Transport();
			
			editedTransport.setTransportId(transportID);
			String output = manager.getTransports().updateTransport(transportID, name, priceperton, editedTransport);
			if(output == "") {
				manager.updateTransport(editedTransport);
				this.close();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Failed to update transport");
	            alert.setContentText(output);
	            alert.showAndWait();
			}
		});
		this.showAndWait();
	}


}
