package spaceTrader.APIs;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spaceTrader.Goods.Firearms;
import spaceTrader.Goods.Food;
import spaceTrader.Goods.Furs;
import spaceTrader.Goods.Games;
import spaceTrader.Goods.Good;
import spaceTrader.Goods.GoodFactory;
import spaceTrader.Goods.Machines;
import spaceTrader.Goods.Medicine;
import spaceTrader.Goods.Narcotics;
import spaceTrader.Goods.Ore;
import spaceTrader.Goods.Robots;
import spaceTrader.Goods.Water;
import spaceTrader.Planets.Capital;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Planets.Universe;
import spaceTrader.Ships.BumbleBee;
import spaceTrader.Ships.Firefly;
import spaceTrader.Ships.Flea;
import spaceTrader.Ships.Gnat;
import spaceTrader.Ships.Mosquito;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
import spaceTrader.Ships.ShipFactory;

/**
 * API for communicating with Sqlite databse
 * 
 * @author Menghang Li
 *
 */
public class SqliteAPI {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static String query;
    private static String update;
    private static GameCharacter player;
    private static PlayerShip ship;
    private static Universe universe;

    /**
     * Delete tables based on given string
     * 
     * 
     * @param args
     *            string input
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void deleteTable(String... args) throws SQLException,
            ClassNotFoundException {

        for (String s : args) {
            if (tableExist(s)) {
                closeConnection();
                openConnection();
                update = String.format("DROP TABLE '%s'", s);
                execUpdate(update);
            }
        }

    }

    /**
     * Constructor that takes in a Player and the Universe
     * 
     * @param player
     *            the player
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public SqliteAPI(GameCharacter player) throws ClassNotFoundException,
            SQLException {
        this.player = player;
        this.universe = new Universe();
        this.ship = new PlayerShip();
        SolarSystem start = universe.getUniverse().get(0);
        this.player.setXpos(start.getX());
        this.player.setYpos(start.getY());
        openConnection();
        initialize();
        closeConnection();
    }

    public SqliteAPI() throws ClassNotFoundException, SQLException {
        openConnection();
        loadData();
        closeConnection();
    }

    public void update(GameCharacter player, PlayerShip ship)
            throws SQLException {
        updatePlayer(player);
        updateShip(ship);
    }

    /**
     * Update the ship player owns
     * 
     * @param ps
     *            the ship player owns
     * @throws SQLException
     */
    public void updateShip(PlayerShip ps) throws SQLException {
        ship = ps;
        System.out.println("ship variable in db now has fuel: " + ship.getBase().getFuel());
        update = "DROP TABLE ship";
        execUpdate(update);
        createShipTable();
        addShip();
        update = "DROP TABLE cargo";
        execUpdate(update);
        createCargoTable();
        loadCargo();
    }

    /**
     * Update player
     * 
     * @param gc
     *            GameCharacter object
     * @throws SQLException
     */
    public void updatePlayer(GameCharacter gc) throws SQLException {
        player = gc;
        System.out.println("Player in sqlite has money  " + player.getMoney() );
        update = "DROP TABLE player";
        execUpdate(update);
        createPlayerTable();
        addPlayer();
    }

    /**
     * Load data from database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void loadData() throws SQLException, ClassNotFoundException {
        openConnection();
        loadPlayer();
        loadPlayerShip();
        loadUniverse();
        closeConnection();
    }

    /**
     * Loads player from database
     * 
     * @throws SQLException
     */
    private void loadPlayer() throws SQLException {
        query = "SELECT * FROM player";
        execQuery(query);
        GameCharacter player = new GameCharacter();
        player.setMoney(resultSet.getInt("money"));
        player.setXpos(resultSet.getInt("xpos"));
        player.setYpos(resultSet.getInt("ypos"));
        player.setName(resultSet.getString("name"));
        player.setEngineerP(resultSet.getInt("engineerP"));
        player.setFighterP(resultSet.getInt("fighterP"));
        player.setPilotP(resultSet.getInt("pilotP"));
        player.setTraderP(resultSet.getInt("traderP"));
        this.player = player;
    }

