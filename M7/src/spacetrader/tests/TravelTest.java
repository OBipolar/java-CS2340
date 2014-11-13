package spacetrader.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import spacetrader.apis.SqliteApi;
import spacetrader.apis.Travel;
import spacetrader.planets.SolarSystem;
import spacetrader.planets.Universe;
import spacetrader.ships.PlayerShip;

/**
 * @author Sicong Chen
 *
 */
public class TravelTest {

    private SqliteApi db;
    private Travel api;
     
    
    @Before
    public void setUp() throws Exception {
        db = new SqliteApi();
        api = new Travel();
    }

    @Test
    public void test() {
        
        
        // find a random solar system to warp to 
        Universe u = SqliteApi.getUniverse();
        System.out.println(u == null);
        System.out.println(u.getUniverse().size());
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
        
    }

}
