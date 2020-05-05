/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddOilField;

import Alert.AlertMaker;
import OilDatabase.DataBaseHandler;
import OilFieldsList.OilField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author MSh
 */
//Limestone
public class FXMLDocumentController implements Initializable {

    Boolean isEditMode = false;
    int ofID = 0;

    @FXML
    private Button addOilFIeld_btn;
    @FXML
    private TextField name;
    @FXML
    private TextField api;
    @FXML
    private TextField vicosity;
    @FXML
    private TextField k;
    @FXML
    private TextField saturaition;
    @FXML
    private TextField depth;
    @FXML
    private TextField thickness;
    @FXML
    private TextField temprature;
    @FXML
    private ComboBox<String> lithology;
    @FXML
    private AnchorPane addOilFieldPane;
    @FXML
    private Button cancel_btn;
    @FXML
    private RadioButton gasCapYes;
    @FXML
    private ToggleGroup gasCapToggel;
    @FXML
    private ToggleGroup depthToggle;
    @FXML
    private RadioButton meterRadio;
    @FXML
    private ToggleGroup tempToggle;
    @FXML
    private RadioButton celsiusRadio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> oL = FXCollections.observableArrayList();
        oL.add("Sandstone");
        oL.add("Limestone");
        lithology.setItems(oL);
    }

    @FXML
    private void addOilField(ActionEvent event) {
        DataBaseHandler handler = DataBaseHandler.getInstance();
        String quId = "SELECT MAX(ID)+1 IDD FROM OIL_TABLE";
        ResultSet rs = handler.execQuery(quId);
        int id = 1;
        try {
            rs.next();
            id = rs.getInt("IDD");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String oilName = name.getText();
        String oilApi = api.getText();
        String oilVicosity = vicosity.getText();
        String oilK = k.getText();
        String oilSaturaition = saturaition.getText();
        String oilDepth = depth.getText();
        String oilThickness = thickness.getText();
        String oilTemprature = temprature.getText();
        String oilLithology = lithology.getValue();
        Boolean oilGasCap = gasCapYes.isSelected();

        if (oilName.isEmpty() || oilApi.isEmpty() || oilVicosity.isEmpty() || oilK.isEmpty() || oilSaturaition.isEmpty() || oilDepth.isEmpty() || oilThickness.isEmpty() || oilTemprature.isEmpty() || oilLithology == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter all fields !");
            alert.showAndWait();
            return;
        }
        float oilDepthFT = Float.parseFloat(oilDepth);
        float oilTempF = Float.parseFloat(oilTemprature);
        if (meterRadio.isSelected()) {
            oilDepthFT *= 3.28084;
        }
        if (celsiusRadio.isSelected()) {
            oilTempF = oilTempF * 9 / 5 + 32;
        }
        if (isEditMode) {
            OilField of = new OilField(ofID, oilName, Float.parseFloat(oilApi), Float.parseFloat(oilVicosity), Float.parseFloat(oilK), Float.parseFloat(oilSaturaition), oilDepthFT, Float.parseFloat(oilThickness), oilTempF, oilLithology, (oilGasCap) ? "Yes" : "No");
            if (handler.execUpdate(of)) {
                AlertMaker.showSimpleAlert("Success", "Oil field updated");
                cancelAddOilField(event);
            } else {
                AlertMaker.showErrorMessage("Faild", "An error occured");
            }
            return;
        }

        String qu = "INSERT INTO OIL_TABLE VALUES("
                + id + ","
                + "'" + oilName + "',"
                + Float.parseFloat(oilApi) + ","
                + Float.parseFloat(oilVicosity) + ","
                + Float.parseFloat(oilK) + ","
                + Float.parseFloat(oilSaturaition) + ","
                + oilDepthFT + ","
                + Float.parseFloat(oilThickness) + ","
                + oilTempF + ","
                + "'" + oilLithology + "',"
                + oilGasCap
                + ")";
        if (handler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Insert new oil field success !");
            alert.showAndWait();
            cancelAddOilField(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Insert new oil field faild !");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelAddOilField(ActionEvent event) {
        Stage stage = (Stage) addOilFieldPane.getScene().getWindow();
        stage.fireEvent(
                new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

    public void inflateUI(OilField of) {
        name.setText(of.getName());
        api.setText(Float.toString(of.getApi()));
        vicosity.setText(Float.toString(of.getVicosity()));
        k.setText(Float.toString(of.getK()));
        saturaition.setText(Float.toString(of.getSaturation()));
        depth.setText(Float.toString(of.getDepth()));
        thickness.setText(Float.toString(of.getThickness()));
        temprature.setText(Float.toString(of.getTemprature()));
        lithology.setValue(of.getLithology());
        gasCapYes.setSelected(of.getGasCap().equals("Yes"));

        isEditMode = true;
        ofID = of.getId();
    }

}
