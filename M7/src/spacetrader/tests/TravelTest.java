package spacetrader.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import org.junit.Before;
import org.junit.Test;

import spacetrader.apis.SqliteApi;
import spacetrader.apis.Travel;
import spacetrader.planets.SolarSystem;
import spacetrader.planets.Universe;
import spacetrader.ships.PlayerShip;

import java.sql.SQLException;

/**
 * @author Sicong Chen
 *
 */
public class TravelTest {
    
    private Travel api;
     
    
    @Before
    public void setUp() throws Exception {
        SqliteApi.start();
        api = new Travel();
    }
    @Test
    public void testClassMissing() throws ClassNotFoundException, SQLException {
        
        
        // find a random solar system to warp to 
        Universe u = SqliteApi.getUniverse();
        //System.out.println(u == null);
        //System.out.println(u.getUniverse().size());
        SolarSystem s = u.getUniverse().get(5);
        PlayerShip ship = SqliteApi.getShip();
        int fuel = ship.getBase().getFuel();
        int hullStrength = ship.getBase().getHullStrength();
        
        api.warpTo(s.getName(), 20 , 1);
        ship = SqliteApi.getShip();
        //System.out.println("in junit now after travel, ship has fuel: " + ship.getBase().getFuel());
        int newFuel = ship.getBase().getFuel();
        int newHullStrength = ship.getBase().getHullStrength();
        assertNotEquals(fuel, newFuel);
        assertEquals(newFuel, fuel - 20);
        assertNotEquals(hullStrength, newHullStrength);
        assertEquals(newHullStrength, hullStrength - 1);
        assertEquals(s.getName(), SqliteApi.getSolarSystem().getName());
    }
    
    @Test(expected=SQLException.class)
    public void testSQL() throws ClassNotFoundException, SQLException {
        
        
        // find a random solar system to warp to 
        Universe u = SqliteApi.getUniverse();
        SolarSystem s = u.getUniverse().get(5);
        PlayerShip ship = SqliteApi.getShip();
        int fuel = ship.getBase().getFuel();
        int hullStrength = ship.getBase().getHullStrength();
        
        api.warpTo("sadfasd", 20 , 1);
        ship = SqliteApi.getShip();
        //System.out.println("in junit now after travel, ship has fuel: " + ship.getBase().getFuel());
        int newFuel = ship.getBase().getFuel();
        int newHullStrength = ship.getBase().getHullStrength();
        assertNotEquals(fuel, newFuel);
        assertEquals(newFuel, fuel - 20);
        assertNotEquals(hullStrength, newHullStrength);
        assertEquals(newHullStrength, hullStrength - 1);
        assertEquals(s.getName(), SqliteApi.getSolarSystem().getName());
    }

}
