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
import javafx.scene.control.ChoiceBox;
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
import spaceTrader.Goods.Good;
/**
 * FXML Controller class
 *
 * @author Shaohui Xu
 */
public class GameScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    private IntegerStringConverter converter;
    private List<String> toBuyList;
    private List<String> toSellList;
    private Map<String, Integer> toSellMap;
    private Map<String, Integer> toBuyMap;
    
    private List<String> toBuyList2;
    private List<String> toSellList2;
    private Map<String, Integer> toSellMap2;
    private Map<String, Integer> toBuyMap2;
    
    private SolarSystem solarSystem;
    private PlayerShip ship;
    private Trade trade;
    private GameCharacter player;
    
    private int maxNum;
    private int sellPrice;
    private int buyPrice;
    private int lpu;
    
    private int waterSellPrice;
    private int fursSellPrice;
    private int foodSellPrice;
    private int oreSellPrice;
    private int gamesSellPrice;
    private int firearmsSellPrice;
    private int medicinesSellPrice;
    private int machinesSellPrice;
    private int narcoticsSellPrice;
     
    private int waterBuyPrice;
    private int fursBuyPrice;
    private int foodBuyPrice;
    private int oreBuyPrice;
    private int gamesBuyPrice;
    private int firearmsBuyPrice;
    private int medicinesBuyPrice;
    private int machinesBuyPrice;
    private int narcoticsBuyPrice;

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
    private ChoiceBox waterChoose;
    @FXML
    private ChoiceBox fursChoose;
    @FXML
    private ChoiceBox foodChoose;
    @FXML
    private ChoiceBox oreChoose;
    @FXML
    private ChoiceBox gamesChoose;
    @FXML
    private ChoiceBox firearmsChoose;
    @FXML
    private ChoiceBox medicinesChoose;
    @FXML
    private ChoiceBox machinesChoose;
    @FXML
    private ChoiceBox narcoticsChoose;
    
    @FXML
    private ChoiceBox waterChoose2;
    @FXML
    private ChoiceBox fursChoose2;
    @FXML
    private ChoiceBox foodChoose2;
    @FXML
    private ChoiceBox oreChoose2;
    @FXML
    private ChoiceBox gamesChoose2;
    @FXML
    private ChoiceBox firearmsChoose2;
    @FXML
    private ChoiceBox medicinesChoose2;
    @FXML
    private ChoiceBox machinesChoose2;
    @FXML
    private ChoiceBox narcoticsChoose2;

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
     private Label medicinesPrice2;
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
     private Label medicinesPrice;
    @FXML
     private Label machinesPrice;
    @FXML
     private Label narcoticsPrice;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void findPlanetFired(ActionEvent event) {
        
    }
    
    @FXML    
    private void waterSellFired(ActionEvent event) {
        if (waterChoose.getValue() != null) {
            int amount = converter.fromString(waterChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + waterSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            int count = 0;
            for (Good g : ship.getCargo()) {
                if (count < amount && g.getName().equals("Water")) {
                    ship.remove(g);
                    count++;
                }
            }
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }      
    }
    
     @FXML    
     private void fursSellFired(ActionEvent event) {
        if (fursChoose.getValue() != null) {
            int amount = converter.fromString(fursChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + fursSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        } 
    }
     @FXML    
     private void foodSellFired(ActionEvent event) {  
        if (foodChoose.getValue() != null) {
            int amount = converter.fromString(foodChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + foodSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }
     @FXML    
     private void oreSellFired(ActionEvent event) {   
        if (oreChoose.getValue() != null) {
            int amount = converter.fromString(oreChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + oreSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }
     @FXML    
     private void gamesSellFired(ActionEvent event) { 
        if (gamesChoose.getValue() != null) {
            int amount = converter.fromString(gamesChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + gamesSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        } 
    }
     @FXML    
     private void firearmsSellFired(ActionEvent event) {   
        if (firearmsChoose.getValue() != null) {
            int amount = converter.fromString(firearmsChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + firearmsSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }
     @FXML    
     private void medicinesSellFired(ActionEvent event) {  
       if (medicinesChoose.getValue() != null) {
            int amount = converter.fromString(medicinesChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + medicinesSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }
     @FXML    
     private void machinesSellFired(ActionEvent event) { 
        if (machinesChoose.getValue() != null) {
            int amount = converter.fromString(machinesChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + machinesSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }
     @FXML    
     private void narcoticsSellFired(ActionEvent event) { 
        if (narcoticsChoose.getValue() != null) {
            int amount = converter.fromString(narcoticsChoose.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() - amount);
            player.setMoney(player.getMoney() + narcoticsSellPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }
//     @FXML    
//     private void robocsSellFired(ActionEvent event) {   
//        
//     }
   
    @FXML    
    private void waterBuyFired(ActionEvent event) {
        if (waterChoose2.getValue() != null) {
            int amount = converter.fromString(waterChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - waterBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
       }
    }


    @FXML    
    private void fursBuyFired(ActionEvent event) {   
        if (fursChoose2.getValue() != null) {
            int amount = converter.fromString(fursChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - fursBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }

    @FXML    
    private void foodBuyFired(ActionEvent event) {  
        if (foodChoose2.getValue() != null) {
            int amount = converter.fromString(foodChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - foodBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        } 
    }

    @FXML    
    private void oreBuyFired(ActionEvent event) {   
        if (oreChoose2.getValue() != null) {
            int amount = converter.fromString(oreChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - oreBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
       }
    }

    @FXML    
    private void gamesBuyFired(ActionEvent event) {   
        if (gamesChoose2.getValue() != null) {
            int amount = converter.fromString(gamesChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - gamesBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }

    @FXML    
    private void firearmsBuyFired(ActionEvent event) {   
        if (firearmsChoose2.getValue() != null) {
            int amount = converter.fromString(firearmsChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - firearmsBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }

    @FXML    
    private void medicinesBuyFired(ActionEvent event) {   
        if (medicinesChoose2.getValue() != null) {
            int amount = converter.fromString(medicinesChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - medicinesBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }

    @FXML    
    private void machinesBuyFired(ActionEvent event) {   
        if (machinesChoose2.getValue() != null) {
            int amount = converter.fromString(machinesChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - machinesBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }

    @FXML    
    private void narcoticsBuyFired(ActionEvent event) {   
        if (narcoticsChoose2.getValue() != null) {
            int amount = converter.fromString(narcoticsChoose2.getValue().toString());
            ship.setNumOfGoods(ship.getNumOfGoods() + amount);
            player.setMoney(player.getMoney() - narcoticsBuyPrice * amount);
            System.out.println(player.getMoney());
            System.out.println(ship.getNumOfGoods());
            //mess(toBuyList, toBuyMap, toSellList, toSellMap);
        }
    }

//    @FXML    
//    private void roboticsBuyFired(ActionEvent event) {   
//    }
    
    
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
        
        player = new GameCharacter("David", 4, 4, 4, 4);
        trade = new Trade(player, ship, solarSystem);
        List<String> toBuyList = trade.getGoodsToBuy();
        List<String> toSellList = trade.getGoodsToSell();
        Map<String, Integer> toSellMap = trade.getPricesToSell();
        Map<String, Integer> toBuyMap = trade.getPricesToBuy();
        converter = new IntegerStringConverter();
        //
    
         waterPrice2.setText("Can't Buy");
         fursPrice2.setText("Can't Buy");
         foodPrice2.setText("Can't Buy");
         orePrice2.setText("Can't Buy");
         gamesPrice2.setText("Can't Buy");
         firearmsPrice2.setText("Can't Buy");
         medicinesPrice2.setText("Can't Buy");
         machinesPrice2.setText("Can't Buy");
         narcoticsPrice2.setText("Can't Buy");
         waterPrice.setText("Can't Sell");
        
         fursPrice.setText("Can't Sell");        
         foodPrice.setText("Can't Sell");        
         orePrice.setText("Can't Sell");        
         gamesPrice.setText("Can't Sell");        
         firearmsPrice.setText("Can't Sell");        
         medicinesPrice.setText("Can't Sell");        
         machinesPrice.setText("Can't Sell");        
         narcoticsPrice.setText("Can't Sell");
         mess(toBuyList, toBuyMap, toSellList, toSellMap);
         System.out.println(player.getMoney());
         System.out.println(ship.getNumOfGoods());
        
    }
    
    public void mess(List<String> toBuyList, Map<String, Integer> toBuyMap, List<String> toSellList, Map<String, Integer> toSellMap) {
        for (String s : toBuyList) {
            if (s.equals("Water")) {
                waterPrice2.setText("" + toBuyMap.get("Water"));
                int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                int maxAmount2 = player.getMoney() / toBuyMap.get("Water");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                waterChoose2.setItems(buyChoice);
            } else if (s.equals("Furs")) {
                fursPrice2.setText("" + toBuyMap.get("Furs"));
                         int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Furs");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                fursChoose2.setItems(buyChoice);
            } else if (s.equals("Food")) {
                foodPrice2.setText("" + toBuyMap.get("Food"));
                         int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Food");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                foodChoose2.setItems(buyChoice);
            } else if (s.equals("Ore")) {
                orePrice2.setText("" + toBuyMap.get("Ore"));
                         int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Ore");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                oreChoose2.setItems(buyChoice);
            } else if (s.equals("Games")) {
                gamesPrice2.setText("" + toBuyMap.get("Games"));
                int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Games");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                gamesChoose2.setItems(buyChoice);
            } else if (s.equals("Firearms")) {
                firearmsPrice2.setText("" + toBuyMap.get("Firearms"));
                         int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Firearms");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                firearmsChoose2.setItems(buyChoice);
            } else if (s.equals("Medicines")) {
                medicinesPrice2.setText("" + toBuyMap.get("Medicines"));
                         int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Medicines");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                medicinesChoose2.setItems(buyChoice);
            } else if (s.equals("Narcotics")) {
                narcoticsPrice2.setText("" + toBuyMap.get("Narcotics"));
                         int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Narcotics");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                narcoticsChoose2.setItems(buyChoice);
            } else if (s.equals("Machines")) {
                machinesPrice2.setText("" + toBuyMap.get("water"));
                         int maxAmount1 = ship.getCargoSpace() - ship.getNumOfGoods();
                System.out.println(player.getMoney());
                System.out.println(ship.getCargo().size() - ship.getNumOfGoods());
                int maxAmount2 = player.getMoney() / toBuyMap.get("Machines");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                machinesChoose2.setItems(buyChoice);
            } 
        }
        
        for (String s : toSellList) {
            if (s.equals("Water")) {
                waterSellPrice = toSellMap.get("Water");
                waterPrice.setText("" + waterSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                waterChoose.setItems(choices);
                waterChoose.show();
                
            } else if (s.equals("Furs")) {
                fursSellPrice = toSellMap.get("Furs");
                fursPrice.setText("" + waterSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                fursChoose.setItems(choices);
                fursChoose.show();
            } else if (s.equals("Food")) {
                foodSellPrice = toSellMap.get("Food");
                foodPrice.setText("" + foodSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                foodChoose.setItems(choices);
                foodChoose.show();
            } else if (s.equals("Ore")) {
                oreSellPrice = toSellMap.get("Ore");
                orePrice.setText("" + oreSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                oreChoose.setItems(choices);
                oreChoose.show();
            } else if (s.equals("Games")) {
                gamesSellPrice = toSellMap.get("Games");
                gamesPrice.setText("" + gamesSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                gamesChoose.setItems(choices);
                gamesChoose.show();
            } else if (s.equals("Firearms")) {
                firearmsSellPrice = toSellMap.get("Firearms");
                firearmsPrice.setText("" + firearmsSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                firearmsChoose.setItems(choices);
                firearmsChoose.show();
            } else if (s.equals("Medicines")) {
                medicinesSellPrice = toSellMap.get("Medicines");
                medicinesPrice.setText("" + medicinesSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                medicinesChoose.setItems(choices);
                medicinesChoose.show();
            } else if (s.equals("Narcotics")) {
                narcoticsSellPrice = toSellMap.get("Narcotics");
                narcoticsPrice.setText("" + narcoticsSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                narcoticsChoose.setItems(choices);
                narcoticsChoose.show();
            } else if (s.equals("Machines")) {
                machinesSellPrice = toSellMap.get("Machines");
                machinesPrice.setText("" + machinesSellPrice);
                int maxSell = ship.getCargo().size();
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                machinesChoose.setItems(choices);
                machinesChoose.show();
            } 
        }
    }
    
    
    
    
    
}
