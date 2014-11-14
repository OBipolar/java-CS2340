package spacetrader.apis;

import spacetrader.goods.Good;
import spacetrader.goods.GoodFactory;
import spacetrader.planets.Capital;
import spacetrader.planets.GameCharacter;
import spacetrader.planets.SolarSystem;
import spacetrader.planets.Universe;
import spacetrader.ships.PlayerShip;
import spacetrader.ships.Ship;
import spacetrader.ships.ShipFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * API for communicating with Sqlite databse
 * 
 * @author Menghang Li
 *
 */
public class SqliteApi {

    private static final Logger LOGGER = 
            Logger.getLogger(MarketPlace.class.getName()); 
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
    private static void deleteTable(String... args) throws SQLException,
            ClassNotFoundException {
        for (String s : args) {
            if (tableExist(s)) {
                update = String.format("DROP TABLE '%s'", s);
                execUpdate(update);
            }
        }
  

    }

    /**
     * Method that takes in a Player and the Universe and sets up variables
     * 
     * @param player
     *            the player
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void start (GameCharacter player) throws ClassNotFoundException,
            SQLException {
        SqliteApi.player = player;
        SqliteApi.universe = new Universe();
        SqliteApi.ship = new PlayerShip();
        SolarSystem start = universe.getUniverse().get(0);
        SqliteApi.player.setXpos(start.getX());
        SqliteApi.player.setYpos(start.getY());
        initialize();
    }

    /**
     * Default initialization method
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void start() throws ClassNotFoundException, SQLException {
        loadData();
    }

    /**
     * Update all models
     */
    public static void update() {
        try {
            updatePlayer();
            updateShip();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        
    }
    
    /**
     * Update models based on given playerShip and player
     * 
     * @param ship a playerShip
     * @param player a GameCharacter
     */
    public static void update(PlayerShip ship, GameCharacter player) {
        try {
            updatePlayer(player);
            updateShip(ship);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        
    }

    /**
     * Update the ship player owns
     * 
     * @param ps
     *            the ship player owns
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void updateShip() throws SQLException, ClassNotFoundException {
        openConnection();
        //System.out.println("ship variable in db now has fuel: " 
        //+ ship.getBase().getFuel());
        update = "DROP TABLE ship";
        execUpdate(update);
        createShipTable();
        addShip();
        update = "DROP TABLE cargo";
        execUpdate(update);
        createCargoTable();
        loadCargo();
        closeConnection();
    }
    
    /**
     * Update the ship player owns
     * 
     * @param ps
     *            the ship player owns
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void updateShip(PlayerShip ship) throws SQLException, ClassNotFoundException {
        openConnection();
        //System.out.println("ship variable in db now has fuel: " 
        //+ ship.getBase().getFuel());
        update = "DROP TABLE ship";
        execUpdate(update);
        createShipTable();
        addShip(ship);
        update = "DROP TABLE cargo";
        execUpdate(update);
        createCargoTable();
        loadCargo(ship);
        closeConnection();
    }

    /**
     * Update player
     * 
     * @param gc
     *            GameCharacter object
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void updatePlayer() throws SQLException, ClassNotFoundException {
        openConnection();
        //System.out.println("Player in sqlite has money  " + player.getMoney() );
        update = "DROP TABLE player";
        execUpdate(update);
        createPlayerTable();
        addPlayer();
        closeConnection();
    }
    
    /**
     * Update player
     * 
     * @param gc
     *            GameCharacter object
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void updatePlayer(GameCharacter player) 
            throws SQLException, ClassNotFoundException {
        openConnection();
        ////System.out.println("Player in sqlite has money  " + player.getMoney() );
        update = "DROP TABLE player";
        execUpdate(update);
        createPlayerTable();
        addPlayer(player);
        closeConnection();
    }

    /**
     * Load data from database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void loadData() throws SQLException, ClassNotFoundException {
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
     * @throws ClassNotFoundException 
     */
    private static void loadPlayer() throws SQLException, ClassNotFoundException {
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
        SqliteApi.player = player;
    }

    /**
     * Loads player ship from database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void loadPlayerShip() throws SQLException, ClassNotFoundException {
        query = "SELECT name FROM cargo";
        execQuery(query);
        List<Good> goods = new ArrayList<>();
        GoodFactory gf = new GoodFactory();
//       / //System.out.println(resultSet.getString(1) == null);
        while (resultSet.next()) {
            goods.add(gf.getGood(resultSet.getString(1)));        
        }

        query = "SELECT * FROM ship";
        execQuery(query);
        ShipFactory sf = new ShipFactory();
        Ship base = sf.getShip(resultSet.getString("name"));
        int fuel = resultSet.getInt("fuel");
        int hullStrength = resultSet.getInt("hullStrength");
        base.setHullStrength(hullStrength);
        base.setFuel(fuel);
        SqliteApi.ship = new PlayerShip(base, goods);
        ship.setAttack(resultSet.getInt("attack"));
        ship.setCargoSpace(resultSet.getInt("cargoBay"));
        ship.setGadgetsSlots(resultSet.getInt("gadgetsSlots"));
        ship.setShield(resultSet.getInt("shield"));
        ship.setShieldSlots(resultSet.getInt("shieldSlots"));
        ship.setWeaponSlots(resultSet.getInt("weaponSlots"));
    }

    /**
     * Load universe from database;
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void loadUniverse() throws SQLException, ClassNotFoundException {
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
        universe = new Universe(systems);


    }

    /**
     * Return true if the database has been created
     * 
     * @return true if the database has been created
     */
    public static boolean isDbCreated() {
        File file = new File("SpaceTrader.db");
        if (file.exists()) {
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
    private static void openConnection() throws ClassNotFoundException, SQLException {

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
    private static void closeConnection() throws SQLException {
/*        if (resultSet != null) {
            resultSet.close();  
        }
        if (statement != null) {
            statement.close();
        }*/
        if (connection != null) {
            connection.close();
        }
        
    }

    /**
     * Returns the universe
     * 
     * @return the universe
     */
    public static Universe getUniverse() {
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
    public static SolarSystem getSolarSystem() throws SQLException,
            ClassNotFoundException {
        openConnection();
        int xpos = player.getXpos();
        int ypos = player.getYpos();
        String query = String.format(
                "SELECT name, PoliticalSystem, ResourcesLevel, "
                        + "TechLevel, Pirate, Police from universe "
                        + "where xPos = '%d' and yPos = '%d'", xpos, ypos);
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
            LOGGER.log(Level.SEVERE, "Ruleset empty");
        }
        closeConnection();
        return new SolarSystem(xpos, ypos, capital.getName(), capital);
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
    public static SolarSystem getSolarSystem(String name) throws SQLException,
            ClassNotFoundException {
        String query = String.format(
                "SELECT * from universe where name = '%s'", name);
        openConnection();
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
            LOGGER.log(Level.SEVERE, "Ruleset empty");
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
    public static GameCharacter getPlayer() {

        return player;
    }

    /**
     * Returns ship
     * 
     * @return ship;
     */
    public static PlayerShip getShip() {
        return ship;
    }

    /**
     * If the database is empty, fill in the data
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void initialize() throws SQLException, ClassNotFoundException {
        openConnection();
        if (isDbCreated()) {
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
        closeConnection();
    }

    /**
     * Execute the query given the query String
     * 
     * @param query
     *            the query String
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void execQuery(String query) throws SQLException, ClassNotFoundException {
        resultSet = statement.executeQuery(query);
    }

    /**
     * Update the database given the sql command
     * 
     * @param update
     *            sql command
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void execUpdate(String update) throws SQLException, ClassNotFoundException {
        openConnection();
        statement.executeUpdate(update);
        closeConnection();
    }

    /**
     * Return true if the table exists given
     * 
     * @param tableName
     *            the name of the table
     * @return true if the table exists given
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static boolean tableExist(String tableName) 
            throws SQLException, ClassNotFoundException {
        openConnection();
        DatabaseMetaData metadata = connection.getMetaData();
        resultSet = metadata.getTables(null, null, tableName, null);
        if (resultSet.next()) {
            closeConnection();
            return true;
        }
        closeConnection();
        return false;
    }

    /**
     * Create the player table
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void createPlayerTable() throws SQLException, ClassNotFoundException {
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
     * @throws ClassNotFoundException 
     */
    private static void createUniverseTable() throws SQLException, ClassNotFoundException {
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
     * @throws ClassNotFoundException 
     */
    private static void createCargoTable() throws SQLException, ClassNotFoundException {
        update = "CREATE TABLE cargo (id INTEGER PRIMARY KEY, "
                + "name TEXT not NULL)";
        execUpdate(update);
    }

    /**
     * Create the ship table
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void createShipTable() throws SQLException, ClassNotFoundException {
        update = "CREATE TABLE ship (id INTEGER PRIMARY KEY, "
                + "name TEXT not NULL, fuel INTEGER, hullStrength INTEGER, "
                + "weaponSlots INTEGER, shieldSlots INTEGER, "
                + "gadgetsSlots INTEGER, attack INTEGER, shield INTEGER, "
                + "cargoBay INTEGER)";

        execUpdate(update);
        ////System.out.println("\n" + update);
        ////System.out.println("ship tatbl");
    }

    /**
     * Add player info to database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void addPlayer() throws SQLException, ClassNotFoundException {
        SolarSystem start = universe.getUniverse().get(0);
        int xpos; 
        int ypos;
        if (player.getXpos() == 0) {
            xpos = start.getX();
            ypos = start.getY();
        } else {
            xpos = player.getXpos();
            ypos = player.getYpos();
        }
        update = String.format(
                "INSERT INTO player (name, pilotP, fighterP, traderP,"
                        + " engineerP, xpos, ypos, money) "
                        + "VALUES ('%s', %d, %d, %d, %d, %d, %d, %d)",
                player.getName(), player.getPilotP(), player.getFighterP(),
                player.getTraderP(), player.getEngineerP(), xpos, ypos,
                player.getMoney());
        // //System.out.println(update);
        execUpdate(update);
    }
    
    /**
     * Add player info to database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void addPlayer(GameCharacter player) 
            throws SQLException, ClassNotFoundException {
        SolarSystem start = universe.getUniverse().get(0);
        int xpos; 
        int ypos;
        if (player.getXpos() == 0) {
            xpos = start.getX();
            ypos = start.getY();
        } else {
            xpos = player.getXpos();
            ypos = player.getYpos();
        }
        update = String.format(
                "INSERT INTO player (name, pilotP, fighterP, traderP,"
                        + " engineerP, xpos, ypos, money) "
                        + "VALUES ('%s', %d, %d, %d, %d, %d, %d, %d)",
                player.getName(), player.getPilotP(), player.getFighterP(),
                player.getTraderP(), player.getEngineerP(), xpos, ypos,
                player.getMoney());
        // //System.out.println(update);
        execUpdate(update);
    }

    /**
     * Adds ship data into database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void addShip() throws SQLException, ClassNotFoundException {
        update = String.format("INSERT INTO ship (name, fuel, hullStrength, "
                + "weaponSlots, shieldSlots, gadgetsSlots, attack, shield, "
                + "cargoBay)"
                + " VALUES('%s', '%d', '%d', '%d', '%d', '%d', '%d', '%d', '%d')", 
                ship.getBase().getName(), ship.getBase().getFuel(), 
                ship.getBase().getHullStrength(), ship.getWeaponSlots(),
                ship.getShieldSlots(), ship.getGadgetsSlots(), 
                ship.getAttack(), ship.getShield(), ship.getCargoSpace());
        execUpdate(update);
    }
    
    /**
     * Adds ship data into database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void addShip(PlayerShip ship) throws SQLException, ClassNotFoundException {
        update = String.format("INSERT INTO ship (name, fuel, hullStrength, "
                + "weaponSlots, shieldSlots, gadgetsSlots, attack, shield, "
                + "cargoBay)"
                + " VALUES('%s', '%d', '%d', '%d', '%d', '%d', '%d', '%d', '%d')", 
                ship.getBase().getName(), ship.getBase().getFuel(), 
                ship.getBase().getHullStrength(), ship.getWeaponSlots(),
                ship.getShieldSlots(), ship.getGadgetsSlots(), 
                ship.getAttack(), ship.getShield(), ship.getCargoSpace());
        execUpdate(update);
    }

    /**
     * Add a SolarSystem to database
     * 
     * @param system
     *            A SolarSystem
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void addPlanet(SolarSystem system) throws SQLException, ClassNotFoundException {
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
     * @throws ClassNotFoundException 
     */
    private static void loadCargo() throws SQLException, ClassNotFoundException {
        List<Good> cargo = ship.getCargo();
        for (Good g : cargo) {
            update = String.format("INSERT INTO cargo (name) VALUES ('%s')",
                    g.getName());
            execUpdate(update);
        }
    }
    
    /**
     * Load cargo into database
     * 
     * @param ship
     *            playerShip
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private static void loadCargo(PlayerShip ship) throws SQLException, ClassNotFoundException {
        List<Good> cargo = ship.getCargo();
        for (Good g : cargo) {
            update = String.format("INSERT INTO cargo (name) VALUES ('%s')",
                    g.getName());
            execUpdate(update);
        }
    }
    
    public static void setPlayer(GameCharacter player) {
        SqliteApi.player = player;
    }
    
    public static void setShip(PlayerShip ship) {
        SqliteApi.ship = ship;
    }

}