    /**
     * Loads player ship from database
     * 
     * @throws SQLException
     */
    private void loadPlayerShip() throws SQLException {
        query = "SELECT name FROM cargo";
        execQuery(query);
        List<Good> goods = new ArrayList<>();
        GoodFactory gF = new GoodFactory();
        while (resultSet.next()) {
        	goods.add(gF.getGood(resultSet.getString(1)));        
        }

        query = "SELECT * FROM ship";
        execQuery(query);
        ShipFactory sF = new ShipFactory();
        Ship base = sF.getShip(resultSet.getString("name"));
        int fuel = resultSet.getInt("fuel");
        int hullStrength = resultSet.getInt("hullStrength");
        base.setHullStrength(hullStrength);
        base.setFuel(fuel);
        this.ship = new PlayerShip(base, goods);

    }

    /**
     * Load universe from database;
     * 
     * @throws SQLException
     */
    private void loadUniverse() throws SQLException {
        String query = "SELECT * from universe";
        execQuery(query);
        Capital capital;
        SolarSystem system;
        List<SolarSystem> systems = new ArrayList<>();
        while (resultSet.next()) {
            capital = new Capital();
            capital.setName(resultSet.getString("name"));
            capital.setSolarSystem(capital.getName());
            capital.setPoliticalSystem(resultSet.getInt("PoliticalSystem"));
            capital.setResourcesLevel(resultSet.getInt("ResourcesLevel"));
            capital.setTechLevel(resultSet.getInt("TechLevel"));
            capital.setPirate(resultSet.getInt("Pirate"));
            capital.setPolice(resultSet.getInt("Police"));
            system = new SolarSystem(resultSet.getInt("xpos"),
                    resultSet.getInt("ypos"), capital.getName(), capital);
            systems.add(system);
        }
        this.universe = new Universe(systems);

    }

