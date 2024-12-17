package UPT_PL.Team_3_GraphUI;

import java.util.Optional;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3_GraphUI.LogisticsSiteGridPane.TextFieldName;
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

public class LogisticsSitesStage extends Stage {

    private ListView<String> listViewCtrl;
    private LogisticsSiteGridPane logisticsSiteGridPane;

    /**
     * Constructor
     */
    public LogisticsSitesStage(Manager manager, Country country) {
		listViewCtrl = new ListView<>();
        buildUI(manager, country);
    }

    public void buildUI(Manager manager, Country country) {
        this.setTitle("Logistics Sites for " + country.getName());
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
            LogisticsSiteUpdateCreateStage logisticsSiteCreateStage = new LogisticsSiteUpdateCreateStage(manager, country);
            logisticsSiteCreateStage.createNewLogisticsSite(manager, country);

            fillListView(manager, country);

            if (!country.getSites().isEmpty()) {
                listViewCtrl.requestFocus();
                listViewCtrl.getSelectionModel().select(0);
            }
        });

        btnEdit.setOnAction(ae -> {
            String selectedSite = listViewCtrl.getSelectionModel().getSelectedItem();
            int selectedSitePos = listViewCtrl.getSelectionModel().getSelectedIndex();
            if (selectedSite != null) {
                LogisticsSiteUpdateCreateStage logisticsSiteUpdateStage = new LogisticsSiteUpdateCreateStage(manager, country);
                logisticsSiteUpdateStage.getLogisticsSiteGridPane().getSiteIdField().setEditable(false);
                logisticsSiteUpdateStage.getLogisticsSiteGridPane().getListViewCtrlTransport().setEditable(false);
                logisticsSiteUpdateStage.getLogisticsSiteGridPane().fillGrid(selectedSite, manager, country);

                logisticsSiteUpdateStage.updateLogisticsSite(manager, country);

                fillListView(manager, country);
                if (!country.getSites().isEmpty()) {
                    listViewCtrl.requestFocus();
                    listViewCtrl.getSelectionModel().select(selectedSitePos);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No logistics site selected");
                alert.setContentText("Please select a logistics site to edit.");
                alert.showAndWait();
            }
        });

        btnDelete.setOnAction(ae -> {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Confirmation Dialog");
            deleteAlert.setHeaderText("Are you sure you want to delete this logistics site?");
            deleteAlert.setContentText("This action cannot be undone.");

            Optional<ButtonType> deleteResult = deleteAlert.showAndWait();

            if (deleteResult.isPresent() && deleteResult.get() == ButtonType.OK) {
                String selectedSite = listViewCtrl.getSelectionModel().getSelectedItem();
                if (selectedSite != null) {
                    String output = country.deleteLogisticsSite(selectedSite,
                    		manager.getLogisticsSupplyChains(), manager.getCountries().getCountries());
                    if (output == "") {
                        manager.deleteLogisticsSite(selectedSite);
                        fillListView(manager, country);

                        if (!country.getSites().isEmpty()) {
                            listViewCtrl.requestFocus();
                            listViewCtrl.getSelectionModel().select(0);
                        } else {
                            logisticsSiteGridPane.setValueToTextField(TextFieldName.SiteIdField, "");
                            logisticsSiteGridPane.setValueToTextField(TextFieldName.NameField, "");
                            logisticsSiteGridPane.getListViewCtrlTransport().getItems().clear();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Failed to delete logistics site");
                        alert.setContentText(output);
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("No logistics site selected");
                    alert.setContentText("Please select a logistics site to delete.");
                    alert.showAndWait();
                }
            }
        });

      

        root.getChildren().addAll(HButtonsBox, HInfoBox);
        VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));
        VBox.setVgrow(HInfoBox, Priority.ALWAYS);

        HButtonsBox.getChildren().addAll(btnNew, btnEdit, btnDelete);

        fillListView(manager, country);
        listViewCtrl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        HInfoBox.getChildren().add(listViewCtrl);
        HInfoBox.getChildren().add(VBoxResult);
        HBox.setMargin(listViewCtrl, new Insets(0, 10, 10, 10));

        logisticsSiteGridPane = buildUILogisticsSiteFields();
        VBoxResult.getChildren().add(logisticsSiteGridPane.getMyGrid());

        listViewCtrl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logisticsSiteGridPane.fillGrid(String.valueOf(newValue), manager, country);
            }
        });

        this.setScene(new Scene(root, 750, 750));

        this.setOnShown(event -> {
            Platform.runLater(() -> {
                if (!country.getSites().isEmpty()) {
                    listViewCtrl.requestFocus();
                    listViewCtrl.getSelectionModel().select(0);
                }
            });
        });
    }

    public void fillListView(Manager manager, Country country) {
        listViewCtrl.getItems().clear();
        for (LogisticsSite s : country.getSites()) {
            listViewCtrl.getItems().add(s.getSiteId());
        }
    }

    public LogisticsSiteGridPane buildUILogisticsSiteFields() {
        LogisticsSiteGridPane logisticsSiteGridPane = new LogisticsSiteGridPane();

        logisticsSiteGridPane.getSiteIdField().setEditable(false);
        logisticsSiteGridPane.getNameField().setEditable(false);

        return logisticsSiteGridPane;
    }
}

