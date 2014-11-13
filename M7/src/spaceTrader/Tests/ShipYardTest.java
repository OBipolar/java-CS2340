package spaceTrader.Tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.Equipment.EquipmentFactory;
import spaceTrader.Equipment.Shield;
import spaceTrader.Equipment.Weapon;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
import spaceTrader.Ships.ShipFactory;
import spaceTrader.apis.ShipYard;
import spaceTrader.apis.SqliteAPI;

public class ShipYardTest {

    private ShipYard sY; 
    private SqliteAPI db;
    @Before
    public void setUp() throws Exception {
    	
    	db = new SqliteAPI();
        sY = new ShipYard();
    }

    @Test
    public void testRefuel() {
   
			
			PlayerShip ship = SqliteAPI.getShip();
	    	GameCharacter player = SqliteAPI.getPlayer();
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
