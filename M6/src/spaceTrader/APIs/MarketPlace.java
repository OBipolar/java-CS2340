package spaceTrader.APIs;

import java.sql.SQLException;

import spaceTrader.Goods.Good;
import spaceTrader.Goods.Trade;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.PlayerShip;
import spaceTrader.Planets.SolarSystem;

/**
 * API for the player to buy and sell goods
 * 
 * @author mli
 *
 */
public class MarketPlace {

    private SqliteAPI db;
    private GameCharacter player;
    private PlayerShip ship;
    private Trade trade;
    private SolarSystem system;

    public MarketPlace() {
        load();
        trade = new Trade(player, ship, system);
    }

    /**
     * Makes player buy a good and then update the database
     * 
     * @param good
     */
    public void playerBuy(Good good) {
        trade.buy(good);
        update();
    }

    /**
     * Makes player sell a good and then update the database
     * 
     * @param good
     */
    public void playerSell(Good good) {
        trade.sell(good);
        update();
    }

    /**
     * Loads data from database
     * 
     */
    private void load() {
        try {
            db = new SqliteAPI();
            player = db.getPlayer();
            ship = db.getShip();
            system = db.getSolarSystem(player.getXpos(), player.getYpos());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * updates the database
     * 
     */
    private void update() {
        try {
            db.openConnection();
            db.update(player, ship);
            db.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
