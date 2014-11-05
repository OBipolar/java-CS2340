package spaceTrader.APIs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.Planet;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
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
			System.out.println("current ship cargo " + ship.getCargo().size());
			
			for (Ship s : ships) {
				if (player.getMoney() >= s.getPrice() && ship.getCargo().size() <= s.getCargoBay()) {
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
		}

	}
	
	
	/**
	 * Returns the names of ships that player can buy on the planet
	 * 
	 * @return
	 */
	public List<String> getShipNames() {

		return shipNames;
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
