package spaceTrader.Tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.APIs.SqliteAPI;
import spaceTrader.Goods.Good;
import spaceTrader.Goods.Ore;
import spaceTrader.Goods.Water;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.SolarSystem;

public class SqliteAPIJunit {

    private SqliteAPI api;

    @Before
    public void setUp() throws Exception {
        if (!SqliteAPI.isDBCreated()) {
            GameCharacter player = new GameCharacter("Tester1", 6, 6, 2, 2);

            api = new SqliteAPI(player);

        } else {
            // else load models from database
            try {
                api = new SqliteAPI();
                api.closeConnection();
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
        List<Good> list = new ArrayList<>();
        new Water();
        list.add(new Water());
        list.add(new Ore());
        list.remove(new Water());
        /*
         * Iterator<Good> it = list.iterator(); while (it.hasNext()) { if
         * (it.next().equals(new Water())) { it.remove(); }
         * 
         * }
         */
        // System.out.println(list.contains(new Water()));
        // list.remove(new Water());
        assertEquals(1, list.size());
        assertEquals(new Ore(), list.get(0));
    }

    @Test
    public void testOverwrite() {
        GameCharacter player = new GameCharacter("overwrite test", 3, 5, 7, 1);
        try {
            api = new SqliteAPI(player);
            player = api.getPlayer();
            assertEquals("overwrite test", player.getName());
            assertEquals(3, player.getPilotP());
            assertEquals(5, player.getFighterP());
            System.out.println("test passed");
            SolarSystem s = api.getSolarSystem();
            System.out.println(s.getX() + " " + s.getY());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*
     * @Test public void testPlayerShipRemove() { PlayerShip ship =
     * api.getShip(); List<Good> cargo = new ArrayList<>(ship.getCargo());
     * System.out.println(cargo); ship.remove(new Water());
     * System.out.println(cargo); assertNotEquals(cargo, ship.getCargo()); }
     */
}
