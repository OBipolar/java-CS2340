package spaceTrader.Tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.Planets.GameCharacter;
import spacetrader.apis.RandomEvent;
import spacetrader.apis.SqliteAPI;

public class RandomEventTest {

    private RandomEvent r;

    @Before
    public void setUp() throws Exception {
        r = new RandomEvent();
    }

    @Test
    public void testUpdate() {
        try {
            SqliteAPI db = new SqliteAPI();
            GameCharacter player = SqliteAPI.getPlayer();
            r.setMoneyLost(false);
            String exp = "Nothing special happened this turn";
            assertEquals(exp, r.update());
            
            r.setMoneyLost(true);
            int amount = player.getMoney() / r.MONEY_LOSS;;
            int money = player.getMoney() - amount;
            exp = "A UGA student just stole " +  amount
                    + " cr of money from you";
            assertEquals(exp, r.update());
            assertEquals(money, SqliteAPI.getPlayer().getMoney());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
