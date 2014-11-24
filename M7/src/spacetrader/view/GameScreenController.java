/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.view;

import spacetrader.goods.Trade;
import spacetrader.apis.RandomEvent;
import spacetrader.apis.ShipYard;
import spacetrader.apis.SqliteApi;
import spacetrader.apis.Travel;
import spacetrader.goods.Firearms;
import spacetrader.goods.Food;
import spacetrader.goods.Furs;
import spacetrader.goods.Games;
import spacetrader.goods.Good;
import spacetrader.goods.Machines;
import spacetrader.goods.Medicine;
import spacetrader.goods.Narcotics;
import spacetrader.goods.Ore;
import spacetrader.goods.Water;
import spacetrader.apis.MarketPlace;
import spacetrader.planets.GameCharacter;
import spacetrader.planets.Planet;
import spacetrader.planets.SolarSystem;
import spacetrader.planets.TechLevels;
import spacetrader.planets.Universe;
import javafx.beans.property.StringProperty;
import spacetrader.ships.PlayerShip;
import spacetrader.ships.Ship;
import spacetrader.ships.ShipFactory;





import static java.lang.Double.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;


/**
 * FXML Controller class
 *
 * @author Zixiang Zhu/Sicong Chen
 */
public class GameScreenController implements Initializable, ControlledScreen {
    
    private static ScreensController myController;
    private transient IntegerStringConverter converter;
    private transient Universe uni;
    private transient List<SolarSystem> solarList;
    private transient List<String> toBuyList;
    private transient List<String> toSellList;
    private transient List<String> shipNames;
    private transient List<String> weaponNames;
    private transient List<String> shieldNames;
    private transient List<String> gadgetNames;
    private transient Map<String, Integer> shipPrices;
    private transient Map<String, Integer> weaponPrices;
    private transient Map<String, Integer> shieldPrices;
    private transient Map<String, Integer> gadgetPrices;
    private transient Map<String, Integer> toSellMap;
    private transient Map<String, Integer> toBuyMap;
    private transient MarketPlace mp;
    private transient SqliteApi db;
    private transient String lowTechmsg;
    private transient ShipYard shipYard;
    private transient String cantbuy;
    private transient String cantsell;
    private transient int currX;
    private transient int currY;
    private transient int maxFuel;
    private transient int maxPull;
    private transient int hull;
    private transient GraphicsContext gc;
    private transient GraphicsContext gc2;
    private transient Travel travel;
    private transient IntegerStringConverter isConvert;
    private transient String tempShipName;
    private transient SolarSystem solarSystem;
    private transient SolarSystem targetSystem;
    private transient PlayerShip ship;
    private transient Ship base;
    private transient Planet planet;
    private transient Trade trade;
    private transient GameCharacter player;
    private transient List<String> reachablePlanets;
    private transient RandomEvent re;
    private transient ObservableList<String> playerInfo;
    private transient String creditStr;
    
    private transient int waterSellPrice;
    private transient int fursSellPrice;
    private transient int foodSellPrice;
    private transient int oreSellPrice;
    private transient int gamesSellPrice;
    private transient int firearmsSellPrice;
    private transient int medicinesSellPrice;
    private transient int machinesSellPrice;
    private transient int narcoticsSellPrice;

    @FXML
    private transient TitledPane galaChart;
    @FXML
    private transient TitledPane srChart;
    @FXML
    private transient TitledPane systemInfo;
    @FXML
    private transient TitledPane targetInfo;
    @FXML
    private transient AnchorPane shipyard;
    @FXML
    private transient AnchorPane dock;
    @FXML
    private transient TabPane shipyardPane;
    @FXML
    private transient ListView targetListView;
    @FXML
    private transient ListView dockListView;
    @FXML
    private transient Canvas canvas;
    @FXML
    private transient Canvas canvas2;
    @FXML
    private transient TextField selectShip;
    @FXML
    private transient AnchorPane cargoChart;
    @FXML
    private transient Button findPlanet;

    @FXML
    private transient ListView systemListView;
    @FXML
    private transient ListView playerListView;
    @FXML
    private transient ListView travelInfoListView;
    @FXML
    private transient ListView shipListView;
    @FXML
    private transient ListView weaponListView;
    @FXML
    private transient ListView shieldListView;
    @FXML
    private transient ListView gadgetListView;
    @FXML
    private transient Tab shipTab;
    @FXML
    private transient Tab weaponTab;
    @FXML
    private transient Tab shieldTab;
    @FXML
    private transient Tab gadgetTab;
    
    
    @FXML
    private transient ChoiceBox waterChoose;
    @FXML
    private transient ChoiceBox fursChoose;
    @FXML
    private transient ChoiceBox foodChoose;
    @FXML
    private transient ChoiceBox oreChoose;
    @FXML
    private transient ChoiceBox gamesChoose;
    @FXML
    private transient ChoiceBox firearmsChoose;
    @FXML
    private transient ChoiceBox medicinesChoose;
    @FXML
    private transient ChoiceBox machinesChoose;
    @FXML
    private transient ChoiceBox narcoticsChoose;
    private transient ObservableList<String> shipItem;
    private transient ObservableList<String> weaponItem;
    private transient ObservableList<String> shieldItem;
    private transient ObservableList<String> gadgetItem;
    
