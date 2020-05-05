/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OilFieldsList;

import AddOilField.FXMLDocumentController;
import Alert.AlertMaker;
import Main.Util;
import OilDatabase.DataBaseHandler;
import OilImproveMethods.ImproveMethodsFXMLController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MSh
 */
public class OilFieldsListController implements Initializable {

    ObservableList<OilField> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<OilField, String> nameCol;
    @FXML
    private TableColumn<OilField, Float> apiCol;
    @FXML
    private TableColumn<OilField, Float> vicosityCol;
    @FXML
    private TableColumn<OilField, Float> kCol;
    @FXML
    private TableColumn<OilField, Float> saturationCol;
    @FXML
    private TableColumn<OilField, Float> depthCol;
    @FXML
    private TableColumn<OilField, Float> thicknessCol;
    @FXML
    private TableColumn<OilField, Float> tempratureCol;
    @FXML
    private TableColumn<OilField, String> lithologyCol;
    @FXML
    private TableColumn<OilField, String> gasCapCol;
    @FXML
    private TableView<OilField> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        apiCol.setCellValueFactory(new PropertyValueFactory<>("api"));
        vicosityCol.setCellValueFactory(new PropertyValueFactory<>("vicosity"));
        kCol.setCellValueFactory(new PropertyValueFactory<>("K"));
        saturationCol.setCellValueFactory(new PropertyValueFactory<>("saturation"));
        depthCol.setCellValueFactory(new PropertyValueFactory<>("depth"));
        thicknessCol.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        tempratureCol.setCellValueFactory(new PropertyValueFactory<>("temprature"));
        lithologyCol.setCellValueFactory(new PropertyValueFactory<>("lithology"));
        gasCapCol.setCellValueFactory(new PropertyValueFactory<>("gasCap"));
        
        nameCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        apiCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        vicosityCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        kCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        saturationCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        depthCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        thicknessCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        tempratureCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        lithologyCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
        gasCapCol.prefWidthProperty().bind(tableView.widthProperty().divide(10));
    }

    private void loadData() {
        list.clear();
        DataBaseHandler dbHandler = DataBaseHandler.getInstance();
        String quId = "SELECT * FROM OIL_TABLE";
        ResultSet rs = dbHandler.execQuery(quId);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                float api = rs.getFloat("API");
                float vicosity = rs.getFloat("VICOSITY");
                float k = rs.getFloat("K");
                float saturation = rs.getFloat("SATURATION");
                float depth = rs.getFloat("DEPTH");
                float thickness = rs.getFloat("THICKNESS");
                float temprature = rs.getFloat("TEMPRATURE");
                String lithology = rs.getString("LITHOLOGY");
                String gasCap = rs.getBoolean("GASCAP") ? "Yes" : "No";
                list.add(new OilField(id, name, api, vicosity, k, saturation, depth, thickness, temprature, lithology, gasCap));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OilFieldsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(list);
    }

    @FXML
    private void loadResults(MouseEvent mouseEvent) {
        OilField selcted = tableView.getSelectionModel().getSelectedItem();
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2 && selcted != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/OilImproveMethods/ImproveMethodsFXML.fxml"));
                    Parent parent = loader.load();
                    ImproveMethodsFXMLController controler = (ImproveMethodsFXMLController) loader.getController();
                    controler.calResults(selcted);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Results");
                    stage.setScene(new Scene(parent));
                    Util.setStageIcon(stage);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(OilFieldsListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void loadAddOilField(ActionEvent event) {
        loadWindow("/AddOilField/FXMLDocument.fxml", "Add oil field");
    }

    @FXML
    private void loadAbout(ActionEvent event) {
        loadWindow("/AboutWindow/FXML.fxml", "About");
    }

    void loadWindow(String path, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            Util.setStageIcon(stage);
            stage.show();
            stage.setResizable(false);
            stage.setOnCloseRequest((e) -> {
                loadData();
            });
        } catch (IOException ex) {
            Logger.getLogger(OilFieldsListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void deleteOilField(ActionEvent event) {
        OilField selctedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selctedForDeletion != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Oil Field");
            alert.setContentText("Are you sure you want to delete " + selctedForDeletion.getName() + "oil field ?");
            alert.getButtonTypes().remove(ButtonType.OK);
            alert.getButtonTypes().add(ButtonType.YES);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.YES) {
                Boolean res = DataBaseHandler.getInstance().execDelete(selctedForDeletion);
                if (res) {
                    AlertMaker.showSimpleAlert("Oil Field Deleted", selctedForDeletion.getName() + " was deleted successfuly.");
                    list.remove(selctedForDeletion);

                } else {
                    AlertMaker.showErrorMessage("Faild", "An error occurred while trying to delete " + selctedForDeletion.getName());
                }
            }
        }
    }

    @FXML
    private void editOilField(ActionEvent event) {
        OilField selctedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selctedForDeletion != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddOilField/FXMLDocument.fxml"));
                Parent parent = loader.load();
                FXMLDocumentController controler = (FXMLDocumentController) loader.getController();
                controler.inflateUI(selctedForDeletion);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Edit Oil Field");
                stage.setScene(new Scene(parent));
                Util.setStageIcon(stage);
                stage.show();
                stage.setResizable(false);
                stage.setOnCloseRequest((e) -> {
                    loadData();
                });

            } catch (IOException ex) {
                Logger.getLogger(OilFieldsListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
