package spacetrader.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import spacetrader.apis.SqliteApi;
import spacetrader.apis.Travel;
import spacetrader.goods.Good;
import spacetrader.goods.Ore;
import spacetrader.goods.Water;
import spacetrader.planets.GameCharacter;
import spacetrader.planets.SolarSystem;
import spacetrader.planets.Universe;
import spacetrader.ships.PlayerShip;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteApiTest {

    @SuppressWarnings("unused")
    private SqliteApi api;

    @Before
    public void setUp() throws Exception {
        if (!SqliteApi.isDbCreated()) {
            GameCharacter player = new GameCharacter("Tester1", 6, 6, 2, 2);

            try {
                api = new SqliteApi(player);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // else load models from database
            try {
                api = new SqliteApi();
                // System.out.println(api.getSolarSystem());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testListRemove() {
        Universe uni = SqliteApi.getUniverse();
        System.out.println(uni == null);
        System.out.println(uni.getUniverse().size());
        SolarSystem sys = uni.getUniverse().get(5);
        PlayerShip ship = SqliteApi.getShip();
     
        Travel api = new Travel();
        api.warpTo(sys.getName(), 20 , 1);
        int fuel = ship.getBase().getFuel();
        int hullStrength = ship.getBase().getHullStrength();
        ship = SqliteApi.getShip();
        //System.out.println("in junit now after travel, ship has fuel: " 
        //+ ship.getBase().getFuel());
        int newFuel = ship.getBase().getFuel();
        int newHullStrength = ship.getBase().getHullStrength();
        assertNotEquals(fuel, newFuel);
        assertNotEquals(hullStrength, newHullStrength);
        List<Good> list = new ArrayList<>();
        new Water();
        list.add(new Water());
        list.add(new Ore());
        list.remove(new Water());
        assertEquals(1, list.size());
        assertEquals(new Ore(), list.get(0));
    }

}