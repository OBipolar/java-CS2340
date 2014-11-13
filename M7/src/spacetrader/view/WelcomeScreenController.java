/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author zixiangzhu
 */
public class WelcomeScreenController implements Initializable, ControlledScreen {

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
        myController.setScreen(Main.screen2ID);
    }

    @FXML
    private void loadGameFired(ActionEvent event) {
    	myController.loadScreen(Main.screen3ID, Main.screen3File);
    	myController.setScreen(Main.screen3ID);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