    @FXML
    private transient ChoiceBox waterChoose2;
    @FXML
    private transient ChoiceBox fursChoose2;
    @FXML
    private transient ChoiceBox foodChoose2;
    @FXML
    private transient ChoiceBox oreChoose2;
    @FXML
    private transient ChoiceBox gamesChoose2;
    @FXML
    private transient ChoiceBox firearmsChoose2;
    @FXML
    private transient ChoiceBox medicinesChoose2;
    @FXML
    private transient ChoiceBox machinesChoose2;
    @FXML
    private transient ChoiceBox narcoticsChoose2;
    @FXML
    private transient ChoiceBox refuelChoose;
    @FXML
    private transient ChoiceBox repairChoose;

    @FXML
    private transient Label waterPrice2;
    @FXML
     private transient Label fursPrice2;
    @FXML
     private transient Label foodPrice2;
    @FXML
     private transient Label orePrice2;
    @FXML
     private transient Label gamesPrice2;
    @FXML
     private transient Label firearmsPrice2;
    @FXML
     private transient Label medicinesPrice2;
    @FXML
     private transient Label machinesPrice2;
    @FXML
     private transient Label narcoticsPrice2;
    @FXML
     private transient Label waterPrice;
    @FXML
     private transient Label fursPrice;
    @FXML
     private transient Label foodPrice;
    @FXML
     private transient Label orePrice;
    @FXML
     private transient Label gamesPrice;
    @FXML
     private transient Label firearmsPrice;
    @FXML
     private transient Label medicinesPrice;
    @FXML
     private transient Label machinesPrice;
    @FXML
     private transient Label narcoticsPrice;
    @FXML
    private transient TextField findPlanetField;
    @FXML
    private transient TextField upgradeTextField;
    @FXML
    private transient MenuBar menuBar;
    private transient String planetName;
    private transient int count;
    private transient int techLevel;
    private transient TechLevels techLevels;
    private static final int SIXFINAL = 6;
    private transient List<Good> cargoList;
    private transient int shipCargoSpace;
    private transient ObservableList<Object> buyChoice;
    private transient ObservableList<Object> choices;
    private transient int maxBuy;
    private transient GraphicsContext graphicsContext;
    private transient double ratio;
    private transient Integer amount;
    private transient Object rawChoose;
    private transient String amountStr;
    private transient int index;
    private transient MultipleSelectionModel selectionMode;
    private transient String id;
    private transient int perCost;
    private transient int cost;
    private transient Object repairChooseRaw;
    private transient Object refuelChooseRaw;
    private transient int currStre;
    private transient Ship modelShip;
    private transient ShipFactory shipfactory;
    private transient int maxRepair;
    private transient int currFuel;
    private transient int maxRefuel;
    private transient String cashmsg;
    private transient String shipmsg;
    private transient String attackmsg;
    private transient String remWeaponmsg;
    private transient String remShieldmsg;
    private transient String remGadgetmsg;
    private transient String remCargomsg;
    private transient List<Good> cargo;
    private transient ObservableList<String> randomInfo;
    private transient int range;
    private transient String targetName;
    private transient String solarName;
    private transient String myString;
    private transient int targetX;
    private transient int targetY;
    private transient double travelSqr;
    private transient int travelDist;
    private transient ObservableList<String> dockInfo;
    private transient String info1;
    private transient String info2;
    private transient String info3;
    private transient String info4;
    private transient ObservableList<String> items2;
    private transient String currTechLevel;
    private transient String currGovernment;
    private transient String currResource;
    private transient String currPolice;
    private transient String currPirate;
    private transient ObservableList<String> items;
    private transient String currName;
    private transient String targetGovernment;
    private transient String targetTechLevel;
    private transient String targetResource;
    private transient String targetPolice;
    private transient String targetPirate;
    private transient StringProperty stringProperty;
    private transient boolean found;
    private transient String temp1;
    private transient StringProperty findFieldProperty;
    private transient String temp2;
    private transient SolarSystem sys;
    private transient String gadgetName;
    private transient String itemName;
    private transient String itemName2;
    //private transient int maxAmount1;
    private transient int maxAmount2;
	private static final Class<GameScreenController> MYCLASS = GameScreenController.class;
	private static final String MYCLASSNAME = MYCLASS.getName();
	private static final Logger LOGGER = Logger.getLogger(MYCLASSNAME);
	private transient Throwable npe;
	private String water;
	
   
    /**
     * set the controller to the parent screen
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    
    @FXML
    private void saveButtonFired(ActionEvent event) {
        db.update();
    }
    
    @FXML
    private void findPlanetFired(ActionEvent event) {
        stringProperty = findPlanetField.textProperty();
        planetName = stringProperty.get();
        found = false;
        for (int i = 0; i < reachablePlanets.size(); i++) {
            temp1 = reachablePlanets.get(i);
            temp1 = temp1.toLowerCase(Locale.ENGLISH);
            temp2 = solarSystem.getName();
            temp2 = temp2.toLowerCase(Locale.ENGLISH);
            if (temp1.equals(planetName) && !planetName.equals(temp2)) {
                solarList = uni.getUniverse();
                for (int j = 0; j < solarList.size(); j++) {
                    sys = solarList.get(j);
                    targetName = sys.getName();
                    targetName = targetName.toLowerCase(Locale.ENGLISH);
                    if (targetName.equals(temp1)) {
                        targetSystem = sys;
                        found = true;
                        loadTargetInfo();
                    }
                }
            }
        }
        if (!found) {
            findFieldProperty = findPlanetField.textProperty();
            findFieldProperty.set("");
        }
        
    }
    
    private void loadTargetInfo() {
        planet = solarSystem.getPlanet();
         //target system
        targetName = "Name:  " + planet.getName();
        //String capSize = "Name  " + cap.get
        targetTechLevel = "Tech Level:  " + planet.getTechLevel();
        targetGovernment = "Government:  " + planet.getPoliticalSystem();
        targetResource = "Resource:  " + planet.getResourcesLevel();
        targetPolice = "Police:  " + planet.getPolice();
        targetPirate = "Pirate:  " + planet.getPirate();
        items = FXCollections.observableArrayList(
        targetName, targetTechLevel, targetGovernment, targetResource, targetPolice, targetPirate);
        targetListView.setItems(items);
    }
    
    private void loadCurrentInfo() {
        
        planet = solarSystem.getPlanet();
         //target system
        currName = "Name:  " + planet.getName();
        //String capSize = "Name  " + cap.get
        currTechLevel = "Tech Level:  " + planet.getTechLevel();
        currGovernment = "Government:  " + planet.getPoliticalSystem();
        currResource = "Resource:  " + planet.getResourcesLevel();
        currPolice = "Police:  " + planet.getPolice();
        currPirate = "Pirate:  " + planet.getPirate();
        items = FXCollections.observableArrayList (
        currName, currTechLevel, currGovernment, currResource, currPolice, currPirate);
        systemListView.setItems(items);
    }
    
    private void clearTargetListView() {
        items2 = FXCollections.observableArrayList(
        "Name: ", "Tech Level: ", "Government: ", "Resource: ", "Police: ", "Pirate: ");
        targetListView.setItems(items2);
    }
    
    private void showDockInfo(final PlayerShip ship, final int actFuel, final int actPull) {
        base = ship.getBase();
        info1 = "You have fuel to fly " + actFuel + " parsecs";
        if (actFuel == maxFuel) {
            info2 = "Your tank is full";
        } else {
            info2 = "Fuel cost per unit: " + base.getFuelCost() + " cr";
        }
        info3 = "Your hull strength is " + actPull;
        if (actPull == maxPull) {
            info4 = "No repairs needed";
        } else {
            info4 = "Repairm Cost per hull strength: " + base.getRepairCost() + " cr";
        }
        dockInfo = FXCollections.observableArrayList(
        info1, info2, info3, info4);
        dockListView.setItems(dockInfo);
    }
    
    @FXML
    private void warpFired(ActionEvent event) {
        targetX = targetSystem.getX();
        targetY = targetSystem.getY();
        travelSqr = pow(targetX - solarSystem.getX(), 2) + pow(targetY - solarSystem.getY(), 2);
        travelDist = (int)(sqrt(travelSqr));
        myString = "";
        travel = new Travel();
        targetName = targetSystem.getName();
        solarName = solarSystem.getName();
        if (!targetName.equals(solarName)) {
            travel.warpTo(targetSystem.getName(), travelDist, 1);
            re = new RandomEvent();
            myString = re.update();
        }
            
        range = 0;
        ship = db.getShip();
        base = ship.getBase();
        range = base.getFuel();
        hull = base.getHullStrength();
            
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
        
        player = db.getPlayer();
        randomInfo = FXCollections.observableArrayList(myString, "");
        travelInfoListView.setItems(randomInfo);
        updatePlayerInfo();
        setRefuelChoose();
        setRepairChoose();
        shipYard = new ShipYard();
        showShipNames(shipYard); 
        
    }
    
    private void updatePlayerInfo() {
            player = db.getPlayer();
            ship = db.getShip();
            base = ship.getBase();
            
            cashmsg = "Cash: " + player.getMoney() + " cr";
            shipmsg = "Ship Type: " + base.getName();
            attackmsg = "Attack: " + ship.getAttack() + "  Shield: " + db.getShip().getShield();
            remWeaponmsg = "Remaining Weapon Slots: " + ship.getWeaponSlots();
            remShieldmsg = "Remaining Shield Slots: " + ship.getShieldSlots();
            remGadgetmsg = "Remaining Gadget Slots: " + ship.getGadgetsSlots();
            cargo = ship.getCargo();
            remCargomsg = "Cargo Space Remaining: " + (ship.getCargoSpace() - cargo.size());
            playerInfo = FXCollections.observableArrayList(
             cashmsg, shipmsg, attackmsg, remWeaponmsg, remShieldmsg, remGadgetmsg, remCargomsg);
            
            for (final Good g : ship.getCargo()) {
                playerInfo.add(g.getName());
            }
            
            playerListView.setItems(playerInfo);
        
    }
    
    private void setRefuelChoose() {
            player = db.getPlayer();
            ship = db.getShip();
            base = ship.getBase();
            currFuel = base.getFuel();
            shipfactory = new ShipFactory();
            modelShip = shipfactory.getShip(base.getName());
            maxRefuel = modelShip.getFuel() - currFuel;
            
            choices = FXCollections.observableArrayList();
            for (int i = maxRefuel; i >= 0; i--) {
                choices.add(Integer.toString(i));
            }
            refuelChoose.setItems(choices);
            //refuelChoose.show();
            
        
    }
    
    
    private void setRepairChoose() {
            player = db.getPlayer();
            ship = db.getShip();
            base = ship.getBase();
            currStre = base.getHullStrength();
            shipfactory = new ShipFactory();
            modelShip = shipfactory.getShip(base.getName());
            maxRepair = modelShip.getHullStrength() - currStre;
            
            choices = FXCollections.observableArrayList();
            for (int i = maxRepair; i >= 0; i--) {
                choices.add(Integer.toString(i));
            }
            repairChoose.setItems(choices);
            //refuelChoose.show();
            
        
        
    }
//    
    @FXML
    private void refuelFired(ActionEvent event) {
            player = db.getPlayer();
            ship = db.getShip();
            base = ship.getBase();
            
            perCost = base.getFuelCost();
            refuelChooseRaw = refuelChoose.getValue();
            amount = converter.fromString(refuelChooseRaw.toString());
            cost = amount * perCost;
            
            
            player.setMoney(player.getMoney() - cost);
            base.setFuel(base.getFuel() + amount);
            db.update(ship, player);
            setRefuelChoose();
            // update dock info, redraw long and short range charts
            showDockInfo(ship, base.getFuel(), base.getHullStrength());
            gc = canvas.getGraphicsContext2D();
            drawLongRange(player.getXpos(), player.getYpos(), base.getFuel(), gc);
            gc2 = canvas2.getGraphicsContext2D();
            drawShortRange(player.getXpos(), player.getYpos(), base.getFuel(), gc2);
            
            
            updatePlayerInfo();
             
    }
    
    @FXML
    private void repairFired(ActionEvent event) {
            player = db.getPlayer();
            ship = db.getShip();
            base = ship.getBase();
            perCost = base.getRepairCost();
            repairChooseRaw = repairChoose.getValue();
            amount = converter.fromString(repairChooseRaw.toString());
            cost = amount * perCost;
            
            player.setMoney(player.getMoney() - cost);
            base.setHullStrength(base.getHullStrength() + amount);
            db.update(ship, player);
            setRepairChoose();
            showDockInfo(ship, base.getFuel(), base.getHullStrength());
            updatePlayerInfo();
        
    }
    
    @FXML
    private void upgradeBuyFired(ActionEvent event) {
        for (final Tab t : shipyardPane.getTabs()) {
            id = t.getId();
            if (t.isSelected() && id.equals("shipTab")) {
                showShips();
            } else if (t.isSelected() && id.equals("weaponTab")) {
                showWeapons();
            } else if (t.isSelected() && id.equals("shieldTab")) {      
                showShields();
            } else if (t.isSelected() && id.equals("gadgetTab")) {
                showGadgets();
            }
        }
    
    }
    
    private void showShips() {
        shipYard = new ShipYard();
        shipNames = shipYard.getShipNames();
        if (!shipNames.isEmpty()) {
            selectionMode = shipListView.getSelectionModel();
            index = selectionMode.getSelectedIndex();
            
            shipYard.playerBuyShip(shipNames.get(index));
            
            updatePlayerInfo();
            ship = db.getShip();
            player = db.getPlayer();
            
            base = ship.getBase();
            showDockInfo(ship, base.getFuel(), base.getHullStrength());
            gc = canvas.getGraphicsContext2D();
            drawLongRange(player.getXpos(), player.getYpos(), base.getFuel(), gc);
            gc2 = canvas2.getGraphicsContext2D();
            drawShortRange(player.getXpos(), player.getYpos(), base.getFuel(), gc2);
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            shipYard = new ShipYard();
            showShipNames(shipYard);
        }
    }
    
    
    private void showWeapons() {
        shipYard = new ShipYard();
        weaponNames = shipYard.getWeaponNames();
        if (!weaponNames.isEmpty()) {
            selectionMode = weaponListView.getSelectionModel();
            index = selectionMode.getSelectedIndex();
            shipYard.playerBuyWeapon(weaponNames.get(index));
            
            updatePlayerInfo();
            
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            shipYard = new ShipYard();
            showShipNames(shipYard);
        }
    }
    
    private void showShields() {
        shipYard = new ShipYard();
        shieldNames = shipYard.getShieldNames();
        if (!shieldNames.isEmpty()) {
            selectionMode = shieldListView.getSelectionModel();
            index = selectionMode.getSelectedIndex();
            shipYard.playerBuyShield(shieldNames.get(index));
            
            updatePlayerInfo();
            
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            shipYard = new ShipYard();
            showShipNames(shipYard);
        }
    }
    
    private void showGadgets() {
        shipYard = new ShipYard();
        gadgetNames = shipYard.getGadgetNames();
        if (!gadgetNames.isEmpty()) {
            selectionMode = gadgetListView.getSelectionModel();
            index = selectionMode.getSelectedIndex();
            
            shipYard.playerBuyCargoExpansion();
            
            updatePlayerInfo();
            
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            shipYard = new ShipYard();
            showShipNames(shipYard);
        }
    }
    
    @FXML    
    private void waterSellFired(ActionEvent event) {
        if (waterChoose.getValue() != null) {
            rawChoose = waterChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Water());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }
    
     @FXML    
     private void fursSellFired(ActionEvent event) {
        if (fursChoose.getValue() != null) {
            rawChoose = fursChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Furs());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       } 
    }

     @FXML    
     private void foodSellFired(ActionEvent event) {  
        if (foodChoose.getValue() != null) {
            rawChoose = foodChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Food());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

     @FXML    
     private void oreSellFired(ActionEvent event) {   
        if (oreChoose.getValue() != null) {
            rawChoose = oreChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Ore());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

     @FXML    
     private void gamesSellFired(ActionEvent event) { 
        if (gamesChoose.getValue() != null) {
            rawChoose = gamesChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Games());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

     @FXML    
     private void firearmsSellFired(ActionEvent event) {   
        if (firearmsChoose.getValue() != null) {
            rawChoose = firearmsChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Firearms());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

     @FXML    
     private void medicinesSellFired(ActionEvent event) {  
       if (medicinesChoose.getValue() != null) {
            rawChoose = medicinesChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Medicine());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

     @FXML    
     private void machinesSellFired(ActionEvent event) { 
        if (machinesChoose.getValue() != null) {
            rawChoose = machinesChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Machines());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

     @FXML    
     private void narcoticsSellFired(ActionEvent event) { 
        if (narcoticsChoose.getValue() != null) {
            rawChoose = narcoticsChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerSell(new Narcotics());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void waterBuyFired(ActionEvent event) {
        if (waterChoose2.getValue() != null) {
            rawChoose = waterChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Water());
            }
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void fursBuyFired(ActionEvent event) {   
        if (fursChoose2.getValue() != null) {
            rawChoose = fursChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Furs());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void foodBuyFired(ActionEvent event) {  
        if (foodChoose2.getValue() != null) {
            rawChoose = foodChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Food());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void oreBuyFired(ActionEvent event) {   
        if (oreChoose2.getValue() != null) {
            rawChoose = oreChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Ore());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
        }
    }

    @FXML    
    private void gamesBuyFired(ActionEvent event) {   
        if (gamesChoose2.getValue() != null) {
            rawChoose = gamesChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Games());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void firearmsBuyFired(ActionEvent event) {   
        if (firearmsChoose2.getValue() != null) {
            rawChoose = firearmsChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Firearms());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void medicinesBuyFired(ActionEvent event) {   
        if (medicinesChoose2.getValue() != null) {
            rawChoose = medicinesChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Medicine());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void machinesBuyFired(ActionEvent event) {   
        if (machinesChoose2.getValue() != null) {
            rawChoose = machinesChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Machines());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }

    @FXML    
    private void narcoticsBuyFired(ActionEvent event) {   
        if (narcoticsChoose2.getValue() != null) {
            rawChoose = narcoticsChoose2.getValue();
            amountStr = rawChoose.toString();
            amount = converter.fromString(amountStr);
            mp = new MarketPlace();
            for (int i = 1; i <= amount; i++) {
                mp.playerBuy(new Narcotics());
            }       
            mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
            //updateDatabase(db.getPlayer(), db.getShip());
            updatePlayerInfo();
            shipYard = new ShipYard();
            showShipNames(shipYard);
       }
    }
    
    private void drawLongRange(final int centerX, final int centerY, final int range, final GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (final SolarSystem ss : solarList) {
            final int planetX = ss.getX();
            final int planetY = ss.getY();
            gc.fillOval(planetX - 1, planetY - 1, 2, 2);
        }
        gc.strokeOval(centerX - range, centerY - range, 2 * range, 2 * range);
    }
    
    private List<String> drawShortRange(final int centerX, final int centerY, final int range, final GraphicsContext gc2) {
        gc2.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        reachablePlanets = new ArrayList<String>();
        final double height = canvas2.getHeight();
        final double width = canvas2.getWidth();
        final double smallBound = min(height, width);
        gc2.fillOval(width / 2 - 2, height / 2 - 2, 4, 4);
        gc2.strokeOval(width / 2 - smallBound / 2, height / 2 - smallBound / 2,
                smallBound, smallBound);
        ratio = (smallBound / 2) / range;
        for (final SolarSystem ss : solarList) {
            final int shortRangeX = ss.getX();
            final int shortRangeY = ss.getY();
            final double zoomInH = (shortRangeX - centerX) * ratio;
            final double zoomInV = (shortRangeY - centerY) * ratio;
            gc2.fillOval(width / 2 + zoomInH - 2, height / 2 + zoomInV - 2, 4, 4);
            final double distSqr = pow(zoomInH, 2) + pow(zoomInV, 2);
            final double newRad = ratio * range;
            final double radSqr = pow(newRad, 2);
            if (distSqr <= radSqr) {
                gc2.strokeText(ss.getName(), width / 2 + zoomInH - 2, height / 2 + zoomInV - 2, 50);
                reachablePlanets.add(ss.getName());
            }
        }
        return reachablePlanets;
    }
    
    private void showShipNames(ShipYard sy) {
        shipItem = FXCollections.observableArrayList();
        weaponItem = FXCollections.observableArrayList();
        shieldItem = FXCollections.observableArrayList();
        gadgetItem = FXCollections.observableArrayList();
        lowTechmsg = "Tech level on this planet is too low to have a shipyard!";
        creditStr = "cr.";
        if (sy.isYardExist()) {
            shipNames = sy.getShipNames();
            shipPrices = sy.getShipPrices();
            if (shipNames.isEmpty()) {
                shipItem.add("You don't have enough money to buy any ship on this planet!");
            } else {
                count = 1;
                for (final String s : shipNames) {
                    shipItem.add(count + ". " + s + ": " + shipPrices.get(s) + creditStr);
                    count++;
                }
            }
            
            weaponNames = sy.getWeaponNames();
            weaponPrices = sy.getWeaponPrices();
            if (weaponNames.isEmpty()) {
                ship = db.getShip();
                if (ship.getWeaponSlots() <= 0) {
                    weaponItem.add("You don't have any extra weapon slots!");
                } else {
                    weaponItem.add("You don't have enough money to buy any weapon on this planet!");
                }
            } else {
                count = 1;
                for (final String s : weaponNames) {
                    weaponItem.add(count + ". " + s + ": " + weaponPrices.get(s) + creditStr);
                    count++;
                }
            }
        
            shieldNames = sy.getShieldNames();
            shieldPrices = sy.getShieldPrices();
            if (shieldNames.isEmpty()) {
                ship = db.getShip();
                if (ship.getShieldSlots() <= 0) {
                    shieldItem.add("You don't have any extra shield slots!");
                } else {
                    shieldItem.add("You don't have enough money to buy any shield on this planet!");
                }
            } else {
                count = 1;
                for (final String s : shieldNames) {
                    shieldItem.add(count + ". " + s + ": " + shieldPrices.get(s) + creditStr);
                    count++;
                }
            }
            
            gadgetNames = sy.getGadgetNames();
            gadgetPrices = sy.getGadgetPrices();
        
            try {
                solarSystem = db.getSolarSystem();
                planet = solarSystem.getPlanet();
                techLevels = planet.getTechLevel();
                techLevel = techLevels.ordinal();
                
                if (techLevel == SIXFINAL) {
                    if (gadgetNames.isEmpty()) {
                        ship = db.getShip();
                        if (ship.getGadgetsSlots() <= 0) {
                            gadgetItem.add("You don't have any extra gadget slots!");
                        } else {
                            gadgetItem.add("You don't have enough"
                                    + "money to buy any gadget on this planet!");
                        }
                    } else {
                        count = 1;
                        for (int i = 0; i < gadgetNames.size(); i++) {
                            gadgetName = gadgetNames.get(i);
                            gadgetItem.add(count + ". " + gadgetName
                                    + ": " + gadgetPrices.get(gadgetName) + "cr.");
                            count++;
                        }
                    }
                } else {
                    gadgetItem.add("Cargo Expansion available only on highest tech");
                }
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
            	LOGGER.log(Level.SEVERE, npe.getMessage(), npe);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
            	LOGGER.log(Level.SEVERE, npe.getMessage(), npe);
            }
            
            
        } else {
            shipItem.add(lowTechmsg);
            weaponItem.add(lowTechmsg);
            shieldItem.add(lowTechmsg);
            gadgetItem.add(lowTechmsg);
        }
        
        shipListView.setItems(shipItem);
        weaponListView.setItems(weaponItem);
        shieldListView.setItems(shieldItem);
        gadgetListView.setItems(gadgetItem);
    }
 
    /**
     * initialize the game screen view
     */
    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
    	//SIXFINAL = 6;
    	
