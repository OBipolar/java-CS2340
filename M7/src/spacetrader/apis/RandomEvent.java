package spacetrader.apis;

import spacetrader.planets.GameCharacter;

import java.util.Random;



/**
 * Class that does random event when the player travels to a planet
 * 
 * @author mli
 *
 */
public class RandomEvent {

    private static final int BOUND = 2;
    public static final int MONEY_LOSS = 20;
    private boolean moneyLost = false;

    /**
     * Returns the result of the random event in string format
     * 
     * @return the result of the random event in string format
     */
    public String update() {

        String str = "";
        GameCharacter player = SqliteApi.getPlayer();
        if (moneyLost) {
            int amount = player.getMoney() / MONEY_LOSS;
            player.setMoney(player.getMoney() - amount);
            str = "A UGA student just stole " + amount
                    + " cr of money from you";
        } else {
            str = "Nothing special happened this turn";
        }
        //System.out.println("money after stolen: " + player.getMoney());
        SqliteApi.setPlayer(player);
        return str;

    }

    /**
     * Return true if money is about to be stolen by UGA student
     * 
     * @return true if money is about to be stolen by UGA student
     */
    @SuppressWarnings("unused")
    private boolean isMoneyLost() {
        if (getRandomNum(BOUND) != 0) {
            return false;
        } 
        return true;


    }

    /**
     * Returns an random integer between 0 and limit (exclusive)
     * 
     * @param limit
     *            an integer
     * @return Returns an random integer between 0 and limit (exclusive)
     */
    private int getRandomNum(int limit) {
        return new Random().nextInt(limit);
    }

    /**
     * Set moneyLost to be true if money is lost, false otherwise
     * 
     * @param moneyLost
     */
    public void setMoneyLost(boolean moneyLost) {
        this.moneyLost = moneyLost;
    }
    
    /**
     * Return true if the money is lost, false otherwise
     * 
     * @return
     */
    public boolean getMoneyLost() {
        return moneyLost;
    }
}
