package spaceTrader.apis;

import java.util.Random;

import spaceTrader.Planets.GameCharacter;

/**
 * Class that does random event when the player travels to a planet
 * 
 * @author mli
 *
 */
public class RandomEvent {

    private final static int BOUND = 2;
    public final static int MONEY_LOSS = 20;
    private boolean moneyLost = false;

    /**
     * Returns the result of the random event in string format
     * 
     * @return the result of the random event in string format
     */
    public String update() {

        String s = "";
        GameCharacter player = SqliteAPI.getPlayer();
        if (moneyLost) {
        	int amount = (player.getMoney() / MONEY_LOSS);
            player.setMoney(player.getMoney() - amount);
            s = "A UGA student just stole " + amount
                    + " cr of money from you";
        } else {
            s = "Nothing special happened this turn";
        }
        //System.out.println("money after stolen: " + player.getMoney());
        SqliteAPI.setPlayer(player);
        return s;

    }

    /**
     * Return true if money is about to be stolen by UGA student
     * 
     * @return true if money is about to be stolen by UGA student
     */
    private boolean isMoneyLost() {
        if (getRandomNum(BOUND) != 0) {
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

    public void setMoneyLost(boolean moneyLost) {
        this.moneyLost = moneyLost;
    }
    public boolean getMoneyLost() {
        return moneyLost;
    }
}
