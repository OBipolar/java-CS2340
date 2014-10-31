package spaceTrader.Tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.APIs.SqliteAPI;
import spaceTrader.APIs.Travel;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;

public class TravelJunit {

	private Travel api;
	private SqliteAPI db;
	
	@Before
	public void setUp() throws Exception {
		api = new Travel();
		db = new SqliteAPI();
	}

	@Test
	public void test() {
		
		
		// find a random solar system to warp to 
		SolarSystem s = db.getUniverse().getUniverse().get(5);
		PlayerShip ship = db.getShip();
		int fuel = ship.getBase().getFuel();
		int hullStrength = ship.getBase().getHullStrength();
		
		api.warpTo(s.getName());
		try {
			db = new SqliteAPI();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ship = db.getShip();
		System.out.println("in junit now after travel, ship has fuel: " + ship.getBase().getFuel());
		int newFuel = ship.getBase().getFuel();
		int newHullStrength = ship.getBase().getHullStrength();
		assertNotEquals(fuel, newFuel);
		assertNotEquals(hullStrength, newHullStrength);
		
	}

}
