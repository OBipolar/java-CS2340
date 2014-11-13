package spacetrader.goods;


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
    public Robots() {
        setName("Robots");
        setMtlp(6);
        setMtlu(4);
        setTtp(7);
        setBasePrice(5000);
        setIpl(-150);
        setVar(100);
        setPriceIncrease(PriceIncrease.LACKOFWORKERS.ordinal());
        setPriceLowCase(-1);
        setPriceHighCase(-1);
        setMinPriceWithTrader(3500);
        setMaxPriceWithTrader(5000);
        
    }

}
