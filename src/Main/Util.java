/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author MSh
 */
public class Util {
    private static final String IMAG_ICON = "/resources/barrel.png";
    
    public static void setStageIcon(Stage stage){
        stage.getIcons().add(new Image(IMAG_ICON));
    }
}
