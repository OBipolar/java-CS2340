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
        sY = new ShipYard();
        db = new SqliteAPI();
    }

    @Test
    public void testRefuel() {
    	PlayerShip ship = SqliteAPI.getShip();
    	int initialFuel = ship.getBase().getFuel();
    	String statement = sY.refuel();
    	String expStatement;
    	GameCharacter player = SqliteAPI.getPlayer();
    	int fullFuel = (new ShipFactory()).getShip(ship.getBase().getName()).getFuel();
    	if (ship.getBase().getFuel() >= fullFuel) {
    		expStatement = "Fuel is full";
    	} else {
    		int cost = ship.getBase().getFuelCost();
    		int loss = cost * (fullFuel - ship.getBase().getFuel());
    		int money = player.getMoney() - loss;
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
