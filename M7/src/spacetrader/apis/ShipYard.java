package spacetrader.apis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.Planet;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
import spaceTrader.Ships.ShipFactory;
import spaceTrader.Planets.TechLevels;
import spacetrader.equipment.CargoExpansion;
import spacetrader.equipment.EquipmentFactory;
import spacetrader.equipment.Shield;
import spacetrader.equipment.Weapon;

/**
 * API for player to buy ship at a planet
 * 
 * @author mli, Jinyu Shi, ZixiangZhu
 *
 */
public class ShipYard {
	
	private SolarSystem system;
	private Planet planet;
	private GameCharacter player;
	private PlayerShip ship;
	private List<String> shipNames;
	private List<String> weaponNames;
	private List<String> shieldNames;
	private List<String> gadgetNames;
	private Map<String, Integer> shipPrices;
	private Map<String, Integer> weaponPrices;
	private Map<String, Integer> shieldPrices;
	private Map<String, Integer> gadgetPrices;
	
	
	/**
	 * Constructor for ship yard that initializes the planet the player is on 
	 * right now
	 * 
	 */
	public ShipYard() {
		
		try {

			system = SqliteAPI.getSolarSystem();
			planet = system.getPlanet();
			player = SqliteAPI.getPlayer();
			ship = SqliteAPI.getShip();
			
			int techLevel = planet.getTechLevel().ordinal();
			
			ShipFactory sF = new ShipFactory();
			EquipmentFactory eF = new EquipmentFactory();
			List<Ship> ships = sF.getShip(techLevel);
			List<Weapon> weapons = eF.getWeapon(techLevel); 
			List<Shield> shields = eF.getShield(techLevel);
			
			shipNames = new ArrayList<>();
			weaponNames = new ArrayList<>();
			shieldNames = new ArrayList<>();
			gadgetNames = new ArrayList<>();
			shipPrices = new HashMap<String, Integer>();
			weaponPrices = new HashMap<String, Integer>(); 
			shieldPrices = new HashMap<String, Integer>(); 
			gadgetPrices = new HashMap<String, Integer>(); 

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
	        CargoExpansion c = eF.getCargoExpansion();
	        if (player.getMoney() >= c.getPrice() && ship.getGadgetsSlots() > 0) {
	            gadgetNames.add(c.getName());
	            gadgetPrices.put(c.getName(), c.getPrice());
	        }		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        ShipFactory sF = new ShipFactory();
        Ship newShip = sF.getShip(name);  
        int fullFuel = newShip.getFuel();
        if (ship.getBase().getFuel() >= fullFuel) {
            return "Fuel is full";
        } else {
            int cost = ship.getBase().getFuelCost();
            int loss = cost * (fullFuel - ship.getBase().getFuel());
            int money = player.getMoney() - loss;
            if (money < 0) {
                return "You don't have enough money to repair";
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
	
		ShipFactory sF = new ShipFactory();
		Ship newShip = sF.getShip(name);		
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
	    EquipmentFactory eF = new EquipmentFactory();
	    Weapon w = eF.getWeapon(name);
	    player = SqliteAPI.getPlayer();
	    ship = SqliteAPI.getShip();
	    ship.addWeapon(w);  
	    player.setMoney(player.getMoney() - w.getPrice());
	    update();
	}
	
	 /**
     * Player buys a Shield with the given game
     * 
     * @param name
     */
	public void playerBuyShield(String name) {
	    EquipmentFactory eF = new EquipmentFactory();
	    Shield s = eF.getShield(name);
	    ship = SqliteAPI.getShip();
	    player = SqliteAPI.getPlayer();
	    ship.addShield(s);
	    player.setMoney(player.getMoney() - s.getPrice());
	    update();
	}
	
	/**
	 * Player buys a CargoExpansoin
	 */
	public void playerBuyCargoExpansion() {
	    EquipmentFactory eF = new EquipmentFactory();
	    CargoExpansion c = eF.getCargoExpansion();
	    player = SqliteAPI.getPlayer();
	    ship = SqliteAPI.getShip();
	    ship.addCargoExpansoin(c);;
	    player.setMoney(player.getMoney() - c.getPrice());
	    SqliteAPI.setShip(ship);
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
	    SqliteAPI.setShip(ship);
	    SqliteAPI.setPlayer(player);
	}
	
	
	
	
}