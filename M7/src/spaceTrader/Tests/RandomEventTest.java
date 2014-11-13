package spaceTrader.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.apis.RandomEvent;
import spaceTrader.apis.SqliteAPI;

public class RandomEventTest {

    private RandomEvent r;

    @Before
    public void setUp() throws Exception {
        r = new RandomEvent();
    }


    @Test
    public void noMoneyStolenTest() {
         try {
            SqliteAPI db = new SqliteAPI();
            GameCharacter player = SqliteAPI.getPlayer();
            r.setMoneyLost(false);
            int current = player.getMoney();
            String str = "Nothing special happened this turn";
            //check string
            assertEquals(str, r.update());
            //check money
            assertEquals(current,SqliteAPI.getPlayer().getMoney());
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }

    
    @Test
    public void MoneyStolenTest() {
    	try{
	        SqliteAPI db = new SqliteAPI();
            GameCharacter player = SqliteAPI.getPlayer();
            int origin = player.getMoney();
            r.setMoneyLost(true);
            int amount = player.getMoney() / r.MONEY_LOSS;;
            int money = player.getMoney() - amount;
            String str = "A UGA student just stole " +  amount
                    + " cr of money from you";
            assertEquals(str, r.update());
            assertNotEquals(origin,SqliteAPI.getPlayer().getMoney());
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
