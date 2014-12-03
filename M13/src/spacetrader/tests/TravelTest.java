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
        
        Universe uni = SqliteApi.getUniverse();
        SolarSystem sys = uni.getUniverse().get(5);
        PlayerShip ship = SqliteApi.getShip();           
        api.warpTo(sys.getName(), 20 , 1);
        PlayerShip newShip = SqliteApi.getShip();
        int fuel = ship.getBase().getFuel();
        int hullStrength = ship.getBase().getHullStrength();   
        int newFuel = newShip.getBase().getFuel();
        int newHullStrength = newShip.getBase().getHullStrength();
        assertNotEquals(fuel, newFuel);
        assertNotEquals(hullStrength, newHullStrength);
        assertEquals(newFuel, fuel - 20);
        assertEquals(newHullStrength, hullStrength - 1);
        assertEquals(sys.getName(), SqliteApi.getSolarSystem().getName());
    }
    

}
