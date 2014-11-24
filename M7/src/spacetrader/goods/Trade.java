package spacetrader.goods;

import spacetrader.planets.GameCharacter;
import spacetrader.planets.SolarSystem;
import spacetrader.ships.PlayerShip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;



/**
 * Class that does trade
 * 
 * @author Menghang Li
 *
 */
public class Trade {

    /**
     * The player model
     */
    private GameCharacter player;
    /**
     * The playership model
     */
    private PlayerShip playerShip;
    /**
     * The solar system the player is on
     */
    private SolarSystem system;
    /**
     * A list of names of goods the player can sell
     */
    private List<String> goodsToSell;
    /**
     * A list of names of goods player can buy 
     */
    private List<String> goodsToBuy;
    /**
     * A map that has the name of a good that the player can sell
     * as a key and the price as the value
     */
    private Map<String, Integer> pricesToSell;
    /**
     * A map that has the name of a good that the player can nuy
     * as a key and the price as the value
     */
    private Map<String, Integer> pricesToBuy;

    /**
     * Constructor that takes in a Player, a PlayerShip and a SolarSystem and
     * sets the trade information
     * 
     * @param player
     *            a Player
     * @param playerShip
     *            a PlayerShip
     * @param system
     *            a SolarSystem
     */
    public Trade(GameCharacter player, PlayerShip playerShip, SolarSystem system) {

        this.player = player;
        this.playerShip = playerShip;
        this.system = system;
        setGoodsToSell(new ArrayList<String>());
        setGoodsToBuy(new ArrayList<String>());
        pricesToSell = new HashMap<String, Integer>();
        pricesToBuy = new HashMap<String, Integer>();
        setupGoodsToSell();
        setupGoodsToBuy();
    }

    /**
     * Return true if the buying is successful
     * 
     * @param good
     *            good the player wants to buy
     * @return true if the buying is successful
     */
    public boolean buy(Good good) {
        int amount = pricesToBuy.get(good.getName());
        int money = player.getMoney();
        if (amount > money || playerShip.isFull()) {
            return false;
        }
        money -= amount;
        player.setMoney(money);
        playerShip.add(good);
        goodsToSell.add(good.getName());
        pricesToSell.put(good.getName(), amount);
        return true;
    }

    /**
     * Sells a good in player ship cargo
     * 
     * @param good
     *            a good in cargo
     */
    public void sell(Good good) {
        int amount = pricesToSell.get(good.getName());
        int money = player.getMoney();
        money += amount;
        player.setMoney(money);
        playerShip.remove(good);
        // goodsToSell.remove(good.getName());

    }

    /**
     * Sets the goods player can sell on a particular planet depending on the
     * planet's tech level
     */
    public void setupGoodsToSell() {

        int techLevel = system.getPlanet().getTechLevel().ordinal();
        // System.out.println("techlevel: " + techLevel);
        List<Good> cargo = playerShip.getCargo();
        // System.out.println("cargo is empty: " + cargo.isEmpty());
        for (Good good : cargo) {
            // System.out.println("MTLU " + good.getMTLU());
            if (good.getMtlu() <= techLevel) {
                goodsToSell.add(good.getName());
                pricesToSell.put(good.getName(), calcPrice(good, techLevel));
                // System.out.println(good.getName() + calcPrice(good,
                // techLevel));
            }
        }

    }
    
    /**
     * Sets goodsToSell
     * 
     * @param goodsToSell a goodsToSell
     */
    public void setGoodsToSell(List<String> goodsToSell) {
        this.goodsToSell = goodsToSell;
    }

    /**
     * Sets the goods player can buy on a particular planet depending on the
     * planet's tech level
     */
    public void setupGoodsToBuy() {

        int techLevel = system.getPlanet().getTechLevel().ordinal();
        List<Good> goods = new ArrayList<Good>();
        goods.add(new Water());
        goods.add(new Furs());
        goods.add(new Food());
        goods.add(new Ore());
        goods.add(new Games());
        goods.add(new Firearms());
        goods.add(new Medicine());
        goods.add(new Machines());
        goods.add(new Narcotics());
        goods.add(new Robots());

        for (Good good : goods) {
            if (good.getMtlp() <= techLevel) {
                goodsToBuy.add(good.getName());
                pricesToBuy.put(good.getName(), calcPrice(good, techLevel));
            }
        }

    }
    
    /**
     * Sets goodsToBuy
     * 
     * @param goodsToBuy a goodsToBuy
     */
    public void setGoodsToBuy(List<String> goodsToBuy) {
        this.goodsToBuy = goodsToBuy;
    }

    /**
     * Return the price the player can buy or sell a good on a particular planet
     * given its tech level
     * 
     * @param good
     *            a good
     * @param techLevel
     *            the planet's tech level
     * @return the price the player can buy or sell a good on a particular
     *         planet given its tech level
     */
    private int calcPrice(Good good, int techLevel) {
        return good.getBasePrice() * (1 + getRandomNum(good.getVar() / 10))
                + good.getIpl() * (techLevel - good.getMtlp());
    }

    /**
     * Returns an random integer between 0 and limit (inclusive)
     * 
     * @param limit
     *            an integer
     * @return Returns an random integer between 0 and limit (inclusive)
     */
    private int getRandomNum(int limit) {
        return new Random().nextInt(limit + 1);
    }

    /**
     * Returns pricesToSell
     * 
     * @return pricesToSell
     */
    public Map<String, Integer> getPricesToSell() {
        return pricesToSell;
    }

    /**
     * Retuns pricesToBuy
     * 
     * @return pricesToBuy
     */
    public Map<String, Integer> getPricesToBuy() {
        return pricesToBuy;
    }

    /**
     * Returns goodsToSell
     * 
     * @return goodsToSell
     */
    public List<String> getGoodsToSell() {
        return goodsToSell;
    }



    /**
     * Returns goodsToBuy
     * 
     * @return goodsToBuy
     */
    public List<String> getGoodsToBuy() {
        return goodsToBuy;
    }



    /**
     * Get the ship in trade
     * 
     * @return the ship in trade;
     */
    public PlayerShip getShip() {
        // TODO Auto-generated method stub
        return playerShip;
    }

    /**
     * Get the player in trade
     * 
     * @return the player in trade
     */
    public GameCharacter getPlayer() {
        // TODO Auto-generated method stub
        return player;
    }

}
