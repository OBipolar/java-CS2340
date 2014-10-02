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
import java.util.Map;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spaceTrader.Goods.Trade;
import spaceTrader.Planets.Capital;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.PlayerShip;
import javafx.util.converter.IntegerStringConverter;
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
    
    SolarSystem solarSystem;
    PlayerShip ship;
    Trade trade;
    
    Label label1;
    Label label2;
    Label label3;
    
    @FXML
    private Label waterPrice2;
    
    @FXML
    private Label fursPrice2;
    
    @FXML
    private Label foodPrice2;
    
    @FXML
    private Label orePrice2;
    
    @FXML
    private Label gamesPrice2;
    
    @FXML
    private Label firearmsPrice2;
    
    @FXML
    private Label medicinePrice2;
    
    @FXML
    private Label machinesPrice2;
    
    @FXML
    private Label narcoticsPrice2;
    
    @FXML
    private Label waterPrice;
    
    @FXML
    private Label fursPrice;
    
    @FXML
    private Label foodPrice;
    
    @FXML
    private Label orePrice;
    
    @FXML
    private Label gamesPrice;
    
    @FXML
    private Label firearmsPrice;
    
    @FXML
    private Label medicinePrice;
    
    @FXML
    private Label machinesPrice;
    
    @FXML
    private Label narcoticsPrice;
    
    int maxNum;
    int sellPrice;
    int buyPrice;
    int lpu;
    GameCharacter player;
    
    @FXML
    private void findPlanetFired(ActionEvent event) {
        
    }
    
    private void setLabel(int maxNum, int sellPrice, int buyPrice, int lpu) {
        String m1 = "You can sell up to " + maxNum + " at " + sellPrice + " cr. each.";
        String m2 = "You paid about " + buyPrice + " cr. per unit.";
        String m3 = "Your loss per unit is " + lpu + " cr.";
        label1.setText(m1);
        label2.setText(m2);
        label3.setText(m3);
    }
    
    @FXML    
    private void waterAmountFired(ActionEvent event) {
        setLabel(maxNum, sellPrice, buyPrice, lpu);
        showPopUp(label1, label2, label3);
        
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    private void showPopUp(Label label1, Label label2, Label label3) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
        children(label1, label2, label3, new TextField(), new Label(), new Button("Ok.")).
        alignment(Pos.CENTER).padding(new Insets(20)).build()));
        dialogStage.show();
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
        solarSystem = solarList.get(index);
        Capital curr = solarSystem.getPlanet();
        ship = new PlayerShip();
        int range = ship.getMaxRange();
        int currX = solarSystem.getX();
        int currY = solarSystem.getY();
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
        
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        player = new GameCharacter("David", 4, 4, 4, 4);
        trade = new Trade(player, ship, solarSystem);
        List<String> toBuyList = trade.getGoodsToBuy();
        List<String> toSellList = trade.getGoodsToSell();
        Map<String, Integer> toSellMap = trade.getPricesToSell();
        Map<String, Integer> toBuyMap = trade.getPricesToBuy();
        //IntegerStringConverter converter = new IntegerStringConverter();
        //
    
         waterPrice2.setText("Can't Buy");
        
         fursPrice2.setText("Can't Buy");
        
         foodPrice2.setText("Can't Buy");
        
         orePrice2.setText("Can't Buy");
        
         gamesPrice2.setText("Can't Buy");
        
         firearmsPrice2.setText("Can't Buy");
        
         medicinePrice2.setText("Can't Buy");
        
         machinesPrice2.setText("Can't Buy");
        
         narcoticsPrice2.setText("Can't Buy");
        
         waterPrice.setText("Can't Sell");
        
         fursPrice.setText("Can't Sell");
        
         foodPrice.setText("Can't Sell");
        
         orePrice.setText("Can't Sell");
        
         gamesPrice.setText("Can't Sell");
        
         firearmsPrice.setText("Can't Sell");
        
         medicinePrice.setText("Can't Sell");
        
         machinesPrice.setText("Can't Sell");
        
         narcoticsPrice.setText("Can't Sell");

        for (String s : toBuyList) {
            if (s.equals("Water")) {
                waterPrice2.setText("" + toBuyMap.get("Water"));
            } else if (s.equals("Furs")) {
                fursPrice2.setText("" + toBuyMap.get("Furs"));
            } else if (s.equals("Food")) {
                foodPrice2.setText("" + toBuyMap.get("Food"));
            } else if (s.equals("Ore")) {
                orePrice2.setText("" + toBuyMap.get("Ore"));
            } else if (s.equals("Games")) {
                gamesPrice2.setText("" + toBuyMap.get("Games"));
            } else if (s.equals("Firearms")) {
                firearmsPrice2.setText("" + toBuyMap.get("Firearms"));
            } else if (s.equals("Medicines")) {
                medicinePrice2.setText("" + toBuyMap.get("Medicines"));
            } else if (s.equals("Narcotics")) {
                narcoticsPrice2.setText("" + toBuyMap.get("Narcotics"));
            } else if (s.equals("Machines")) {
                machinesPrice2.setText("" + toBuyMap.get("water"));
            } 
        }
        
        for (String s : toSellList) {
            if (s.equals("Water")) {
                sellPrice = toSellMap.get("Water");
                waterPrice.setText("" + sellPrice);
            } else if (s.equals("Furs")) {
                sellPrice = toSellMap.get("Furs");
                fursPrice.setText("" + sellPrice);
            } else if (s.equals("Food")) {
                sellPrice = toSellMap.get("Food");
                foodPrice.setText("" + sellPrice);
            } else if (s.equals("Ore")) {
                sellPrice = toSellMap.get("Ore");
                orePrice.setText("" + sellPrice);
            } else if (s.equals("Games")) {
                sellPrice = toSellMap.get("Games");
                gamesPrice.setText("" + sellPrice);
            } else if (s.equals("Firearms")) {
                sellPrice = toSellMap.get("Firearms");
                firearmsPrice.setText("" + sellPrice);
            } else if (s.equals("Medicines")) {
                sellPrice = toSellMap.get("Medicines");
                medicinePrice.setText("" + sellPrice);
            } else if (s.equals("Narcotics")) {
                sellPrice = toSellMap.get("Narcotics");
                narcoticsPrice.setText("" + sellPrice);
            } else if (s.equals("Machines")) {
                sellPrice = toSellMap.get("water");
                machinesPrice.setText("" + sellPrice);
            } 
        }
        
        
    }
}
