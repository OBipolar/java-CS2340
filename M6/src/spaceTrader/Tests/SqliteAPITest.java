package spaceTrader.Tests;

import java.sql.SQLException;

import spaceTrader.APIs.SqliteAPI;
import spaceTrader.Planets.GameCharacter;

public class SqliteAPITest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // If database does not exist, provide player model
        if (!SqliteAPI.isDBCreated()) {
            GameCharacter player = new GameCharacter("Tester1", 6, 6, 2, 2);

            try {
                SqliteAPI db = new SqliteAPI(player);
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
                SqliteAPI db = new SqliteAPI();
                System.out.println(db.getSolarSystem());
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
