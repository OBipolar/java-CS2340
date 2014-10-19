package spaceTrader.APIs;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.Planet;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;

/**
 * API for player to buy ship at a planet
 * 
 * @author mli
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
		//insert logic here
	}
	
	
	
	
	/**
	 * Players buys a ship with the given name
	 * 
	 * 
	 * @param name
	 */
	public void playerBuy(String name) {
		//insert logic here
		
		
		try {
			update();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Returns the names of ships that player can buy on the planet
	 * 
	 * @return
	 */
	public List<String> getShips() {
		//insert logic here
	}
	
	
	/**
	 * Returns a map with ship name as key and the price of ship as value
	 * 
	 * @return
	 */
	public Map<String, Integer> getShipPrices() {
		//insert logic here
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
