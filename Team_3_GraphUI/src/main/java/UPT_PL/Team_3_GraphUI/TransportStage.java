package UPT_PL.Team_3_GraphUI;

import java.util.Optional;

import UPT_PL.Team_3_GraphUI.TransportGridPane.TextFieldName;
import UPT_PL.Team_3.model.Transport;
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

public class TransportStage extends Stage {

    private ListView<String> listViewCtrl;
    private TransportGridPane transportGridPane;

    /**
     * Constructor
     */
    public TransportStage(Manager manager) {
        listViewCtrl = new ListView<>();
        buildUI(manager);
    }

    public void buildUI(Manager manager) {
        this.setTitle("Transports");
        this.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_LEFT);

        HBox HButtonsBox = new HBox();
        HButtonsBox.setSpacing(10);
        HButtonsBox.setAlignment(Pos.CENTER);
        HBox HInfoBox = new HBox();
        HInfoBox.setSpacing(10);

        Button btnNew = new Button("New");
        Button btnEdit = new Button("Edit");
        Button btnDelete = new Button("Delete");

        VBox VBoxResult = new VBox();

        btnNew.setOnAction(ae -> {
            TransportUpdateCreateStage transportCreateStage = new TransportUpdateCreateStage(manager);
            transportCreateStage.createNewTransport(manager);

            fillListView(manager);

            if (!manager.getTransports().getTransports().isEmpty()) {
                listViewCtrl.requestFocus();
                listViewCtrl.getSelectionModel().select(0);
            }
        });

        btnEdit.setOnAction(ae -> {
            String selectedTransport = listViewCtrl.getSelectionModel().getSelectedItem();
            int selectedTransportPos = listViewCtrl.getSelectionModel().getSelectedIndex();
            if (selectedTransport != null) {
                TransportUpdateCreateStage transportUpdateStage = new TransportUpdateCreateStage(manager);
                transportUpdateStage.getTransportGridPane().getTransportIdField().setEditable(false);
                transportUpdateStage.getTransportGridPane().fillGrid(selectedTransport, manager);

                transportUpdateStage.updateTransport(manager);

                fillListView(manager);
                if (!manager.getTransports().getTransports().isEmpty()) {
                    listViewCtrl.requestFocus();
                    listViewCtrl.getSelectionModel().select(selectedTransportPos);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No transport selected");
                alert.setContentText("Please select a transport to edit.");
                alert.showAndWait();
            }
        });

        btnDelete.setOnAction(ae -> {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Confirmation Dialog");
            deleteAlert.setHeaderText("Are you sure you want to delete this transport?");
            deleteAlert.setContentText("This action cannot be undone.");

            Optional<ButtonType> deleteResult = deleteAlert.showAndWait();
            
            if (deleteResult.isPresent() && deleteResult.get() == ButtonType.OK) {
                String selectedTransport = listViewCtrl.getSelectionModel().getSelectedItem();

                if (selectedTransport != null) {
                    String output = manager.getTransports().deleteTransport(selectedTransport, manager.getCountries().getCountries());
                    if (output.equals("Transport successfully deleted.")) {
                        manager.deleteTransport(selectedTransport);
                        fillListView(manager);

                        if (!manager.getTransports().getTransports().isEmpty()) {
                            listViewCtrl.requestFocus();
                            listViewCtrl.getSelectionModel().select(0);
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Failed to delete transport");
                        alert.setContentText(output);
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("No transport selected");
                    alert.setContentText("Please select a transport to delete.");
                    alert.showAndWait();
                }
            }
        });

        root.getChildren().addAll(HButtonsBox, HInfoBox);
        VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));
        VBox.setVgrow(HInfoBox, Priority.ALWAYS);

        HButtonsBox.getChildren().addAll(btnNew, btnEdit, btnDelete);

        fillListView(manager);
        listViewCtrl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        HInfoBox.getChildren().add(listViewCtrl);
        HInfoBox.getChildren().add(VBoxResult);
        HBox.setMargin(listViewCtrl, new Insets(0, 10, 10, 10));

        transportGridPane = buildUITransportFields();
        VBoxResult.getChildren().add(transportGridPane.getGrid());

        listViewCtrl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                transportGridPane.fillGrid(newValue, manager);
            }
        });

        this.setScene(new Scene(root, 750, 750));

        this.setOnShown(event -> {
            Platform.runLater(() -> {
                if(!manager.getCountries().getCountries().isEmpty()) {
                    listViewCtrl.requestFocus();
                    listViewCtrl.getSelectionModel().select(0);
                }
            });
        });
    }

    public void fillListView(Manager manager) {
        listViewCtrl.getItems().clear();
        for (Transport t : manager.getTransports().getTransports()) {
            listViewCtrl.getItems().add(t.getTransportId());
        }
    }

    public TransportGridPane buildUITransportFields() {
        TransportGridPane transportGridPane = new TransportGridPane();

        transportGridPane.getTransportIdField().setEditable(false);
        transportGridPane.getNameField().setEditable(false);
        transportGridPane.getPricePerTonField().setEditable(false);

        return transportGridPane;
    }
}



