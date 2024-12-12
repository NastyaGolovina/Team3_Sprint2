package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3_GraphUI.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogisticsSupplyChainStage extends Stage {

    private ListView<LogisticsSupplyChain> listViewCtrl;
    private LogisticsSupplyChainGridPane logisticsSupplyChainGridPane;

    public LogisticsSupplyChainStage(Manager manager) {
        listViewCtrl = new ListView<>();
        // Set a CellFactory to display the chainId
        listViewCtrl.setCellFactory(lv -> {
            ListCell<LogisticsSupplyChain> cell = new ListCell<LogisticsSupplyChain>() {
                @Override
                protected void updateItem(LogisticsSupplyChain item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getChainId()); // Display the chainId in the ListView
                    }
                }
            };
            return cell;
        });
        buildUI(manager);
    }

    public void buildUI(Manager manager) {
        this.setTitle("Logistics Supply Chains"); 
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
        	LogisticsSupplyChainCreateStage logisticsCreateStage = new LogisticsSupplyChainCreateStage(manager);
        	logisticsCreateStage.createNewLogisticsSupplyChain(manager, () -> refreshSupplyChainList(manager));
        	
        	fillListView(manager);
        	
        	if (!manager.getLogisticsSupplyChains().getSupplyChains().isEmpty()) {
				listViewCtrl.requestFocus();
				listViewCtrl.getSelectionModel().select(0);
        	}
        });

      
        btnEdit.setOnAction(ae -> {
        	LogisticsSupplyChain selectedSupplyChain = listViewCtrl.getSelectionModel().getSelectedItem();
        	if (selectedSupplyChain != null) {
        		int selectedSupplyChainIndex = Integer.parseInt(selectedSupplyChain.getChainId());
        		
        		LogisticsSupplyChainCreateStage logisticsCreateStage = new LogisticsSupplyChainCreateStage(manager);
        		
//        		for (int i = 0; i < manager.getLogisticsSupplyChains().getSupplyChains().size(); i++) {
//        			if (manager.getLogisticsSupplyChains().getSupplyChains().get(i).getChainId().equals(selectedSupplyChain)) {
//        				logisticsCreateStage.getLogisticsSupplyGridPane().fillGrid(String.valueOf(i), manager);
//        			}
//        		}
        		logisticsCreateStage.getLogisticsSupplyGridPane().fillGrid(selectedSupplyChain, manager);
        		
        		logisticsCreateStage.updateSupplyChain(selectedSupplyChain.getChainId(), manager, () -> refreshSupplyChainList(manager));
        		
        		fillListView(manager);
        		if (!manager.getLogisticsSupplyChains().getSupplyChains().isEmpty()) {
					listViewCtrl.requestFocus();
					listViewCtrl.getSelectionModel().select(selectedSupplyChainIndex);
        		}
        	} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("No supply chain selected");
				alert.setContentText("Please select a supply chain to edit.");
				alert.showAndWait();
        	}
        });

        btnDelete.setOnAction(ae -> {
        	LogisticsSupplyChain selectedSupplyChain = listViewCtrl.getSelectionModel().getSelectedItem();

            if (selectedSupplyChain != null) {
            	manager.deleteSupplyChain(Integer.parseInt(selectedSupplyChain.getChainId()));
            	refreshSupplyChainList(manager);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No supply chain selected");
                alert.setContentText("Please select a supply chain to delete.");
                alert.showAndWait();
            }
        });

		root.getChildren().addAll(new HBox[] { HButtonsBox, HInfoBox});
		VBox.setMargin(HButtonsBox, new Insets(10, 0, 0, 0));
		VBox.setVgrow(HInfoBox, Priority.ALWAYS);
		
		HButtonsBox.getChildren().addAll(new Button[] {btnNew, btnEdit, btnDelete});
		fillListView(manager);
		listViewCtrl.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		HInfoBox.getChildren().add(listViewCtrl);
		HInfoBox.getChildren().add(VBoxResult);
		HBox.setMargin(listViewCtrl, new Insets(0,10,10,10));

		logisticsSupplyChainGridPane = buildUISupplyChainFields();
		VBoxResult.getChildren().add(logisticsSupplyChainGridPane.getMyGrid());
		
		listViewCtrl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
            	
//				for (int i = 0; i < manager.getLogisticsSupplyChains().getSupplyChains().size(); i++) {
//					if (manager.getLogisticsSupplyChains().getSupplyChains().get(i).getChainId().equals(newValue)) {
//						logisticsSupplyChainGridPane.fillGrid(String.valueOf(i), manager);
//					}
//				}
				
				logisticsSupplyChainGridPane.fillGrid(newValue, manager);
           
            }
		});

        this.setScene(new Scene(root, 750, 750));

        this.setOnShown(event -> {
            Platform.runLater(() -> {
                if (!manager.getLogisticsSupplyChains().getSupplyChains().isEmpty()) {
                    listViewCtrl.requestFocus();
                    listViewCtrl.getSelectionModel().select(0);
                }
            });
        });
    }

    public void fillListView(Manager manager) {
        listViewCtrl.getItems().clear();
        for (LogisticsSupplyChain supplyChain : manager.getLogisticsSupplyChains().getSupplyChains()) {
            listViewCtrl.getItems().add(supplyChain);
        }
    }
    
	public LogisticsSupplyChainGridPane buildUISupplyChainFields() {
		LogisticsSupplyChainGridPane logisticsSupplyChainGridPane = new LogisticsSupplyChainGridPane();
		
		logisticsSupplyChainGridPane.getSenderSiteIdField().setEditable(false);
		logisticsSupplyChainGridPane.getReceiverIdField().setEditable(false);
		logisticsSupplyChainGridPane.getTransportIdField().setEditable(false);
		logisticsSupplyChainGridPane.getCostPerTonField().setEditable(false);
		logisticsSupplyChainGridPane.getDurationInDaysField().setEditable(false);
		
		return logisticsSupplyChainGridPane;
	}
	
	private void refreshSupplyChainList(Manager manager) {
		manager.readAllSupplyChains();
		fillListView(manager);
	}
}
