/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication6;

import java.net.URL;
import javafx.util.Duration;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 *
 * @author zixiangzhu
 */
public class ScreensController extends StackPane {
    private HashMap<String, Node> screens = new HashMap<>();
    
    public ScreensController() {
        super();
    }
    
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    */
    
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }
    
    public Node getScreen(String name) {
        return screens.get(name);
    }
    
    public boolean loadScreen(String name, String resource) {
        try{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
    
    public boolean setScreen(final String name) {
        //System.out.println(toString(getScreen))
        if (screens.get(name) != null) {
            DoubleProperty opacity = opacityProperty();
            if (!getChildren().isEmpty()) { //
                Timeline fade;
                fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(100), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);
                                getChildren().add(0, screens.get(name));
                                Timeline fadeIn;
                                fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(80), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                        fade.play();   
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(120), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println(name);
            System.out.println("Screen hasn't been laoded!\n");
            return false;
        }
    }
    
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen doesn't exist");
            return false;
        } else {
            return true;
        }
    }
    
    
    
    
    
}
