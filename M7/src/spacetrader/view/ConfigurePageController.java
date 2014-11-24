/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import spacetrader.apis.SqliteApi;
import spacetrader.planets.GameCharacter;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    static ScreensController myController;
    private transient String userName;
    private transient int pilotP;
    private transient int fighterP;
    private transient int traderP;
    private transient int engineerP;
    private transient GameCharacter player;
    private transient String sixteenPt;

    @FXML
    private transient TextField userNameInput;

    @FXML
    private transient TextField pilotPt;

    @FXML
    private transient TextField fighterPt;

    @FXML
    private transient TextField traderPt;

    @FXML
    private transient TextField engineerPt;

    @FXML
    private transient Label ptRecord;

    @FXML
    private transient Label messageLabel;
    private transient StringProperty userNameProperty;
    private transient IntegerStringConverter converter;
    private transient StringProperty pilotProperty;
    private transient StringProperty fighterProperty;
    private transient StringProperty traderProperty;
    private transient StringProperty engineerProperty;
    private transient SqliteApi sqlite;
    private static Logger logger;
    private static Throwable npe;
    private static Class<ConfigurePageController> myClass;
    private transient String myClassName;

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
        if (pilotP + fighterP + traderP + engineerP == 16 && !userName.isEmpty()) {
            
            messageLabel.setText("Profile Successfully Created!");
            
        } else if (userName.isEmpty()) {
            messageLabel.setText("Username Required!");
        } else {
            messageLabel.setText("Needs to allocate all skill points!");
        }

    }

    @FXML
    private void cancelButtonFired(ActionEvent event) {
        myController.setScreen(Main.screen1ID);
        clear();
    }

    @FXML
    private void nextButtonFired(ActionEvent event) {
            player = new GameCharacter(userName, pilotP, fighterP, traderP,
                engineerP);
            try {
                sqlite = new SqliteApi(player);
                myController.loadScreen("gamePage", "GameScreen.fxml");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                logger.log(Level.SEVERE, npe.getMessage(), npe);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.log(Level.SEVERE, npe.getMessage(), npe);
            }
            myController.setScreen(Main.screen3ID);
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
        sixteenPt = "/16 points remaining";
        myClass = ConfigurePageController.class;
        myClassName = myClass.getName();
        logger = Logger.getLogger(myClassName);

        userNameProperty = userNameInput.textProperty();
        userNameProperty.addListener(
            (observable, oldValue, newValue) -> {
            userName = newValue;
        });
        converter = new IntegerStringConverter();
        
        pilotProperty = pilotPt.textProperty();
        pilotProperty.addListener(
            (observable, oldValue, newValue) -> {
                if (newValue.isEmpty()) {
                    pilotP = 0;
                } else {
                    pilotP = converter.fromString(newValue);
                }
                if (!checkOk()) {
                    pilotP = 16 - fighterP - traderP - engineerP;
                    pilotPt.replaceText(0, newValue.length(), Integer.toString(pilotP));
                            ptRecord.setText(0 + sixteenPt);
                }
                ptRecord.setText(getPtRemain(pilotP, fighterP, traderP, engineerP)
                          + sixteenPt);
                });

        fighterProperty = fighterPt.textProperty();
        fighterProperty.addListener(
                        (observable, oldValue, newValue) -> {
                            if (newValue.isEmpty()) {
                                fighterP = 0;
                            } else {
                                fighterP = converter.fromString(newValue);
                            }
                            if (!checkOk()) {
                                fighterP = 16 - pilotP - traderP - engineerP;
                                fighterPt.replaceText(0, newValue.length(),
                                          Integer.toString(fighterP));
                                ptRecord.setText(0 + sixteenPt);
                            }
                            ptRecord.setText(getPtRemain(pilotP, fighterP,
                                    traderP, engineerP)
                                    + sixteenPt);
                        });

        traderProperty = traderPt.textProperty();
        traderProperty.addListener(
                        (observable, oldValue, newValue) -> {
                            if (newValue.isEmpty()) {
                                traderP = 0;
                            } else {
                                traderP = converter.fromString(newValue);
                            }
                            if (!checkOk()) {
                                traderP = 16 - fighterP - pilotP - engineerP;
                                traderPt.replaceText(0, newValue.length(),
                                         Integer.toString(traderP));
                                ptRecord.setText(0 + sixteenPt);
                            }
                            ptRecord.setText(getPtRemain(pilotP, fighterP,
                                    traderP, engineerP)
                                    + sixteenPt);
                        });

        engineerProperty = engineerPt.textProperty();
        engineerProperty.addListener(
                        (observable, oldValue, newValue) -> {
                            if (newValue.isEmpty()) {
                                engineerP = 0;
                            } else {
                                engineerP = converter.fromString(newValue);
                            }
                            if (!checkOk()) {
                                engineerP = 16 - fighterP - traderP - pilotP;
                                engineerPt.replaceText(0, newValue.length(),
                                           Integer.toString(engineerP));
                                ptRecord.setText(0 + sixteenPt);
                            }
                            ptRecord.setText(getPtRemain(pilotP, fighterP,
                                    traderP, engineerP)
                                    + sixteenPt);
                        });
    }

    private boolean checkOk() {
        return 16 - (pilotP + fighterP + traderP + engineerP) >= 0;
    }

    private int getPtRemain(final int pilot, final int fighter,
          final int trader, final int engineer) {
        return 16 - (pilot + fighter + trader + engineer);
    }

}
