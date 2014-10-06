package spaceTrader.Goods;


/**
 * Child of Good, Robots
 * 
 * @author Menghang Li
 *
 */
public class Robots extends Good {
    
    /**
     * Constructor thats sets all properties for Robots
     */
    public Robots () {
        setName("Robots");
        setMTLP(6);
        setMTLU(4);
        setTTP(7);
        setBasePrice(5000);
        setIPL(-150);
        setVar(100);
        setPriceIncrease(PriceIncrease.LACKOFWORKERS.ordinal());
        setPriceLowCase(-1);
        setPriceHighCase(-1);
        setMinPriceWithTrader(3500);
        setMaxPriceWithTrader(5000);
        
    }

}
