package spacetrader.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import spacetrader.apis.RandomEvent;
import spacetrader.apis.SqliteApi;
import spacetrader.planets.GameCharacter;

import java.sql.SQLException;

/**
 * Junit test for RandomEvent 
 * 
 * @author Menghang Li
 *
 */
public class RandomEventTest {

    private RandomEvent rand;

    @Before
    public void setUp() throws Exception {
        rand = new RandomEvent();
    }


    @Test   
    public void testUpdate() throws ClassNotFoundException, SQLException {

             @SuppressWarnings("unused")
             SqliteApi db = new SqliteApi();
             GameCharacter player = SqliteApi.getPlayer();
             int money = player.getMoney();
             rand.setMoneyLost(false);
             String exp = "Nothing special happened this turn";
             assertEquals(exp, rand.update());
             assertEquals(money, SqliteApi.getPlayer().getMoney());
             
             rand.setMoneyLost(true);
             int amount = player.getMoney() / RandomEvent.MONEY_LOSS;;
             int newMoney = player.getMoney() - amount;
             exp = "A UGA student just stole " + amount
                       + " cr of money from you";
             assertEquals(exp, rand.update());
             assertEquals(newMoney, SqliteApi.getPlayer().getMoney());
     }
}