        try {
            db = new SqliteApi();
            shipYard = new ShipYard();
            player = db.getPlayer();
            ship = db.getShip();
            uni = db.getUniverse();
            base = ship.getBase();
            maxFuel = base.getFuel();
            hull = maxPull = base.getHullStrength();
            solarList = uni.getUniverse();
            
            shipTab.setId("shipTab");
            weaponTab.setId("weaponTab");
            shieldTab.setId("shieldTab");
            gadgetTab.setId("gadgetTab");
            
            showShipNames(shipYard);
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
        	LOGGER.log(Level.SEVERE, npe.getMessage(), npe);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        	LOGGER.log(Level.SEVERE, npe.getMessage(), npe);
        }

        graphicsContext = canvas.getGraphicsContext2D();
        // draw map
        for (final SolarSystem ss : solarList) {
            final int systemX = ss.getX();
            final int systemY = ss.getY();
            graphicsContext.fillOval(systemX - 1, systemY - 1, 2, 2);
            if (systemX == player.getXpos() && systemY == player.getYpos()) {
                solarSystem = ss;
                
            }
        }
        
        final int range = base.getFuel();
        currX = solarSystem.getX();
        currY = solarSystem.getY();
        drawLongRange(currX , currY, range, gc);
        final GraphicsContext graphicsContext2 = canvas2.getGraphicsContext2D();
        reachablePlanets = drawShortRange(currX, currY, range, graphicsContext2);
        
