package spacetrader.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import spacetrader.apis.RandomEvent;
import spacetrader.apis.SqliteApi;
import spacetrader.planets.GameCharacter;

import java.sql.SQLException;

public class RandomEventTest {

    private RandomEvent rand;

    @Before
    public void setUp() throws Exception {
        rand = new RandomEvent();
    }


    @Test
    public void testUpdate() {
         try {
             @SuppressWarnings("unused")
            SqliteApi db = new SqliteApi();
             GameCharacter player = SqliteApi.getPlayer();
             rand.setMoneyLost(false);
             String exp = "Nothing special happened this turn";
             assertEquals(exp, rand.update());
             
             rand.setMoneyLost(true);
             int amount = player.getMoney() / RandomEvent.MONEY_LOSS;;
             int money = player.getMoney() - amount;
             exp = "A UGA student just stole " + amount
                       + " cr of money from you";
             assertEquals(exp, rand.update());
             assertEquals(money, SqliteApi.getPlayer().getMoney());
         } catch (ClassNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }
}
