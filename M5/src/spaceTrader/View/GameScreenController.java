/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceTrader.View;

import static java.lang.Double.max;
import static java.lang.Double.min;
import java.net.URL;
//import java.util.List;
import java.util.ResourceBundle;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Planets.Universe;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import spaceTrader.Goods.Trade;
import spaceTrader.Planets.Capital;
import spaceTrader.Planets.PlayerShip;
/**
 * FXML Controller class
 *
 * @author Shaohui Xu
 */
public class GameScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;

    @FXML
    private TitledPane galaChart;

    @FXML
    private TitledPane srChart;
    
    @FXML
    private TitledPane systemInfo;
    
    @FXML
    private TitledPane targetInfo;
    
    @FXML
    private AnchorPane shipyard;
    
    @FXML
    private AnchorPane dock;
    
    @FXML
    private ListView targetListView;
    
    @FXML
    private Canvas canvas;
    
    @FXML
    private Canvas canvas2;
    
    @FXML
    private AnchorPane cargoChart;
    
    @FXML
    private Button findPlanet;
    
    @FXML
    private ListView systemListView;
    
    @FXML
    private void findPlanetFired(ActionEvent event) {
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Universe uni = new Universe();
        List<SolarSystem> solarList = uni.getUniverse();
        
        // draw long-range
        for (SolarSystem ss : solarList) {
            int x = ss.getX();
            int y = ss.getY();
            gc.fillOval(x - 1, y - 1, 2, 2);
        }
        Random rand = new Random();
        int index = rand.nextInt(solarList.size());
        SolarSystem startSystem = solarList.get(index);
        Capital curr = startSystem.getPlanet();
        PlayerShip ship = new PlayerShip();
        int range = ship.getMaxRange();
        int currX = startSystem.getX();
        int currY = startSystem.getY();
        gc.strokeOval(currX - range, currY - range, 2*range, 2*range);
        
        //target system
        String currName = "Name:  " + curr.getName();
        //String capSize = "Name  " + cap.get
        String currTechLevel = "Tech Level:  " + curr.getTechLevel();
        String currGovernment = "Government:  " + curr.getPoliticalSystem();
        String currResource = "Resource:  " + curr.getResourcesLevel();
        String currPolice = "Police:  " + curr.getPolice();
        String currPirate = "Pirate:  " + curr.getPirate();
        ObservableList<String> items =FXCollections.observableArrayList (
        currName, currTechLevel, currGovernment, currResource, currPolice, currPirate);
        systemListView.setItems(items);
        
        //draw short-range
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        double h = canvas2.getHeight();
        double w = canvas2.getWidth();
        double smallBound = min(h, w);
        double bigBound = max(h, w);
        gc2.fillOval(w/2 - 2, h/2 - 2, 4, 4);
        gc2.strokeOval(w/2 - smallBound/4, h/2 - smallBound/4, smallBound/2, smallBound/2);
        double startX = currX - 40;
        double startY = currY - 40;
        double endX = currX + 40;
        double endY = currY + 40;
        double ratio = (smallBound/4)/20;
        for (SolarSystem ss : solarList) {
            int x = ss.getX();
            int y = ss.getY();
            if (x >= startX && x <= endX && y >= startY && y <= endY) {
                double zoomInH = (x - currX) * ratio;
                double zoomInV = (y - currY) * ratio;
                gc2.fillOval(w/2 + zoomInH - 2, h/2 + zoomInV - 2, 4, 4);
            }
        }
        ship.loadShip();
        //set target system listview
        ObservableList<String> items2 = FXCollections.observableArrayList(
        "Name: ", "Tech Level: ", "Government: ", "Resource: ", "Police: ", "Pirate: ");
        targetListView.setItems(items2);
        
//        Trade trade = new Trade(player, playerShip, system);
//        List<String> goodsToSell = trade.getGoodsToSell();
//        List<String> goodsToBuy = trade.getGoodsToBuy();
    }
}
