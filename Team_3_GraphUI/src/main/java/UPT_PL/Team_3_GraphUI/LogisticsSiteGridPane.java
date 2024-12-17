//package UPT_PL.Team_3_GraphUI;
//
//import UPT_PL.Team_3.model.Country;
//import UPT_PL.Team_3.model.LogisticsSite;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Priority;
//import java.util.List;
//
//public class LogisticsSiteGridPane extends GridPane {
//
//    private GridPane grid;
//    private TextField siteIdField;
//    private TextField nameField;
//    /**
//     * TextFieldName
//     */
//    public enum TextFieldName {
//        SiteIdField, NameField;
//    }
//
//    public LogisticsSiteGridPane() {
//        buildUI();
//    }
//
//    /**
//     * @return the grid
//     */
//    public GridPane getMyGrid() {
//        return grid;
//    }
//
//    /**
//     * @param grid the grid to set
//     */
//    public void setGrid(GridPane grid) {
//        this.grid = grid;
//    }
//
//    /**
//     * @return the siteIdField
//     */
//    public TextField getSiteIdField() {
//        return siteIdField;
//    }
//
//    /**
//     * @param siteIdField the siteIdField to set
//     */
//    public void setSiteIdField(TextField siteIdField) {
//        this.siteIdField = siteIdField;
//    }
//
//    /**
//     * @return the nameField
//     */
//    public TextField getNameField() {
//        return nameField;
//    }
//
//    /**
//     * @param nameField the nameField to set
//     */
//    public void setNameField(TextField nameField) {
//        this.nameField = nameField;
//    }
//
//   
//    public void buildUI() {
//        Label siteIdLabel = new Label("Site Id");
//        Label nameLabel = new Label("Name");
//        this.siteIdField = new TextField();
//        this.nameField = new TextField();
//
//        this.grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(5);
//
//        grid.add(siteIdLabel, 0, 0);
//        grid.add(nameLabel, 0, 1);
//        grid.add(this.siteIdField, 1, 0);
//        grid.add(this.nameField, 1, 1);
//
//        GridPane.setHgrow(this.siteIdField, Priority.ALWAYS);
//        GridPane.setHgrow(this.nameField, Priority.ALWAYS);
//    }
//
//    public void fillGrid(String newValue, Manager manager, Country country) {
//        int sitePos = manager.getCountries().searchSite(newValue);
//        if (sitePos != -1) {
//            LogisticsSite site = manager.getLogisticsSites().getSiteId().get(site);
//            this.siteIdField.setText(site.getSiteId());
//            this.nameField.setText(site.getName());
//        }
//    }
//
//    public String getValueFromTextField(TextFieldName textFieldName) {
//        switch (textFieldName) {
//            case SiteIdField:
//                return siteIdField.getText();
//            case NameField:
//                return nameField.getText();
//            default:
//                return "";
//        }
//    }
//
//    public void setValueToTextField(TextFieldName textFieldName, String text) {
//        switch (textFieldName) {
//            case SiteIdField:
//                siteIdField.setText(text);
//            case NameField:
//                nameField.setText(text);
//            default:
//        }
//    }
//}
