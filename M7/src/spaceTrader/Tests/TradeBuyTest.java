package spaceTrader.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import spaceTrader.apis.SqliteAPI;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.Goods.Good;
import spaceTrader.Goods.Water;
import spaceTrader.Goods.Trade;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Planets.Planet;
import spaceTrader.Planets.Capital;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.apis.MarketPlace;

/**
 * Trade buy related Junit tests
 * @author Shaohui Xu (Obipolar)
 * @version 1.0
 */
public class TradeBuyTest {
    SqliteAPI db;
    PlayerShip playerShip;
    SolarSystem system;
    Trade trade;
    
    
    @Before
    public void setUp() throws Exception {
        if (!SqliteAPI.isDBCreated()) {
            GameCharacter player = new GameCharacter("tempPlayer", 6, 6, 2, 2);
            try {
                db = new SqliteAPI(player);
                playerShip = SqliteAPI.getShip();
                system = SqliteAPI.getSolarSystem();
                trade = new Trade(player, playerShip, system);
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
                SqliteAPI db = new SqliteAPI();
                GameCharacter player = SqliteAPI.getPlayer();
                playerShip = SqliteAPI.getShip();
                system = SqliteAPI.getSolarSystem();
                trade = new Trade(player, playerShip, system);
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
    public void testBuySuccessful() {
        assertEquals(true,trade.buy(new Water()));
    }

    @Test
    public void testBuyFail() {
        playerShip.setCargoSpace(0);
        assertEquals(false,trade.buy(new Water()));
    }   
    
    @Test
    public void testBasicBuy() {
        List<Good> cargo = new ArrayList<>(playerShip.getCargo());
        int oldMoney = player.getMoney();
        trade.buy(new Water());
        
        assertNotEquals(cargo, playerShip.getCargo());
        assertNotEquals(oldMoney, player.getMoney());
    }


    // @Test
    // public void testSetGoodsToBuy() {

    // }

}

