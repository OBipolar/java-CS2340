package spacetrader.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import spacetrader.apis.ShipYard;
import spacetrader.apis.SqliteApi;
import spacetrader.equipment.EquipmentFactory;
import spacetrader.equipment.Shield;
import spacetrader.equipment.Weapon;
import spacetrader.planets.GameCharacter;
import spacetrader.ships.PlayerShip;
import spacetrader.ships.Ship;
import spacetrader.ships.ShipFactory;

public class ShipYardTest {

    private ShipYard sY; 
    private SqliteApi db;
    @Before
    public void setUp() throws Exception {
    	
    	db = new SqliteApi();
        sY = new ShipYard();
    }

    @Test
    public void testRefuel() {
   
			
			PlayerShip ship = SqliteApi.getShip();
	    	GameCharacter player = SqliteApi.getPlayer();
	    	int initialMoney = player.getMoney();
	    	int initialFuel = ship.getBase().getFuel();
	    	int fullFuel = (new ShipFactory()).getShip(ship.getBase().getName()).getFuel();
	    	String statement = sY.refuel();
	    	String expStatement;
	    	if (initialFuel >= fullFuel) {
	    		expStatement = "Fuel is full";
	    		assertEquals(initialFuel, ship.getBase().getFuel());
	    	} else {
	    		int cost = ship.getBase().getFuelCost();
	    		int loss = cost * (fullFuel - initialFuel);
	    		int money = initialMoney - loss;
	    		if (money < 0) {
	    			expStatement = "You don't have enough money to refuel";
	    			assertEquals(initialFuel, ship.getBase().getFuel());
	    		} else {
	    			assertEquals(money, player.getMoney());
	    			assertEquals(fullFuel, ship.getBase().getFuel());
	    			expStatement = "This refuel cost " + loss + " cr";
	    		}
	    	}
	    	
	    	assertEquals(statement, expStatement);

    	
    }
}
