package spaceTrader.Tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.Planets.SolarSystem;
import spaceTrader.Planets.Universe;
import spaceTrader.Ships.PlayerShip;
import spacetrader.apis.SqliteAPI;
import spacetrader.apis.Travel;

public class TravelTest {

	private Travel api;
	
	@Before
	public void setUp() throws Exception {
		api = new Travel();
	}

	@Test
	public void test() {
		
		
		// find a random solar system to warp to 
	    Universe u = SqliteAPI.getUniverse();
	    System.out.println(u == null);
	    System.out.println(u.getUniverse().size());
		SolarSystem s = u.getUniverse().get(5);
		PlayerShip ship = SqliteAPI.getShip();
		int fuel = ship.getBase().getFuel();
		int hullStrength = ship.getBase().getHullStrength();
		
		api.warpTo(s.getName(), 20 , 1);
		ship = SqliteAPI.getShip();
		System.out.println("in junit now after travel, ship has fuel: " + ship.getBase().getFuel());
		int newFuel = ship.getBase().getFuel();
		int newHullStrength = ship.getBase().getHullStrength();
		assertNotEquals(fuel, newFuel);
		assertNotEquals(hullStrength, newHullStrength);
		
	}

}
