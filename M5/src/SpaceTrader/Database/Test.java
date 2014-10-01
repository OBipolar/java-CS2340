package SpaceTrader.Database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import SpaceTrader.Goods.Good;
import SpaceTrader.Goods.Trade;
import SpaceTrader.Goods.Water;
import SpaceTrader.Model.GameCharacter;
import SpaceTrader.Model.PlayerShip;
import SpaceTrader.Model.SolarSystem;
import SpaceTrader.Model.Universe;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        GameCharacter player = new GameCharacter("fasrf", 5, 5, 7, 8);
        PlayerShip playerShip = new PlayerShip();
        playerShip.loadShip();
        Universe universe = new Universe();
        SqliteAPI api = new SqliteAPI(player, universe);
        
        SolarSystem system =  universe.getUniverse().get(0);
        
        System.out.println("techlevel:  " + system.getPlanet().getTechLevel().ordinal());
        
        Trade trade = new Trade(player, playerShip, system);
        List<String> goodsToSell = trade.getGoodsToSell();
        List<String> goodsToBuy = trade.getGoodsToBuy();
        Map<String, Integer> map = trade.getPricesToSell();
        System.out.println(map.isEmpty());
        
/*        for (String s : goodsToSell) {
            System.out.println(s);
        }
        System.out.println("Money: " + player.getMoney());
        trade.sell(new Water());
        goodsToSell = trade.getGoodsToSell();
        for (String s : goodsToSell) {
            System.out.println(s);
        }
        System.out.println("Money: " + player.getMoney());*/
        
        System.out.println("goods on ship: ");
        for (Good g: playerShip.getCargo()) {
            System.out.println(g.getName());
        }
        System.out.println("goods on planet: ");
        for (String s : goodsToBuy) {
            System.out.println(s);
        }
        System.out.println("Money before buy : " + player.getMoney());
        trade.buy(new Water());
        System.out.println("Money before after : " + player.getMoney());
        System.out.println("goods on ship: ");
        for (Good g: playerShip.getCargo()) {
            System.out.println(g.getName());
        }
        
        
        try {
            api.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        api.closeConnection();

    }

}
