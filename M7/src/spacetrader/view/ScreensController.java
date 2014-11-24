/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
//import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


/**
 *
 * @author zixiangzhu
 */
public class ScreensController extends StackPane {
    private HashMap<String, Node> screens;
    private Class<? extends ScreensController> myClass;
    private URL myResource;
    private Parent loadScreen;
    private FXMLLoader myLoader;
    private ControlledScreen myScreenController;
    private boolean loadSuccess;
    private DoubleProperty opacity;
    private ObservableList<Node> children;
    private Timeline fadeIn;
    private boolean screenSet;
    private Timeline fade;
    private static final Class<ScreensController> MYCLASS = ScreensController.class;
	private static final String MYCLASSNAME = MYCLASS.getName();
	private static final Logger LOGGER = Logger.getLogger(MYCLASSNAME);
	private transient Throwable npe;
    
    public ScreensController() {
        super();
        screens = new HashMap<>();
        myClass = ScreensController.class;

    }
    
    /**
     * add screen to the map
     * @param name
     * @param screen
     */
    public void addScreen(final String name, final Node screen) {
        screens.put(name, screen);
    }
    
    /**
     * get name of the screen
     * @param name
     * @return
     */
    public Node getScreen(final String name) {
        return screens.get(name);
    }
    
    public boolean loadScreen(String name, String resource) {
        loadSuccess = false;
        myClass = getClass();
        myResource = myClass.getResource(resource);
        myLoader = new FXMLLoader(myResource);
        try {
            loadScreen = (Parent) myLoader.load();
            myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            loadSuccess = true;
        } catch (IOException e) {
                // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, npe.getMessage(), npe);
        }
       
        return loadSuccess;
    }
    
    public boolean setScreen(final String name) {
        
        screenSet = false;
        if (screens.get(name) == null) {
            screenSet = false;
        } else {
            opacity = opacityProperty();
            children = getChildren();
            if (children.isEmpty()) { //
                setOpacity(0.0);
                children.add(screens.get(name));
                fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(120), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            } else {
                fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(100), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t1) {
                                children.remove(0);
                                children.add(0, screens.get(name));
                                Timeline fadeIn;
                                fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO,
                                                new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(150),
                                                new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                        fade.play();
            }
            screenSet = true;
        }
        return screenSet;
    }
    
    public boolean unloadScreen(String name) {
        return screens.remove(name) != null;
    }
    
    
    
    
    
} 
