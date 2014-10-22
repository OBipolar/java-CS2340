package spaceTrader.APIs;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.Planet;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.ShipFactory;
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
	private List<String> ships;
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
		Ship newShipType = sF.getShip(name);
		int techLevel = planet.getTechLevel().ordinal();
		if (newShipType.minTechLevel >= techLevel) {
			int incomings = ship.getBase().getPrice();
			for (Good good : ship.getCargo()) {
				if (good.getMTLU() <= techLevel) {
					income += good.getBasePrice() * (1 + getRandomNum(good.getVar() / 10))
					+ good.getIPL() * (techLevel - good.getMTLP());
				}
			}
			int balance = player.getMoney() + incomings - newShip.getBase().getPrice();
			if (balance >= 0) {
				PlayerShip newShip = new PlayerShip(newShipType, new ArrayList<Good>);
				player.setMoney(balance);
			}
			try {
				update();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	}
	
	
	/**
	 * Returns a map with ship name as key and the price of ship as value
	 * 
	 * @return
	 */
	public Map<String, Integer> getShipPrices() {
		Map<String, Integer> priceMap = new HashMap<String, Integer>();
		int techLevel = planet.getTechLevel().ordinal();
		if (techLevel >= 4) {
			priceMap.put("Flea", 2000);
			priceMap.put("Gnat", 10000);
			priceMap.put("Firefly", 25000);
			priceMap.put("Mosquito", 30000);
			priceMap.put("BumbleBee", 60000);
		} else if (techLevel >= 5) {
			priceMap.put("Gnat", 10000);
			priceMap.put("Firefly", 25000);
			priceMap.put("Mosquito", 30000);
			priceMap.put("BumbleBee", 60000);
		}
		return priceMap;
	}
	
	/**
	 * updates 
	 * @throws SQLException 
	 * 
	 */
	private void update() throws SQLException {
		db.update(player, ship);
	}
	
	
	
	
}
