package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.LogisticsSupplyChain;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class LogisticsSupplyChainGridPane extends GridPane {

    private GridPane grid; 
    private TextField senderSiteId;
    private TextField receiverId;
    private TextField transportId;
    private TextField costPerTon;
    private TextField durationInDays;

    public enum TextFieldName {
        SupplyChainIdField, CostPerTon, DurationInDays, ReceiverId, SenderSiteId, TransportId;
    }

    public LogisticsSupplyChainGridPane() {
        buildUI();
    }
    
	public GridPane getMyGrid() {
		return grid;
	}
	
	public void setGrid(GridPane grid) {
		this.grid = grid;
	}
	
	public TextField getSenderSiteIdField() {
		return senderSiteId;
	}
	
	public TextField getReceiverIdField() {
		return receiverId;
	}
	
	public TextField getTransportIdField() {
		return transportId;
	}
	
	public TextField getCostPerTonField() {
		return costPerTon;
	}
	
	public TextField getDurationInDaysField() {	
		return durationInDays;
	}
	
	
	public void buildUI() {
		Label senderSiteIdLabel = new Label("Sender Site Id");
		Label receiverIdLabel = new Label("Receiver Id");
		Label transportIdLabel = new Label("Transport Id");
		Label costPerTonLabel = new Label("Cost Per Ton");
		Label durationInDaysLabel = new Label("Duration In Days");

		this.senderSiteId = new TextField();
		this.receiverId = new TextField();
		this.transportId = new TextField();
		this.costPerTon = new TextField();
		this.durationInDays = new TextField();

		this.grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(5);

		grid.add(senderSiteIdLabel, 0, 1);
		grid.add(receiverIdLabel, 0, 2);
		grid.add(transportIdLabel, 0, 3);
		grid.add(costPerTonLabel, 0, 4);
		grid.add(durationInDaysLabel, 0, 5);

		grid.add(this.senderSiteId, 1, 1);
		grid.add(this.receiverId, 1, 2);
		grid.add(this.transportId, 1, 3);
		grid.add(this.costPerTon, 1, 4);
		grid.add(this.durationInDays, 1, 5);

		GridPane.setHgrow(this.senderSiteId, Priority.ALWAYS);
		GridPane.setHgrow(this.receiverId, Priority.ALWAYS);
		GridPane.setHgrow(this.transportId, Priority.ALWAYS);
		GridPane.setHgrow(this.costPerTon, Priority.ALWAYS);
		GridPane.setHgrow(this.durationInDays, Priority.ALWAYS);
	}

    public void fillGrid(LogisticsSupplyChain supplyChain, Manager manager) {
//    	int supplyChainId = Integer.parseInt(newValue);
		if (supplyChain != null) {
			System.out.println("Supply chains: " + manager.getLogisticsSupplyChains().getSupplyChains());
//			LogisticsSupplyChain supplyChain = manager.getLogisticsSupplyChains().getSupplyChains().get(supplyChainId);
			this.senderSiteId.setText(supplyChain.getSender().getSiteId().toString());
			this.receiverId.setText(supplyChain.getReceiver().getSiteId().toString());
			this.transportId.setText(supplyChain.getTransport().getTransportId().toString());
			this.costPerTon.setText(String.valueOf(supplyChain.getCostPerTon()));
			this.durationInDays.setText(String.valueOf(supplyChain.getDurationInDays()));
		}
    }

	
	public String getValueFromTextField(TextFieldName textFieldName) {
		switch (textFieldName) {
		case SenderSiteId:
			return senderSiteId.getText();
		case ReceiverId:
			return receiverId.getText();
		case TransportId:
			return transportId.getText();
		case CostPerTon:
			return costPerTon.getText();
		case DurationInDays:
			return durationInDays.getText();
		default:
			return "";
		}
	}

	public void setValueToTextField(TextFieldName textFieldName, String text) {
		switch (textFieldName) {
		case SenderSiteId:
			senderSiteId.setText(text);
			break;
		case ReceiverId:
			receiverId.setText(text);
			break;
		case TransportId:
			transportId.setText(text);
			break;
		case CostPerTon:
			costPerTon.setText(text);
			break;
		case DurationInDays:
			durationInDays.setText(text);
			break;
		default:
			break;
		}
	}
}
