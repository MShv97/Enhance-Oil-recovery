/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AboutWindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MSh
 */
public class AboutController implements Initializable {

    @FXML
    private Label about;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        about.setText("Developed by:\nMohammad Shehab\nAbdulrahman Nassan\n\n\nAll rights reserved © 2020");
    }    
    
}
