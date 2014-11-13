package spacetrader.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spacetrader.goods.Good;
import spacetrader.goods.Trade;
import spacetrader.goods.Water;
import spacetrader.planets.GameCharacter;
import spacetrader.planets.SolarSystem;
import spacetrader.planets.Universe;
import spacetrader.ships.PlayerShip;


/***
 * 
 * @author Zixiang Zhu
 *
 */

public class TradeSellTest {
    
    private Trade trade;
    private GameCharacter player;
    private PlayerShip ps;
    private SolarSystem ss;

    @Before
    public void setUp() throws Exception {
        player = new GameCharacter("david", 4, 4, 4, 4);
        ps = new PlayerShip();
        Universe uni = new Universe();
        ss = uni.getUniverse().get(0);
        trade = new Trade(player, ps, ss);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetGoodsToSell() {
        List<String> goodsToSell = new ArrayList<String>();
        Map<String, Integer> map = trade.getPricesToSell();
        int techLevel = ss.getPlanet().getTechLevel().ordinal();
        List<Good> cargo = ps.getCargo();
        for (Good good : cargo) {
            if (good.getMtlu() <= techLevel) {
                goodsToSell.add(good.getName());
            }
        }
        assertEquals("goods to sell is not set properly", goodsToSell, trade.getGoodsToSell());
        assertNotNull("pricesToSell is not set", map);
    }
    
    @Test
    public void testSetGoodsToSellWithDifferentTechLevel() {
        List<String> goodsToSell = new ArrayList<String>();
        Map<String, Integer> map = trade.getPricesToSell();
        int techLevel = 0;
        List<Good> cargo = ps.getCargo();
        for (Good good : cargo) {
            if (good.getMtlu() <= techLevel) {
                goodsToSell.add(good.getName());
            }
        }
        assertNotEquals("goods to sell do not depend on tech levels", goodsToSell, trade.getGoodsToSell());
        assertNotNull("pricesToSell is not set", map);
    }
    
    @Test
    public void testSell1() {
        
         int oldMoney = player.getMoney();
         List<Good> oldCargo = new ArrayList<Good>(ps.getCargo());
         
         trade.sell(new Water());
         
         player = trade.getPlayer();
         ps = trade.getShip();
         int money = player.getMoney();

         assertNotEquals("Sell does not change player's money", oldMoney, money);
         System.out.println(oldCargo.size());
         assertNotEquals("Sell does not change playership's cargo", oldCargo, ps.getCargo());
    }

    
    
    @Test
    public void testSell2() {     
         Map<String, Integer> map = trade.getPricesToSell();
         List<Good> cargo = ps.getCargo();
         int amount = map.get("Water");
         int money = player.getMoney();
         money += amount;
         cargo.remove(new Water());
         
         trade.sell(new Water());
         
         player = trade.getPlayer();
         ps = trade.getShip();
         
         assertEquals("Sell does not correctly change player's money", money, player.getMoney());
         assertEquals("Sell does not remove the good correctly from playership's cargo", cargo, ps.getCargo());
    }
    
    @Test
    public void cargoEmptySell() {
        List<Good> oldCargo = new ArrayList<Good>(ps.getCargo());
        oldCargo.remove(new Water());
        oldCargo.remove(new Water());
        oldCargo.remove(new Water());
        trade.sell(new Water());
        trade.sell(new Water());
        trade.sell(new Water());
        trade.sell(new Water());
        assertEquals("should not sell more than what was in the cargo", oldCargo, ps.getCargo());
    }

}



