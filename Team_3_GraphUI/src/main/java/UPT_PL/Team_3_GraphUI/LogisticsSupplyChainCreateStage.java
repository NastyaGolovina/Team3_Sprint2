package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3_GraphUI.LogisticsSupplyChainDto;
import UPT_PL.Team_3_GraphUI.LogisticsSupplyChainGridPane.TextFieldName;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class LogisticsSupplyChainCreateStage extends Stage {
	private LogisticsSupplyChainGridPane logisticsGridPane;
	private Button btnOK;
	
	public LogisticsSupplyChainCreateStage(Manager manager) {
		buildUI(manager);
	}
	
	public LogisticsSupplyChainGridPane getLogisticsSupplyGridPane() {
		return logisticsGridPane;
	}

	public void setCountryGridPane(LogisticsSupplyChainGridPane logisticsGridPane) {
		this.logisticsGridPane = logisticsGridPane;
	}
	
	public void buildUI(Manager manager) {
		this.setTitle("Create Logistics Supply Chain"); 
		this.initModality(Modality.APPLICATION_MODAL);
		
		logisticsGridPane = new LogisticsSupplyChainGridPane();
		logisticsGridPane.getMyGrid().setPadding(new Insets(20, 20, 20, 20));
		logisticsGridPane.getMyGrid().setAlignment(Pos.TOP_CENTER);
		

		HBox HButtonsBox = new HBox();
		HButtonsBox.setAlignment(Pos.BOTTOM_CENTER);
		HButtonsBox.setSpacing(10);
		btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");
		
		
		btnCancel.setOnAction(ae -> {
			this.close();
		});
		
		
		HButtonsBox.getChildren().addAll(new Button[] {btnOK,btnCancel});
		
	    VBox mainLayout = new VBox();
	    mainLayout.setPadding(new Insets(20)); 
	    mainLayout.setSpacing(15);
	    mainLayout.getChildren().addAll(logisticsGridPane.getMyGrid(), HButtonsBox);
		
		this.setScene(new Scene(mainLayout, 500, 500));
	}
	
	public void createNewLogisticsSupplyChain(Manager manager, Runnable refreshSupplyChainList) {
		btnOK.setOnAction(ae -> {
			String senderId = logisticsGridPane.getValueFromTextField(TextFieldName.SenderSiteId);
			String receiverId = logisticsGridPane.getValueFromTextField(TextFieldName.ReceiverId);
			String transportId = logisticsGridPane.getValueFromTextField(TextFieldName.TransportId);
			String costPerTon = logisticsGridPane.getValueFromTextField(TextFieldName.CostPerTon);
			String durationInDays = logisticsGridPane.getValueFromTextField(TextFieldName.DurationInDays);
			
			LogisticsSupplyChainDto newLogisticsSupplyChain = new LogisticsSupplyChainDto();
			newLogisticsSupplyChain.setSenderId(senderId);
			newLogisticsSupplyChain.setReceiverId(receiverId);
			newLogisticsSupplyChain.setTransportId(transportId);
			newLogisticsSupplyChain.setCostPerTon(costPerTon);
			newLogisticsSupplyChain.setDurationInDays(durationInDays);
			
			
			try {
				manager.addLogisticsSupplyChain(newLogisticsSupplyChain);
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Failed to add logistics supply chain");
	            alert.setContentText(e.getMessage());
	            alert.showAndWait();
	            logisticsGridPane.setValueToTextField(TextFieldName.SupplyChainIdField, "");
	            logisticsGridPane.setValueToTextField(TextFieldName.SenderSiteId, "");
	            logisticsGridPane.setValueToTextField(TextFieldName.ReceiverId,"");
	            logisticsGridPane.setValueToTextField(TextFieldName.TransportId,"");
	            logisticsGridPane.setValueToTextField(TextFieldName.CostPerTon, "");
	            logisticsGridPane.setValueToTextField(TextFieldName.DurationInDays, "");
			} finally {
				refreshSupplyChainList.run();
				this.close();
			}
		});
		this.showAndWait();
	}
	
	public void updateSupplyChain(String chainId, Manager manager, Runnable refreshSupplyChainList) {
		btnOK.setOnAction(ae -> {
			String senderId = logisticsGridPane.getValueFromTextField(TextFieldName.SenderSiteId);
			String receiverId = logisticsGridPane.getValueFromTextField(TextFieldName.ReceiverId);
			String transportId = logisticsGridPane.getValueFromTextField(TextFieldName.TransportId);
			String costPerTon = logisticsGridPane.getValueFromTextField(TextFieldName.CostPerTon);
			String durationInDays = logisticsGridPane.getValueFromTextField(TextFieldName.DurationInDays);
			
			LogisticsSupplyChainDto editedSupplyChain = new LogisticsSupplyChainDto();
			editedSupplyChain.setChainId(chainId);
			editedSupplyChain.setSenderId(senderId);
			editedSupplyChain.setReceiverId(receiverId);
			editedSupplyChain.setTransportId(transportId);
			editedSupplyChain.setCostPerTon(costPerTon);
			editedSupplyChain.setDurationInDays(durationInDays);
			
			try {
				manager.updateLogisticsSupplyChain(editedSupplyChain);
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Failed to update logistics supply chain");
	            alert.setContentText(e.getMessage());
	            alert.showAndWait();
	            logisticsGridPane.setValueToTextField(TextFieldName.SupplyChainIdField, "");
	            logisticsGridPane.setValueToTextField(TextFieldName.SenderSiteId, "");
	            logisticsGridPane.setValueToTextField(TextFieldName.ReceiverId,"");
	            logisticsGridPane.setValueToTextField(TextFieldName.TransportId,"");
	            logisticsGridPane.setValueToTextField(TextFieldName.CostPerTon, "");
	            logisticsGridPane.setValueToTextField(TextFieldName.DurationInDays, "");
			} finally {
				refreshSupplyChainList.run();
				this.close();
			}
		});
		this.showAndWait();
	}
	
}