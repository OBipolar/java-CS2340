package spaceTrader.Tests;

import spaceTrader.APIs.MarketPlace;
import spaceTrader.Goods.Good;
import spaceTrader.Goods.Water;

public class MarketPlaceTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarketPlace mp = new MarketPlace();

        // Print out goods on board
        System.out.println("Goods before buy");
        for (Good g : mp.getCargo()) {
            System.out.println(g);
        }
        // Print out Money
        System.out.println("money before a buy " + mp.getPlayerMoney());

        mp.playerBuy(new Water());
        mp.playerBuy(new Water());

        // Print out goods on board
        System.out.println("Goods after buy");
        for (Good g : mp.getCargo()) {
            System.out.println(g);
        }
        // Print out Money
        System.out.println("money after a buy " + mp.getPlayerMoney());

        // Print out goods on board
        System.out.println("Goods before sell");
        for (Good g : mp.getCargo()) {
            System.out.println(g);
        }
        // Print out Money
        System.out.println("money before a sell " + mp.getPlayerMoney());

        mp.playerSell(new Water());
        mp.playerSell(new Water());

        // Print out goods on board
        System.out.println("Goods after sell");
        for (Good g : mp.getCargo()) {
            System.out.println(g);
        }
        // Print out Money
        System.out.println("money after a sell " + mp.getPlayerMoney());

    }

}
