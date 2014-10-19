package spaceTrader.APIs;

import java.sql.SQLException;

import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.SolarSystem;

/**
 * API for player to travel to another planet
 * 
 * @author mli
 *
 */
public class Travel {

    private SqliteAPI db;
    private GameCharacter player;

    public Travel() {
        load();
    }

    /**
     * Warp to the system with the given name
     * 
     * @param planetName
     *            the name of the planet
     */
    public void warpTo(String planetName) {
        SolarSystem system;
        try {
            system = db.getSolarSystem(planetName);
            player.travel(system.getX(), system.getY());
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
        try {
            db.openConnection();
            db.updatePlayer(player);
            db.closeConnection();
            player = db.getPlayer();
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
