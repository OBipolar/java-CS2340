package spacetrader.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import spacetrader.apis.SqliteApi;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import spacetrader.apis.SqliteApi;
import spacetrader.goods.Good;
import spacetrader.goods.Water;
import spacetrader.goods.Trade;
import spacetrader.planets.Capital;
import spacetrader.planets.GameCharacter;
import spacetrader.planets.Planet;
import spacetrader.planets.SolarSystem;
import spacetrader.ships.PlayerShip;
import spacetrader.apis.MarketPlace;

import java.sql.SQLException;
import java.util.List;





/**
 * Trade buy related Junit tests
 * @author Shaohui Xu (Obipolar)
 * @version 1.0
 */
public class TradeBuyTest {
    PlayerShip playerShip;
    SolarSystem system;
    Trade trade;
    GameCharacter player;

    @Before
    public void setUp() throws Exception {
        if (!SqliteApi.isDbCreated()) {
            player = new GameCharacter("tempPlayer", 6, 6, 2, 2);
            GameCharacter player = new GameCharacter("tempPlayer", 6, 6, 2, 2);
            SqliteApi.start(player);
            playerShip = SqliteApi.getShip();
            system = SqliteApi.getSolarSystem();
            trade = new Trade(player, playerShip, system);

        } else {
            SqliteApi.start();
            player = SqliteApi.getPlayer();
            GameCharacter player = SqliteApi.getPlayer();
            playerShip = SqliteApi.getShip();
            system = SqliteApi.getSolarSystem();
            trade = new Trade(player, playerShip, system);
        }
    }

    
    @Test
    public void testBuySuccessful() {
        assertEquals("Basic buy failed", true,trade.buy(new Water()));
    }

    @Test
    public void testBuyFail1() {
        playerShip.setCargoSpace(playerShip.getNumOfGoods());
        trade = new Trade(player, playerShip, system);
        assertEquals("Fail to restrict the cargo space",false,trade.buy(new Water()));
    }   
    
    @Test
    public void testBuyFail2() {
        player.setMoney(0);
        trade = new Trade(player, playerShip, system);
        assertEquals("fail to restric the money", false, trade.buy(new Water()));
    }
    
    @Test
    public void testBasicBuy() {
        List<Good> cargo = new ArrayList<>(playerShip.getCargo());
        int oldMoney = player.getMoney();
        trade.buy(new Water());
        
        assertNotEquals("Fail to add new gods to the cargo", cargo, playerShip.getCargo());
        assertNotEquals("Fail to update money after buying", oldMoney, player.getMoney());
        assertEquals(true,trade.buy(new Water()));
    }

}

