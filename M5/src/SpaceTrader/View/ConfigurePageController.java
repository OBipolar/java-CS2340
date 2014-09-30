/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SpaceTrader.View;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
//import SpaceTrader.Model.GameCharacter;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import SpaceTrader.Model.GameCharacter;
import SpaceTrader.Model.Universe;

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
    private GameCharacter player;

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

    private void clear() {
        userNameInput.setText("");
        pilotPt.setText("0");
        fighterPt.setText("0");
        traderPt.setText("0");
        engineerPt.setText("0");
        messageLabel.setText("");
    }

    @FXML
    private void okButtonFired(ActionEvent event) {
        System.out.println("ok fired");
        if (pilotP + fighterP + traderP + engineerP == 16 && userName != "") {
            player = new GameCharacter(userName, pilotP, fighterP, traderP,
                    engineerP);
            messageLabel.setText("Profile Successfully Created!");
        } else if (userName == "") {
            messageLabel.setText("Username Required!");
        } else {
            messageLabel.setText("Needs to allocate all skill points!");
        }

    }

    @FXML
    private void cancelButtonFired(ActionEvent event) {
        System.out.println("cancel fired");
        myController.setScreen(Main.screen1ID);
        clear();
    }

    @FXML
    private void nextButtonFired(ActionEvent event) {
        if (player != null) {
            myController.setScreen(Main.screen3ID);
            Universe universe = new Universe();
            System.out.println(universe.toString());
        }
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
        player = null;

        userNameInput.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    userName = newValue;
                });

        pilotPt.textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            System.out.println(oldValue);
                            IntegerStringConverter converter = new IntegerStringConverter();
                            if (newValue.equals("")) {
                                pilotP = 0;
                            } else {
                                pilotP = converter.fromString(newValue);
                            }
                            if (!checkOk()) {
                                pilotP = 16 - fighterP - traderP - engineerP;
                                pilotPt.replaceText(0, newValue.length(), ""
                                        + pilotP);
                                ptRecord.setText(0 + "/16 points remaining");
                            }
                            ptRecord.setText(getPtRemain(pilotP, fighterP,
                                    traderP, engineerP)
                                    + "/16 points remaining");
                        });

        fighterPt
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            IntegerStringConverter converter = new IntegerStringConverter();
                            if (newValue.equals("")) {
                                fighterP = 0;
                            } else {
                                fighterP = converter.fromString(newValue);
                            }
                            if (!checkOk()) {
                                fighterP = 16 - pilotP - traderP - engineerP;
                                fighterPt.replaceText(0, newValue.length(), ""
                                        + fighterP);
                                ptRecord.setText(0 + "/16 points remaining");
                            }
                            ptRecord.setText(getPtRemain(pilotP, fighterP,
                                    traderP, engineerP)
                                    + "/16 points remaining");
                        });

        traderPt.textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            IntegerStringConverter converter = new IntegerStringConverter();
                            if (newValue.equals("")) {
                                traderP = 0;
                            } else {
                                traderP = converter.fromString(newValue);
                            }
                            if (!checkOk()) {
                                traderP = 16 - fighterP - pilotP - engineerP;
                                traderPt.replaceText(0, newValue.length(), ""
                                        + traderP);
                                ptRecord.setText(0 + "/16 points remaining");
                            }
                            ptRecord.setText(getPtRemain(pilotP, fighterP,
                                    traderP, engineerP)
                                    + "/16 points remaining");
                        });

        engineerPt
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            IntegerStringConverter converter = new IntegerStringConverter();
                            if (newValue.equals("")) {
                                engineerP = 0;
                            } else {
                                engineerP = converter.fromString(newValue);
                            }
                            if (!checkOk()) {
                                engineerP = 16 - fighterP - traderP - pilotP;
                                engineerPt.replaceText(0, newValue.length(), ""
                                        + engineerP);
                                ptRecord.setText(0 + "/16 points remaining");
                            }
                            ptRecord.setText(getPtRemain(pilotP, fighterP,
                                    traderP, engineerP)
                                    + "/16 points remaining");
                        });
    }

    private boolean checkOk() {
        return 16 - (pilotP + fighterP + traderP + engineerP) >= 0;
    }

    private int getPtRemain(int p, int f, int t, int e) {
        return 16 - (p + f + t + e);
    }

}
