package spaceTrader.Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.APIs.ShipYard;
import spaceTrader.APIs.SqliteAPI;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
import spaceTrader.Ships.ShipFactory;

public class ShipYardTest {

    private ShipYard sY;
    private SqliteAPI db;
    
    
    @Before
    public void setUp() throws Exception {
        sY = new ShipYard();
        db = new SqliteAPI();
    }

    @Test
    public void testPlayerBuy() {
        PlayerShip playerShip = db.getShip();
        List<String> shipNames = sY.getShipNames();
        ShipFactory sF = new ShipFactory();
        Ship s = sF.getShip(shipNames.get(0));
        sY.playerBuy(shipNames.get(1));
        assertNotEquals(playerShip, db.getShip());
        
    }

    @Test
    public void testGetShipNames() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetShipPrices() {
        fail("Not yet implemented");
    }

}
