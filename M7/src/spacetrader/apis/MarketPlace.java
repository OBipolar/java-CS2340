package spacetrader.apis;

import java.sql.SQLException;
import java.util.List;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;
import spacetrader.goods.Good;
import spacetrader.goods.Trade;

/**
 * API for the player to buy and sell goods
 * 
 * @author mli
 *
 */
public class MarketPlace {

    private GameCharacter player;
    private PlayerShip ship;
    private Trade trade;
    private SolarSystem system;

    /**
     * Constructor 
     */
    public MarketPlace() {
        load();
        trade = new Trade(player, ship, system);
    }

    /**
     * Makes player buy a good and then update the database
     * 
     * @param good a good
     */
    public void playerBuy(Good good) {
        trade.buy(good);
        update();
    }

    /**
     * Makes player sell a good and then update the database
     * 
     * @param good a good
     */
    public void playerSell(Good good) {
        trade.sell(good);
        update();
    }

    /**
     * Returns the money player owns
     * 
     * @return the money player owns
     */
    public int getPlayerMoney() {
        return player.getMoney();
    }

    /**
     * Return cargo on ship
     * 
     * @return cargo on ship
     */
    public List<Good> getCargo() {
        return ship.getCargo();
    }

    /**
     * Loads data from database
     * 
     */
    private void load() {

        player = SqliteAPI.getPlayer();
        ship = SqliteAPI.getShip();

        try {
            system = SqliteAPI.getSolarSystem();
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e);
        }


    }

    /**
     * updates the database and variables
     * 
     */
    private void update() {
        SqliteAPI.setPlayer(player);
        SqliteAPI.setShip(ship);
    }

}