        loadCurrentInfo();
        clearTargetListView();
                
        trade = new Trade(player, ship, solarSystem);
        toBuyList = trade.getGoodsToBuy();
        toSellList = trade.getGoodsToSell();
        toSellMap = trade.getPricesToSell();
        toBuyMap = trade.getPricesToBuy();
        converter = new IntegerStringConverter();
    
         cantbuy = "Can't Buy";
         waterPrice2.setText(cantbuy);
         fursPrice2.setText(cantbuy);
         foodPrice2.setText(cantbuy);
         orePrice2.setText(cantbuy);
         gamesPrice2.setText(cantbuy);
         firearmsPrice2.setText(cantbuy);
         medicinesPrice2.setText(cantbuy);
         machinesPrice2.setText(cantbuy);
         narcoticsPrice2.setText(cantbuy);

         cantsell = "Can't Sell";
         waterPrice.setText(cantsell);
         fursPrice.setText(cantsell);        
         foodPrice.setText(cantsell);        
         orePrice.setText(cantsell);        
         gamesPrice.setText(cantsell);        
         firearmsPrice.setText(cantsell);        
         medicinesPrice.setText(cantsell);        
         machinesPrice.setText(cantsell);        
         narcoticsPrice.setText(cantsell);
         mp = new MarketPlace();
         mess(toBuyList, toBuyMap, toSellList, toSellMap, mp);
         
