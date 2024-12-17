package UPT_PL.Team_3_GraphUI;

import java.util.ArrayList;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.model.Transport;
import UPT_PL.Team_3_GraphUI.LogisticsSiteGridPane.TextFieldName;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogisticsSiteUpdateCreateStage extends Stage {
    
    private LogisticsSiteGridPane logisticsSiteGridPane;
    private Button btnOK;

    /**
     * Constructor
     */
    public LogisticsSiteUpdateCreateStage(Manager manager, Country country) {
        buildUI(manager, country);
    }

    /**
     * @return the logisticsSiteGridPane
     */
    public LogisticsSiteGridPane getLogisticsSiteGridPane() {
        return logisticsSiteGridPane;
    }

    /**
     * @param logisticsSiteGridPane the logisticsSiteGridPane to set
     */
    public void setLogisticsSiteGridPane(LogisticsSiteGridPane logisticsSiteGridPane) {
        this.logisticsSiteGridPane = logisticsSiteGridPane;
    }

    public void buildUI(Manager manager, Country country) {
        this.initModality(Modality.APPLICATION_MODAL);

        logisticsSiteGridPane = new LogisticsSiteGridPane();
        logisticsSiteGridPane.getMyGrid().setPadding(new Insets(20, 20, 20, 20));
        logisticsSiteGridPane.getMyGrid().setAlignment(Pos.TOP_CENTER);
        logisticsSiteGridPane.fillListViewCtrlTransport(manager);

        HBox HButtonsBox = new HBox();
        HButtonsBox.setAlignment(Pos.TOP_RIGHT);
        HButtonsBox.setSpacing(10);
        btnOK = new Button("OK");
        Button btnCancel = new Button("Cancel");

        btnCancel.setOnAction(ae -> {
            this.close();
        });

        HButtonsBox.getChildren().addAll(btnOK, btnCancel);
        logisticsSiteGridPane.getMyGrid().add(HButtonsBox, 1, 4);
        this.setScene(new Scene(logisticsSiteGridPane.getMyGrid(), 500, 500));
    }

    public void createNewLogisticsSite(Manager manager, Country country) {
        this.setTitle("Create Logistics Site"); 
        logisticsSiteGridPane.getListViewCtrlTransport().getSelectionModel().clearSelection();
        logisticsSiteGridPane.getListViewCtrlTransport().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        btnOK.setOnAction(ae -> {
        	ObservableList<String> items = logisticsSiteGridPane.getListViewCtrlTransport().getItems();
        	ArrayList<Transport> transports = new ArrayList<Transport>();
        	for(String s : items) {
        		int pos = manager.getTransports().searchTransport(s);
        		if(pos != -1) {
        			transports.add(manager.getTransports().getTransportList().get(pos));
        		}
        	}
            String siteId = logisticsSiteGridPane.getValueFromTextField(TextFieldName.SiteIdField);
            String name = logisticsSiteGridPane.getValueFromTextField(TextFieldName.NameField);
            
            LogisticsSite newSite = new LogisticsSite();
            String output = country.addLogisticsSite(manager.getCountries().getCountries(), transports, siteId, name, newSite) ;
            if (output.isEmpty()) {
                manager.addLogisticsSitesToCountry(newSite);
                this.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to add logistics site");
                alert.setContentText(output);
                alert.showAndWait();
                logisticsSiteGridPane.setValueToTextField(TextFieldName.SiteIdField, "");
                logisticsSiteGridPane.setValueToTextField(TextFieldName.NameField, "");
            }
        });
        this.showAndWait();
    }

    public void updateLogisticsSite(Manager manager, Country country) {
        this.setTitle("Update Logistics Site"); 
        btnOK.setOnAction(ae -> {
        	  String siteId = logisticsSiteGridPane.getValueFromTextField(TextFieldName.SiteIdField);
              String name = logisticsSiteGridPane.getValueFromTextField(TextFieldName.NameField);
              
            LogisticsSite editedSite = new LogisticsSite();
            editedSite.setSiteId(siteId);
            String output = country.updateLogisticsSite(manager.getCountries().getCountries(), siteId, name, editedSite);
            
            if (output.isEmpty()) {
                manager.updateLogisticsSite(editedSite);
                this.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to edit logistics site");
                alert.setContentText(output);
                alert.showAndWait();
            }
        });
        this.showAndWait();
    }
}
