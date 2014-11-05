/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceTrader.View;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.APIs.MarketPlace;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
import spaceTrader.Ships.ShipFactory;
import static java.lang.Double.max;
import static java.lang.Double.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spaceTrader.Goods.Trade;
import spaceTrader.Planets.Capital;
import spaceTrader.Planets.GameCharacter;
//import spaceTrader.Planets.PlayerShip;
import javafx.util.converter.IntegerStringConverter;
import spaceTrader.APIs.MarketPlace;
import spaceTrader.APIs.RandomEvent;
import spaceTrader.APIs.ShipYard;
import spaceTrader.APIs.SqliteAPI;
import spaceTrader.APIs.Travel;
import spaceTrader.Goods.Firearms;
import spaceTrader.Goods.Food;
import spaceTrader.Goods.Furs;
import spaceTrader.Goods.Games;
import spaceTrader.Goods.Good;
import spaceTrader.Goods.Machines;
import spaceTrader.Goods.Medicine;
import spaceTrader.Goods.Narcotics;
import spaceTrader.Goods.Ore;
import spaceTrader.Goods.Water;
/**
 * FXML Controller class
 *
 * @author Zixiang Zhu/Sicong Chen
 */
public class GameScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    private IntegerStringConverter converter;
    private Universe uni;
    private List<SolarSystem> solarList;
    private List<String> toBuyList;
    private List<String> toSellList;
    private List<String> shipNames;
    private Map<String, Integer> toSellMap;
    private Map<String, Integer> toBuyMap;
    private MarketPlace mp;
    private SqliteAPI db;
    private ShipYard sy;
    private int currX;
    private int currY;
    private int maxFuel;
    private int maxPull;
    private int realFuel;
    private int hull;
    private int travelDistance;
    private GraphicsContext gc;
    private GraphicsContext gc2;
    private Travel travel;
    private IntegerStringConverter isConvert;
    
    private SolarSystem solarSystem;
    private SolarSystem targetSystem;
    private PlayerShip ship;
    private Trade trade;
    private GameCharacter player;
    private List<String> reachablePlanets;
    private RandomEvent re;
    private boolean checkFindFired;
    
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
    private ListView dockListView;
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas canvas2;
    @FXML
    private TextField selectShip;
    @FXML
    private AnchorPane cargoChart;
    @FXML
    private Button findPlanet;
    @FXML
    private Button buyShip;
    @FXML
    private ListView systemListView;
    @FXML
    private ListView playerListView;
    @FXML
    private ListView travelInfoListView;
    @FXML
    private ListView shipyardListView;
    
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
    private ChoiceBox refuelChoose;
    @FXML
    private ChoiceBox repairChoose;

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
    @FXML
    private TextField findPlanetField;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    private void setMarketPlace() {
        mp = new MarketPlace();
    }
    
    @FXML
    private void findPlanetFired(ActionEvent event) {
        String planetName = findPlanetField.textProperty().get().toLowerCase();
        boolean found = false;
        for (String s : reachablePlanets) {
            String temp1 = s.toLowerCase();
            if (temp1.equals(planetName) && (!planetName.equals(solarSystem.getName().toLowerCase()))) {
                for (SolarSystem sys : uni.getUniverse()) {
                    if (sys.getName().equals(s)) {
                        targetSystem = sys;
                        found = true;
                        loadTargetInfo();
                    }
                }
            }
        }
        if (!found) {
            findPlanetField.textProperty().set("");
        }
        
    }
    
    private void loadTargetInfo() {
         //target system
        String targetName = "Name:  " + targetSystem.getPlanet().getName();
        //String capSize = "Name  " + cap.get
        String targetTechLevel = "Tech Level:  " + targetSystem.getPlanet().getTechLevel();
        String targetGovernment = "Government:  " + targetSystem.getPlanet().getPoliticalSystem();
        String targetResource = "Resource:  " + targetSystem.getPlanet().getResourcesLevel();
        String targetPolice = "Police:  " + targetSystem.getPlanet().getPolice();
        String targetPirate = "Pirate:  " + targetSystem.getPlanet().getPirate();
        ObservableList<String> items =FXCollections.observableArrayList (
        targetName, targetTechLevel, targetGovernment, targetResource, targetPolice, targetPirate);
        targetListView.setItems(items);
    }
    
    private void loadCurrentInfo() {
         //target system
        String currName = "Name:  " + solarSystem.getPlanet().getName();
        //String capSize = "Name  " + cap.get
        String currTechLevel = "Tech Level:  " + solarSystem.getPlanet().getTechLevel();
        String currGovernment = "Government:  " + solarSystem.getPlanet().getPoliticalSystem();
        String currResource = "Resource:  " + solarSystem.getPlanet().getResourcesLevel();
        String currPolice = "Police:  " + solarSystem.getPlanet().getPolice();
        String currPirate = "Pirate:  " + solarSystem.getPlanet().getPirate();
        ObservableList<String> items =FXCollections.observableArrayList (
        currName, currTechLevel, currGovernment, currResource, currPolice, currPirate);
        systemListView.setItems(items);
    }
    
    private void clearTargetListView() {
        ObservableList<String> items2 = FXCollections.observableArrayList(
        "Name: ", "Tech Level: ", "Government: ", "Resource: ", "Police: ", "Pirate: ");
        targetListView.setItems(items2);
    }
    
    private void showDockInfo(PlayerShip ship, int actFuel, int actPull) {
        String info1 = "You have fuel to fly " + actFuel + " parsecs";
        String info2;
        if (actFuel == maxFuel) {
            info2 = "Your tank is full";
        } else {
            info2 = "Fuel cost per unit: " + ship.getBase().getFuelCost() + " cr";
        }
        String info3 = "Your hull strength is " + actPull;
        String info4;
        if (actPull == maxPull) {
            info4 = "No repairs needed";
        } else {
            info4 = "Repairm Cost per hull strength: " + ship.getBase().getRepairCost() + " cr";
        }
        ObservableList<String> dockInfo = FXCollections.observableArrayList(
        info1, info2, info3, info4);
        dockListView.setItems(dockInfo);
    }
    
    @FXML
    private void warpFired(ActionEvent event) {
    	int targetX = targetSystem.getX();
        int targetY = targetSystem.getY();
        double travelSqr = pow(targetX - solarSystem.getX(), 2) + pow(targetY - solarSystem.getY(), 2);
        int travelDist = (int)(sqrt(travelSqr));
        
        travel = new Travel();
        travel.warpTo(targetSystem.getName(), travelDist, 1);
        int range = 0;
        try {
			db = new SqliteAPI();
			ship = db.getShip();
			range = ship.getBase().getFuel();
			hull = ship.getBase().getHullStrength();
			System.out.println("updated ship fuel: " + range);
			
			System.out.println("dist: " + travelDist);
		    System.out.println("range: " + range);
		    solarSystem = targetSystem;
		    trade = new Trade(player, ship, solarSystem);
		    toBuyList = trade.getGoodsToBuy();
		    toBuyMap = trade.getPricesToBuy();
		    toSellList = trade.getGoodsToSell();
		    toSellMap = trade.getPricesToSell();
		    mp = new MarketPlace();
		    mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
		        
		    showDockInfo(ship, range, hull);
		    gc = canvas.getGraphicsContext2D();
		    drawLongRange(targetX, targetY, range, gc);
		    gc2 = canvas2.getGraphicsContext2D();
		    drawShortRange(targetX, targetY, range, gc2);
		    loadCurrentInfo();
		    clearTargetListView();
		    re = new RandomEvent();
		    String s = re.update();
		    player = db.getPlayer();
	     
		    System.out.println("random event: " + s);
		    ObservableList<String> randomInfo = FXCollections.observableArrayList(s, "");
		    travelInfoListView.setItems(randomInfo);
		    updatePlayerInfo();
		    setRefuelChoose();
		    setRepairChoose();
		    sy = new ShipYard();
		    showShipNames(sy);
		        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        
    }
    
    private void updatePlayerInfo() {
    	try {
			db = new SqliteAPI();
			System.out.println("money after buy ship: " + db.getPlayer().getMoney());
			String s1 = "Cash: " + db.getPlayer().getMoney() + " cr";
			String s3 = "Current ship type: " + db.getShip().getBase().getName();
	        System.out.println("money after stolen (2): " + db.getPlayer().getMoney());
	        String s2 = "Cargo Space Remaining: " + (ship.getCargoSpace() - db.getShip().getCargo().size());
	        ObservableList<String> playerInfo = FXCollections.observableArrayList(
	         s1, s3, s2);
	        
	        for (Good g : db.getShip().getCargo()) {
	        	playerInfo.add(g.getName());
	        }
	        
	        playerListView.setItems(playerInfo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    private void setRefuelChoose() {
    	try {
			db = new SqliteAPI();
			
			int currFuel = db.getShip().getBase().getFuel();
			ShipFactory sf = new ShipFactory();
			Ship modelShip = sf.getShip(db.getShip().getBase().getName());
			int maxRefuel = modelShip.getFuel() - currFuel;
			
			ObservableList<String> choices =FXCollections.observableArrayList ();
            for (int i = maxRefuel; i >= 0; i--) {
                choices.add("" + i);
            }
            refuelChoose.setItems(choices);
            //refuelChoose.show();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    private void setRepairChoose() {
    	try {
			db = new SqliteAPI();
			int currStre = db.getShip().getBase().getHullStrength();
			ShipFactory sf = new ShipFactory();
			Ship modelShip = sf.getShip(db.getShip().getBase().getName());
			int maxRepair = modelShip.getHullStrength() - currStre;
			
			ObservableList<String> choices =FXCollections.observableArrayList ();
            for (int i = maxRepair; i >= 0; i--) {
                choices.add("" + i);
            }
            repairChoose.setItems(choices);
            //refuelChoose.show();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    private void refuelFired(ActionEvent event) {
        try {
			db = new SqliteAPI();
			int perCost = db.getShip().getBase().getFuelCost();
			int amount = converter.fromString(refuelChoose.getValue().toString());
			int Cost = amount * perCost;
			
			player = db.getPlayer();
			ship = db.getShip();
			player.setMoney(db.getPlayer().getMoney() - Cost);
			ship.getBase().setFuel(db.getShip().getBase().getFuel() + amount);
			updateDatabase(player,ship);
			setRefuelChoose();
			// update dock info, redraw long and short range charts
			showDockInfo(db.getShip(), db.getShip().getBase().getFuel(), db.getShip().getBase().getHullStrength());
			gc = canvas.getGraphicsContext2D();
		    drawLongRange(db.getPlayer().getXpos(), db.getPlayer().getYpos(), db.getShip().getBase().getFuel(), gc);
		    gc2 = canvas2.getGraphicsContext2D();
		    drawShortRange(db.getPlayer().getXpos(), db.getPlayer().getYpos(), db.getShip().getBase().getFuel(), gc2);
			
			
			updatePlayerInfo();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    @FXML
    private void repairFired(ActionEvent event) {
    	try {
			db = new SqliteAPI();
			int perCost = db.getShip().getBase().getRepairCost();
			int amount = converter.fromString(repairChoose.getValue().toString());
			int cost = amount * perCost;
			player = db.getPlayer();
			ship = db.getShip();
			player.setMoney(player.getMoney() - cost);
			ship.getBase().setHullStrength(ship.getBase().getHullStrength() + amount);
			updateDatabase(player,ship);
			setRepairChoose();
			showDockInfo(ship, ship.getBase().getFuel(), ship.getBase().getHullStrength());
			updatePlayerInfo();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    private void buyShipFired(ActionEvent event) {
    	sy = new ShipYard();
    	isConvert = new IntegerStringConverter();
    	int index = isConvert.fromString(selectShip.textProperty().get());
    	sy.playerBuy(shipNames.get(index - 1));
    	updatePlayerInfo();
    	try {
			db = new SqliteAPI();
			showDockInfo(db.getShip(), db.getShip().getBase().getFuel(), db.getShip().getBase().getHullStrength());
			gc = canvas.getGraphicsContext2D();
		    drawLongRange(db.getPlayer().getXpos(), db.getPlayer().getYpos(), db.getShip().getBase().getFuel(), gc);
		    gc2 = canvas2.getGraphicsContext2D();
		    drawShortRange(db.getPlayer().getXpos(), db.getPlayer().getYpos(), db.getShip().getBase().getFuel(), gc2);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML    
    private void waterSellFired(ActionEvent event) {
        if (waterChoose.getValue() != null) {
            int amount = converter.fromString(waterChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Water());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
    
     @FXML    
     private void fursSellFired(ActionEvent event) {
        if (fursChoose.getValue() != null) {
            int amount = converter.fromString(fursChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Furs());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       } 
    }
     @FXML    
     private void foodSellFired(ActionEvent event) {  
        if (foodChoose.getValue() != null) {
            int amount = converter.fromString(foodChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Food());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
     @FXML    
     private void oreSellFired(ActionEvent event) {   
        if (oreChoose.getValue() != null) {
            int amount = converter.fromString(oreChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Ore());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
     @FXML    
     private void gamesSellFired(ActionEvent event) { 
        if (gamesChoose.getValue() != null) {
            int amount = converter.fromString(gamesChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Games());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
     @FXML    
     private void firearmsSellFired(ActionEvent event) {   
        if (firearmsChoose.getValue() != null) {
            int amount = converter.fromString(firearmsChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Firearms());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
     @FXML    
     private void medicinesSellFired(ActionEvent event) {  
       if (medicinesChoose.getValue() != null) {
            int amount = converter.fromString(medicinesChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Medicine());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
     @FXML    
     private void machinesSellFired(ActionEvent event) { 
        if (machinesChoose.getValue() != null) {
            int amount = converter.fromString(machinesChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Machines());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
     @FXML    
     private void narcoticsSellFired(ActionEvent event) { 
        if (narcoticsChoose.getValue() != null) {
            int amount = converter.fromString(narcoticsChoose.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Narcotics());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

   
    @FXML    
    private void waterBuyFired(ActionEvent event) {
        if (waterChoose2.getValue() != null) {
            int amount = converter.fromString(waterChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Water());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void fursBuyFired(ActionEvent event) {   
        if (fursChoose2.getValue() != null) {
            int amount = converter.fromString(fursChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Furs());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void foodBuyFired(ActionEvent event) {  
        if (foodChoose2.getValue() != null) {
            int amount = converter.fromString(foodChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Food());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void oreBuyFired(ActionEvent event) {   
        if (oreChoose2.getValue() != null) {
            int amount = converter.fromString(oreChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Ore());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void gamesBuyFired(ActionEvent event) {   
        if (gamesChoose2.getValue() != null) {
            int amount = converter.fromString(gamesChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Games());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void firearmsBuyFired(ActionEvent event) {   
        if (firearmsChoose2.getValue() != null) {
            int amount = converter.fromString(firearmsChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Firearms());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void medicinesBuyFired(ActionEvent event) {   
        if (medicinesChoose2.getValue() != null) {
            int amount = converter.fromString(medicinesChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Medicine());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void machinesBuyFired(ActionEvent event) {   
        if (machinesChoose2.getValue() != null) {
            int amount = converter.fromString(machinesChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Machines());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }

    @FXML    
    private void narcoticsBuyFired(ActionEvent event) {   
        if (narcoticsChoose2.getValue() != null) {
            int amount = converter.fromString(narcoticsChoose2.getValue().toString());
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Narcotics());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            updatePlayerInfo();
       }
    }
    
    private void drawLongRange(int centerX, int centerY, int range, GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (SolarSystem ss : solarList) {
            int x = ss.getX();
            int y = ss.getY();
            gc.fillOval(x - 1, y - 1, 2, 2);
        }
        gc.strokeOval(centerX - range, centerY - range, 2*range, 2*range);
    }
    
    private List<String> drawShortRange(int centerX, int centerY, int range, GraphicsContext gc2) {
        gc2.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        reachablePlanets = new ArrayList<String>();
        double h = canvas2.getHeight();
        double w = canvas2.getWidth();
        double smallBound = min(h, w);
        gc2.fillOval(w/2 - 2, h/2 - 2, 4, 4);
        gc2.strokeOval(w/2 - smallBound/2, h/2 - smallBound/2, smallBound, smallBound);
        double ratio = (smallBound/2)/range;
        for (SolarSystem ss : solarList) {
            int x = ss.getX();
            int y = ss.getY();
            double zoomInH = (x - centerX) * ratio;
            double zoomInV = (y - centerY) * ratio;
            gc2.fillOval(w/2 + zoomInH - 2, h/2 + zoomInV - 2, 4, 4);
            double distSqr = pow(zoomInH, 2) + pow(zoomInV, 2);
            double newRad = ratio * range;
            double radSqr = pow(newRad, 2);
            if (distSqr <= radSqr) {
                gc2.strokeText(ss.getName(), w/2 + zoomInH - 2, h/2 + zoomInV - 2, 50);
                reachablePlanets.add(ss.getName());
            }
        }
        return reachablePlanets;
    }
    
    private void showShipNames(ShipYard sy) {
    	ObservableList<String> shipItem = FXCollections.observableArrayList();
    	 if (!sy.isYardExist()) {
    		 shipItem.add("Tech level on this planet is too low to have a shipyard!");
    	 } else {
    		 shipNames = sy.getShipNames();
    		 if (shipNames.size() == 0) {
    			 shipItem.add("You don't have enough money to buy any ship on this planet!");
    		 } else {
    			 int count = 1;
    	    	 for (String s : shipNames) {
    	    		 shipItem.add(count + ". " + s);
    	    		 count++;
    	    	 }
    		 }
    		 
    	 }
    	 shipyardListView.setItems(shipItem);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			db = new SqliteAPI();
			sy = new ShipYard();
			player = db.getPlayer();
	        ship = db.getShip();
	        uni = db.getUniverse();
	        //uni = new Universe();
	        realFuel = maxFuel = ship.getBase().getFuel();
	        hull = maxPull = ship.getBase().getHullStrength();
	        solarList = uni.getUniverse();
	        System.out.println("shipyard exists: " + sy.isYardExist());
	        showShipNames(sy);
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        GraphicsContext gc = canvas.getGraphicsContext2D();
        // draw map
        for (SolarSystem ss : solarList) {
            int x = ss.getX();
            int y = ss.getY();
            gc.fillOval(x - 1, y - 1, 2, 2);
            if (x == player.getXpos() && y == player.getYpos()) {
            	solarSystem = ss;
            	
            }
        }
        
        Capital curr = solarSystem.getPlanet();
        
        int range = ship.getBase().getFuel();
        currX = solarSystem.getX();
        currY = solarSystem.getY();
        drawLongRange(currX , currY, range, gc);
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        reachablePlanets = drawShortRange(currX, currY, range, gc2);
        
        loadCurrentInfo();
        clearTargetListView();
                
        trade = new Trade(player, ship, solarSystem);
        toBuyList = trade.getGoodsToBuy();
        toSellList = trade.getGoodsToSell();
        toSellMap = trade.getPricesToSell();
        toBuyMap = trade.getPricesToBuy();
        converter = new IntegerStringConverter();
    
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
         mp = new MarketPlace();
         mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
         
         //dock info
         showDockInfo(ship, maxFuel, maxPull);
         updatePlayerInfo();
         
    }
    
    private int countNumOfGood(List<Good> cargo, String name) {
        int count = 0;
        for (Good g : cargo) {
            //System.out.println(g.getName());
            if (name.equals(g.getName())) {
                count++;
            }
        }
        //System.out.println("countnum: " + count);
        return count;
    }
    
    private void updateDatabase(GameCharacter player, PlayerShip ship) {
        try {
            db.openConnection();
            db.update(player, ship);
            db.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void mess(List<String> toBuyList, Map<String, Integer> toBuyMap, List<String> toSellList, Map<String, Integer> toSellMap, MarketPlace mp) {
        int maxAmount1;
        int maxAmount2;
        maxAmount1 = ship.getCargoSpace() - mp.getCargo().size();
        for (String s : toBuyList) {
            if (s.equals("Water")) {
                waterPrice2.setText("" + toBuyMap.get("Water"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Water");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                waterChoose2.setItems(buyChoice);
            } else if (s.equals("Furs")) {
                fursPrice2.setText("" + toBuyMap.get("Furs"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Furs");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                fursChoose2.setItems(buyChoice);
            } else if (s.equals("Food")) {
                foodPrice2.setText("" + toBuyMap.get("Food"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Food");  
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                foodChoose2.setItems(buyChoice);
            } else if (s.equals("Ore")) {
                orePrice2.setText("" + toBuyMap.get("Ore"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Ore"); 
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                oreChoose2.setItems(buyChoice);
            } else if (s.equals("Games")) {
                gamesPrice2.setText("" + toBuyMap.get("Games"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Games");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                gamesChoose2.setItems(buyChoice);
            } else if (s.equals("Firearms")) {
                firearmsPrice2.setText("" + toBuyMap.get("Firearms"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Firearms");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                firearmsChoose2.setItems(buyChoice);
            } else if (s.equals("Medicines")) {
                medicinesPrice2.setText("" + toBuyMap.get("Medicines"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Medicines");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                medicinesChoose2.setItems(buyChoice);
            } else if (s.equals("Narcotics")) {
                narcoticsPrice2.setText("" + toBuyMap.get("Narcotics"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Narcotics");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                narcoticsChoose2.setItems(buyChoice);
            } else if (s.equals("Machines")) {
                machinesPrice2.setText("" + toBuyMap.get("water"));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Machines");
                int maxBuy = Math.min(maxAmount1,maxAmount2);
                ObservableList<String> buyChoice = FXCollections.observableArrayList ();
                for (int i = maxBuy; i >= 0;i--) {
                    buyChoice.add("" + i);
                }
                machinesChoose2.setItems(buyChoice);
            } 
        }
        int maxSell;
        for (String s : toSellList) {
            if (s.equals("Water")) {
                waterSellPrice = toSellMap.get("Water");
                waterPrice.setText("" + waterSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Water");
                //System.out.println("maxsell: " + maxSell);
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                waterChoose.setItems(choices);
                waterChoose.show();
                
            } else if (s.equals("Furs")) {
                fursSellPrice = toSellMap.get("Furs");
                fursPrice.setText("" + fursSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Furs");
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                fursChoose.setItems(choices);
                fursChoose.show();
            } else if (s.equals("Food")) {
                foodSellPrice = toSellMap.get("Food");
                foodPrice.setText("" + foodSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Food");
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                foodChoose.setItems(choices);
                foodChoose.show();
            } else if (s.equals("Ore")) {
                oreSellPrice = toSellMap.get("Ore");
                orePrice.setText("" + oreSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Ore");
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                oreChoose.setItems(choices);
                oreChoose.show();
            } else if (s.equals("Games")) {
                gamesSellPrice = toSellMap.get("Games");
                gamesPrice.setText("" + gamesSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Games");
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                gamesChoose.setItems(choices);
                gamesChoose.show();
            } else if (s.equals("Firearms")) {
                firearmsSellPrice = toSellMap.get("Firearms");
                firearmsPrice.setText("" + firearmsSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Firearms");
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                firearmsChoose.setItems(choices);
                firearmsChoose.show();
            } else if (s.equals("Medicines")) {
                medicinesSellPrice = toSellMap.get("Medicines");
                medicinesPrice.setText("" + medicinesSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Medicines");
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                medicinesChoose.setItems(choices);
                medicinesChoose.show();
            } else if (s.equals("Narcotics")) {
                narcoticsSellPrice = toSellMap.get("Narcotics");
                narcoticsPrice.setText("" + narcoticsSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Narcotics");
                ObservableList<String> choices =FXCollections.observableArrayList ();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add("" + i);
                }
                narcoticsChoose.setItems(choices);
                narcoticsChoose.show();
            } else if (s.equals("Machines")) {
                machinesSellPrice = toSellMap.get("Machines");
                machinesPrice.setText("" + machinesSellPrice);
                maxSell = countNumOfGood(mp.getCargo(), "Machines");
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
