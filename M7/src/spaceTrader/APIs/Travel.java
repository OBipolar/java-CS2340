package spaceTrader.APIs;

import java.sql.SQLException;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Ships.PlayerShip;

/**
 * API for player to travel to another planet
 * 
 * @author mli
 *
 */
public class Travel {
	
    private SqliteAPI db;
    private GameCharacter player;
    private PlayerShip ship;

    public Travel() {
        load();
    }

    /**
     * Warp to the system with the given name
     * 
     * @param planetName
     *            the name of the planet
     */
    public void warpTo(String planetName, int travelDist, int hullExhaust) {
        SolarSystem system;
        try {
            system = db.getSolarSystem(planetName);
            updateShip();
            player.travel(system.getX(), system.getY());
            ship.getBase().setFuel(ship.getBase().getFuel() - travelDist);
            ship.getBase().setHullStrength(ship.getBase().getHullStrength() - hullExhaust);
            update();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * updates the database and variables
     * 
     */
    private void update() {
        db.setPlayer(player);
        db.setShip(ship);
    }
    
    /**
     * Update fuel and hull of the player ship given the destination 
     * coordinates
     * 
     */
    private void updateShip() {
    	int fuel = ship.getBase().getFuel();
    	int fuelLoss = ship.getBase().getFuelCost();
    	ship.getBase().setFuel(fuel - fuelLoss);
    	System.out.println("new fuel is " + (fuel - fuelLoss));
    	int hullStrength = ship.getBase().getHullStrength();
    	hullStrength--;
    	ship.getBase().setHullStrength(hullStrength);
    	
    }

}
