package spaceTrader.Tests;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.APIs.MarketPlace;
import spaceTrader.Goods.Good;
import spaceTrader.Goods.Water;

public class MarketPlaceJunit {

    private MarketPlace mp;

    @Before
    public void setUp() throws Exception {

        mp = new MarketPlace();
    }

    @Test
    public void testBasicBuy() {
        List<Good> cargo = new ArrayList<>(mp.getCargo());
        int oldMoney = mp.getPlayerMoney();
        mp.playerBuy(new Water());
        assertNotEquals(cargo, mp.getCargo());
        assertNotEquals(new Integer(oldMoney), new Integer(mp.getPlayerMoney()));

        cargo = new ArrayList<>(mp.getCargo());
        oldMoney = mp.getPlayerMoney();
        mp.playerBuy(new Water());
        assertNotEquals(cargo, mp.getCargo());
        assertNotEquals(new Integer(oldMoney), new Integer(mp.getPlayerMoney()));
    }

    @Test
    public void testBasicSell() {
        List<Good> cargo = new ArrayList<>(mp.getCargo());
        int oldMoney = mp.getPlayerMoney();
        mp.playerSell(new Water());
        assertNotEquals(cargo, mp.getCargo());
        assertNotEquals(new Integer(oldMoney), new Integer(mp.getPlayerMoney()));
    }

}
