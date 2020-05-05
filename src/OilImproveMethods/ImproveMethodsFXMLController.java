/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OilImproveMethods;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author MSh
 */
public class ImproveMethodsFXMLController implements Initializable {

    OilFieldsList.OilField of;
    ObservableList<MethodResult> list = FXCollections.observableArrayList();

    @FXML
    private TableView<MethodResult> tableView;
    @FXML
    private TableColumn<MethodResult, String> methodCol;
    @FXML
    private TableColumn<MethodResult, String> resultCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        System.out.println(tableView.getProperties());
    }

    private void initCol() {
        methodCol.setCellValueFactory(new PropertyValueFactory<>("methodName"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("res"));
    }

    public void calResults(OilFieldsList.OilField of) {
        this.of = of;
        steamMethod();
        inSituComb();
        alkaline();
        polymers();
        surfactants();
        co2Injection();
        tableView.setItems(list);
        resultCol.setComparator(resultCol.getComparator().reversed());
        tableView.getSortOrder().add(resultCol);
        resultCol.setSortable(false);
    }

    private void polymers() {
        double cnt = 0.0;
        if (of.getApi() > 25) {
            cnt += 15.0;
        }
        if (of.getSaturation() > 25) {
            cnt += 25.0;
        }
        if (50 >= of.getVicosity() && of.getVicosity() <= 100) {
            cnt += 2.0;
        }
        if (of.getTemprature() < 200) {
            cnt += 35.0;
        }
        if (of.getK() > 50) {
            cnt += 5.0;
        }
        if (of.getDepth() < 9000) {
            cnt += 1.0;
        }
        if (of.getLithology().equals("Sandstone")) {
            cnt += 15.0;
        }
        // Thickness isn't important
        if (of.getGasCap().equals("No")) {
            cnt += 2.0;
        }
        if (cnt < 50.0) {
            return;
        }
        String ss = Double.toString(cnt);
        list.add(new MethodResult("Polymers", ss.substring(0, 5)));
    }

    private void surfactants() {
        double cnt = 0.0;
        if (of.getApi() > 20) {
            cnt += 15.0;
        }
        if (of.getSaturation() > 25) {
            cnt += 25.0;
        }
        if (of.getVicosity() < 50) {
            cnt += 5.0;
        }
        if (of.getTemprature() < 212) {
            cnt += 35.0;
        }
        if (of.getK() > 10) {
            cnt += 2.0;
        }
        if (of.getDepth() < 9000) {
            cnt += 1.0;
        }
        if (of.getLithology().equals("Sandstone")) {
            cnt += 15.0;
        }
        // Thickness isn't important
        if (of.getGasCap().equals("No")) {
            cnt += 2.0;
        }
        if (cnt < 50.0) {
            return;
        }
        String ss = Double.toString(cnt);
        list.add(new MethodResult("Surfactants", ss.substring(0, 5)));
    }

    private void alkaline() {
        double cnt = 0.0;
        if (20 < of.getApi() && of.getApi() < 30) {
            cnt += 15.0;
        }
        if (of.getSaturation() > 35) {
            cnt += 25.0;
        }
        if (of.getVicosity() < 35) {
            cnt += 5.0;
        }
        if (of.getTemprature() < 200) {
            cnt += 35.0;
        }
        if (of.getK() > 10) {
            cnt += 2.0;
        }
        if (of.getDepth() < 9000) {
            cnt += 1.0;
        }
        if (of.getLithology().equals("Sandstone")) {
            cnt += 15.0;
        }
        // Thickness isn't important
        if (of.getGasCap().equals("No")) {
            cnt += 2.0;
        }
        if (cnt < 50.0) {
            return;
        }
        String ss = Double.toString(cnt);
        list.add(new MethodResult("Alkaline", ss.substring(0, 5)));
    }

    void inSituComb() {
        double cnt = 0.0;
        if (10 < of.getApi() && of.getApi() < 16) {
            cnt += 10.0;
        }
        if (of.getSaturation() > 40) {
            cnt += 10.0;
        }
        if (of.getVicosity() > 10) {
            cnt += 15.0;
        }
        if (of.getTemprature() > 100) {
            cnt += 15.0;
        }
        if (of.getK() > 10) {
            cnt += 10.0;
        }
        if (of.getDepth() < 3500) {
            cnt += 25.0;
        }
        if (of.getLithology().equals("Sandstone")) {
            cnt += 5.0;
        }
        if (of.getThickness() < 10) {
            cnt += 10.0;
        }
        //GasCap isn't important
        if (cnt < 50.0) {
            return;
        }
        String ss = Double.toString(cnt);
        list.add(new MethodResult("In Situ Combustion", ss.substring(0, 5)));

    }

    void steamMethod() {
        double cnt = 0.0;
        if (8 < of.getApi() && of.getApi() < 20) {
            cnt += 10.0;
        }
        if (of.getSaturation() > 35) {
            cnt += 10.0;
        }
        if (of.getVicosity() > 50) {
            cnt += 15.0;
        }
        if (of.getTemprature() > 100) {
            cnt += 15.0;
        }
        if (of.getK() > 20) {
            cnt += 10.0;
        }
        if (of.getDepth() < 1500) {
            cnt += 25.0;
        }
        if (of.getLithology().equals("Sandstone")) {
            cnt += 5.0;
        }
        if (of.getThickness() < 20) {
            cnt += 10.0;
        }
        //GasCap isn't important
        if (cnt < 50.0) {
            return;
        }
        String ss = Double.toString(cnt);
        list.add(new MethodResult("Steam", ss.substring(0, 5)));

    }

    private void co2Injection() {
        double cnt = 0.0;
        if (of.getApi() > 25) {
            cnt += 15.0;
        }
        if (of.getSaturation() > 30) {
            cnt += 12.0;
        }
        if (of.getVicosity() < 15) {
            cnt += 15.0;
        }
        if (of.getTemprature() > 86) {
            cnt += 5.0;
        }
        if (of.getK() > 100) {
            cnt += 3.0;
        }
        if (of.getDepth() > 1968.5) {
            cnt += 25.0;
        }
        //Lithology isn't important
        if (of.getThickness() <= 20) {
            cnt += 5.0;
        }
        if (of.getGasCap().equals("No")) {
            cnt += 20.0;
        }
        if (cnt < 50.0) {
            return;
        }
        String ss = Double.toString(cnt);
        list.add(new MethodResult("CO2 Injection", ss.substring(0, 5)));
    }

}