         //dock info
         showDockInfo(ship, maxFuel, maxPull);
         updatePlayerInfo();
         
    }
    
    private int countNumOfGood(List<Good> cargo, String name) {
        count = 0;
        for (final Good g : cargo) {
            //System.out.println(g.getName());
            if (name.equals(g.getName())) {
                count++;
            }
        }
        //System.out.println("countnum: " + count);
        return count;
    }

    
    private void mess(final List<String> toBuyList, final Map<String, Integer> toBuyMap,
            final List<String> toSellList, final Map<String, Integer> toSellMap, final MarketPlace mp) {
        ship = db.getShip();
        shipCargoSpace = ship.getCargoSpace();
        cargoList = mp.getCargo();
        final int maxAmount1 = shipCargoSpace - cargoList.size();
        
        for (int j = 0 ; j < toBuyList.size(); j++) {
            itemName = toBuyList.get(j);
            water = "Water";
            if (water.equals(itemName)) {
                waterPrice2.setText(Integer.toString(toBuyMap.get("Water")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Water");
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                waterChoose2.setItems(buyChoice);
            } else if (itemName.equals("Furs")) {
                fursPrice2.setText(Integer.toString(toBuyMap.get("Furs")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Furs");
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                fursChoose2.setItems(buyChoice);
            } else if (itemName.equals("Food")) {
                foodPrice2.setText(Integer.toString(toBuyMap.get("Food")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Food");  
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                foodChoose2.setItems(buyChoice);
            } else if (itemName.equals("Ore")) {
                orePrice2.setText(Integer.toString(toBuyMap.get("Ore")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Ore"); 
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) { 
                    buyChoice.add(Integer.toString(i));
                } 
                oreChoose2.setItems(buyChoice);
            } else if (itemName.equals("Games")) {
                gamesPrice2.setText(Integer.toString(toBuyMap.get("Games")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Games");
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                gamesChoose2.setItems(buyChoice);
            } else if (itemName.equals("Firearms")) {
                firearmsPrice2.setText(Integer.toString(toBuyMap.get("Firearms")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Firearms");
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                firearmsChoose2.setItems(buyChoice);
            } else if (itemName.equals("Medicines")) {
                medicinesPrice2.setText(Integer.toString(toBuyMap.get("Medicines")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Medicines");
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                medicinesChoose2.setItems(buyChoice);
            } else if (itemName.equals("Narcotics")) {
                narcoticsPrice2.setText(Integer.toString(toBuyMap.get("Narcotics")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Narcotics");
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                narcoticsChoose2.setItems(buyChoice);
            } else if (itemName.equals("Machines")) {
                machinesPrice2.setText(Integer.toString(toBuyMap.get("water")));
                maxAmount2 = mp.getPlayerMoney() / toBuyMap.get("Machines");
                maxBuy = Math.min(maxAmount1,maxAmount2);
                buyChoice = FXCollections.observableArrayList();
                for (int i = maxBuy; i >= 0; i--) {
                    buyChoice.add(Integer.toString(i));
                }
                machinesChoose2.setItems(buyChoice);
            } 
        }
        int maxSell;
        for (int k = 0; k < toSellList.size(); k++) {
            itemName2 = toSellList.get(k);
            if (itemName2.equals("Water")) {
                waterSellPrice = toSellMap.get("Water");
                waterPrice.setText(Integer.toString(waterSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Water");
                //System.out.println("maxsell: " + maxSell);
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                waterChoose.setItems(choices);
                waterChoose.show();
                
            } else if (itemName2.equals("Furs")) {
                fursSellPrice = toSellMap.get("Furs");
                fursPrice.setText(Integer.toString(fursSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Furs");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                fursChoose.setItems(choices);
                fursChoose.show();
            } else if (itemName2.equals("Food")) {
                foodSellPrice = toSellMap.get("Food");
                foodPrice.setText(Integer.toString(foodSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Food");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                foodChoose.setItems(choices);
                foodChoose.show();
            } else if (itemName2.equals("Ore")) {
                oreSellPrice = toSellMap.get("Ore");
                orePrice.setText(Integer.toString(oreSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Ore");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                oreChoose.setItems(choices);
                oreChoose.show();
            } else if (itemName2.equals("Games")) {
                gamesSellPrice = toSellMap.get("Games");
                gamesPrice.setText(Integer.toString(gamesSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Games");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                gamesChoose.setItems(choices);
                gamesChoose.show();
            } else if (itemName2.equals("Firearms")) {
                firearmsSellPrice = toSellMap.get("Firearms");
                firearmsPrice.setText(Integer.toString(firearmsSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Firearms");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                firearmsChoose.setItems(choices);
                firearmsChoose.show();
            } else if (itemName2.equals("Medicines")) {
                medicinesSellPrice = toSellMap.get("Medicines");
                medicinesPrice.setText(Integer.toString(medicinesSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Medicines");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                medicinesChoose.setItems(choices);
                medicinesChoose.show();
            } else if (itemName2.equals("Narcotics")) {
                narcoticsSellPrice = toSellMap.get("Narcotics");
                narcoticsPrice.setText(Integer.toString(narcoticsSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Narcotics");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                narcoticsChoose.setItems(choices);
                narcoticsChoose.show();
            } else if (itemName2.equals("Machines")) {
                machinesSellPrice = toSellMap.get("Machines");
                machinesPrice.setText(Integer.toString(machinesSellPrice));
                maxSell = countNumOfGood(mp.getCargo(), "Machines");
                choices = FXCollections.observableArrayList();
                for (int i = maxSell; i >= 0; i--) {
                    choices.add(Integer.toString(i));
                }
                machinesChoose.setItems(choices);
                machinesChoose.show();
            } 
        }
    }
                
    
}
