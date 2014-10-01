package SpaceTrader.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SpaceTrader.Model.Capital;
import SpaceTrader.Model.GameCharacter;
import SpaceTrader.Model.SolarSystem;
import SpaceTrader.Model.Universe;

public class SqliteAPI {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    private String update;
    private GameCharacter player;
    private Universe universe;

    public SqliteAPI(GameCharacter player, Universe universe) {
        this.player = player;
        this.universe = universe;
    }

    public void openConnection() throws ClassNotFoundException, SQLException {
        
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager
                .getConnection("jdbc:sqlite:SpaceTrader.db");
        statement = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.
        initialize();

    }
    
    
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void initialize() throws SQLException {
        if (!tableExist("player")) {
            createPlayerTable();             
            addPlayer();
            createUniverse();
            for (SolarSystem system : universe.getUniverse()) {
                addPlanet(system);
            }
        }
    }
    
    
    public void execQuery(String query) throws SQLException {
        statement.execute(query);
        
    }
    
    public void execUpdate(String update) throws SQLException {
        statement.executeUpdate(update);
    }
    
    private boolean tableExist(String tableName) throws SQLException {
        DatabaseMetaData metadata = connection.getMetaData();
        resultSet = metadata.getTables(null, null, tableName, null);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }
    
    private void createPlayerTable() throws SQLException {
        update = "CREATE TABLE player "
                + "(id INTEGER PRIMARY KEY, name TEXT not NULL, "
                + " pilotP INTEGER, fighterP INTEGER, "
                + " traderP INTEGER, " + " engineerP INTEGER)";
        execUpdate(update);
    }
    
    private void createUniverse() throws SQLException {
        update = "CREATE TABLE universe (id INTEGER PRIMARY KEY, "
                + "name TEXT not NULL, xPos INTEGER, yPos INTEGER, "
                + "PoliticalSystem INTEGER, ResourcesLevel INTEGER,"
                + "TechLevels INTEGER, Pirates INTEGER,"
                + "Polices INTGER)";
        execUpdate(update);
    }
    
    
    private void addPlayer() throws SQLException {
        update = String.format("INSERT INTO player (name, pilotP, fighterP, traderP, engineerP) "
                + "VALUES ('%s', %d, %d, %d, %d)",
                    player.getName(), player.getPilotP(),
                    player.getFighterP(), player.getTraderP(),
                    player.getEngineerP());
        //System.out.println(update);
        execUpdate(update);
    }
    
    private void addPlanet(SolarSystem system) throws SQLException {
        Capital planet = system.getPlanet();
        update = String.format("INSERT INTO universe (name, xPos, yPos, "
                + "PoliticalSystem, ResourcesLevel, TechLevels, Pirates, "
                + "Polices) VALUES ('%s', %d, %d, %d, %d, %d, %d, %d)",
                    system.getName(), system.getX(), system.getY(),
                    planet.getPoliticalSystem().ordinal(),
                    planet.getResourcesLevel().ordinal(),
                    planet.getTechLevel().ordinal(),
                    planet.getPirate().ordinal(),
                    planet.getPolice().ordinal());
        execUpdate(update);
    }

}
