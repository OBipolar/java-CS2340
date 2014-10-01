package spaceTrader.Database;

import java.sql.SQLException;
import java.util.List;

import spaceTrader.Goods.Good;
import spaceTrader.Goods.Trade;
import spaceTrader.Goods.Water;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.PlayerShip;
import spaceTrader.Planets.SolarSystem;
import spaceTrader.Planets.Universe;

public class Test {

    public static void main(String[] args) {

        GameCharacter player = new GameCharacter("testPlayer", 5, 5, 3, 3);
        PlayerShip playerShip = new PlayerShip();
        playerShip.loadShip();
        Universe universe = new Universe();
        SqliteAPI api = new SqliteAPI(player, universe);

        SolarSystem system = universe.getUniverse().get(0);

        System.out.println("techlevel:  "
                + system.getPlanet().getTechLevel().ordinal());

        Trade trade = new Trade(player, playerShip, system);
        List<String> goodsToSell = trade.getGoodsToSell();
        List<String> goodsToBuy = trade.getGoodsToBuy();
        // Map<String, Integer> map = trade.getPricesToSell();
        // System.out.println(map.isEmpty());

        // Test begins here

        System.out.println("The cargo on ship before sale: ");
        for (String s : goodsToSell) {
            System.out.println(s);
        }
        System.out.println("Money before sell: " + player.getMoney());
        trade.sell(new Water());
        goodsToSell = trade.getGoodsToSell();
        System.out.println("The cargo on ship after sale: ");
        for (String s : goodsToSell) {
            System.out.println(s);
        }
        System.out.println("Money after sell: " + player.getMoney());

        System.out.println("goods on ship: ");
        for (Good g : playerShip.getCargo()) {
            System.out.println(g.getName());
        }
        System.out.println("goods on planet: ");
        for (String s : goodsToBuy) {
            System.out.println(s);
        }
        System.out.println("Money before buy : " + player.getMoney());
        trade.buy(new Water());
        System.out.println("Money after buy : " + player.getMoney());
        System.out.println("goods on ship: ");
        for (Good g : playerShip.getCargo()) {
            System.out.println(g.getName());
        }

        try {
            api.openConnection();
            api.closeConnection();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
