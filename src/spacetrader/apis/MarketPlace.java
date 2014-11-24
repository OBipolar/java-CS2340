package spacetrader.apis;

import spacetrader.goods.Good;
import spacetrader.goods.Trade;
import spacetrader.planets.GameCharacter;
import spacetrader.planets.SolarSystem;
import spacetrader.ships.PlayerShip;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * API for the player to buy and sell goods
 * 
 * @author mli
 *
 */
public class MarketPlace {

    private static final Logger LOGGER = 
            Logger.getLogger(MarketPlace.class.getName()); 
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

        player = SqliteApi.getPlayer();
        ship = SqliteApi.getShip();

        try {
            system = SqliteApi.getSolarSystem();
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }


    }

    /**
     * updates the database and variables
     * 
     */
    private void update() {
        player = trade.getPlayer();
        ship = trade.getShip();
        SqliteApi.setPlayer(player);
        SqliteApi.setShip(ship);
    }

}
