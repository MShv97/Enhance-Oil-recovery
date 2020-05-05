/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author MSh
 */
public class MainControler implements Initializable {

    @FXML
    private HBox hbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ImageView img = new ImageView("resources/Main.jpg");
        img.fitHeightProperty().bind(hbox.heightProperty());
        
        img.setPreserveRatio(true);
        hbox.getChildren().add(img);

        
    }

}
