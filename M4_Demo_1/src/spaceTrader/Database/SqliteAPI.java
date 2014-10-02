package spaceTrader.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import spaceTrader.Planets.Capital;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Planets.Universe;

/**
 * API for communicating with Sqlite databse
 * 
 * @author Menghang Li
 *
 */
public class SqliteAPI {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    private String update;
    private GameCharacter player;
    private Universe universe;

    /**
     * Constructor that takes in a Player and the Universe
     * 
     * @param player
     *            the player
     * @param universe
     *            the universe
     */
    public SqliteAPI(GameCharacter player, Universe universe) {
        this.player = player;
        this.universe = universe;
    }

    /**
     * Opens connection to the universe
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void openConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:SpaceTrader.db");
        statement = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.
        initialize();

    }

    /**
     * Closes connection to the databse
     * 
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        connection.close();
    }

    /**
     * If the database is empty, fill in the data
     * 
     * @throws SQLException
     */
    private void initialize() throws SQLException {
        if (!tableExist("player")) {
            createPlayerTable();
            addPlayer();
            createUniverseTable();
            for (SolarSystem system : universe.getUniverse()) {
                addPlanet(system);
            }
        }
    }

    /**
     * Execute the query given the query String
     * 
     * @param query
     *            the query String
     * @throws SQLException
     */
    public void execQuery(String query) throws SQLException {
        statement.execute(query);

    }

    /**
     * Update the database given the sql command
     * 
     * @param update
     *            sql command
     * @throws SQLException
     */
    public void execUpdate(String update) throws SQLException {
        statement.executeUpdate(update);
    }

    /**
     * Return true if the table exists given
     * 
     * @param tableName
     *            the name of the table
     * @return true if the table exists given
     * @throws SQLException
     */
    private boolean tableExist(String tableName) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        resultSet = metadata.getTables(null, null, tableName, null);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    /**
     * Create the player table
     * 
     * @throws SQLException
     */
    private void createPlayerTable() throws SQLException {
        update = "CREATE TABLE player "
                + "(id INTEGER PRIMARY KEY, name TEXT not NULL, "
                + " pilotP INTEGER, fighterP INTEGER, " + " traderP INTEGER, "
                + " engineerP INTEGER)";
        execUpdate(update);
    }

    /**
     * Creates the universe table
     * 
     * @throws SQLException
     */
    private void createUniverseTable() throws SQLException {
        update = "CREATE TABLE universe (id INTEGER PRIMARY KEY, "
                + "name TEXT not NULL, xPos INTEGER, yPos INTEGER, "
                + "PoliticalSystem INTEGER, ResourcesLevel INTEGER,"
                + "TechLevels INTEGER, Pirates INTEGER," + "Polices INTGER)";
        execUpdate(update);
    }

    /**
     * Add player info to database
     * 
     * @throws SQLException
     */
    private void addPlayer() throws SQLException {
        update = String.format(
                "INSERT INTO player (name, pilotP, fighterP, traderP, engineerP) "
                        + "VALUES ('%s', %d, %d, %d, %d)", player.getName(),
                player.getPilotP(), player.getFighterP(), player.getTraderP(),
                player.getEngineerP());
        // System.out.println(update);
        execUpdate(update);
    }

    /**
     * Add a SolarSystem to database
     * 
     * @param system
     *            A SolarSystem
     * @throws SQLException
     */
    private void addPlanet(SolarSystem system) throws SQLException {
        Capital planet = system.getPlanet();
        update = String.format("INSERT INTO universe (name, xPos, yPos, "
                + "PoliticalSystem, ResourcesLevel, TechLevels, Pirates, "
                + "Polices) VALUES ('%s', %d, %d, %d, %d, %d, %d, %d)", system
                .getName(), system.getX(), system.getY(), planet
                .getPoliticalSystem().ordinal(), planet.getResourcesLevel()
                .ordinal(), planet.getTechLevel().ordinal(), planet.getPirate()
                .ordinal(), planet.getPolice().ordinal());
        execUpdate(update);
    }

}
