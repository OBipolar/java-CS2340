package spacetrader.apis;

import spacetrader.planets.GameCharacter;
import spacetrader.planets.SolarSystem;
import spacetrader.ships.PlayerShip;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * API for player to travel to another planet
 * 
 * @author mli
 *
 */
public class Travel {
    
    private static final Logger LOGGER = 
            Logger.getLogger(MarketPlace.class.getName()); 
    private GameCharacter player;
    private PlayerShip ship;

    /**
     * Default constructor that loads in variable 
     */
    public Travel() {
        load();
    }

    /**
     * Warp to the system with the given name
     * 
     * @param planetName
     *            the name of the planet
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void warpTo(String planetName, int travelDist, int hullExhaust) {
        SolarSystem system;
            try {
                system = SqliteApi.getSolarSystem(planetName);
                //updateShip();
                player.travel(system.getX(), system.getY());
                ship.getBase().setFuel(ship.getBase().getFuel() - travelDist);
                ship.getBase().setHullStrength(ship.getBase().getHullStrength() - hullExhaust);
                update();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                LOGGER.log(Level.SEVERE, e.getMessage());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                LOGGER.log(Level.SEVERE, e.getMessage());
            }


    }

    /**
     * Loads data from database
     * 
     */
    private void load() {

        player = SqliteApi.getPlayer();
        ship = SqliteApi.getShip();

    }

    /**
     * updates the database and variables
     * 
     */
    private void update() {
        SqliteApi.setPlayer(player);
        SqliteApi.setShip(ship);
    }
    
    /**
     * Update fuel and hull of the player ship given the destination 
     * coordinates
     * 
     */
    @SuppressWarnings("unused")
    private void updateShip() {
        int fuel = ship.getBase().getFuel();
        int fuelLoss = ship.getBase().getFuelCost();
        ship.getBase().setFuel(fuel - fuelLoss);
        //System.out.println("new fuel is " + (fuel - fuelLoss));
        int hullStrength = ship.getBase().getHullStrength();
        hullStrength--;
        ship.getBase().setHullStrength(hullStrength);
        
    }

}
