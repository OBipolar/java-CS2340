package spaceTrader.View;

import java.sql.SQLException;

//import org.junit.Before;

import spaceTrader.APIs.SqliteAPI;
import spaceTrader.Planets.GameCharacter;

public class Sqlite {
	private SqliteAPI api;
	
    public void setUp() throws Exception {
        if (!SqliteAPI.isDBCreated()) {
            try {
                api = new SqliteAPI(player);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // else load models from database
            try {
                api = new SqliteAPI();
                // System.out.println(api.getSolarSystem());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
