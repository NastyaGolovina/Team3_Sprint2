package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.model.LogisticsSupplyChain;
import UPT_PL.Team_3.model.Transport;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class LogisticsSupplyChainGridPane extends GridPane {
	private GridPane logisticsSupplyChainGrid;
	private TextField chainIdField;
	private TextField costPerTonField;

	private ComboBox<String> senderComboBox;
	private ComboBox<String> receiverComboBox;
	private ComboBox<String> transportComboBox;

	private TextField senderField;
	private TextField receiverField;
	private TextField transportField;
	private TextField durationInDaysField;

	public enum ChainTextFieldName {
		ChainIdField, CostPerTonField,
		SenderField, ReceiverField,
		TransportField,DurationInDaysField,
		SenderComboBox,ReceiverComboBox,
		TransportComboBox;
	}

	public enum DisplayType {
		View, CreateEdit;
	}
	
	


	/**
	 * @return the logisticsSupplyChainGrid
	 */
	public GridPane getLogisticsSupplyChainGrid() {
		return logisticsSupplyChainGrid;
	}

	/**
	 * @param logisticsSupplyChainGrid the logisticsSupplyChainGrid to set
	 */
	public void setLogisticsSupplyChainGrid(GridPane logisticsSupplyChainGrid) {
		this.logisticsSupplyChainGrid = logisticsSupplyChainGrid;
	}

	/**
	 * @return the chainIdField
	 */
	public TextField getChainIdField() {
		return chainIdField;
	}

	/**
	 * @param chainIdField the chainIdField to set
	 */
	public void setChainIdField(TextField chainIdField) {
		this.chainIdField = chainIdField;
	}

	/**
	 * @return the costPerTonField
	 */
	public TextField getCostPerTonField() {
		return costPerTonField;
	}

	/**
	 * @param costPerTonField the costPerTonField to set
	 */
	public void setCostPerTonField(TextField costPerTonField) {
		this.costPerTonField = costPerTonField;
	}

	/**
	 * @return the senderComboBox
	 */
	public ComboBox<String> getSenderComboBox() {
		return senderComboBox;
	}

	/**
	 * @param senderComboBox the senderComboBox to set
	 */
	public void setSenderComboBox(ComboBox<String> senderComboBox) {
		this.senderComboBox = senderComboBox;
	}

	/**
	 * @return the receiverComboBox
	 */
	public ComboBox<String> getReceiverComboBox() {
		return receiverComboBox;
	}

	/**
	 * @param receiverComboBox the receiverComboBox to set
	 */
	public void setReceiverComboBox(ComboBox<String> receiverComboBox) {
		this.receiverComboBox = receiverComboBox;
	}

	/**
	 * @return the transportComboBox
	 */
	public ComboBox<String> getTransportComboBox() {
		return transportComboBox;
	}

	/**
	 * @param transportComboBox the transportComboBox to set
	 */
	public void setTransportComboBox(ComboBox<String> transportComboBox) {
		this.transportComboBox = transportComboBox;
	}

	/**
	 * @return the senderField
	 */
	public TextField getSenderField() {
		return senderField;
	}

	/**
	 * @param senderField the senderField to set
	 */
	public void setSenderField(TextField senderField) {
		this.senderField = senderField;
	}

	/**
	 * @return the receiverField
	 */
	public TextField getReceiverField() {
		return receiverField;
	}

	/**
	 * @param receiverField the receiverField to set
	 */
	public void setReceiverField(TextField receiverField) {
		this.receiverField = receiverField;
	}

	/**
	 * @return the transportField
	 */
	public TextField getTransportField() {
		return transportField;
	}

	/**
	 * @param transportField the transportField to set
	 */
	public void setTransportField(TextField transportField) {
		this.transportField = transportField;
	}

	/**
	 * @return the durationInDaysField
	 */
	public TextField getDurationInDaysField() {
		return durationInDaysField;
	}

	/**
	 * @param durationInDaysField the durationInDaysField to set
	 */
	public void setDurationInDaysField(TextField durationInDaysField) {
		this.durationInDaysField = durationInDaysField;
	}

	public LogisticsSupplyChainGridPane(DisplayType displayType) {
        chainIdField = new TextField();
        costPerTonField = new TextField();
        senderField = new TextField();
        receiverField = new TextField();
        transportField = new TextField();
        durationInDaysField = new TextField();


        senderComboBox = new ComboBox<String>();
        receiverComboBox = new ComboBox<String>();
        transportComboBox = new ComboBox<String>();
        
		buildUI(displayType);
	}

	public void buildUI(DisplayType displayType) {
		Label chainIdLabel = new Label("Chain Id");
		Label senderLabel = new Label("Sender");
		Label receiverLabel = new Label("Receiver");
		Label transportLabel = new Label("Transport");
		Label costPerTonLabel = new Label("Cost Per Ton");
		Label durationInDaysLabel = new Label("Duration In Days");
		

		this.logisticsSupplyChainGrid = new GridPane();
		logisticsSupplyChainGrid.setHgap(10);
		logisticsSupplyChainGrid.setVgap(5);

		logisticsSupplyChainGrid.add(chainIdLabel, 0, 0);
		logisticsSupplyChainGrid.add(senderLabel, 0, 1);
		logisticsSupplyChainGrid.add(receiverLabel, 0, 2);
		logisticsSupplyChainGrid.add(transportLabel, 0, 3);
		logisticsSupplyChainGrid.add(costPerTonLabel, 0, 4);
		logisticsSupplyChainGrid.add(durationInDaysLabel, 0, 5);

		logisticsSupplyChainGrid.add(chainIdField, 1, 0);
		logisticsSupplyChainGrid.add(costPerTonField, 1, 4);
		logisticsSupplyChainGrid.add(durationInDaysField, 1, 5);
		GridPane.setHgrow(chainIdField, Priority.ALWAYS);
		GridPane.setHgrow(costPerTonField, Priority.ALWAYS);
		GridPane.setHgrow(durationInDaysField, Priority.ALWAYS);
		switch (displayType) {
		case View:

			logisticsSupplyChainGrid.add(senderField, 1, 1);
			logisticsSupplyChainGrid.add(receiverField, 1, 2);
			logisticsSupplyChainGrid.add(transportField, 1, 3);
			chainIdField.setEditable(false);
			costPerTonField.setEditable(false);
			senderField.setEditable(false);
			receiverField.setEditable(false);
			transportField.setEditable(false);
			durationInDaysField.setEditable(false);
			GridPane.setHgrow(senderField, Priority.ALWAYS);
			GridPane.setHgrow(receiverField, Priority.ALWAYS);
			GridPane.setHgrow(transportField, Priority.ALWAYS);
			break;

		case CreateEdit:

			logisticsSupplyChainGrid.add(senderComboBox, 1, 1);
			logisticsSupplyChainGrid.add(receiverComboBox, 1, 2);
			logisticsSupplyChainGrid.add(transportComboBox, 1, 3);
			break;
		}

	}
	
	public void fillGrid(String newValue, Manager manager) {
		int chainPos = manager.getLogisticsSupplyChains().searchChain(newValue);
		if(chainPos != -1) {
			LogisticsSupplyChain chain = manager.getLogisticsSupplyChains().getSupplyChains().get(chainPos);
			this.chainIdField.setText(chain.getChainId());
			this.senderField.setText(chain.getSender().getSiteId());
			this.receiverField.setText(chain.getReceiver().getSiteId());
			this.transportField.setText(chain.getTransport().getTransportId());
			this.costPerTonField.setText(String.valueOf(chain.getCostPerTon()));
			this.durationInDaysField.setText(String.valueOf(chain.getDurationInDays()));
			this.senderComboBox.setValue(chain.getSender().getSiteId());
			this.receiverComboBox.setValue(chain.getReceiver().getSiteId());
			this.transportComboBox.setValue(chain.getTransport().getTransportId());
		}
	}

	public void fillTransportComboBox(Manager manager) {
		if(manager.getTransports().getTransportList() != null) {
			for(Transport t : manager.getTransports().getTransportList()) {
				transportComboBox.getItems().add(t.getTransportId());
			}
		}
		if (!transportComboBox.getItems().isEmpty()) {
			transportComboBox.setValue(transportComboBox.getItems().get(0));
	    }
		
	}
	
	public void fillSiteComboBoxes(Manager manager) {
		if(manager.getCountries().getCountries() != null) {
			for(Country c : manager.getCountries().getCountries()) {
				for(LogisticsSite ls : c.getSites()) {
					senderComboBox.getItems().add(ls.getSiteId());
					receiverComboBox.getItems().add(ls.getSiteId());
				}
			}
		}
		if (!senderComboBox.getItems().isEmpty()) {
			senderComboBox.setValue(senderComboBox.getItems().get(0));
	    }
		if (!receiverComboBox.getItems().isEmpty()) {
			receiverComboBox.setValue(receiverComboBox.getItems().get(0));
	    }
	}
	
	
	
	
	
	public String getValueFromTextField(ChainTextFieldName textFieldName) {
	    switch (textFieldName) {
	        case ChainIdField:
	            return chainIdField.getText();
	        case CostPerTonField:
	            return costPerTonField.getText();
	        case SenderField:
	            return senderField.getText();
	        case ReceiverField:
	            return receiverField.getText();
	        case TransportField:
	            return transportField.getText();
	        case DurationInDaysField:
	            return durationInDaysField.getText();
	        case SenderComboBox:
	            return senderComboBox.getSelectionModel().getSelectedItem();
	        case ReceiverComboBox:
	            return receiverComboBox.getSelectionModel().getSelectedItem();
	        case TransportComboBox:
	            return transportComboBox.getSelectionModel().getSelectedItem();
	        default:
	            return "";
	    }
	}

	public void setValueToTextField(ChainTextFieldName textFieldName, String text) {
		switch (textFieldName) {
		case ChainIdField:
			chainIdField.setText(text);
			break;
		case CostPerTonField:
			costPerTonField.setText(text);
			break;
		case SenderField:
			senderField.setText(text);
			break;
		case ReceiverField:
			receiverField.setText(text);
			break;
		case TransportField:
			transportField.setText(text);
			break;
		case DurationInDaysField:
			durationInDaysField.setText(text);
			break;
		case SenderComboBox:
			if (!senderComboBox.getItems().isEmpty()) {
				if (text.matches("-?\\d+")) {
					int pos = Integer.parseInt(text);
					if ((pos <= receiverComboBox.getItems().size()) && (pos >= 0)) {
						senderComboBox.setValue(senderComboBox.getItems().get(pos));
					}
				}

			}
			break;
		case ReceiverComboBox:
			if (!receiverComboBox.getItems().isEmpty()) {
				if (text.matches("-?\\d+")) {
					int pos = Integer.parseInt(text);
					if ((pos <= receiverComboBox.getItems().size()) && (pos >= 0)) {
						receiverComboBox.setValue(receiverComboBox.getItems().get(pos));
					}
				}

			}
			break;
		case TransportComboBox:
			if (!transportComboBox.getItems().isEmpty()) {
				if (text.matches("-?\\d+")) {
					int pos = Integer.parseInt(text);
					if ((pos <= receiverComboBox.getItems().size()) && (pos >= 0)) {
						transportComboBox.setValue(transportComboBox.getItems().get(pos));
					}
				}

			}
			break;
		default:
			break;
		}
	}

}
