/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication6;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

/**
 *
 * @author zixiangzhu
 */
public class FXMLDocumentController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    @FXML
    private Button newGame;
    @FXML
    private Button loadGame;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void newGameFired(ActionEvent event) {
        myController.setScreen(JavaFXApplication6.screen2ID);
    }
    @FXML
    private void loadGameFired(ActionEvent event) {}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
