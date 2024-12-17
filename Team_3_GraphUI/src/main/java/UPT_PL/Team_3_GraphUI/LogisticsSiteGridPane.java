package UPT_PL.Team_3_GraphUI;

import UPT_PL.Team_3.model.Country;
import UPT_PL.Team_3.model.LogisticsSite;
import UPT_PL.Team_3.model.Transport;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import java.util.List;

public class LogisticsSiteGridPane extends GridPane {

	private GridPane grid;
	private TextField siteIdField;
	private TextField nameField;
	private ListView<String> listViewCtrlTransport;

	/**
	 * TextFieldName
	 */
	public enum TextFieldName {
		SiteIdField, NameField;
	}

	/**
	 * @return the listViewCtrlTransport
	 */
	public ListView<String> getListViewCtrlTransport() {
		return listViewCtrlTransport;
	}

	/**
	 * @param listViewCtrlTransport the listViewCtrlTransport to set
	 */
	public void setListViewCtrlTransport(ListView<String> listViewCtrlTransport) {
		this.listViewCtrlTransport = listViewCtrlTransport;
	}


	public LogisticsSiteGridPane() {
		this.siteIdField = new TextField();
		this.nameField = new TextField();
		this.listViewCtrlTransport = new ListView<String>();
		buildUI();
	}

	/**
	 * @return the grid
	 */
	public GridPane getMyGrid() {
		return grid;
	}
	
	

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	/**
	 * @return the siteIdField
	 */
	public TextField getSiteIdField() {
		return siteIdField;
	}

	/**
	 * @param siteIdField the siteIdField to set
	 */
	public void setSiteIdField(TextField siteIdField) {
		this.siteIdField = siteIdField;
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

	public void buildUI() {
		Label siteIdLabel = new Label("Site Id");
		Label nameLabel = new Label("Name");
		Label transportLabel = new Label("Transports");
		
		this.grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(5);

		grid.add(siteIdLabel, 0, 0);
		grid.add(nameLabel, 0, 1);
		grid.add(transportLabel, 0, 2);
		grid.add(this.siteIdField, 1, 0);
		grid.add(this.nameField, 1, 1);
		grid.add(this.listViewCtrlTransport, 1, 3);

		GridPane.setHgrow(this.siteIdField, Priority.ALWAYS);
		GridPane.setHgrow(this.nameField, Priority.ALWAYS);
		GridPane.setHgrow(this.listViewCtrlTransport, Priority.ALWAYS);
	}

	public void fillGrid(String newValue, Manager manager, Country country) {
		LogisticsSite site = country.searchSite(newValue, manager.getCountries().getCountries());
		if(site != null) {
			this.siteIdField.setText(site.getSiteId());
			this.nameField.setText(site.getName());
			if(site.getSuppliedTransports() != null) {
				listViewCtrlTransport.getItems().clear();
				for(Transport t : site.getSuppliedTransports()) {
					listViewCtrlTransport.getItems().add(t.getTransportId());
				}
			}
		}
		

	}
	
	public void fillListViewCtrlTransport(Manager manager) {
		if(manager.getTransports().getTransportList() != null) {
			listViewCtrlTransport.getItems().clear();
			for(Transport t : manager.getTransports().getTransportList()) {
				listViewCtrlTransport.getItems().add(t.getTransportId());
			}
		}
	}

	public String getValueFromTextField(TextFieldName textFieldName) {
		switch (textFieldName) {
		case SiteIdField:
			return siteIdField.getText();
		case NameField:
			return nameField.getText();
		default:
			return "";
		}
	}

	public void setValueToTextField(TextFieldName textFieldName, String text) {
		switch (textFieldName) {
		case SiteIdField:
			siteIdField.setText(text);
		case NameField:
			nameField.setText(text);
		default:
		}
	}

}