    /**
     * Return true if the database has been created
     * 
     * @return true if the database has been created
     */
    public static boolean isDBCreated() {
        File f = new File("SpaceTrader.db");
        if (f.exists()) {
            return true;
        }
        return false;
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
     * Returns the universe
     * 
     * @return the universe
     */
    public Universe getUniverse() {
        return universe;
    }

    /**
     * Returns the planet the player is on
     * 
     * @return the planet the player is on or near with the given x and y
     *         coordinates
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public SolarSystem getSolarSystem() throws SQLException,
            ClassNotFoundException {
        int xPos = player.getXpos();
        int yPos = player.getYpos();
        openConnection();
        String query = String.format(
                "SELECT name, PoliticalSystem, ResourcesLevel, "
                        + "TechLevel, Pirate, Police from universe "
                        + "where xPos = '%d' and yPos = '%d'", xPos, yPos);
        execQuery(query);
        Capital capital = new Capital();
        if (resultSet.next()) {

            capital.setName(resultSet.getString("name"));
            capital.setSolarSystem(capital.getName());
            capital.setPoliticalSystem(resultSet.getInt("PoliticalSystem"));
            capital.setResourcesLevel(resultSet.getInt("ResourcesLevel"));
            capital.setTechLevel(resultSet.getInt("TechLevel"));
            capital.setPirate(resultSet.getInt("Pirate"));
            capital.setPolice(resultSet.getInt("Police"));

        } else {
            System.out.println("result set empty");
        }
        closeConnection();
        return new SolarSystem(xPos, yPos, capital.getName(), capital);
    }

    /**
     * Returns the solar system given the name
     * 
     * @param name
     *            the name of the solar system
     * @return the solar system given the name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public SolarSystem getSolarSystem(String name) throws SQLException,
            ClassNotFoundException {
        openConnection();
        String query = String.format(
                "SELECT * from universe where name = '%s'", name);
        execQuery(query);
        Capital capital = new Capital();
        if (resultSet.next()) {

            capital.setName(name);
            capital.setSolarSystem(name);
            capital.setPoliticalSystem(resultSet.getInt("PoliticalSystem"));
            capital.setResourcesLevel(resultSet.getInt("ResourcesLevel"));
            capital.setTechLevel(resultSet.getInt("TechLevel"));
            capital.setPirate(resultSet.getInt("Pirate"));
            capital.setPolice(resultSet.getInt("Police"));

        } else {
            System.out.println("result set empty");
        }
        int xpos = resultSet.getInt("xpos");
        int ypos = resultSet.getInt("ypos");
        closeConnection();
        return new SolarSystem(xpos, ypos, name, capital);
    }

    /**
     * Return the player model
     * 
     * @return the player model
     */
    public GameCharacter getPlayer() {

        return player;
    }

    /**
     * Returns ship
     * 
     * @return ship;
     */
    public PlayerShip getShip() {
        return ship;
    }

    /**
     * If the database is empty, fill in the data
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void initialize() throws SQLException, ClassNotFoundException {

        if (isDBCreated()) {
            String[] tables = { "cargo", "player", "ship", "universe" };
            deleteTable(tables);
        }

        createPlayerTable();
        addPlayer();
        createUniverseTable();
        for (SolarSystem system : universe.getUniverse()) {
            addPlanet(system);
        }
        createShipTable();
        addShip();
        createCargoTable();
        loadCargo();
    }

    /**
     * Execute the query given the query String
     * 
     * @param query
     *            the query String
     * @throws SQLException
     */
    public void execQuery(String query) throws SQLException {
        resultSet = statement.executeQuery(query);

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
                + " engineerP INTEGER, xpos INTEGER, ypos INTEGER, "
                + "money INTEGER)";
        execUpdate(update);
    }

    /**
     * Creates the universe table
     * 
     * @throws SQLException
     */
    private void createUniverseTable() throws SQLException {
        update = "CREATE TABLE universe (id INTEGER PRIMARY KEY, "
                + "name TEXT not NULL, xpos INTEGER, ypos INTEGER, "
                + "PoliticalSystem INTEGER, ResourcesLevel INTEGER,"
                + "TechLevel INTEGER, Pirate INTEGER," + "Police INTGER)";
        execUpdate(update);
    }

    /**
     * Create the cargo table
     * 
     * @throws SQLException
     */
    private void createCargoTable() throws SQLException {
        update = "CREATE TABLE cargo (id INTEGER PRIMARY KEY, "
                + "name TEXT not NULL)";
        execUpdate(update);
    }

    /**
     * Create the ship table
     * 
     * @throws SQLException
     */
    private void createShipTable() throws SQLException {
        update = "CREATE TABLE ship (id INTEGER PRIMARY KEY, "
                + "name TEXT not NULL, fuel INTEGER, hullStrength INTEGER)";
        execUpdate(update);

    }

    /**
     * Add player info to database
     * 
     * @throws SQLException
     */
    private void addPlayer() throws SQLException {
        SolarSystem start = universe.getUniverse().get(0);
        int xPos, yPos;
        if (player.getXpos() == 0) {
            xPos = start.getX();
            yPos = start.getY();
        } else {
            xPos = player.getXpos();
            yPos = player.getYpos();
        }
        update = String.format(
                "INSERT INTO player (name, pilotP, fighterP, traderP,"
                        + " engineerP, xpos, ypos, money) "
                        + "VALUES ('%s', %d, %d, %d, %d, %d, %d, %d)",
                player.getName(), player.getPilotP(), player.getFighterP(),
                player.getTraderP(), player.getEngineerP(), xPos, yPos,
                player.getMoney());
        // System.out.println(update);
        execUpdate(update);
    }

    /**
     * Adds ship data into database
     * 
     * @throws SQLException
     */
    private void addShip() throws SQLException {
        update = String.format("INSERT INTO ship (name, fuel, hullStrength)"
        		+ " VALUES('%s', '%d', '%d')", ship.getBase().getName(),
        		ship.getBase().getFuel(), ship.getBase().getHullStrength());
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
                + "PoliticalSystem, ResourcesLevel, TechLevel, Pirate, "
                + "Police) VALUES ('%s', %d, %d, %d, %d, %d, %d, %d)", system
                .getName(), system.getX(), system.getY(), planet
                .getPoliticalSystem().ordinal(), planet.getResourcesLevel()
                .ordinal(), planet.getTechLevel().ordinal(), planet.getPirate()
                .ordinal(), planet.getPolice().ordinal());
        execUpdate(update);
    }

    /**
     * Load cargo into database
     * 
     * @param ship
     *            playerShip
     * @throws SQLException
     */
    private void loadCargo() throws SQLException {
        List<Good> cargo = ship.getCargo();
        for (Good g : cargo) {
            update = String.format("INSERT INTO cargo (name) VALUES ('%s')",
                    g.getName());
            execUpdate(update);
        }
    }

}
