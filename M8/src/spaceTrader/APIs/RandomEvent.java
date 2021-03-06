package spaceTrader.APIs;

import java.sql.SQLException;
import java.util.Random;

import spaceTrader.Planets.GameCharacter;

/**
 * Class that does random event when the player travels to a planet
 * 
 * @author mli
 *
 */
public class RandomEvent {

    private int num;
    private final static int BOUND = 2;

    /**
     * Returns the result of the random event in string format
     * 
     * @return the result of the random event in string format
     */
    public String update() {
        SqliteAPI api;
        try {
            api = new SqliteAPI();
            String s = "";
            GameCharacter player = api.getPlayer();
            if (isMoneyLost()) {
            	int amount = (player.getMoney() / 5);
                player.setMoney(player.getMoney() - amount);
                s = "A UGA student just stole " + amount
                        + " cr of money from you";
            } else {
                s = "Nothing special happens this turn";
            }
            System.out.println("money after stolen: " + player.getMoney());
            api.openConnection();
            api.updatePlayer(player);
            api.closeConnection();
            return s;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Return true if money is about to be stolen by UGA student
     * 
     * @return true if money is about to be stolen by UGA student
     */
    private boolean isMoneyLost() {
        if (getRandomNum(BOUND) == 0) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Returns an random integer between 0 and limit (exclusive)
     * 
     * @param limit
     *            an integer
     * @return Returns an random integer between 0 and limit (exclusive)
     */
    private int getRandomNum(int limit) {
        return (new Random()).nextInt(limit);
    }
}
