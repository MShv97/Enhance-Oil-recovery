/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author MSh
 */
import OilDatabase.DataBaseHandler;
import OilFieldsList.OilFieldsListController;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MSh
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        DataBaseHandler.getInstance();

        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Enhance Oil Recovery");
        stage.setScene(scene);
        Util.setStageIcon(stage);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();

        scene.setOnKeyPressed(e -> {
            check();
        });
        scene.setOnMouseClicked(e -> {
            check();
        });
    }

    boolean check() {
        try {
            DataBaseHandler dbHandler = DataBaseHandler.getInstance();
            String quId = "SELECT COUNT(ID) x FROM OIL_TABLE";
            ResultSet rs = dbHandler.execQuery(quId);
            rs.next();
            if (rs.getInt("x") == 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Add Oil Field");
                alert.setContentText("There is no oil fields, add new one ?");
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setHeaderText(null);
                alert.setGraphic(null);
                Optional<ButtonType> answer = alert.showAndWait();
                if (answer.get() == ButtonType.YES) {
                    loadAddOilField();
                }

            } else {
                loadTableWindow();
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    void loadTableWindow() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/OilFieldsList/OilFieldsList.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Enhance Oil Recovery");
            stage.setScene(new Scene(parent));
            Util.setStageIcon(stage);
            stage.show();
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(OilFieldsListController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loadAddOilField() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/AddOilField/FXMLDocument.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add oil Field");
            stage.setScene(new Scene(parent));
            Util.setStageIcon(stage);
            stage.show();
            stage.setResizable(false);

        } catch (IOException ex) {
            Logger.getLogger(OilFieldsListController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
