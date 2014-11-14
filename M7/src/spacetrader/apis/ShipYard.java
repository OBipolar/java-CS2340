package spacetrader.apis;

import spacetrader.equipment.CargoExpansion;
import spacetrader.equipment.EquipmentFactory;
import spacetrader.equipment.Shield;
import spacetrader.equipment.Weapon;
import spacetrader.planets.GameCharacter;
import spacetrader.planets.Planet;
import spacetrader.planets.SolarSystem;
import spacetrader.ships.PlayerShip;
import spacetrader.ships.Ship;
import spacetrader.ships.ShipFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * API for player to buy ship at a planet
 * 
 * @author mli, Jinyu Shi, ZixiangZhu
 *
 */
public class ShipYard {
    
    private static final Logger LOGGER = 
            Logger.getLogger(MarketPlace.class.getName()); 
    private SolarSystem system;
    private Planet planet;
    private GameCharacter player;
    private PlayerShip ship;
    /**
     * A list of names of avaliable ships
     */
    private List<String> shipNames;
    /**
     * A list of names of avaliable weapons
     */
    private List<String> weaponNames;
    /**
     * A list of names of avaliable shields
     */
    private List<String> shieldNames;
    /**
     * A list of names of avaliable gadgets
     */
    private List<String> gadgetNames;
    /**
     * A map that has the name of ship as key and its price as value
     */
    private Map<String, Integer> shipPrices;
    /**
     * A map that has the name of weapon as key and its price as value
     */
    private Map<String, Integer> weaponPrices;
    /**
     * A map that has the name of shield as key and its price as value
     */
    private Map<String, Integer> shieldPrices;
    /**
     * A map that has the name of gadget as key and its price as value
     */
    private Map<String, Integer> gadgetPrices;
    
    
    /**
     * Constructor for ship yard that initializes the planet the player is on 
     * right now
     * 
     */
    public ShipYard() {
        
        try {

            system = SqliteApi.getSolarSystem();
            planet = system.getPlanet();
            player = SqliteApi.getPlayer();
            ship = SqliteApi.getShip();
            

           
            shipNames = new ArrayList<>();
            weaponNames = new ArrayList<>();
            shieldNames = new ArrayList<>();
            gadgetNames = new ArrayList<>();
            shipPrices = new HashMap<String, Integer>();
            weaponPrices = new HashMap<String, Integer>(); 
            shieldPrices = new HashMap<String, Integer>(); 
            gadgetPrices = new HashMap<String, Integer>(); 

            int techLevel = planet.getTechLevel().ordinal();
            
            ShipFactory sf = new ShipFactory();
            EquipmentFactory ef = new EquipmentFactory();
            List<Ship> ships = sf.getShip(techLevel);
            List<Weapon> weapons = ef.getWeapon(techLevel); 
            List<Shield> shields = ef.getShield(techLevel);
            
            for (Ship s : ships) {
                if (player.getMoney() >= s.getPrice()
                        && ship.getCargoSpace() <= s.getCargoBay() 
                        && planet.getTechLevel().ordinal() >= s.getMinTechLevel()) {
                    shipNames.add(s.getName());
                    shipPrices.put(s.getName(), s.getPrice());
                }
            }
            for (Weapon w : weapons) {
                if (player.getMoney() >= w.getPrice() && ship.getWeaponSlots() > 0
                        && planet.getTechLevel().ordinal() >= w.getMinTechLevel()) {
                    weaponNames.add(w.getName());
                    weaponPrices.put(w.getName(), w.getPrice());
                }
            }
            for (Shield s : shields) {
                if (player.getMoney() >= s.getPrice() && ship.getShieldSlots() > 0
                        && planet.getTechLevel().ordinal() >= s.getMinTechLevel()) {
                    shieldNames.add(s.getName());
                    shieldPrices.put(s.getName(), s.getPrice());
                }
            }
            CargoExpansion cargoExp = ef.getCargoExpansion();
            if (player.getMoney() >= cargoExp.getPrice() && ship.getGadgetsSlots() > 0) {
                gadgetNames.add(cargoExp.getName());
                gadgetPrices.put(cargoExp.getName(), cargoExp.getPrice());
            }        
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        
    }
    
    
    /**
     * Returns true if the ship yard exists on this planet, return false
     * otherwise
     * 
     * @return
     */
    public boolean isYardExist() {
        return planet.getTechLevel().ordinal() >= 4;

    }    

    
    /**
     * Return the result of this refuel attempt
     * 
     * @return the result of this refuel attempt
     */
    public String refuel() {
        String name = ship.getBase().getName();
        ShipFactory sf = new ShipFactory();
        Ship newShip = sf.getShip(name);  
        int fullFuel = newShip.getFuel();
        if (ship.getBase().getFuel() >= fullFuel) {
            return "Fuel is full";
        } else {
            int cost = ship.getBase().getFuelCost();
            int loss = cost * (fullFuel - ship.getBase().getFuel());
            int money = player.getMoney() - loss;
            if (money < 0) {
                return "You don't have enough money to refuel";
            }
            player.setMoney(money);
            ship.getBase().setFuel(fullFuel);
            update();
            return "This refuel cost " + loss + " cr";
        }
    }
    
    
    
    
    /**
     * Players buy a ship with the given name
     * 
     * 
     * @param name
     */
    public void playerBuyShip(String name) {
    
        ShipFactory sf = new ShipFactory();
        Ship newShip = sf.getShip(name);        
        PlayerShip temp = new PlayerShip(ship);        
        ship = new PlayerShip(newShip, temp.getCargo());        
        player.setMoney(player.getMoney() - newShip.getPrice() + temp.getBase().getPrice());
        update();
    }
    
    /**
     * Player buys a weapon with the given game
     * 
     * @param name
     */
    public void playerBuyWeapon(String name) {
        EquipmentFactory ef = new EquipmentFactory();
        Weapon weapon = ef.getWeapon(name);
        player = SqliteApi.getPlayer();
        ship = SqliteApi.getShip();
        ship.addWeapon(weapon);  
        player.setMoney(player.getMoney() - weapon.getPrice());
        update();
    }
    
     /**
     * Player buys a Shield with the given game
     * 
     * @param name
     */
    public void playerBuyShield(String name) {
        EquipmentFactory ef = new EquipmentFactory();
        Shield sh = ef.getShield(name);
        ship = SqliteApi.getShip();
        player = SqliteApi.getPlayer();
        ship.addShield(sh);
        player.setMoney(player.getMoney() - sh.getPrice());
        update();
    }
    
    /**
     * Player buys a CargoExpansoin
     */
    public void playerBuyCargoExpansion() {
        EquipmentFactory ef = new EquipmentFactory();
        CargoExpansion cargoExp = ef.getCargoExpansion();
        player = SqliteApi.getPlayer();
        ship = SqliteApi.getShip();
        ship.addCargoExpansoin(cargoExp);
        player.setMoney(player.getMoney() - cargoExp.getPrice());
        SqliteApi.setShip(ship);
    }
    
    
    /**
     * Returns the names of ships that player can buy on the planet
     * 
     * @return
     */
    public List<String> getShips() {
            List<String> shipList = new ArrayList<String>();
            int techLevel = planet.getTechLevel().ordinal();
            if (techLevel >= 4) {
                shipList.add("Flea");
                shipList.add("Gnat");
                shipList.add("Firefly");
                shipList.add("Mosquito");
                shipList.add("BumbleBee");
            } else if (techLevel >= 5) {
                shipList.add("Gnat");
                shipList.add("Firefly");
                shipList.add("Mosquito");
                shipList.add("BumbleBee");
            }
            return shipList;
    }

    public List<String> getShipNames() {
        return shipNames;
    }   
    
    public List<String> getWeaponNames() {
        return weaponNames;
    }
    
    public List<String> getShieldNames() {
        return shieldNames;
    }
    
    public List<String> getGadgetNames() {
        return gadgetNames;
    }    
    
    
    /**
     * Returns a map with ship name as key and the price of ship as value
     * 
     * @return
     */
    public Map<String, Integer> getShipPrices() {
        return shipPrices;
    }
    
    public Map<String, Integer> getWeaponPrices() {
        return weaponPrices;
    }
    
    public Map<String, Integer> getShieldPrices() {
        return shieldPrices;
    }
    
    public Map<String, Integer> getGadgetPrices() {
        return gadgetPrices;
    }    
    
    /**
     * updates 
     */
    private void update() {
        SqliteApi.setShip(ship);
        SqliteApi.setPlayer(player);
    }
    
    
    
    
}

