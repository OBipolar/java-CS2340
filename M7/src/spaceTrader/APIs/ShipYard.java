package spaceTrader.APIs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.Planet;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
import spaceTrader.Ships.ShipFactory;
import spaceTrader.Ships.Ship;
import spaceTrader.Goods.Good;
import spaceTrader.Planets.TechLevels;

/**
 * API for player to buy ship at a planet
 * 
 * @author mli, Jinyu Shi
 *
 */
public class ShipYard {
	
	private SolarSystem system;
	private Planet planet;
	private SqliteAPI db;
	private GameCharacter player;
	private PlayerShip ship;
	// ships that player can buy on the planet
	private List<Ship> ships;
	private List<String> shipNames;
	private Map<String, Integer> shipPrices;
	
	
	/**
	 * Constructor for ship yard that initializes the planet the player is on 
	 * right now
	 * 
	 */
	public ShipYard() {
		
		try {
			db = new SqliteAPI();
			system = db.getSolarSystem();
			planet = system.getPlanet();
			player = db.getPlayer();
			ship = db.getShip();
			ShipFactory sF = new ShipFactory();
			ships = sF.getShip(planet.getTechLevel().ordinal());
			shipNames = new ArrayList<>();
			for (Ship s : ships) {
				if (player.getMoney() >= s.getPrice()) {
					shipNames.add(s.getName());
				}
			}
            shipPrices = new HashMap<String, Integer>();
			for (Ship s : ships) {
				shipPrices.put(s.getName(), s.getPrice());
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
	 * 
	 * @return
	 */
	public boolean isYardExist() {
		return planet.getTechLevel().ordinal() >= 4;
	}
	
	/**
	 * Players buy a ship with the given name
	 * 
	 * 
	 * @param name
	 */
	public void playerBuy(String name) {
	
		ShipFactory sF = new ShipFactory();
<<<<<<< HEAD
		Ship newShipType = sF.getShip(name);
		int techLevel = planet.getTechLevel().ordinal();
		if (newShipType.getMinTechLevel() >= techLevel) {
			int incomings = ship.getBase().getPrice();
			for (Good good : ship.getCargo()) {
				if (good.getMTLU() <= techLevel) {
					incomings += good.getBasePrice() * (1 + (new Random()).nextInt(good.getVar() / 10 + 1))
					+ good.getIPL() * (techLevel - good.getMTLP());
				}
			}
			int balance = player.getMoney() + incomings - newShipType.getPrice();
			if (balance >= 0) {
				PlayerShip newShip = new PlayerShip(newShipType, new ArrayList<Good>());
				player.setMoney(balance);
			}
			try {
				update();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
=======
		Ship newShip = sF.getShip(name);		
		PlayerShip temp = new PlayerShip(ship);		
		ship = new PlayerShip(newShip, temp.getCargo());		
		player.setMoney(player.getMoney() - newShip.getPrice() + temp.getBase().getPrice());
		System.out.println("playerBuy called");
		try {
			update();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> FETCH_HEAD
		}

	}
	
	
	/**
	 * Returns the names of ships that player can buy on the planet
	 * 
	 * @return
	 */
<<<<<<< HEAD
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
=======
	public List<String> getShipNames() {

		return shipNames;
>>>>>>> FETCH_HEAD
	}
	
	
	/**
	 * Returns a map with ship name as key and the price of ship as value
	 * 
	 * @return
	 */
	public Map<String, Integer> getShipPrices() {

		return shipPrices;
	}
	
	/**
	 * updates 
	 * @throws SQLException 
	 * 
	 */
	private void update() throws SQLException {
		try {
            db.update(player, ship);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	
	
}
