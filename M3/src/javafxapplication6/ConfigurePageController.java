/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication6;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.converter.IntegerStringConverter;
/**
 * FXML Controller class
 *
 * @author zixiangzhu
 */
public class ConfigurePageController implements Initializable, ControlledScreen {
    /**
     * Initializes the controller class.
     */
    
    ScreensController myController;
    private String userName;
    private int pilotP, fighterP, traderP, engineerP;
    private boolean edited = false;
    
    @FXML
    private TextField userNameInput;
    
    @FXML
    private TextField pilotPt;
    
    @FXML
    private TextField fighterPt;
    
    @FXML
    private TextField traderPt;
    
    @FXML
    private TextField engineerPt;
    
    @FXML
    private Label ptRecord;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button okButton;
    
    @FXML
    private Label messageLabel;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void okButtonFired(ActionEvent event) {
        System.out.println("ok fired");
        CharacterShip ship = new CharacterShip();
        ship.record(userName, pilotP, fighterP, traderP, engineerP);
        messageLabel.setText("Profile Successfully Created!");
        edited = true;
    }
    
    @FXML
    private void cancelButtonFired(ActionEvent event) {
        System.out.println("cancel fired");
        myController.setScreen(JavaFXApplication6.screen1ID);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userName = "";
        pilotP = fighterP = traderP = engineerP = 0;
        userNameInput.setText("");
        pilotPt.setText("0");
        fighterPt.setText("0");
        traderPt.setText("0");
        engineerPt.setText("0");

        userNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            userName = newValue;
        });

        pilotPt.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            IntegerStringConverter converter = new IntegerStringConverter();
            if (newValue.equals("")) {
                pilotP = 0;
            } else {
                pilotP = converter.fromString(newValue);
            }
            if (!checkOk()) {
                pilotP = 16 - fighterP - traderP - engineerP;
                pilotPt.replaceText(0, newValue.length(), "" + pilotP);
                ptRecord.setText(0 + "/16 points remaining");
            }
            ptRecord.setText(getPtRemain(pilotP, fighterP, traderP, engineerP) + "/16 points remaining");
        });
        
        fighterPt.textProperty().addListener((observable, oldValue, newValue) -> {
            IntegerStringConverter converter = new IntegerStringConverter();
            if (newValue.equals("")) {
                fighterP = 0;
            } else {
                fighterP = converter.fromString(newValue);
            }
            if (!checkOk()) {
                fighterP = 16 - pilotP - traderP - engineerP;
                fighterPt.replaceText(0, newValue.length(), "" + fighterP);
                ptRecord.setText(0 + "/16 points remaining");
            }
            ptRecord.setText(getPtRemain(pilotP, fighterP, traderP, engineerP) + "/16 points remaining");
        });
        
        traderPt.textProperty().addListener((observable, oldValue, newValue) -> {
            IntegerStringConverter converter = new IntegerStringConverter();
            if (newValue.equals("")) {
                traderP = 0;
            } else {
                traderP = converter.fromString(newValue);
            }
            if (!checkOk()) {
                traderP = 16 - fighterP - pilotP - engineerP;
                traderPt.replaceText(0, newValue.length(), "" + traderP);
                ptRecord.setText(0 + "/16 points remaining");
            }
            ptRecord.setText(getPtRemain(pilotP, fighterP, traderP, engineerP) + "/16 points remaining");
        });
        
        engineerPt.textProperty().addListener((observable, oldValue, newValue) -> {
            IntegerStringConverter converter = new IntegerStringConverter();
            if (newValue.equals("")) {
                engineerP = 0;
            } else {
                engineerP = converter.fromString(newValue);
            }
            if (!checkOk()) {
                engineerP = 16 - fighterP - traderP - pilotP;
                engineerPt.replaceText(0, newValue.length(), "" + engineerP);
                ptRecord.setText(0 + "/16 points remaining");
            }
            ptRecord.setText(getPtRemain(pilotP, fighterP, traderP, engineerP) + "/16 points remaining");
        });
    }
    
    private boolean checkOk() {
        return 16 - (pilotP + fighterP + traderP + engineerP) >= 0;
    }
    
    
    private int getPtRemain(int p, int f, int t, int e) {
        return 16 - (p + f + t + e);
    }
    

}
