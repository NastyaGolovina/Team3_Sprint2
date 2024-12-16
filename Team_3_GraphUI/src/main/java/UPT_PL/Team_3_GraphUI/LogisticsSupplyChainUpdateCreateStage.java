package UPT_PL.Team_3_GraphUI;


import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3_GraphUI.CountryGridPane.TextFieldName;
import UPT_PL.Team_3_GraphUI.LogisticsSupplyChainGridPane.ChainTextFieldName;
import UPT_PL.Team_3_GraphUI.LogisticsSupplyChainGridPane.DisplayType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogisticsSupplyChainUpdateCreateStage extends Stage {
	
	private LogisticsSupplyChainGridPane logisticsSupplyChainGridPane;
	private Button btnOK;
	
	/**
	 * Constructor
	 */
	public LogisticsSupplyChainUpdateCreateStage(Manager manage) {
		buildUI(manage);
	}
	
	
	/**
	 * @return the logisticsSupplyChainGridPane
	 */
	public LogisticsSupplyChainGridPane getLogisticsSupplyChainGridPane() {
		return logisticsSupplyChainGridPane;
	}


	/**
	 * @param logisticsSupplyChainGridPane the logisticsSupplyChainGridPane to set
	 */
	public void setLogisticsSupplyChainGridPane(LogisticsSupplyChainGridPane logisticsSupplyChainGridPane) {
		this.logisticsSupplyChainGridPane = logisticsSupplyChainGridPane;
	}


	/**
	 * @return the btnOK
	 */
	public Button getBtnOK() {
		return btnOK;
	}


	/**
	 * @param btnOK the btnOK to set
	 */
	public void setBtnOK(Button btnOK) {
		this.btnOK = btnOK;
	}


	public void buildUI(Manager manager) {
		this.initModality(Modality.APPLICATION_MODAL);
		
		logisticsSupplyChainGridPane = new LogisticsSupplyChainGridPane(DisplayType.CreateEdit);
		logisticsSupplyChainGridPane.fillSiteComboBoxes(manager);
		logisticsSupplyChainGridPane.fillTransportComboBox(manager);
		logisticsSupplyChainGridPane.getLogisticsSupplyChainGrid().setPadding(new Insets(20, 20, 20, 20));
		logisticsSupplyChainGridPane.getLogisticsSupplyChainGrid().setAlignment(Pos.TOP_CENTER);
		

		HBox HButtonsBox = new HBox();
		HButtonsBox.setAlignment(Pos.TOP_RIGHT);
		HButtonsBox.setSpacing(10);
		btnOK = new Button("OK");
		Button btnCancel = new Button("Cancel");
		
		
		btnCancel.setOnAction(ae -> {
			this.close();
		});
		
		
		
		HButtonsBox.getChildren().addAll(new Button[] {btnOK,btnCancel});
		logisticsSupplyChainGridPane.getLogisticsSupplyChainGrid().add(HButtonsBox, 1, 6);
		this.setScene(new Scene(logisticsSupplyChainGridPane.getLogisticsSupplyChainGrid(), 500, 500));
	}
	
	
	public void createNewLogisticsSupplyChain(Manager manager) {
		this.setTitle("Create Logistics Supply Chain"); 
		btnOK.setOnAction(ae -> {
			String chainId = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.ChainIdField);
			String sender = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.SenderComboBox);
			String receiver = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.ReceiverComboBox);
			String transport = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.TransportComboBox);
			String costPerTon = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.CostPerTonField);
			String durationInDays = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.DurationInDaysField);
			LogisticsSupplyChain newLogisticsSupplyChain = new LogisticsSupplyChain();
			String output = manager.getLogisticsSupplyChains().addNewSupplyChain(chainId, sender, receiver,
																				transport, costPerTon, durationInDays, 
																				newLogisticsSupplyChain, manager.getCountries());
			if(output == "") {
				manager.addLogisticsSupplyChain(newLogisticsSupplyChain);
				this.close();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Failed to add new Logistics Supply Chain");
	            alert.setContentText(output);
	            alert.showAndWait();
	            logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.ChainIdField, "");
				logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.SenderComboBox,"0");
				logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.ReceiverComboBox,"0");
				logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.TransportComboBox,"0");
				logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.CostPerTonField, "");
				logisticsSupplyChainGridPane.setValueToTextField(ChainTextFieldName.DurationInDaysField, "");
			}
		});
		this.showAndWait();
	}
	
	
	
	public void updateLogisticsSupplyChain(Manager manager) {
		this.setTitle("Update Logistics Supply Chain"); 
		btnOK.setOnAction(ae -> {
			String chainId = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.ChainIdField);
			String sender = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.SenderComboBox);
			String receiver = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.ReceiverComboBox);
			String transport = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.TransportComboBox);
			String costPerTon = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.CostPerTonField);
			String durationInDays = logisticsSupplyChainGridPane.getValueFromTextField(ChainTextFieldName.DurationInDaysField);
			LogisticsSupplyChain editedChain = new LogisticsSupplyChain();
			editedChain.setChainId(chainId);
			String output = manager.getLogisticsSupplyChains().updateSupplyChain(chainId, sender, receiver,
																				transport, costPerTon, durationInDays, 
																				editedChain, manager.getCountries());
			if(output == "") {
				manager.updateLogisticsSupplyChain(editedChain);
				this.close();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText("Failed to update Logistics Supply Chain");
	            alert.setContentText(output);
	            alert.showAndWait();
	            logisticsSupplyChainGridPane.fillGrid(chainId, manager);
			}
		});
		this.showAndWait();
	}
}
